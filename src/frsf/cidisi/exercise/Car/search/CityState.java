package frsf.cidisi.exercise.car.search;

import java.util.ArrayList;
import java.util.Iterator;

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
    
    public CityState clone() {
    	CityState aux = new CityState();
    	aux.mundo = (ArrayList<Nodo>) mundo.clone();
    	aux.posicionAgente = posicionAgente.Clone();
        return aux;
    }

    /**
     * This method is used to setup the initial real world.
     */
    @Override
    public void initState() {
    	
    	mundo = GestorNodo.getNodosExistentes();
    	//MUESTRO TODOS LOS DATOS CARGADOS
    	posicionAgente = new Posicion(null,GestorNodo.obtenerNodo("Juan Castelli y Antonia Godoy"));
    	
    	
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
    	
    	for(int i=0; i<mundo.size(); i++){
    		for(int k = 0; k<mundo.get(i).getEnlaces().size(); k++){
    			//Iterator iterador = mundo.get(i).getEnlaces().get(k).getEventos().entrySet().iterator();
    			for(String key : mundo.get(i).getEnlaces().get(k).getEventos().keySet()){
    				System.out.println(key +
    		    	"----> Calle: " + mundo.get(i).getEnlaces().get(k).getNombre());
    			}
    		}
    		
    	}
   	
    	System.out.println("\t\t\t\t------- Estados de los enlaces ---------");
    	
    	for(int i=0; i<mundo.size(); i++){
    		for(int j=0; j<mundo.get(i).getEnlaces().size(); j++){
    			System.out.println(mundo.get(i).getEnlaces().get(j).getNombre() + "----> " +
    				String.valueOf(mundo.get(i).getEnlaces().get(j).isDisponible()));
    		}
    	}
    	
    }
    
    @Override
	public String toString() {
		return "CityState [agState=" + agState + ", mundo=" + mundo
				+ ", posicionAgente=" + posicionAgente
				+ ", getPosicionAgente()=" + getPosicionAgente() + "]";
	}
    
    public Posicion getPosicionAgente() {
		return posicionAgente;
	}

	public void setPosicionAgente(Posicion posicionAgente) {
		this.posicionAgente = posicionAgente;
	}

	public void setPosicionAgente(Enlace enlace, Nodo nodoDestino) {
		 this.posicionAgente.setEnlaceRecorrido(enlace);
    	 this.posicionAgente.setNodoActual(nodoDestino);
		
	}
}

