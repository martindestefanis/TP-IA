package frsf.cidisi.exercise.car.search;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
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
    private boolean atras = false;
    private boolean esSolucionFinal = false;

    private static String modalidadSolucion;
      
	private ArrayList<String> productosComprar = new ArrayList<String>();
    
    private ArrayList<String> productosComprados = new ArrayList<String>();
    
    static Grafo grafo = new Grafo();
    
    private ArrayList<Nodo> esquinasVisitadas = new ArrayList<Nodo>();
    
    private static ArrayList<Nodo> mundo = grafo.iniciarMundo();
	private int iteracion = 0;
	private ArrayList<JFrame> listaMapas = new ArrayList<JFrame>();

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
    	generarMapaIteración();
    }

    /**
     * This method is optional, and sets the initial state of the agent.
     */
    @Override
    public void initState() {
        //LA MODALIDAD PUEDE SER O "A PIE" O EN "AUTOMOVIL"
    	//modalidadSolucion = "Bicicleta";
    	modalidadSolucion = "Automovil";
		
		//SETEO TODOS LOS ENLACES CON PERCEPCIONES EMPTY
   		for(int i=0; i<mundo.size(); i++){
			for(int j=0; j<mundo.get(i).getEnlaces().size(); j++){
				mundo.get(i).getEnlaces().get(j).setEventos(CarAgentPerception.EMPTY_PERCEPTION,0);
			}
		}
   		
		productosComprar.add("Café");
		productosComprar.add("Huevos");
		//productosComprar.add("Leche");
		//productosComprar.add("Maní");
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
	
	public ArrayList<JFrame> getListaMapas() {
		return listaMapas;
	}
	
	public boolean isSeguir() {
		return seguir;
	}

	public void setSeguir(boolean seguir) {
		this.seguir = seguir;
	}
	
	public boolean isAtras() {
		return atras;
	}

	public void setAtras(boolean atras) {
		this.atras = atras;
	}
	
	public boolean isEsSolucionFinal() {
		return esSolucionFinal;
	}

	public void setEsSolucionFinal(boolean esSolucionFinal) {
		this.esSolucionFinal = esSolucionFinal;
	}
	
	public static Grafo getGrafo() {
		return grafo;
	}

	public void generarMapaIteración(){
		
		JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info,BoxLayout.Y_AXIS));
		
        JLabel tituloEventoAleatorio = new JLabel();
        tituloEventoAleatorio.setText("------------------------------------ Evento Aleatorio ------------------------------------");
       	tituloEventoAleatorio.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        JLabel eventoAleatorio = new JLabel();
        eventoAleatorio.setText(grafo.getEventoAgregado() + " ------> Calle: " + grafo.getEnlacePercepcionAgregada());
        
        JLabel tituloEventosAmbiente = new JLabel();
        tituloEventosAmbiente.setText("------------------------------------ Eventos Ambiente ------------------------------------");
        tituloEventosAmbiente.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));

        JLabel tituloEventosAgente = new JLabel();
        tituloEventosAgente.setText("------------------------------------ Eventos Agente ------------------------------------");
        tituloEventosAgente.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        
        JLabel tituloAccionRealizada = new JLabel();
        tituloAccionRealizada.setText("------------------------------------ Accion Realizada ------------------------------------");
        tituloAccionRealizada.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        JLabel accionRealizada = new JLabel(grafo.getSelectedAction());
        
        JLabel tituloProductosAComprar = new JLabel();
        tituloProductosAComprar.setText("------------------------------------ Productos a Comprar ------------------------------------");
        tituloProductosAComprar.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        JLabel productosAComprar = new JLabel();
        productosAComprar.setText(this.getproductosComprar().toString());
        
        info.add(tituloEventoAleatorio);
        info.add(eventoAleatorio);
        info.add(tituloEventosAmbiente);
        
        
        for(int i=0; i<CityState.mundo.size(); i++){
    		for(int k = 0; k<CityState.mundo.get(i).getEnlaces().size(); k++){
    				for(String key : CityState.mundo.get(i).getEnlaces().get(k).getEventos().keySet()){
    					if(!key.equalsIgnoreCase("Sin percepcion")){
    						JLabel evento = new JLabel();
    						evento.setText(key +"----> Calle: " + CityState.mundo.get(i).getEnlaces().get(k).getNombre());
    						info.add(evento);
    					}else{
    						if(!CityState.mundo.get(i).getEnlaces().get(k).isDisponible()){
	    						JLabel evento = new JLabel();
	    						evento.setText("Corte de Calle ----> Calle: " + CityState.mundo.get(i).getEnlaces().get(k).getNombre());
	    						info.add(evento);
            				}
    					}
        				
        			}
    		}	
    	}
        info.add(tituloEventosAgente);
        for(int i=0; i<mundo.size(); i++){
    		for(int k = 0; k<mundo.get(i).getEnlaces().size(); k++){
    				for(String key : mundo.get(i).getEnlaces().get(k).getEventos().keySet()){
    					if(!key.equalsIgnoreCase("Sin percepcion")){
    						JLabel eventoA = new JLabel();
    						eventoA.setText(key +"----> Calle: " + mundo.get(i).getEnlaces().get(k).getNombre());
    						info.add(eventoA);
    					}else{
    						if(!mundo.get(i).getEnlaces().get(k).isDisponible()){
	    						JLabel eventoA = new JLabel();
	    						eventoA.setText("Corte de Calle ----> Calle: " + mundo.get(i).getEnlaces().get(k).getNombre());
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
			  			seguir = true;
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
			  			atras = true;
			  		}
			     }
			});
		
		botones.add(botonSiguiente);
		botones.add(botonAtras);
		info.add(botones);
		JFrame pantallaMapa = new JFrame("Iteracion " + iteracion);
		
		pantallaMapa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MapViewOptions options = new MapViewOptions();
		options.importPlaces();
		options.setApiKey("AIzaSyDXeR9Z3IqVz25_JKRdKjT7tLKXttLgnj4");
		Mapa mapa = new Mapa(options,esquinasVisitadas,mundo);
		
		JPanel contenedor = new JPanel();
	        
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.X_AXIS));
        
      	pantallaMapa.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      	
      	contenedor.add(mapa);
      	contenedor.add(info);
      	
      	pantallaMapa.add(contenedor);
       	pantallaMapa.setExtendedState(JFrame.MAXIMIZED_BOTH);
       	pantallaMapa.setLocationRelativeTo(null);

       	listaMapas.add(pantallaMapa);

		iteracion++;
	}
	
	
     
}

