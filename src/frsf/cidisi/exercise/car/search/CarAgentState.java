package frsf.cidisi.exercise.car.search;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import pantalla.Mapa;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import com.teamdev.jxmaps.MapViewOptions;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import grafo.Enlace;
import grafo.GestorNodo;
import grafo.Grafo;
import grafo.Nodo;

/**
 * Represent the internal state of the Agent.
 */
public class CarAgentState extends SearchBasedAgentState{
	
	//TODO: Setup Variables
	
    //Posicion posicionInicial;
    Posicion posicionActual;
    private boolean seguir =false;

    private static String modalidadSolucion;
      
	private ArrayList<String> productosComprar = new ArrayList<String>();
    
    private ArrayList<String> productosComprados = new ArrayList<String>();
    
    static Grafo grafo = new Grafo();
    
    private ArrayList<Nodo> esquinasVisitadas = new ArrayList<Nodo>();
    
    private static ArrayList<Nodo> mundo = grafo.iniciarMundo();
	private int iteracion = 0;

    public CarAgentState() {
    
    	//TODO: Complete Method
    		this.initState();
    }

    /**
     * This method clones the state of the agent. It's used in the search
     * process, when creating the search tree.
     */
    @Override
    public SearchBasedAgentState clone() {
        
    	 CarAgentState newState = new CarAgentState();
         
    	 newState.setPosicionActual(posicionActual.Clone());
    	 
    	 ArrayList<Nodo> mundo1 = new ArrayList<Nodo>(mundo);
         newState.setMundo(mundo1);

         ArrayList<String> productosComprados1 = new ArrayList<String>(productosComprados);
         newState.setProductosComprados(productosComprados1);

         ArrayList<String> productosComprar1 = new ArrayList<String>(productosComprar);
         newState.setProductosComprar(productosComprar1);
         
         return newState;
    }

    /**
     * This method is used to update the Agent State when a Perception is
     * received by the Simulator.
     * 
     */
    
    @Override
    public void updateState(Perception p) {
    	
    	CarAgentPerception p1 = (CarAgentPerception) p;
    	
    	for(int i = 0; i<mundo.size(); i++){
    		for(int j=0; j<mundo.get(i).getEnlaces().size();j++){
    			for(int k=0; k< p1.getSensorEnlaces().size(); k++){
    				if(mundo.get(i).getEnlaces().get(j).getNombre().equalsIgnoreCase(p1.getSensorEnlaces().get(k).getNombre())){
    					mundo.get(i).getEnlaces().get(j).setEventos(p1.getSensorEnlaces().get(k).getEventos());
    					if(!p1.getSensorEnlaces().get(k).isDisponible()){
    						mundo.get(i).getEnlaces().get(j).setDisponible(false);
    					}
    					
    				}
    			}
    		}
    	}
		final JButton boton = new JButton("Siguiente");
		boton.setFocusPainted(false);
		boton.setForeground(Color.BLACK);
		boton.setBackground(Color.WHITE);
		Border line = new LineBorder(Color.BLACK);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
		boton.setBorder(compound);
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    	  if(e.getSource()==boton){
			  			seguir = true;
			  		}
			     }
			});
		JPanel toolBar = new JPanel();
		toolBar.setBackground(Color.WHITE);
		toolBar.setSize(100,100);
		 
		JLabel accion = new JLabel("Acci�n: " + grafo.getSelectedAction() + " || ");
		accion.setFont(new java.awt.Font("Tahoma", Font.BOLD, 15));
		accion.setForeground(new java.awt.Color(0, 0, 0));
		JLabel prodAComprar = new JLabel("Productos a comprar: " + this.getproductosComprar().toString() + " || "); 
		prodAComprar.setFont(new java.awt.Font("Tahoma", Font.BOLD, 15));
		prodAComprar.setForeground(new java.awt.Color(0, 0, 0));
		JLabel eventoAgregado = new JLabel("Evento agregado: " + grafo.getEventoAgregado());
		eventoAgregado.setFont(new java.awt.Font("Tahoma", Font.BOLD, 15));
		eventoAgregado.setForeground(new java.awt.Color(0, 0, 0));
		JLabel enlacePercepcionAgregada = new JLabel(" en el enlace: " + grafo.getEnlacePercepcionAgregada()); 
		enlacePercepcionAgregada.setFont(new java.awt.Font("Tahoma", Font.BOLD, 15));
		enlacePercepcionAgregada.setForeground(new java.awt.Color(0, 0, 0));
		   
		JFrame pantallaMapa = new JFrame("Iteracion " + iteracion);
		MapViewOptions options = new MapViewOptions();
		options.importPlaces();
		options.setApiKey("AIzaSyDXeR9Z3IqVz25_JKRdKjT7tLKXttLgnj4");
		Mapa mapa = new Mapa(options,esquinasVisitadas,mundo);
		
		toolBar.add(prodAComprar);
		toolBar.add(accion);
		toolBar.add(eventoAgregado);
		toolBar.add(eventoAgregado);
		toolBar.add(enlacePercepcionAgregada);
		toolBar.add(boton);
		
		pantallaMapa.add(toolBar, BorderLayout.NORTH);
		pantallaMapa.add(mapa, BorderLayout.CENTER);
		pantallaMapa.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//pantallaMapa.setSize(1650,1080);
		pantallaMapa.setLocationRelativeTo(null);
		pantallaMapa.setVisible(true);
		
		
		while(!seguir){
		}
		iteracion++;
		pantallaMapa.setVisible(false);
		seguir=false;
	    
       	
    	System.out.println("\t\t\t\t------- MUNDO AGENTE ----------");
    	
    	System.out.println("\t\t\t\t------- Eventos percibidos --------");
    	
    	for(int i=0; i<mundo.size(); i++){
    		for(int k = 0; k<mundo.get(i).getEnlaces().size(); k++){
    				for(String key : mundo.get(i).getEnlaces().get(k).getEventos().keySet()){
    					if(!key.equalsIgnoreCase("Sin percepcion")){
    						System.out.println(key +
        		    		    	"----> Calle: " + mundo.get(i).getEnlaces().get(k).getNombre());
    					}else{
    						if(!mundo.get(i).getEnlaces().get(k).isDisponible()){
            					System.out.println("Corte Calle" +
            		    		    	"----> Calle: " + mundo.get(i).getEnlaces().get(k).getNombre());
            				}
    					}
        				
        			}
    		}	
    	}
    }

    /**
     * This method is optional, and sets the initial state of the agent.
     */
    @Override
    public void initState() {
        //LA MODALIDAD PUEDE SER O "A PIE" O EN "AUTOMOVIL"
    	//modalidadSolucion = "Bicicleta";
    	modalidadSolucion = "Automovil";
    	//modalidadSolucion = "Mas barato";
		
		//SETEO TODOS LOS ENLACES CON PERCEPCIONES EMPTY
   		for(int i=0; i<mundo.size(); i++){
			for(int j=0; j<mundo.get(i).getEnlaces().size(); j++){
				mundo.get(i).getEnlaces().get(j).setEventos(CarAgentPerception.EMPTY_PERCEPTION,0);
			}
		}
		
		productosComprar.add("Caf�");
		productosComprar.add("Huevos");
	//	productosComprar.add("Leche");
	//	productosComprar.add("Man�");
		posicionActual = new Posicion(null,GestorNodo.obtenerNodo(mundo,"Juan Castelli y Antonia Godoy"));
		esquinasVisitadas.add(GestorNodo.obtenerNodo(mundo, "Juan Castelli y Antonia Godoy"));
    }

    /**
     * This method returns the String representation of the agent state.
     */
    @Override
    public String toString() {
    	String str = "Posicion: " + posicionActual.getNodoActual().getNombre();

        return str;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((esquinasVisitadas == null) ? 0 : esquinasVisitadas
						.hashCode());
		result = prime * result
				+ ((posicionActual == null) ? 0 : posicionActual.hashCode());
		result = prime
				* result
				+ ((productosComprados == null) ? 0 : productosComprados
						.hashCode());
		result = prime
				* result
				+ ((productosComprar == null) ? 0 : productosComprar.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		CarAgentState other = (CarAgentState) obj;
		if (esquinasVisitadas == null) {
			if (other.esquinasVisitadas != null)
				return false;
		} else if (!esquinasVisitadas.equals(other.esquinasVisitadas))
			return false;
		if (posicionActual == null) {
			if (other.posicionActual != null)
				return false;
		} else if (!posicionActual.equals(other.posicionActual))
			return false;
		if (productosComprados == null) {
			if (other.productosComprados != null)
				return false;
		} else if (!productosComprados.equals(other.productosComprados))
			return false;
		if (productosComprar == null) {
			if (other.productosComprar != null)
				return false;
		} else if (!productosComprar.equals(other.productosComprar))
			return false;
		return true;
	}
    

    //TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
	
     public ArrayList<Nodo> getmundo(){
        return mundo;
     }
     public static String getModalidadSolucion() {
		return modalidadSolucion;
	}

	public static void setModalidadSolucion(String modalidad) {
		modalidadSolucion = modalidad;
	}

	public void setMundo(ArrayList<Nodo> arg){
        mundo = arg;
     }
     public ArrayList<String> getproductosComprar(){
        return productosComprar;
     }
     public void setProductosComprar(ArrayList<String> arg){
        productosComprar = arg;
     }
     public Posicion getPosicionActual(){
        return posicionActual;
     }
     public void setPosicionActual(Posicion arg){
        posicionActual = arg;
     }
     
     public void setPosicionActual(Enlace enlace, Nodo nodo){
    	 this.posicionActual.setEnlaceRecorrido(enlace);
    	 this.posicionActual.setNodoActual(nodo);
     }
     
     public ArrayList<String> getProductosComprados(){
        return productosComprados;
     }
     public void setProductosComprados(ArrayList<String> arg){
        productosComprados = arg;
     }

	public ArrayList<Nodo> getEsquinasVisitadas() {
		return esquinasVisitadas;
	}

	public void setEsquinasVisitadas(Nodo esquina) {
		this.esquinasVisitadas.add(esquina);
	}
     
     
}

