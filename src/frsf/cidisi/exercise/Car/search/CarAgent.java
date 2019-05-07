package frsf.cidisi.exercise.car.search;

import frsf.cidisi.exercise.car.actions.Comprar_producto;
import frsf.cidisi.exercise.car.actions.Avanzar;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.solver.search.*;
import grafo.GestorNegocio;
import grafo.GestorNodo;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;


public class CarAgent extends SearchBasedAgent {

    public CarAgent() {

        // The Agent Goal
        CarGoal agGoal = new CarGoal();

        // The Agent State
        CarAgentState agState = new CarAgentState();
        this.setAgentState(agState);

        // Create the operators
        Vector<SearchAction> operators = new Vector<SearchAction>();
        
        for(int i=0; i<agState.getproductosComprar().size(); i++){
        	operators.addElement(new Comprar_producto(agState.getproductosComprar().get(i)));
        }
                
        
        for(int i =0; i<GestorNodo.getNodosExistentes().size();i++){
        	operators.addElement(new Avanzar(GestorNodo.getNodosExistentes().get(i)));
        }
        	

        // Create the Problem which the agent will resolve
        Problem problem = new Problem(agGoal, agState, operators);
        this.setProblem(problem);
    }

    /**
     * This method is executed by the simulator to ask the agent for an action.
     */
    @Override
    public Action selectAction() {

        // Create the search strategy
        IStepCostFunction cost = new CostFunction();
        IEstimatedCostFunction heuristic = new Heuristic(); 
        AStarSearch strategy = new AStarSearch(cost, heuristic);          

        // Create a Search object with the strategy
        Search searchSolver = new Search(strategy);

        /* Generate an XML file with the search tree. It can also be generated
         * in other formats like PDF with PDF_TREE */
        searchSolver.setVisibleTree(Search.GRAPHVIZ_TREE);

        // Set the Search searchSolver.
        this.setSolver(searchSolver);

        // Ask the solver for the best action
        Action selectedAction = null;
        try {
            selectedAction =
                    this.getSolver().solve(new Object[]{this.getProblem()});
        } catch (Exception ex) {
            Logger.getLogger(CarAgent.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Return the selected action
        return selectedAction;

    }

    /**
     * This method is executed by the simulator to give the agent a perception.
     * Then it updates its state.
     * @param p
     */
    @Override
    public void see(Perception p) {
        this.getAgentState().updateState(p);
    }
}
