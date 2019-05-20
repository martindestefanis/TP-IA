package frsf.cidisi.exercise.car.search;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import pantalla.Mapa;

import com.teamdev.jxmaps.MapViewOptions;

//import pantalla.Mapa;
//import pantalla.Mapa;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import grafo.Grafo;
import grafo.Nodo;

public class CarAgentMain {
	
	private static CityState estadoAmbiente = new CityState();
	private static CarAgentState estadoAgente = new CarAgentState();

    public static void main(String[] args) throws PrologConnectorException {
    	
     	CarAgent agent = new CarAgent();

        CityEnviroment environment = new CityEnviroment();

        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        
        simulator.start();
        
       estadoAgente = (CarAgentState) agent.getAgentState();
       estadoAmbiente = (CityState) environment.getEnvironmentState();
       for(int i=0; i<estadoAmbiente.getMundo().size(); i++){
   		for(int k = 0; k<estadoAmbiente.getMundo().get(i).getEnlaces().size(); k++){
   				for(String key : estadoAmbiente.getMundo().get(i).getEnlaces().get(k).getEventos().keySet()){
   					if(!key.equalsIgnoreCase("Sin percepcion")){
   						System.out.println(key +"----> Calle: " + estadoAmbiente.getMundo().get(i).getEnlaces().get(k).getNombre());
   						
   					}else{
   						if(!estadoAmbiente.getMundo().get(i).getEnlaces().get(k).isDisponible()){
	    						System.out.println("Corte de Calle ----> Calle: " + estadoAmbiente.getMundo().get(i).getEnlaces().get(k).getNombre());
           				}
   					}
       				
       			}
   		}	
   	}
       System.out.println("AG");
       for(int i=0; i<estadoAgente.getmundo().size(); i++){
      		for(int k = 0; k<estadoAgente.getmundo().get(i).getEnlaces().size(); k++){
      				for(String key : estadoAgente.getmundo().get(i).getEnlaces().get(k).getEventos().keySet()){
      					if(!key.equalsIgnoreCase("Sin percepcion")){
      						System.out.println(key +"----> Calle: " + estadoAgente.getmundo().get(i).getEnlaces().get(k).getNombre());
      						
      					}else{
      						if(!estadoAgente.getmundo().get(i).getEnlaces().get(k).isDisponible()){
   	    						System.out.println("Corte de Calle ----> Calle: " + estadoAgente.getmundo().get(i).getEnlaces().get(k).getNombre());
              				}
      					}
          				
          			}
      		}	
      	}
       
       generarPantallaSolucion();
        
        for(int i=0; i<estadoAgente.getListaMapas().size();i++){
        	estadoAgente.getListaMapas().get(i).setVisible(true);
        	while(!estadoAgente.isSeguir() && !estadoAgente.isAtras()){
        	}
        	if(estadoAgente.isSeguir()){
        		estadoAgente.getListaMapas().get(i).setVisible(false);
        		estadoAgente.setSeguir(false);
        	}else{
        		if(i>0){
        			estadoAgente.getListaMapas().get(i).setVisible(false);
            		i = i-2;
            		estadoAgente.setAtras(false);
        		}
        		
        	}
        	
        }
        
    }

	public static void generarPantallaSolucion(){
    	JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info,BoxLayout.Y_AXIS));
		
        JLabel tituloEventoAleatorio = new JLabel();
        tituloEventoAleatorio.setText("------------------------------------ Evento Aleatorio ------------------------------------");
        tituloEventoAleatorio.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        JLabel eventoAleatorio = new JLabel();
        eventoAleatorio.setText(estadoAgente.getGrafo().getEventoAgregado() + " ------> Calle: " + estadoAgente.getGrafo().getEnlacePercepcionAgregada());
        
        JLabel tituloEventosAmbiente = new JLabel();
        tituloEventosAmbiente.setText("------------------------------------ Eventos Ambiente ------------------------------------");
        tituloEventosAmbiente.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));

        JLabel tituloEventosAgente = new JLabel();
        tituloEventosAgente.setText("------------------------------------ Eventos Agente ------------------------------------");
        tituloEventosAgente.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));

        JLabel tituloAccionRealizada = new JLabel();
        tituloAccionRealizada.setText("------------------------------------ Accion Realizada ------------------------------------");
        tituloAccionRealizada.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        JLabel accionRealizada = new JLabel(estadoAgente.getGrafo().getSelectedAction());
        
        JLabel tituloProductosAComprar = new JLabel();
        tituloProductosAComprar.setText("------------------------------------ Productos a Comprar ------------------------------------");
        tituloProductosAComprar.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        JLabel productosAComprar = new JLabel();
        productosAComprar.setText(estadoAgente.getproductosComprar().toString());
        
        info.add(tituloEventoAleatorio);
        info.add(eventoAleatorio);
        info.add(tituloEventosAmbiente);
        for(int i=0; i<estadoAmbiente.getMundo().size(); i++){
    		for(int k = 0; k<estadoAmbiente.getMundo().get(i).getEnlaces().size(); k++){
    				for(String key : estadoAmbiente.getMundo().get(i).getEnlaces().get(k).getEventos().keySet()){
    					if(!key.equalsIgnoreCase("Sin percepcion")){
    						JLabel evento = new JLabel();
    						evento.setText(key +"----> Calle: " + estadoAmbiente.getMundo().get(i).getEnlaces().get(k).getNombre());
    						info.add(evento);
    					}else{
    						if(!estadoAmbiente.getMundo().get(i).getEnlaces().get(k).isDisponible()){
	    						JLabel evento = new JLabel();
	    						evento.setText("Corte de Calle ----> Calle: " + estadoAmbiente.getMundo().get(i).getEnlaces().get(k).getNombre());
	    						info.add(evento);
            				}
    					}
        				
        			}
    		}	
    	}
        info.add(tituloEventosAgente);
        for(int i=0; i<estadoAgente.getmundo().size(); i++){
    		for(int k = 0; k<estadoAgente.getmundo().get(i).getEnlaces().size(); k++){
    				for(String key : estadoAgente.getmundo().get(i).getEnlaces().get(k).getEventos().keySet()){
    					if(!key.equalsIgnoreCase("Sin percepcion")){
    						JLabel eventoA = new JLabel();
    						eventoA.setText(key +"----> Calle: " + estadoAgente.getmundo().get(i).getEnlaces().get(k).getNombre());
    						info.add(eventoA);
     					}else{
    						if(!estadoAgente.getmundo().get(i).getEnlaces().get(k).isDisponible()){
	    						JLabel eventoA = new JLabel();
	    						eventoA.setText("Corte de Calle ----> Calle: " + estadoAgente.getmundo().get(i).getEnlaces().get(k).getNombre());
	    						info.add(eventoA);
            				}
    					}
        				
        			}
    		}	
    	}

        info.add(tituloAccionRealizada);
        info.add(accionRealizada);
        info.add(tituloProductosAComprar);
        info.add(productosAComprar);
		
		JPanel botones = new JPanel();
		botones.setLayout(new BoxLayout(botones,BoxLayout.X_AXIS));
		
		final JButton botonSiguiente = new JButton("Siguiente");
		botonSiguiente.setFocusPainted(false);
		botonSiguiente.setForeground(Color.BLACK);
		botonSiguiente.setBackground(Color.WHITE);
		Border line = new LineBorder(Color.BLACK);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
		botonSiguiente.setBorder(compound);
		botonSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    	  if(e.getSource()==botonSiguiente){
			  			estadoAgente.setSeguir(true);
			  		}
			     }
			});
		
		final JButton botonAtras = new JButton("Atras");
		botonAtras.setFocusPainted(false);
		botonAtras.setForeground(Color.BLACK);
		botonAtras.setBackground(Color.WHITE);
		botonAtras.setBorder(compound);
		botonAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    	  if(e.getSource()==botonAtras){
			  			estadoAgente.setAtras(true);
			  		}
			     }
			});
		
		botones.add(botonSiguiente);
		botones.add(botonAtras);
		info.add(botones);
		JFrame pantallaMapa = new JFrame("Solucion Final");
		
		pantallaMapa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MapViewOptions options = new MapViewOptions();
		options.importPlaces();
		options.setApiKey("AIzaSyDXeR9Z3IqVz25_JKRdKjT7tLKXttLgnj4");
		Mapa mapa = new Mapa(options,estadoAgente.getEsquinasVisitadas(),estadoAgente.getmundo());
		
		JPanel contenedor = new JPanel();
	        
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.X_AXIS));
        
      	pantallaMapa.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      	
      	contenedor.add(mapa);
      	contenedor.add(info);
      	
      	pantallaMapa.add(contenedor);
       	pantallaMapa.setExtendedState(JFrame.MAXIMIZED_BOTH);
       	pantallaMapa.setLocationRelativeTo(null);
       	
       	estadoAgente.getListaMapas().add(pantallaMapa);

    }

}
