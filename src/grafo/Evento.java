package grafo;

public class Evento {
	
	private String nombre;
	private Nodo esquina1;
	private Nodo esquina2;
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

	public Nodo getEsquina1() {
		return esquina1;
	}

	public void setEsquina1(Nodo esquina1) {
		this.esquina1 = esquina1;
	}

	public Nodo getEsquina2() {
		return esquina2;
	}

	public void setEsquina2(Nodo esquina2) {
		this.esquina2 = esquina2;
	}
	
	
	

}
