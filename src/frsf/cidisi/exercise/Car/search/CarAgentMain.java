package frsf.cidisi.exercise.car.search;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.MapViewOptions;

import pantalla.HelloWorld;
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
        
       // Mapa mapa = new Mapa(agState.getEsquinasVisitadas(),agState.getmundo());
        JFrame frame = new JFrame("JxMaps - Hello, World!");
        MapViewOptions options = new MapViewOptions();
        options.importPlaces();
        options.setApiKey("AIzaSyDXeR9Z3IqVz25_JKRdKjT7tLKXttLgnj4");
        HelloWorld mapView = new HelloWorld(options,agState.getEsquinasVisitadas(),agState.getmundo());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(mapView, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
