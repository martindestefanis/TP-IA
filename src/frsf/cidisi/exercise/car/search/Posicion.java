package frsf.cidisi.exercise.car.search;

import grafo.Enlace;
import grafo.Nodo;

public class Posicion {

	private Enlace enlaceRecorrido;
	private Nodo nodoActual;
	
	public Posicion(Enlace enlace, Nodo nodo){
		this.enlaceRecorrido = enlace;
		this.nodoActual = nodo;
	}
	
	public Enlace getEnlaceRecorrido() {
		return enlaceRecorrido;
	}
	
	public void setEnlaceRecorrido(Enlace enlaceRecorrido) {
		this.enlaceRecorrido = enlaceRecorrido;
	}
	
	public Nodo getNodoActual() {
		return nodoActual;
	}
	
	public void setNodoActual(Nodo nodoActual) {
		this.nodoActual = nodoActual;
	}
	
	
}
