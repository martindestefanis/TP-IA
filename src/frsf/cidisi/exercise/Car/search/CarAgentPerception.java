package frsf.cidisi.exercise.car.search;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class CarAgentPerception extends Perception {

	//TODO: Setup Statics
    //public static int UNKNOWN_PERCEPTION = -1;   
	
	
	//TODO: Setup Sensors
	private int cortes_calles;
	
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
    	
    	/*MODIFICAR LAS PERCEPCIONES PARA QUE SEAN UNA LISTA
		*
    	 * ACA HAY QUE LEER EL ARCHIVO DE EVENTOS Y POR CADA ENLACE
    	 * SETEAR EL EVENTO EN EL ENLACE EN EL MUNDO DEL AMBIENTE Y EN LA LISTA DE PERCEPCIONES CREO
    	 *HAY QUE BORRAR (COMENTALO ASI NO SE PIERDE) EL METODO LEEREVENTOS DEL INICIAR MUNDO
    	 * Y VERIFICAR QUE AL COMENTAR ESE METODO NO PASE NADA
    	 * 
    	 * */
    	
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
	
     public int getcortes_calles(){
        return cortes_calles;
     }
     public void setcortes_calles(int arg){
        this.cortes_calles = arg;
     }
     public int getcongestion_transito(){
        return congestion_transito;
     }
     public void setcongestion_transito(int arg){
        this.congestion_transito = arg;
     }
	
   
}
