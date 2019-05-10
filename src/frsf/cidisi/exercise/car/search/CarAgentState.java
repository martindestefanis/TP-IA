package frsf.cidisi.exercise.car.search;

import java.util.ArrayList;
import java.util.Vector;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import grafo.Enlace;
import grafo.GestorNodo;
import grafo.Nodo;

/**
 * Represent the internal state of the Agent.
 */
public class CarAgentState extends SearchBasedAgentState {
	
	//TODO: Setup Variables
    //Posicion posicionInicial;
    Posicion posicionActual;
    
    private ArrayList<Nodo> mundo = new ArrayList<Nodo>();
   
	private ArrayList<String> productosComprar = new ArrayList<String>();
    
    private ArrayList<String> productosComprados = new ArrayList<String>();
	

    public CarAgentState() {
    
    	//TODO: Complete Method
        this.initState();
    }

    /**
     * This method clones the state of the agent. It's used in the search
     * process, when creating the search tree.
     */
    @Override
    public SearchBasedAgentState clone() {
        
    	 CarAgentState newState = new CarAgentState();
         
    	 newState.setPosicionActual(posicionActual.Clone());
         
    	 //No sé si es necesario clonar la posición inicial, no debería cambiar nunca
    	 // newState.setPosicionInicial(posicionInicial);
         
         //No sé si es necesario clonar el mundo no debería cambiar tampoco
    	 //ArrayList<Nodo> mundo1 = (ArrayList<Nodo>) mundo.clone();
    	 
    	 ArrayList<Nodo> mundo1 = new ArrayList<Nodo>(mundo);
         newState.setMundo(mundo1);
                
         //ArrayList<String> productosComprados1 =  (ArrayList<String>) productosComprados.clone();
         ArrayList<String> productosComprados1 = new ArrayList<String>(productosComprados);
         newState.setProductosComprados(productosComprados1);
         
         //ArrayList<String> productosComprar1 = (ArrayList<String>) productosComprar.clone();
         ArrayList<String> productosComprar1 = new ArrayList<String>(productosComprar);
         newState.setProductosComprados(productosComprar1);
         
         return newState;
    }

    /**
     * This method is used to update the Agent State when a Perception is
     * received by the Simulator.
     * 
     */
    @Override
    public void updateState(Perception p) {
    	
    	CarAgentPerception p1 = (CarAgentPerception) p;

    	for(int i=0; i< p1.getSensorEnlaces().size(); i++){
    		for(int j=0; j<posicionActual.getNodoActual().getEnlaces().size();j++){
    			if(posicionActual.getNodoActual().getEnlaces().get(i).getNodoOrigen().getNombre().equalsIgnoreCase(p1.getSensorEnlaces().get(j).getNodoOrigen().getNombre())
    					&& posicionActual.getNodoActual().getEnlaces().get(i).getNodoDestino().getNombre().equalsIgnoreCase(p1.getSensorEnlaces().get(j).getNodoDestino().getNombre())){
    				posicionActual.getNodoActual().getEnlaces().get(j).setEvento(p1.getSensorEnlaces().get(i).getEvento());
    				if(posicionActual.getNodoActual().getEnlaces().get(j).getEvento()==p1.CORTE_CALLE){
    					posicionActual.getNodoActual().getEnlaces().get(j).setDisponible(false);
    				}
    			}
    		}
    	}
    	
    }

    /**
     * This method is optional, and sets the initial state of the agent.
     */
    @Override
    public void initState() {
        
    	//No se si es necesario el mundo
		//mundo = GestorNodo.getNodosExistentes();
    	
		//posicionInicial = new Posicion(null,GestorNodo.obtenerNodo("Juan Castelli y Antonia Godoy"));
		
		productosComprar.add("Huevos");
		
		posicionActual = new Posicion(null,GestorNodo.obtenerNodo("Juan Castelli y Antonia Godoy"));

    }

    /**
     * This method returns the String representation of the agent state.
     */
    @Override
    public String toString() {
    	String str = "Posicion: " + posicionActual.getNodoActual().getNombre();

        return str;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mundo == null) ? 0 : mundo.hashCode());
		result = prime * result
				+ ((posicionActual == null) ? 0 : posicionActual.hashCode());
		result = prime
				* result
				+ ((productosComprados == null) ? 0 : productosComprados
						.hashCode());
		result = prime
				* result
				+ ((productosComprar == null) ? 0 : productosComprar.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		CarAgentState other = (CarAgentState) obj;
		if (mundo == null) {
			if (other.mundo != null)
				return false;
		} else if (!mundo.equals(other.mundo))
			return false;
		if (posicionActual == null) {
			if (other.posicionActual != null)
				return false;
		} else if (!posicionActual.equals(other.posicionActual))
			return false;
		if (productosComprados == null) {
			if (other.productosComprados != null)
				return false;
		} else if (!productosComprados.equals(other.productosComprados))
			return false;
		if (productosComprar == null) {
			if (other.productosComprar != null)
				return false;
		} else if (!productosComprar.equals(other.productosComprar))
			return false;
		return true;
	}
    

    //TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
   	
 /*    public Posicion getPosicionInicial(){
       return posicionInicial;
    }
    public void setPosicionInicial(Posicion arg){
      posicionInicial = arg;
     }
 */ 
     public ArrayList<Nodo> getmundo(){
        return mundo;
     }
     public void setMundo(ArrayList<Nodo> arg){
        mundo = arg;
     }
     public ArrayList<String> getproductosComprar(){
        return productosComprar;
     }
     public void setProductosComprar(ArrayList<String> arg){
        productosComprar = arg;
     }
     public Posicion getPosicionActual(){
        return posicionActual;
     }
     public void setPosicionActual(Posicion arg){
        posicionActual = arg;
     }
     
     public void setPosicionActual(Enlace enlace, Nodo nodo){
    	 this.posicionActual.setEnlaceRecorrido(enlace);
    	 this.posicionActual.setNodoActual(nodo);
     }
     
     public ArrayList<String> getProductosComprados(){
        return productosComprados;
     }
     public void setProductosComprados(ArrayList<String> arg){
        productosComprados = arg;
     }
}

