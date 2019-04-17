package frsf.cidisi.exercise.Car.search;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class CarAgentPerception extends Perception {

	//TODO: Setup Statics
    //public static int UNKNOWN_PERCEPTION = -1;   
	
	
	//TODO: Setup Sensors
	private int negocio_cerrado;
	private int congestion_transito;
	
 

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
    	
    	//TODO: Complete Method
        
        //CarAgent agent = (CarAgent) agentIn;
        //CityEnviroment environment = (CityEnviroment) environmentIn;
        //CityState environmentState =
        //        environment.getEnvironmentState();
       
        
    }
    
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        //TODO: Complete Method

        return str.toString();
    }

    // The following methods are agent-specific:
    //TODO: Complete this section with the agent-specific methods
	
     public int getnegocio_cerrado(){
        return negocio_cerrado;
     }
     public void setnegocio_cerrado(int arg){
        this.negocio_cerrado = arg;
     }
     public int getcongestion_transito(){
        return congestion_transito;
     }
     public void setcongestion_transito(int arg){
        this.congestion_transito = arg;
     }
	
   
}
