package frsf.cidisi.exercise.car.actions;

import java.util.ArrayList;

import frsf.cidisi.exercise.car.search.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import grafo.Negocio;

public class Comprar_producto extends SearchAction {

	private double costoProducto;
	private String productoComprar;
	
	/**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
	
	public Comprar_producto(String producto){
    	this.productoComprar = producto;
    }
	
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        CarAgentState agState = (CarAgentState) s;
        
        Negocio negocio = new Negocio();  
        
     /*
      * ACLARACION: ESTE METODO LO QUE HACE ES COMPRAR EL PRODUCTO QUE SE ENCUENTRA PRIMERO EN LA LISTA DE
      * PRODUCTOS A COMPRAR
      * 
      * */
        
        if(agState.getPosicionActual().getEnlaceRecorrido() != null){
	        for(int i=0; i<agState.getPosicionActual().getEnlaceRecorrido().getNegocios().size(); i++){
	        	negocio = agState.getPosicionActual().getEnlaceRecorrido().getNegocios().get(i);
	        	if(negocio.getProductoPrecio().containsKey(this.productoComprar)){
	        		
	        		agState.getProductosComprados().add(this.productoComprar);
	        		agState.getproductosComprar().remove(productoComprar);
	        		
	        		this.costoProducto = negocio.getProductoPrecio().get(this.productoComprar);
	        		return agState;
	        	}
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
        Negocio negocio = new Negocio();
        if(agState.getPosicionActual().getEnlaceRecorrido() != null){
        	
	        for(int i=0; i<agState.getPosicionActual().getEnlaceRecorrido().getNegocios().size(); i++){
	        	negocio = agState.getPosicionActual().getEnlaceRecorrido().getNegocios().get(i);
	        	if(negocio.getProductoPrecio().containsKey(this.productoComprar)){
	        		
	        		agState.getProductosComprados().add(this.productoComprar);
	        		agState.getproductosComprar().remove(productoComprar);
	        		this.costoProducto = negocio.getProductoPrecio().get(this.productoComprar);
	        		return environmentState;
	        	}
	        }
        }

       return null;
    }

    /**
     * This method returns the action cost.
     */
    @Override
    public Double getCost() {
        return new Double(this.costoProducto);
    }

    @Override
	public String toString() {
		return "Comprar_producto [costoProducto=" + costoProducto
				+ ", productoComprar=" + productoComprar + ", CostoOperacion="
				+ getCost() + "]";
	}
}