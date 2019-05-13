package grafo;

public class Evento {
	
	private String nombre;
	private Enlace enlace;
	private double costo;

	public Evento(){
		
	}
	
	public Evento(String nombre, Enlace enlace, double costo){
		this.nombre = nombre;
		this.enlace = enlace;
		this.costo = costo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setCosto(double costoLeido){
		this.costo = costoLeido;
	}
	
	public double getCosto(){
		return this.costo;
	}

	public Enlace getEnlace() {
		return enlace;
	}

	public void setEnlace(Enlace enlace) {
		this.enlace = enlace;
	}
	
	
	
}
