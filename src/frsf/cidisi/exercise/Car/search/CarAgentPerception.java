package frsf.cidisi.exercise.car.search;

import java.util.ArrayList;
import java.util.Arrays;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import grafo.Enlace;
import grafo.Evento;

public class CarAgentPerception extends Perception {
	
	Posicion pos= new Posicion(null,null);

	//TODO: Setup Statics   
    public static int EMPTY_PERCEPTION = 0;
    public static int CORTE_CALLE = 1;
    public static int CONGESTION = 2;
	
	
	//TODO: Setup Sensors
    //son los enlaces que tiene el nodo actual, de ahi saco cada evento (percepción)
	private ArrayList<Enlace> setSensorEnlaces = new ArrayList<Enlace>();


	public  CarAgentPerception() {
    	//TODO: Complete Method
    }

    public CarAgentPerception(Agent agent, Environment environment) {
        super(agent, environment);
    }

    /**
     * This method is used to setup the perception.
     */
    @Override
    public void initPerception(Agent agentIn, Environment environmentIn) {
        CarAgent agent = (CarAgent) agentIn;
        CityEnviroment environment = (CityEnviroment) environmentIn;
        CityState environmentState =  environment.getEnvironmentState();
        
        pos = environmentState.getPosicionAgente().Clone();
        
        this.setSensorEnlaces(environment.getEnlaces());
        
    }
    

    // The following methods are agent-specific:
    //TODO: Complete this section with the agent-specific methods
    


	public ArrayList<Enlace> getSensorEnlaces() {
		return setSensorEnlaces;
	}
	public void setSensorEnlaces(ArrayList<Enlace> setSensorEnlaces) {
		this.setSensorEnlaces = setSensorEnlaces;
	}

	@Override
	public String toString() {
		return "CarAgentPerception [pos=" + pos + ", setSensorEnlaces="
				+ setSensorEnlaces + ", getSensorEnlaces()="
				+ getSensorEnlaces() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
}
