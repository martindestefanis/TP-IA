package grafo;


import java.util.ArrayList;

import frsf.cidisi.exercise.car.search.CarAgentPerception;


public class Grafo {
   	
	//METODO PARA INICIAR EL MUNDO DEL AMBIENTE Y DEL AGENTE CON LAS CALLES Y NEGOCIOS ASOCIADOS
	public static ArrayList<Nodo> iniciarMundo(){
		
		GestorNodo gestor = new GestorNodo();
		
		gestor.setNodosExistentes(leerEnlaces());
		
		System.out.println("NODOS MUNDO");
		System.out.println("\t\t\t\t------- Nodos y sus enlaces -------");
    	for(int i=0; i< gestor.getNodosExistentes().size(); i++){
	    	   for(int j=0; j<gestor.getNodosExistentes().get(i).getEnlaces().size(); j++){
	    		   System.out.println(gestor.getNodosExistentes().get(i).getNombre() + " ---> " + gestor.getNodosExistentes().get(i).getEnlaces().get(j).getNombre());
	    	   }
	    	   System.out.println("FINALIZAN LOS ENLACES DEL NODO " + gestor.getNodosExistentes().get(i).getNombre());
	       }
		//gestor.getNodosExistentes().get(1).getEnlaces().get(2).setNegocios(negocio);
		
    	leerNegocios(gestor);
    	
		System.out.println("NEGOCIOS MUNDO");
		for(int i=0; i<gestor.getNodosExistentes().size(); i++){
			for(int j=0; j<gestor.getNodosExistentes().get(i).getEnlaces().size(); j++){
				//System.out.println("Tamaño: " + gestor.getNodosExistentes().get(i).getEnlaces().size());
				for(int k=0; k<gestor.getNodosExistentes().get(i).getEnlaces().get(j).getNegocios().size(); k++){
					if(gestor.getNodosExistentes().get(i).getEnlaces().get(j).getNegocios().size()>0){
						System.out.println(gestor.getNodosExistentes().get(i).getEnlaces().get(j).getNegocios().get(k).getNombre());
					}
					
				}
			}
			
		}
		
		return gestor.getNodosExistentes();
	}
	
	private static ArrayList<Nodo> leerEnlaces(){
		
		GestorNodo gestorNodo = new GestorNodo();
	
		//DIRECTORIO DEL FICHERO Y DELIMITADOR DE LECTURA
	    String csvEnlaces = "..\\TP-IA\\src\\grafo\\Enlaces.csv";
	    String delimitador = ";";
		ArrayList<Csv> registrosLeidos = null;
	    Csv fila = new Csv();
	    
	    Nodo nodoOrigen = new Nodo();
	    Nodo nodoDestino = new Nodo();
	    double costo;
	    
	    Enlace enlace = new Enlace();
		
    try {
	    	
	    	//LEO ARCHIVO DE ENLACES
	    	registrosLeidos = fila.leerEnlaces(csvEnlaces,delimitador);
	    	for(int i=0 ; i < registrosLeidos.size(); i++){
	    	   
	    	   nodoOrigen = GestorNodo.crearNodo(registrosLeidos.get(i).getNodoOrigen());
	    	   nodoDestino = GestorNodo.crearNodo(registrosLeidos.get(i).getNodoDestino());
	    	   costo = registrosLeidos.get(i).getCosto();
	    	   
	    	   enlace = GestorEnlace.crearEnlace(nodoOrigen,nodoDestino);
	    	   enlace.setDisponible(true);
	    	   enlace.setCosto(costo);
	    
	  /*VERIFICO SI EL NODO YA SE ENCUENTRA REGISTRADO
	   * SI SE ENCUENTRA REGISTRADO LO QUE HAGO ES AGREGARLE EL NUEVO ENLACE A SU LISTA YA EXISTENTE DE 
	   * ENLACES.
	   * SI NO SE ENCUENTRA REGISTRADO, LO REGISTRO.
	   * 
	   * ESTO SE HACE YA QUE PUEDE HABER MAS DE UN ENLACE POR NODO Y SI NO SE VERIFICA LA EXISTENCIA DEL
	   * NODO SE CREARAN COMO SI FUERAN DOS NODOS DISTINTOS Y NO LO SON
	   * 
	   * */
	    	   if(!gestorNodo.existeNodo(nodoOrigen)){
	     		   nodoOrigen.setEnlaces(enlace);
	    		   gestorNodo.agregarNodo(nodoOrigen);   
	    	   }
	    	   else{
	    		   gestorNodo.agregarEnlaceANodo(nodoOrigen, enlace);
	    	   }
	    	   
	    	   GestorEnlace.agregarEnlace(enlace); 	   
	    	   
	       }
    } catch (Exception e) {            
        e.printStackTrace();
    }
		
		
		return gestorNodo.getNodosExistentes();
	}
	
	private static void leerNegocios(GestorNodo gestor){
		//DIRECTORIO DEL FICHERO Y DELIMITADOR DE LECTURA
	    String csvNegocios = "..\\TP-IA\\src\\grafo\\Negocios.csv";
	    String delimitador = ";";
		ArrayList<Csv> registrosLeidos = null;
	    Csv fila = new Csv();
	    
	    Nodo nodoOrigen = new Nodo();
	    Nodo nodoDestino = new Nodo();
	    
	    Enlace enlace = new Enlace();

	    Negocio negocio = new Negocio();
	    String nombre;
		
	    try{
			//LEO ARCHIVO DE NEGOCIOS
			registrosLeidos = fila.leerNegocios(csvNegocios,delimitador);
	    	String producto;
	    	Double precio;
	       	Boolean abierto;
	       	
	    	 	    	
	    	for(int i=0; i< registrosLeidos.size(); i++){
	    		nombre = registrosLeidos.get(i).getNombreNegocio();
	    		nodoOrigen = GestorNodo.crearNodo(registrosLeidos.get(i).getEsquina1());
	    		nodoDestino = GestorNodo.crearNodo(registrosLeidos.get(i).getEsquina2());
	    		producto = registrosLeidos.get(i).getProducto();
	    		precio = registrosLeidos.get(i).getPrecioProducto();
	    		abierto = registrosLeidos.get(i).getNegocioAbierto();   	    		
	   
		    		/*PRIMERO CREO UN ENLACE SUPONIENDO QUE EL SENTIDO DE LAS ESQUINAS ES ORIGEN-DESTINO
	    		 * DESPUES VERIFICO SI EXISTE UN ENLACE CON ESE SENTIDO
	    		 * SI NO EXISTE ES PORQUE LA ORIENTACION ES DESTINO-ORIGEN
	    		 * */
	    		enlace = GestorEnlace.crearEnlace(nodoOrigen, nodoDestino);
	    		
	    		if(!GestorEnlace.existeEnlace(enlace)){
	    			/*CREO UN ENLACE CON EL SENTIDO INVERSO Y SE LO MANDO AL GESTOR PARA QUE
	    			 * A ESE ENLACE LE CAMBIE LA DISPONIBILIDAD.
	    			 * LUEGO CREO EL ENCALE QUE LE CORRESPONDA AL NODO EVENTO
	    			 * */
	    			enlace = GestorEnlace.crearEnlace(nodoDestino, nodoOrigen);
	    		}
	    		
	    		negocio = GestorNegocio.crearNegocio(nombre, enlace.getNodoOrigen(), enlace.getNodoDestino(),abierto);
	    		
	    		//System.out.println("Negocio: " + negocio.getNombre());
	    	//VERIFICO QUE NO EXISTA EL NEGOCIO EN ESA CALLE (PROBLEMA CON LAS CALLES DOBLE SENTIDO)
	    		if(!GestorNegocio.existeNegocio(negocio)){
	       			negocio.agregarProductoPrecio(producto,precio);
	    			System.out.println("ENTRO");
	    			GestorNegocio.agregarNegocio(negocio);
	    			GestorEnlace.agregarNegocio(enlace, negocio);
	    			gestor.agregarNegocio(enlace, negocio);
	    			for(int a=0; i<gestor.getNodosExistentes().size(); i++){
	    				for(int t=0; t<gestor.getNodosExistentes().get(a).getEnlaces().size(); t++){
	    					System.out.println("Tamaño: " + gestor.getNodosExistentes().get(a).getEnlaces().get(t).getNegocios().size());
	    				}
	    			}
	    			
	    		}
	    		else{
	    				System.out.println("ENTRO2");
		    			GestorNegocio.agregarProducto(negocio,producto,precio);
		    			GestorEnlace.agregarProducto(negocio,producto,precio);
		    			gestor.agregarProducto(enlace,negocio,producto,precio);
	    		}
	    	}
	    } catch (Exception e) {            
	        e.printStackTrace();
	    }
		
	}
	
	public static ArrayList<Evento> leerEventos(){
		//DIRECTORIO DEL FICHERO Y DELIMITADOR DE LECTURA
		String csvEventos = "..\\TP-IA\\src\\grafo\\Eventos.csv";
		String delimitador = ";";
		  
		ArrayList<Csv> registrosLeidos = null;
		Csv fila = new Csv();
		
		Nodo nodoOrigen = new Nodo();
	    Nodo nodoDestino = new Nodo();
		
		Evento evento;
		ArrayList<Evento> listaEventos = new ArrayList<Evento>();
			    
		Enlace enlace;
		    
		String nombre;
		double costo;
			    
			    
		try{
			//LEO ARCHIVO DE EVENTOS
		    registrosLeidos = fila.leerEventos(csvEventos,delimitador);
		    	
		    for(int i=0; i< registrosLeidos.size(); i++){
		    	nombre = registrosLeidos.get(i).getNombreEvento();
		    	nodoOrigen = GestorNodo.crearNodo(registrosLeidos.get(i).getEsquina1());
		    	nodoDestino = GestorNodo.crearNodo(registrosLeidos.get(i).getEsquina2());
		    	costo = registrosLeidos.get(i).getCosto();
		    		
		    /*PRIMERO CREO UN ENLACE SUPONIENDO QUE EL SENTIDO DE LAS ESQUINAS ES ORIGEN-DESTINO
		    * DESPUES VERIFICO SI EXISTE UN ENLACE CON ESE SENTIDO
		    * SI NO EXISTE ES PORQUE LA ORIENTACION ES DESTINO-ORIGEN
		    * 
		    * */
		    enlace = GestorEnlace.crearEnlace(nodoOrigen, nodoDestino);
		    		
		    	if(!GestorEnlace.existeEnlace(enlace)){
		    	/*CREO UN ENLACE CON EL SENTIDO INVERSO Y SE LO MANDO AL GESTOR PARA QUE
		    	 * A ESE ENLACE LE CAMBIE LA DISPONIBILIDAD.
		    	* LUEGO CREO EL ENCALE QUE LE CORRESPONDA AL NODO EVENTO
		    	* */
		    	enlace = GestorEnlace.crearEnlace(nodoDestino, nodoOrigen);
		    	
		    	}
		    		
		    evento = Evento.crearEvento(nombre,enlace,costo);
		    listaEventos.add(evento);
		    
    		GestorEnlace.agregarEvento(enlace,nombre,costo);

	    	}
		    
		}catch (Exception e) {            
		    e.printStackTrace();
		}
		
		return listaEventos;

	}
	
	public static void percepcionesAleatorias(ArrayList<Nodo> mundo){
		boolean salir = false;
		int indiceNodoElegido, indiceEnlaceElegido, indiceAccionElegida;
		String eventoElegido;
		listaAcciones[] acciones = listaAcciones.values();
		
		indiceNodoElegido = (int) Math.random() * mundo.size();
		System.out.println("NODO ELEGIDO: " + mundo.get(indiceNodoElegido).getNombre());
		indiceEnlaceElegido = (int) Math.random() * mundo.get(indiceNodoElegido).getEnlaces().size();
		System.out.println("ENLACE ELEGIDO: " + mundo.get(indiceNodoElegido).getEnlaces().get(indiceEnlaceElegido).getNombre());
		indiceAccionElegida = (int) Math.random() * acciones.length;
		System.out.println("ACCION ELEGIDA: " + acciones[indiceAccionElegida]);
		/*
		
		switch(acciones[indiceAccionElegida]){
		case "EVENTO_SOCIAL":
			
		}
		
		
		if(!mundo.get(indiceNodoElegido).getEnlaces().get(indiceEnlaceElegido).getEventos().containsKey(acciones[indiceAccionElegida])){
			salir = true;
		}*/
		
		
		
	}
	
	private enum listaAcciones{
		EVENTO_SOCIAL,
		CONGESTION,
		ABRIR_NEGOCIO,
		CERRAR_NEGOCIO,
		ELIMINAR,
		NADA
	}
}
