package frsf.cidisi.exercise.Car.search;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

/**
 * Represent the internal state of the Agent.
 */
public class CarState extends SearchBasedAgentState {
	
	//TODO: Setup Variables
    //private Other posicionInicial;
    //private Other mundo;
    //private Other productosComprar;
    //private Other posicionActual;
	

    public CarState() {
    
    	//TODO: Complete Method
    	/*
			// posicionInicial = initData0;
			// mundo = initData1;
			// productosComprar = initData2;
			// posicionActual = initData3;
        */
        this.initState();
    }

    /**
     * This method clones the state of the agent. It's used in the search
     * process, when creating the search tree.
     */
    @Override
    public SearchBasedAgentState clone() {
        
		//TODO: Complete Method
		
        return null;
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
        
	//TODO: Complete Method

    }

    /**
     * This method returns the String representation of the agent state.
     */
    @Override
    public String toString() {
        String str = "";

        //TODO: Complete Method

        return str;
    }

    /**
     * This method is used in the search process to verify if the node already
     * exists in the actual search.
     */
    @Override
    public boolean equals(Object obj) {
       
       //TODO: Complete Method
        
        return true;
    }

    //TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
   	
//     public Other getposicionInicial(){
//        return posicionInicial;
//     }
//     public void setposicionInicial(Other arg){
//        posicionInicial = arg;
//     }
//     public Other getmundo(){
//        return mundo;
//     }
//     public void setmundo(Other arg){
//        mundo = arg;
//     }
//     public Other getproductosComprar(){
//        return productosComprar;
//     }
//     public void setproductosComprar(Other arg){
//        productosComprar = arg;
//     }
//     public Other getposicionActual(){
//        return posicionActual;
//     }
//     public void setposicionActual(Other arg){
//        posicionActual = arg;
//     }
	
}

