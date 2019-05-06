package grafo;

public class Evento {
	
	private String nombre;
	private double costo;

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
	

}
