package grafo;


import java.util.ArrayList;



public class Grafo {
   	
	public static void iniciarMundo(){    
		//DIRECTORIO DEL FICHERO Y DELIMITADOR DE LECTURA
	    String csvEnlaces = "..\\TP-IA\\src\\grafo\\Enlaces.csv";
	    String csvEventos = "..\\TP-IA\\src\\grafo\\Eventos.csv";
	    String csvNegocios = "..\\TP-IA\\src\\grafo\\Negocios.csv";
	    String delimitador = ";";
	    
	    ArrayList<Csv> registrosLeidos = null;
	    Csv fila = new Csv();
	    
	    Nodo nodoOrigen = new Nodo();
	    Nodo nodoDestino = new Nodo();
	    double costo;
	    
	    Negocio negocio = new Negocio();
	    Evento evento = new Evento();
	    
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
	    	   if(!GestorNodo.existeNodo(nodoOrigen)){
	     		   nodoOrigen.setEnlaces(enlace);
	    		   GestorNodo.agregarNodo(nodoOrigen);   
	    	   }
	    	   else{
	    		   GestorNodo.agregarEnlaceANodo(nodoOrigen, enlace);
	    	   }
	    	   
	    	   GestorEnlace.agregarEnlace(enlace); 	   
	    	   
	       }
	       	     
	       //LEO ARCHIVO DE EVENTOS
	    	registrosLeidos = fila.leerEventos(csvEventos,delimitador);
	    	
	    	for(int i=0; i< registrosLeidos.size(); i++){
	    		
	    		evento.setNombre(registrosLeidos.get(i).getNombreEvento());
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
	    		
	    		evento.setEsquina1(enlace.getNodoOrigen());
	    		evento.setEsquina2(enlace.getNodoDestino());
	    		evento.setCosto(costo);
	    		
	    		//VERIFICO QUE NO EXISTA UN EVENTO YA EN EL ENLACE (PROBLEMA EN LAS CALLES DE DOBLE SENTIDO)
	    		if(!GestorEnlace.existeEvento(enlace,evento.getNombre())){
	    			//VERIFICO QUE NO SE TRATE DE UN EVENTO DE CORTE DE CALLE
	    			if(evento.getNombre().equalsIgnoreCase("Corte Calle")){
		    			GestorEnlace.cambiarDisponibilidad(enlace,false);
		    		}
	    			GestorEnlace.agregarEvento(enlace,evento);
	    			
	    			GestorNodo.agregarEvento(enlace,evento);
	    		}
	    		else{
	    			System.out.println("Ya existe el evento: " + evento.getNombre() + " en la calle " +
	    					enlace.getNombre());
	    		}
	    	}
	    		
    		//LEO ARCHIVO DE NEGOCIOS
    			registrosLeidos = fila.leerNegocios(csvNegocios,delimitador);
    	    	String producto;
    	    	Double precio;
    	    	
    	    	for(int i=0; i< registrosLeidos.size(); i++){
    	    		
    	    		negocio.setNombre(registrosLeidos.get(i).getNombreNegocio());
    	    		nodoOrigen = GestorNodo.crearNodo(registrosLeidos.get(i).getEsquina1());
    	    		nodoDestino = GestorNodo.crearNodo(registrosLeidos.get(i).getEsquina2());
    	    		producto = registrosLeidos.get(i).getProducto();
    	    		precio = registrosLeidos.get(i).getPrecioProducto();
    	    		
    	    		System.out.println("Producto: " + producto + " con precio: " + precio.toString());
    	    		
    	   
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
    	    		
    	    		negocio.setEsquina1(enlace.getNodoOrigen());
    	    		negocio.setEsquina2(enlace.getNodoDestino());
    	    		
    	    	//VERIFICO QUE NO EXISTA EL NEGOCIO EN ESA CALLE (PROBLEMA CON LAS CALLES DOBLE SENTIDO)
    	    		if(!GestorNegocio.existeNegocio(negocio)){
    	    			System.out.println("P: " + producto + " PR: " + precio);
    	    			negocio.agregarProductoPrecio(producto,precio);
    	    			
    	    			negocio.setAbierto(registrosLeidos.get(i).getNegocioAbierto());
    	    			
    	    			GestorNegocio.agregarNegocio(negocio);
    	    			GestorEnlace.agregarNegocio(enlace, negocio);
    	    			GestorNodo.agregarNegocio(enlace, negocio);
    	    		}
    	    		else{
    	    			System.out.println("Esquina1: "+ negocio.getEsquina1().getNombre());
    	    			System.out.println("Esquina2: "+ negocio.getEsquina2().getNombre());
    	    			GestorNegocio.agregarProducto(negocio,producto,precio);
    	    		}
    	    		
    	    	}
	       
	    } catch (Exception e) {            
	        e.printStackTrace();
	    }
	}
}
