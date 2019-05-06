package frsf.cidisi.exercise.car.search;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import grafo.Grafo;

public class CarAgentMain {

    public static void main(String[] args) throws PrologConnectorException {
    	
    	//LEVANTA TODO EL MUNDO
    	Grafo.iniciarMundo();
    	
    	CarAgent agent = new CarAgent();

        CityEnviroment environment = new CityEnviroment();

        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        
        simulator.start();
    }

}
