package frsf.cidisi.exercise.car.search;

import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;
import frsf.cidisi.faia.state.AgentState;

/**
 * This class can be used in any search strategy like
 * Uniform Cost.
 */
public class CostFunction implements IStepCostFunction {
	
	

    /**
     * This method calculates the cost of the given NTree node.
     */
    @Override
    public double calculateCost(NTree node) {
        
        //TODO: Complete Method
    	double costoAccion = node.getAction().getCost();
    	//System.out.println("Costo nodo: " + node.getCost());
    	//System.out.println("Costo accion: " + costoAccion);
        
        return costoAccion;
    }
}
