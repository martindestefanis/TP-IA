package frsf.cidisi.exercise.car.search;

import java.util.ArrayList;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.environment.Environment;
import grafo.Enlace;

public class CityEnviroment extends Environment {

    public CityEnviroment() {
        // Create the environment state
        this.environmentState = new CityState();
    }

    public CityState getEnvironmentState() {
        return (CityState) super.getEnvironmentState();
    }

    /**
     * This method is called by the simulator. Given the Agent, it creates
     * a new perception reading, for example, the agent position.
     * @param agent
     * @return A perception that will be given to the agent by the simulator.
     */
    @Override
    public  CarAgentPerception getPercept() {
        // Create a new perception to return
         CarAgentPerception perception = new CarAgentPerception();
         
         //TRAE TODOS LOS ENLACES DEL MUNDO?
         perception.setSensorEnlaces(this.getEnlaces());
        
        // Return the perception
        return perception;
    }

    
    @Override
	public String toString() {
		return "[environmentState="+ " Posicion agente:" + getEnvironmentState().getPosicionAgente().getNodoActual().getNombre()+"]";
	}

    
    public boolean agentFailed(Action actionReturned) {

        CityState envState =
                this.getEnvironmentState();

        // TODO: Complete Method        

        return false;
    }
    
    public ArrayList<Enlace> getEnlaces(){
    	return ((CityState) this.environmentState).getEnlaces();
    }

	//TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
    
    
}
