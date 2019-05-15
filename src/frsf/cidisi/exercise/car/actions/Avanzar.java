package frsf.cidisi.exercise.car.actions;

import java.util.ArrayList;

import frsf.cidisi.exercise.car.search.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import grafo.Enlace;
import grafo.GestorEnlace;
import grafo.Grafo;
import grafo.Nodo;

public class Avanzar extends SearchAction {
	
	private Nodo nodoDestino;
	private Enlace enlace = new Enlace();
	
	public Avanzar(Nodo nodoDestino){
		this.nodoDestino = nodoDestino;
	}

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        CarAgentState agState = (CarAgentState) s;
        
        for(int i=0; i<agState.getPosicionActual().getNodoActual().getEnlaces().size(); i++){
        	if(agState.getPosicionActual().getNodoActual().getEnlaces().get(i).isDisponible() && 
        		agState.getPosicionActual().getNodoActual().getEnlaces().get(i).getNodoDestino().getNombre().equalsIgnoreCase(nodoDestino.getNombre())){
        		
        		this.enlace = agState.getPosicionActual().getNodoActual().getEnlaces().get(i);
        		agState.setPosicionActual(agState.getPosicionActual().getNodoActual().getEnlaces().get(i),nodoDestino);
           		return agState;
        	}
        }       		
        
        return null;
    }

    /**
     * This method updates the agent state and the real world state.
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
    CityState environmentState = (CityState) est;
       CarAgentState agState = ((CarAgentState) ast);
                    
       for(int i=0; i<agState.getPosicionActual().getNodoActual().getEnlaces().size(); i++){
        	if(agState.getPosicionActual().getNodoActual().getEnlaces().get(i).isDisponible() && 
        		agState.getPosicionActual().getNodoActual().getEnlaces().get(i).getNodoDestino().getNombre().equalsIgnoreCase(nodoDestino.getNombre())){
        		
        		this.enlace = agState.getPosicionActual().getNodoActual().getEnlaces().get(i);
        		System.out.println("Calle recorrida: " + enlace.getNombre());
        		agState.setPosicionActual(agState.getPosicionActual().getNodoActual().getEnlaces().get(i),nodoDestino);
        		environmentState.setPosicionAgente(agState.getPosicionActual().getNodoActual().getEnlaces().get(i),nodoDestino);
        		System.out.println("Posicion Actual: " + agState.getPosicionActual().getNodoActual().getNombre());
        		agState.setEsquinasVisitadas(agState.getPosicionActual().getNodoActual());
        		Grafo.percepcionesAleatorias(environmentState.getMundo());
/*        		System.out.println("\t\t\t\t------- PRUEBA Eventos Cambiados ambiente --------");
            	
            	for(int j=0; j<environmentState.getMundo().size(); j++){
            		for(int k = 0; k<environmentState.getMundo().get(j).getEnlaces().size(); k++){
            			for(String key : environmentState.getMundo().get(j).getEnlaces().get(k).getEventos().keySet()){
            				System.out.println(key +
            		    	"----> Calle: " + environmentState.getMundo().get(j).getEnlaces().get(k).getNombre());
            			}
            		}
            		
            	}
 */           	
        		return environmentState;
        	}
        }  
       return null;
    }

    /**
     * This method returns the action cost.
     */
    @Override
    public Double getCost() {
    	GestorEnlace gestorEnlace = new GestorEnlace();
        return new Double(gestorEnlace.calcularCosto(this.enlace));
    }

    @Override
	public String toString() {
		return "Avanzar [nodoDestino=" + nodoDestino.getNombre() + ", CostoOperacion="
				+ getCost() + "]";
	}
}