package frsf.cidisi.exercise.car.search;


import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class CarGoal extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
    
    	// TODO: Complete Method
        if  (((CarAgentState) agentState).getproductosComprar().isEmpty()==true){
            return true;
        }
        else{
        	 return false;
        }
	}
}