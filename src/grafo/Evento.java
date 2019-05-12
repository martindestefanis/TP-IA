package grafo;

public class Evento {
	
	private String nombre;
	private Enlace enlace;
	private double costo;

	public Evento(){
		
	}
	
	public static Evento crearEvento(String nombre,Enlace enlace,Double costo){
		Evento evento = new Evento();
		evento.setNombre(nombre);
		evento.setEnlace(enlace);
		evento.setCosto(costo);
		
		return evento;
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
