package grafo;


import java.util.ArrayList;

import com.teamdev.jxmaps.LatLng;

public class Nodo {

	private String nombre;
	private ArrayList<Enlace> enlaces = new ArrayList<Enlace>();
	private LatLng latitudLongitud = new LatLng();

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

	public LatLng getLatitudLongitud() {
		return latitudLongitud;
	}

	public void setLatitudLongitud(LatLng latitudLongitud) {
		this.latitudLongitud = latitudLongitud;
	}
	
	
}
