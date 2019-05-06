package grafo;


import java.util.ArrayList;
import java.util.HashMap;

public class Enlace {

	private String nombre;
	private Nodo nodoOrigen;
	private Nodo nodoDestino;
	private double costoCalle;
	private boolean disponible;
	private ArrayList<Negocio> negocios = new ArrayList();
	private ArrayList<Evento> eventos = new ArrayList();
	
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(){
		this.nombre = "(" + this.nodoOrigen.getNombre() + ")" + " A " + "(" + this.nodoDestino.getNombre() + ")";
	}
	
	public Nodo getNodoOrigen() {
		return nodoOrigen;
	}
	
	public void setNodoOrigen(Nodo nodoOrigen) {
		this.nodoOrigen = nodoOrigen;
	}
	
	public Nodo getNodoDestino() {
		return nodoDestino;
	}
	
	public void setNodoDestino(Nodo nodoDestino) {
		this.nodoDestino = nodoDestino;
	}
	
	public double getCosto() {
		return costoCalle;
	}
	
	
	public void setCosto(double costoCalle) {
		this.costoCalle = costoCalle;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	public ArrayList<Negocio> getNegocios() {
		return negocios;
	}

	public void setNegocios(Negocio negocio) {
		this.negocios.add(negocio);
	}

	public ArrayList<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(Evento evento) {
		this.eventos.add(evento);
	}
	
	

	
	
	
	
	
	
}
