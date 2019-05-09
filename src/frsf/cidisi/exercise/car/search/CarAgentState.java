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
    Posicion posicionInicial;
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
         newState.setPosicionInicial(posicionInicial);
         
         //No sé si es necesario clonar el mundo no debería cambiar tampoco
         ArrayList<Nodo> mundo1 = (ArrayList<Nodo>) mundo.clone();
         newState.setMundo(mundo1);
                
         ArrayList<String> productosComprados1 =  (ArrayList<String>) productosComprados.clone();
         newState.setProductosComprados(productosComprados1);
         
         ArrayList<String> productosComprar1 = (ArrayList<String>) productosComprar.clone();
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
    	
    	/*
    	 * SE SUPONE QUE P TIENE TODOS LOS EVENTOS QUE CARGAMOS EN EL INIT
    	 * ENTONCES ITERAR POR P (VER LA FORMA DADO QUE CREO QUE EN ESTE METODO SE OBTIENE UNA SOLA
    	 * PERCEPCION) Y AGREGAR UN EVENTO QUE NO SE ENCUENTRE YA AGREGADO AL ENLACE CORRESPONDIENTE 
    	 * DE ESA PERCEPCION.
    	 * */
    	
    	CarAgentPerception p1 = (CarAgentPerception) p;
    	
    	
        //TODO: Complete Method
    	
    }

    /**
     * This method is optional, and sets the initial state of the agent.
     */
    @Override
    public void initState() {
        
    	//No se si es necesario el mundo
		//mundo = GestorNodo.getNodosExistentes();
    	
		posicionInicial = new Posicion(null,GestorNodo.obtenerNodo("Juan Castelli y Antonia Godoy"));
		
		productosComprar.add("Huevos");
		
		posicionActual = new Posicion(posicionInicial.getEnlaceRecorrido(),posicionInicial.getNodoActual());

    }

    /**
     * This method returns the String representation of the agent state.
     */
    @Override
    public String toString() {
    	String str = "Posicion: " + posicionActual.getNodoActual().getNombre();

        return str;
    }

    /**
     * This method is used in the search process to verify if the node already
     * exists in the actual search.
     */
    @Override
    public boolean equals(Object obj) {
       
    	if (!(obj instanceof CarAgentState)) {
            return false;
        }
        return posicionActual.equals(((CarAgentState) obj).getPosicionActual());
    }
    

    //TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
   	
     public Posicion getPosicionInicial(){
       return posicionInicial;
    }
    public void setPosicionInicial(Posicion arg){
      posicionInicial = arg;
     }
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

