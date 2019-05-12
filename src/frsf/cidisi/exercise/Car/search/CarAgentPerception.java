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
    public static String EMPTY_PERCEPTION = "Sin percepcion";
    public static String CORTE_CALLE = "Corte Calle";
    public static String CONGESTION = "Congestion";
    public static String EVENTO_SOCIAL = "Evento Social";
    //public static String NEGOCIO_CERRADO;
	
	
	//TODO: Setup Sensors
    
    //son los enlaces que tiene el nodo actual, de ahi saco cada evento (percepción)
	private ArrayList<Enlace> SensorEnlaces = new ArrayList<Enlace>();


	public  CarAgentPerception() {
    	//TODO: Complete Method
    }
	
	public  CarAgentPerception Clone(){
		CarAgentPerception aux = new CarAgentPerception();
		ArrayList<Enlace> SensorEnlaces1 = new ArrayList<Enlace>(SensorEnlaces);
		aux.setSensorEnlaces(SensorEnlaces1);
		return aux;
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
        
        pos = environmentState.getPosicionAgente();
        
        this.setSensorEnlaces(environment.getEnlaces());
        
    }
    

    // The following methods are agent-specific:
    //TODO: Complete this section with the agent-specific methods
    


	public ArrayList<Enlace> getSensorEnlaces() {
		return SensorEnlaces;
	}
	public void setSensorEnlaces(ArrayList<Enlace> setSensorEnlaces) {
		this.SensorEnlaces = setSensorEnlaces;
	}

	@Override
	public String toString() {
		return "";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pos == null) ? 0 : pos.hashCode());
		result = prime
				* result
				+ ((SensorEnlaces == null) ? 0 : SensorEnlaces.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarAgentPerception other = (CarAgentPerception) obj;
		if (pos == null) {
			if (other.pos != null)
				return false;
		} else if (!pos.equals(other.pos))
			return false;
		if (SensorEnlaces == null) {
			if (other.SensorEnlaces != null)
				return false;
		} else if (!SensorEnlaces.equals(other.SensorEnlaces))
			return false;
		return true;
	}
}
