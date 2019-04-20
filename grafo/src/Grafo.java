
import java.util.ArrayList;



public class Grafo {

	public static void main(String[] args) {    
        
		//DIRECTORIO DEL FICHERO Y DELIMITADOR DE LECTURA
	    String csvEnlaces = "..\\grafo\\src\\Enlaces.csv";
	    String csvEventos = "..\\grafo\\src\\Eventos.csv";
	    String delimitador = ";";
	    
	    ArrayList<Csv> registrosLeidos = null;
	    Csv fila = new Csv();
	    
	    Nodo nodoOrigen = new Nodo();
	    Nodo nodoDestino = new Nodo();
	    
	    //UN EVENTO PUEDE SER UN NEGOCIO O UN BACHEO
	    Nodo evento = new Nodo();
	    
	    
	    Enlace enlace = new Enlace();
	    Enlace enlaceEntrante = new Enlace();
	    
	        
	    try {
	    	
	    	//LEO ARCHIVO DE ENLACES
	    	registrosLeidos = fila.leer(csvEnlaces,delimitador);
	    	for(int i=0 ; i < registrosLeidos.size(); i++){
	    	   
	    	   nodoOrigen = GestorNodo.crearNodo(registrosLeidos.get(i).getNodoOrigen());
	    	   nodoDestino = GestorNodo.crearNodo(registrosLeidos.get(i).getNodoDestino());
	    	   
	    	   enlace = GestorEnlace.crearEnlace(nodoOrigen, nodoDestino);
	    	   enlace.setDisponible(true);
	    	   enlace.setCosto(100);
	    
	  /*VERIFICO SI EL NODO YA SE ENCUENTRA REGISTRADO
	   * SI SE ENCUENTRA REGISTRADO LO QUE HAGO ES AGREGARLE EL NUEVO ENLACE A SU LISTA YA EXISTENTE DE 
	   * ENLACES.
	   * SI NO SE ENCUENTRA REGISTRADO LO REGISTRO.
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
	       
	       ArrayList<Nodo> nodosCargados = GestorNodo.getNodosExistentes();
	       
	       System.out.println("---- Nodos y sus enlaces ----");
	       for(int i=0; i< nodosCargados.size(); i++){
	    	   for(int j=0; j<nodosCargados.get(i).getEnlaces().size(); j++){
	    		   System.out.println(nodosCargados.get(i).getNombre() + " ---> " + nodosCargados.get(i).getEnlaces().get(j).getNombre());
	    	   }
	    	   System.out.println("FINALIZAN LOS ENLACES DEL NODO " + nodosCargados.get(i).getNombre());
	       }
	     
	       //LEO ARCHIVO DE EVENTOS
	    	registrosLeidos = fila.leer(csvEventos,delimitador);
	    	
	    	for(int i=0; i< registrosLeidos.size(); i++){
	    		
	    		evento = GestorNodo.crearNodo(registrosLeidos.get(i).getNombreEvento());
	    		nodoOrigen = GestorNodo.crearNodo(registrosLeidos.get(i).getEsquina1());
	    		nodoDestino = GestorNodo.crearNodo(registrosLeidos.get(i).getEsquina2());
	    		
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
	    			GestorEnlace.cambiarDisponibilidad(enlace, false);
	    			enlace = GestorEnlace.crearEnlace(evento, nodoOrigen);
	    			enlaceEntrante = GestorEnlace.crearEnlace(nodoDestino,evento);
	    			
	    		}
	    		else{
	    			GestorEnlace.cambiarDisponibilidad(enlace, false);
	    			enlace = GestorEnlace.crearEnlace(evento, nodoDestino);
	    			enlaceEntrante = GestorEnlace.crearEnlace(nodoOrigen,evento);
	    		}
	   //TENER EN CUENTA QUE DE AQUI EN MAS SE TRABAJA CON EL ENLACE CON ORIGEN EL EVENTO Y DESTINO UNA ESQUINA
	    		enlace.setDisponible(false);
	    		enlace.setCosto(10000); //PODRIA NO SETEARLE EL COSTO YA QUE NO SE UTILIZARA CUANDO SE ELIJA EL CAMINO
	    		
	    		//AGREGAR EL ENLACE A LA LISTA DE ENLACES DEL NODO
	    		evento.setEnlaces(enlace);
	    		
	    		//AGREGA EL NODO NUEVO A LOS YA EXISTENTES
	    		GestorNodo.agregarNodo(evento);
	    		//AGREGAR EL ENLACE A LA LISTA DE ENLACES
	    		GestorEnlace.agregarEnlace(enlace);
	    		
	    		//AGREGA EL ENLACE NODO-EVENTO AL NODO QUE CORRESPONDE
	    		GestorNodo.agregarEnlaceANodo(enlaceEntrante.getNodoOrigen(),enlaceEntrante);
	    		//FALTA TERMINAR
	    		
	    	}
	       
	    } catch (Exception e) {            
	        e.printStackTrace();
	    }
	}
}
