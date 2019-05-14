package grafo;


import java.util.ArrayList;
import java.util.Iterator;

import frsf.cidisi.exercise.car.search.CarAgentPerception;


public class Grafo {
	
	GestorEnlace gestorEnlace = new GestorEnlace();
   	
	//METODO PARA INICIAR EL MUNDO DEL AMBIENTE Y DEL AGENTE CON LAS CALLES Y NEGOCIOS ASOCIADOS
	public ArrayList<Nodo> iniciarMundo(){
		
		GestorNodo gestor = new GestorNodo();
		Enlace enlace = new Enlace();
		ArrayList<Negocio> listaNegocios = new ArrayList<Negocio>();
				
		gestor.setNodosExistentes(leerEnlaces());
		
    	listaNegocios = leerNegocios();
    	
    	for(int i=0; i<listaNegocios.size(); i++){
    		for(int j=0; j<gestor.getNodosExistentes().size(); j++){
    			for(int k=0; k<gestor.getNodosExistentes().get(j).getEnlaces().size(); k++){
    				enlace = gestorEnlace.crearEnlace(listaNegocios.get(i).getEsquina1(), listaNegocios.get(i).getEsquina2());
    				if(enlace.getNombre().equalsIgnoreCase(gestor.getNodosExistentes().get(j).getEnlaces().get(k).getNombre())
    						&& !GestorNegocio.existeNegocio(listaNegocios.get(i))){
    					gestor.getNodosExistentes().get(j).getEnlaces().get(k).setNegocios(listaNegocios.get(i));
    				}
    			}
    			
    		}
    	}
		return gestor.getNodosExistentes();
	}
	
	private ArrayList<Nodo> leerEnlaces(){
		
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
	    	   
	    	   enlace = gestorEnlace.crearEnlace(nodoOrigen,nodoDestino);
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
	    	   
	    	   gestorEnlace.agregarEnlace(enlace); 	   
	    	   
	       }
    } catch (Exception e) {            
        e.printStackTrace();
    }
		
		
		return gestorNodo.getNodosExistentes();
	}
	
	private ArrayList<Negocio> leerNegocios(){
		//DIRECTORIO DEL FICHERO Y DELIMITADOR DE LECTURA
	    String csvNegocios = "..\\TP-IA\\src\\grafo\\Negocios.csv";
	    String delimitador = ";";
		ArrayList<Csv> registrosLeidos = null;
	    Csv fila = new Csv();
	    
	    Nodo nodoOrigen = new Nodo();
	    Nodo nodoDestino = new Nodo();
	    
	    Enlace enlace = new Enlace();

	    ArrayList<Negocio> listaNegocios = new ArrayList<Negocio>();
	   	    
	    Negocio negocio = new Negocio();
	    String nombre;
	    
	    boolean existeNegocio;
		
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
	    		enlace = gestorEnlace.crearEnlace(nodoOrigen, nodoDestino);
	    		
	    		if(!gestorEnlace.existeEnlace(enlace)){
	    			/*CREO UN ENLACE CON EL SENTIDO INVERSO Y SE LO MANDO AL GESTOR PARA QUE
	    			 * A ESE ENLACE LE CAMBIE LA DISPONIBILIDAD.
	    			 * LUEGO CREO EL ENCALE QUE LE CORRESPONDA AL NODO EVENTO
	    			 * */
	    			enlace = gestorEnlace.crearEnlace(nodoDestino, nodoOrigen);
	    		}
	    		
	    		negocio = GestorNegocio.crearNegocio(nombre, enlace.getNodoOrigen(), enlace.getNodoDestino(),abierto);
	    		
	    	//VERIFICO QUE NO EXISTA EL NEGOCIO EN ESA CALLE
	    		
	    		existeNegocio = false;
	    		
	    		for(int j=0; j<listaNegocios.size(); j++){
	    			if(listaNegocios.get(j).getNombre().equalsIgnoreCase(negocio.getNombre()) &&
	    				listaNegocios.get(j).getEsquina1().getNombre().equalsIgnoreCase(enlace.getNodoOrigen().getNombre())
	    				&& listaNegocios.get(j).getEsquina2().getNombre().equalsIgnoreCase(enlace.getNodoDestino().getNombre())){
	    				negocio = listaNegocios.get(j);
	    				existeNegocio = true;
	    				break;
	    			}
	    		}
	
	    		if(!existeNegocio){
	       			negocio.agregarProductoPrecio(producto,precio);
	    			GestorNegocio.agregarNegocio(negocio);
	    			gestorEnlace.agregarNegocio(enlace, negocio);
	    			listaNegocios.add(negocio);	
	    		}
	    		else{
		    			GestorNegocio.agregarProducto(negocio,producto,precio);
		    			gestorEnlace.agregarProducto(negocio,producto,precio);
		    			negocio.getProductoPrecio().put(producto,precio);
	    		}

	    	}
	    } catch (Exception e) {            
	        e.printStackTrace();
	    }
	    
	    return listaNegocios;
		
	}
	
	public ArrayList<Evento> leerEventos(){
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
		    enlace = gestorEnlace.crearEnlace(nodoOrigen, nodoDestino);
		    		
		    	if(!gestorEnlace.existeEnlace(enlace)){
		    	/*CREO UN ENLACE CON EL SENTIDO INVERSO Y SE LO MANDO AL GESTOR PARA QUE
		    	 * A ESE ENLACE LE CAMBIE LA DISPONIBILIDAD.
		    	* LUEGO CREO EL ENCALE QUE LE CORRESPONDA AL NODO EVENTO
		    	* */
		    	enlace = gestorEnlace.crearEnlace(nodoDestino, nodoOrigen);
		    	
		    	}
		    		
		    evento = new Evento(nombre,enlace,costo);
		    listaEventos.add(evento);
		    
		    gestorEnlace.agregarEvento(enlace,nombre,costo);

	    	}
		    
		}catch (Exception e) {            
		    e.printStackTrace();
		}
		
		return listaEventos;

	}
	
	public static void percepcionesAleatorias(ArrayList<Nodo> mundo){
	
		boolean salir = false;
		boolean elegirOtraAccion = true;
		
		int indiceNodoElegido;
		int indiceEnlaceElegido;
		int indiceAccionElegida;
		int indiceNegocioElegido;
		double costoElegido;
		
		listaAcciones[] acciones = listaAcciones.values();
		
		Negocio negocioAleatorio = new Negocio();
		System.out.println("PERCEPCIONES ALEATORIAS");
		while(!salir && elegirOtraAccion){
			//SELECCIONO UN INDICE PARA ELEGIR UN NODO ALEATORIO
			indiceNodoElegido = (int) Math.floor(Math.random()*mundo.size());
			System.out.println("NODO ELEGIDO: " + mundo.get(indiceNodoElegido).getNombre());
			
			//SELECCIONO UN INDICE PARA ELEGIR UN ENLACE ALEATORIO EN BASE AL NODO ALEATORIO ELEGIDO
			indiceEnlaceElegido = (int) Math.floor(Math.random()* mundo.get(indiceNodoElegido).getEnlaces().size());
			System.out.println("ENLACE ELEGIDO: " + mundo.get(indiceNodoElegido).getEnlaces().get(indiceEnlaceElegido).getNombre());
			
			/*SELECCIONO UNA ACCION ALEATORIA EN BASE A LA LISTA DE OPCIONES:
			 * -- AGREGAR/ELIMINAR UN EVENTO SOCIAL
			 * -- AGREGAR/ELIMINAR UNA CONGESTION
			 * -- ABRIR/CERRAR UN NEGOCIO
			 * -- NO CAMBIAR EL MUNDO DEL AMBIENTE
			 */
			indiceAccionElegida = (int) Math.floor(Math.random()* acciones.length);
			System.out.println("ACCION ELEGIDA: " + acciones[indiceAccionElegida]);
			
			//SELECCIONO UN COSTO ALEATORIO PARA EL EVENTO. ENTRE 0 Y 100 (PORCENTAJE)
			costoElegido = Math.floor(Math.random()*100);
			System.out.println("COSTO ELEGIDO: " + costoElegido);
			
			//MODIFICO EL MUNDO DEL AMBIENTE SEGUN LA ACCION SELECCIONADA

			switch(acciones[indiceAccionElegida]){
				case AGREGAR_EVENTO_SOCIAL:
					if(!mundo.get(indiceNodoElegido).getEnlaces().get(indiceEnlaceElegido).getEventos().containsKey(CarAgentPerception.EVENTO_SOCIAL)){
						mundo.get(indiceNodoElegido).getEnlaces().get(indiceEnlaceElegido).getEventos().put(CarAgentPerception.EVENTO_SOCIAL,costoElegido);
						salir = true;
						elegirOtraAccion = false;
					}else{
						elegirOtraAccion = true;
					}
					break;
				case AGREGAR_CORTECALLE:
					if(!mundo.get(indiceNodoElegido).getEnlaces().get(indiceEnlaceElegido).getEventos().containsKey(CarAgentPerception.CORTE_CALLE)){
						mundo.get(indiceNodoElegido).getEnlaces().get(indiceEnlaceElegido).setDisponible(false);
						salir = true;
						elegirOtraAccion = false;
					}else{
						elegirOtraAccion = true;
					}
					break;
				case ELIMINAR_CORTECALLE:
					if(mundo.get(indiceNodoElegido).getEnlaces().get(indiceEnlaceElegido).getEventos().containsKey(CarAgentPerception.CORTE_CALLE)){
						mundo.get(indiceNodoElegido).getEnlaces().get(indiceEnlaceElegido).setDisponible(true);
						salir = true;
						elegirOtraAccion = false;
					}else{
						elegirOtraAccion = true;
					}
					break;
				case ELIMINAR_EVENTO_SOCIAL:
					if(mundo.get(indiceNodoElegido).getEnlaces().get(indiceEnlaceElegido).getEventos().containsKey(CarAgentPerception.EVENTO_SOCIAL)){
						mundo.get(indiceNodoElegido).getEnlaces().get(indiceEnlaceElegido).getEventos().remove(CarAgentPerception.EVENTO_SOCIAL);
						salir = true;
						elegirOtraAccion = false;
					}else{
						elegirOtraAccion = true;
					}
					break;
				case AGREGAR_CONGESTION:
					if(!mundo.get(indiceNodoElegido).getEnlaces().get(indiceEnlaceElegido).getEventos().containsKey(CarAgentPerception.CONGESTION)){
						mundo.get(indiceNodoElegido).getEnlaces().get(indiceEnlaceElegido).getEventos().put(CarAgentPerception.CONGESTION,costoElegido);
						salir = true;
						elegirOtraAccion = false;
					}else{
						elegirOtraAccion = true;
					}
					break;
				case ELIMINAR_CONGESTION:
					if(mundo.get(indiceNodoElegido).getEnlaces().get(indiceEnlaceElegido).getEventos().containsKey(CarAgentPerception.CONGESTION)){
						mundo.get(indiceNodoElegido).getEnlaces().get(indiceEnlaceElegido).getEventos().remove(CarAgentPerception.CONGESTION);
						salir = true;
						elegirOtraAccion = false;
					}else{
						elegirOtraAccion = true;
					}
					break;
				case ABRIR_NEGOCIO:
					if(mundo.get(indiceNodoElegido).getEnlaces().get(indiceEnlaceElegido).getNegocios().size() != 0){
						indiceNegocioElegido = (int) Math.floor(Math.random()*mundo.get(indiceNodoElegido).getEnlaces().get(indiceEnlaceElegido).getNegocios().size());
						negocioAleatorio = mundo.get(indiceNodoElegido).getEnlaces().get(indiceEnlaceElegido).getNegocios().get(indiceNegocioElegido);
						negocioAleatorio.setAbierto(true);
					}else{
						elegirOtraAccion = true;
					}
					break;
				case CERRAR_NEGOCIO:
					if(mundo.get(indiceNodoElegido).getEnlaces().get(indiceEnlaceElegido).getNegocios().size() != 0){
						indiceNegocioElegido = (int) Math.floor(Math.random()*mundo.get(indiceNodoElegido).getEnlaces().get(indiceEnlaceElegido).getNegocios().size());
						negocioAleatorio = mundo.get(indiceNodoElegido).getEnlaces().get(indiceEnlaceElegido).getNegocios().get(indiceNegocioElegido);
						negocioAleatorio.setAbierto(false);
						salir = true;
					}else{
						elegirOtraAccion = true;
					}
					break;
				default:
					//CASO EN QUE LA ACCION SELECCIONADA SEA NO MODIFICAR EL MUNDO
					salir = true;
					elegirOtraAccion = false;
			}
		}	
	}
	
	private enum listaAcciones{
		AGREGAR_EVENTO_SOCIAL,
		AGREGAR_CONGESTION,
		AGREGAR_CORTECALLE,
		ELIMINAR_CORTECALLE,
		ELIMINAR_EVENTO_SOCIAL,
		ELIMINAR_CONGESTION,
		ABRIR_NEGOCIO,
		CERRAR_NEGOCIO,
		NADA
	}
}
