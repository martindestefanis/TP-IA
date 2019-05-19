package frsf.cidisi.exercise.car.search;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
        
        for(int i=0; i<agState.getListaMapas().size();i++){
        	agState.getListaMapas().get(i).setVisible(true);
        	while(!agState.isSeguir()){
        	}
        	agState.getListaMapas().get(i).setVisible(false);
        	agState.setSeguir(false);
        }
        JLabel prueba = new JLabel();
        prueba.setText("----- Evento ------");
        JLabel prueba2 = new JLabel();
        prueba2.setText("Corte de calle");
        JPanel info = new JPanel();
        info.add(prueba);
        info.add(prueba2);
        
        JFrame pantallaMapa = new JFrame("Solucion Final");
        MapViewOptions options = new MapViewOptions();
        options.importPlaces();
        options.setApiKey("AIzaSyDXeR9Z3IqVz25_JKRdKjT7tLKXttLgnj4");
        Mapa mapa = new Mapa(options,agState.getEsquinasVisitadas(),agState.getmundo());
        
        JPanel panel = new JPanel( new GridLayout());
        
        JPanel container = new JPanel();
        
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        
        panel.add(mapa);
      	pantallaMapa.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      	container.add(mapa);
      	container.add(info);
       	pantallaMapa.add(container);
      	//pantallaMapa.add(mapa, BorderLayout.SOUTH);
       	pantallaMapa.setExtendedState(JFrame.MAXIMIZED_BOTH);
       	//pantallaMapa.setSize(700, 500);
       	pantallaMapa.setLocationRelativeTo(null);
       	pantallaMapa.setVisible(true);
        
    }

}
