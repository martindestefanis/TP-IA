package grafo;


import java.util.ArrayList;

public class Nodo {

	private String nombre;
	private ArrayList<Enlace> enlaces = new ArrayList<Enlace>();
	
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public ArrayList<Enlace> getEnlaces() {
		return enlaces;
	}
	
	public void setEnlaces(Enlace enlace) {
		this.enlaces.add(enlace);
	}
	
	public void setEnlaces(ArrayList<Enlace> enlaces){
		this.enlaces = enlaces;
	}
	
	
}
