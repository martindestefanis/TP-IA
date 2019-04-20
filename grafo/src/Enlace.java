
import java.util.HashMap;

public class Enlace {

	private String nombre;
	private Nodo nodoOrigen;
	private Nodo nodoDestino;
	private double costo;
	private boolean disponible;
	
	
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
		return costo;
	}
	
	
	public void setCosto(double costo) {
		this.costo = costo;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	
	
	
}
