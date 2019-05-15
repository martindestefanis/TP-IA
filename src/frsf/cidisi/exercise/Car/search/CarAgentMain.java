package frsf.cidisi.exercise.car.search;

import java.util.ArrayList;

import pantalla.Mapa;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import grafo.Grafo;
import grafo.Nodo;

public class CarAgentMain {

    public static void main(String[] args) throws PrologConnectorException {
    	
     	CarAgent agent = new CarAgent();

        CityEnviroment environment = new CityEnviroment();

        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        
        simulator.start();
        CarAgentState agState = (CarAgentState) agent.getAgentState();
        /*for(int i=0; i< agState.getEsquinasVisitadas().size(); i++){
        	System.out.println(agState.getEsquinasVisitadas().get(i).getNombre());
        	System.out.println(agState.getEsquinasVisitadas().get(i).getLatitudLongitud());
        }*/
        
        Mapa mapa = new Mapa(agState.getEsquinasVisitadas(),agState.getmundo());
    }

}
