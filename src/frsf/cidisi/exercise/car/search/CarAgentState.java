package frsf.cidisi.exercise.car.search;

import java.util.ArrayList;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import grafo.GestorNodo;
import grafo.Nodo;

/**
 * Represent the internal state of the Agent.
 */
public class CarAgentState extends SearchBasedAgentState {
	
	//TODO: Setup Variables
    private Nodo posicionInicial = new Nodo();
    private ArrayList<Nodo> mundo = new ArrayList<Nodo>();
   
	private ArrayList<Producto> productosComprar = new ArrayList<Producto>();
    
    private Nodo posicionActual = new Nodo();
    
    private ArrayList<Producto> productosComprados = new ArrayList<Producto>();
	

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
         
    	 newState.setposicionActual(posicionActual);
         
    	 //No sé si es necesario clonar la posición inicial, no debería cambiar nunca
         newState.setposicionInicial(posicionInicial);
         
         //No sé si es necesario clonar el mundo no debería cambiar tampoco
         ArrayList<Nodo> mundo1 = (ArrayList<Nodo>) mundo.clone();
         newState.setMundo(mundo1);
         
         ArrayList<Producto> productosComprados1 = (ArrayList<Producto>) productosComprados.clone();
         newState.setproductosComprados(productosComprados1);
         
         ArrayList<Producto> productosComprar1 = (ArrayList<Producto>) productosComprar.clone();
         newState.setproductosComprados(productosComprar1);
         
         return newState;
    }

    /**
     * This method is used to update the Agent State when a Perception is
     * received by the Simulator.
     */
    @Override
    public void updateState(Perception p) {
        
        //TODO: Complete Method
    }

    /**
     * This method is optional, and sets the initial state of the agent.
     */
    @Override
    public void initState() {
        
    	//No se si es necesario el mundo
		mundo = GestorNodo.getNodosExistentes();
		posicionInicial = GestorNodo.obtenerNodo("Juan Castelli y Antonia Godoy");
		productosComprar.add(new Producto("Huevos",1));
		posicionActual = posicionInicial;
		productosComprados = null;

    }

    /**
     * This method returns the String representation of the agent state.
     */
    @Override
    public String toString() {
    	String str = "Posicion: " + posicionActual.getNombre();

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
        return posicionActual.equals(((CarAgentState) obj).getposicionActual());
    }
    
    public class Producto{
		public String getProducto() {
			return producto;
		}
		public void setProducto(String producto) {
			this.producto = producto;
		}
		public int getCantidad() {
			return cantidad;
		}
		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}
		private String producto;
		private int cantidad;
		Producto(String producto, int cantidad){
			this.producto=producto;
			this.cantidad=cantidad;
		}
    }

    //TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
   	
     public Nodo getposicionInicial(){
       return posicionInicial;
    }
    public void setposicionInicial(Nodo arg){
      posicionInicial = arg;
     }
     public ArrayList<Nodo> getmundo(){
        return mundo;
     }
     public void setmundo(ArrayList<Nodo> arg){
        mundo = arg;
     }
     public ArrayList<Producto> getproductosComprar(){
        return productosComprar;
     }
     public void setproductosComprar(ArrayList<Producto> arg){
        productosComprar = arg;
     }
     public Nodo getposicionActual(){
        return posicionActual;
     }
     public void setposicionActual(Nodo arg){
        posicionActual = arg;
     }
     public ArrayList<Producto> getproductosComprados(){
        return productosComprados;
     }
     public void setproductosComprados(ArrayList<Producto> arg){
        productosComprados = arg;
     }
     
     public ArrayList<Nodo> getMundo() {
 		return mundo;
 	}

 	public void setMundo(ArrayList<Nodo> mundo) {
 		this.mundo = mundo;
 	}
	
}

