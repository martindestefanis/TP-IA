package frsf.cidisi.exercise.car.search;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import pantalla.Mapa;

import com.teamdev.jxmaps.MapViewOptions;

//import pantalla.Mapa;
//import pantalla.Mapa;

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
        
        
        JFrame pantallaMapa = new JFrame("Mapa");
        MapViewOptions options = new MapViewOptions();
        options.importPlaces();
        options.setApiKey("AIzaSyDXeR9Z3IqVz25_JKRdKjT7tLKXttLgnj4");
        Mapa mapa = new Mapa(options,agState.getEsquinasVisitadas(),agState.getmundo());
       	//Mapa2 mapView = new Mapa2(options,agState.getEsquinasVisitadas(),agState.getmundo());
       	pantallaMapa.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       	pantallaMapa.add(mapa, BorderLayout.CENTER);
       	pantallaMapa.setSize(700, 500);
       	pantallaMapa.setLocationRelativeTo(null);
       	pantallaMapa.setVisible(true);
        //JFrame pantallaDatos = new JFrame("Estados Agente y Mundo");*/
        
    }

}
