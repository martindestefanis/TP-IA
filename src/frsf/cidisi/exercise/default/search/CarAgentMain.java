package frsf.cidisi.exercise.default.search;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class CarAgentMain {

    public static void main(String[] args) throws PrologConnectorException {
        CarAgent agent = new CarAgent();

        CityEnviroment environment = new CityEnviroment();

        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        
        simulator.start();
    }

}
