package frsf.cidisi.exercise.car.search;

import java.util.ArrayList;

import frsf.cidisi.faia.state.EnvironmentState;
import grafo.Csv;
import grafo.Enlace;
import grafo.Evento;
import grafo.GestorEnlace;
import grafo.GestorNodo;
import grafo.Negocio;
import grafo.Nodo;

/**
 * This class represents the real world state.
 */
public class CityState extends EnvironmentState {
	
	//TODO: Setup Variables
    private ArrayList<Nodo> mundo = new ArrayList<Nodo>();
    private Posicion posicionAgente;

	private CarAgentState agState = new CarAgentState();
    
	
    public CityState() {
        this.initState();
    }
    
    public Object clone() {
        return mundo.clone();
    }

    /**
     * This method is used to setup the initial real world.
     */
    @Override
    public void initState() {
    	
    	mundo = GestorNodo.getNodosExistentes();
    	//MUESTRO TODOS LOS DATOS CARGADOS
    	posicionAgente = agState.getPosicionActual();
    	
    	
    	System.out.println("\t\t\t\t------- Nodos y sus enlaces -------");
    	for(int i=0; i< mundo.size(); i++){
	    	   for(int j=0; j<mundo.get(i).getEnlaces().size(); j++){
	    		   System.out.println(mundo.get(i).getNombre() + " ---> " + mundo.get(i).getEnlaces().get(j).getNombre());
	    	   }
	    	   System.out.println("FINALIZAN LOS ENLACES DEL NODO " + mundo.get(i).getNombre());
	       }
    	
    	System.out.println("\t\t\t\t------- Negocios --------");
    	for(int i=0; i<mundo.size(); i++){
    		for(int k = 0; k<mundo.get(i).getEnlaces().size(); k++){
    			for(int j=0; j<mundo.get(i).getEnlaces().get(k).getNegocios().size(); j++){
    				System.out.println(mundo.get(i).getEnlaces().get(k).getNegocios().get(j).getNombre() +
    		    	"----> Calle: " + mundo.get(i).getEnlaces().get(k).getNombre());
    			}
    		}
    		
    	}
    	
    	System.out.println("\t\t\t\t------ Negocio y productos -------");
    	for(int i=0; i<mundo.size(); i++){
    		for(int j=0; j<mundo.get(i).getEnlaces().size(); j++){
    			for(int k=0; k<mundo.get(i).getEnlaces().get(j).getNegocios().size(); k++){
    				System.out.println("Negocio: " + mundo.get(i).getEnlaces().get(j).getNegocios().get(k).getNombre() + 
    					" Producto: " + mundo.get(i).getEnlaces().get(j).getNegocios().get(k).getProductoPrecio().toString());
    				
    			}
    		}
    	}
    	
    	System.out.println("\t\t\t\t------- Eventos --------");
    	
 /*   	for(int i=0; i<mundo.size(); i++){
    		for(int k = 0; k<mundo.get(i).getEnlaces().size(); k++){
    			for(int j=0; j<mundo.get(i).getEnlaces().get(k).getEventos().size(); j++){
    				System.out.println(mundo.get(i).getEnlaces().get(k).getEventos().get(j).getNombre() +
    		    	"----> Calle: " + mundo.get(i).getEnlaces().get(k).getNombre());
    			}
    		}
    		
    	}
  */  	
    	System.out.println("\t\t\t\t------- Estados de los enlaces ---------");
    	
    	for(int i=0; i<mundo.size(); i++){
    		for(int j=0; j<mundo.get(i).getEnlaces().size(); j++){
    			System.out.println(mundo.get(i).getEnlaces().get(j).getNombre() + "----> " +
    				String.valueOf(mundo.get(i).getEnlaces().get(j).isDisponible()));
    		}
    	}
    	
    	
    	for(int i=0; i<mundo.size(); i++){
    		for(int k = 0; k<mundo.get(i).getEnlaces().size(); k++){
    			mundo.get(i).getEnlaces().get(k).setEvento(CarAgentPerception.EMPTY_PERCEPTION);
    		}
    	}
    	
    	//traigo todos los eventos del excel
    	ArrayList<Evento> listaEventos = new ArrayList<Evento>();
    	try {
			listaEventos = leerEventos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Coloco cada percepción en el enlace que iría (hay que hacer que las coloque de a una o en cada iteración)
		
		for(int i=0; i<mundo.size(); i++){
			for(int k = 0; k<mundo.get(i).getEnlaces().size(); k++){
				for(int z=0; z<listaEventos.size(); z++){
					if(mundo.get(i).getEnlaces().get(k).getNodoOrigen().getNombre().equalsIgnoreCase(listaEventos.get(z).getEnlace().getNodoOrigen().getNombre())
							&& mundo.get(i).getEnlaces().get(k).getNodoDestino().getNombre().equalsIgnoreCase(listaEventos.get(z).getEnlace().getNodoDestino().getNombre()))
					{
						if(listaEventos.get(z).getNombre().equalsIgnoreCase("Corte calle")){
							mundo.get(i).getEnlaces().get(k).setEvento(CarAgentPerception.CORTE_CALLE);
						}
						else{
							mundo.get(i).getEnlaces().get(k).setEvento(CarAgentPerception.CONGESTION);
						}
					}
				}
			}
			
		}
    	
        //TODO: Complete Method
    }
    
    @Override
	public String toString() {
		return "CityState [agState=" + agState + ", mundo=" + mundo
				+ ", posicionAgente=" + posicionAgente
				+ ", getPosicionAgente()=" + getPosicionAgente() + "]";
	}

	public ArrayList<Evento> leerEventos() throws Exception{
    	ArrayList<Csv> registrosLeidos = null;
    	ArrayList<Evento> eventosLeidos = new ArrayList<Evento>();
    	Csv fila = new Csv();
    	String csvEventos = "..\\TP-IA\\src\\grafo\\Eventos.csv";
    	String delimitador = ";";
 	    
 	    Nodo nodoOrigen = new Nodo();
 	    Nodo nodoDestino = new Nodo();
 	    double costo;
 	    
 	    Evento evento = new Evento();
 	    
 	    Enlace enlace = new Enlace();
 	    
 	    String nombre;
    	
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
    		eventosLeidos.add(evento);		
    	}
    	return eventosLeidos;
    	
    }
    
    public Posicion getPosicionAgente() {
		return posicionAgente;
	}

	public void setPosicionAgente(Posicion posicionAgente) {
		this.posicionAgente = posicionAgente;
	}

	//TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
	
	

}

