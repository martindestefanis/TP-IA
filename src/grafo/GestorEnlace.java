package grafo;


import java.util.ArrayList;

import frsf.cidisi.exercise.car.search.CarAgentPerception;
import frsf.cidisi.exercise.car.search.CarAgentState;


public class GestorEnlace {
	
	private  ArrayList<Enlace> enlacesExistentes = new ArrayList<Enlace>();

	public ArrayList<Enlace> getEnlacesExistentes() {
		return enlacesExistentes;
	}
	
	public Enlace crearEnlace(Nodo nodoOrigen, Nodo nodoDestino){
		Enlace enlace = new Enlace();
		enlace.setNodoOrigen(nodoOrigen);
		enlace.setNodoDestino(nodoDestino);
		enlace.setNombre();
		
		return enlace;
	}
	
	public void agregarEnlace(Enlace enlace){
		//VERIFICAR EXISTENCIA
		enlacesExistentes.add(enlace);
	}
	
	public boolean existeEnlace(Enlace enlace){
		boolean existe = false;
		
		for(int i=0; i<enlacesExistentes.size(); i++){
			if(enlacesExistentes.get(i).getNombre().equalsIgnoreCase(enlace.getNombre())){
				existe = true;
				i = enlacesExistentes.size();
			}
		}
		
		return existe;
	}
	
	public void agregarNegocio(Enlace enlace, Negocio negocio){
		for(int i=0; i<enlacesExistentes.size();i++){
			if(enlacesExistentes.get(i).getNombre().equalsIgnoreCase(enlace.getNombre())){
				enlacesExistentes.get(i).setNegocios(negocio);
				i = enlacesExistentes.size();
			}
		}
	}
		
	public void cambiarDisponibilidad(Enlace enlace, Boolean disponibilidad){
		for(int i=0; i<enlacesExistentes.size(); i++){
			if(enlacesExistentes.get(i).getNombre().equalsIgnoreCase(enlace.getNombre())){
				enlacesExistentes.get(i).setDisponible(disponibilidad);
				i = enlacesExistentes.size();
			}
		}
	}		
	public void agregarProducto(Negocio negocio, String producto, Double precio){
		for(int i=0; i<enlacesExistentes.size(); i++){
			for(int j=0; j<enlacesExistentes.get(i).getNegocios().size(); j++){
				if(enlacesExistentes.get(i).getNegocios().get(j).getNombre().equalsIgnoreCase(negocio.getNombre()) &&
					enlacesExistentes.get(i).getNodoOrigen().getNombre().equalsIgnoreCase(negocio.getEsquina1().getNombre())){
					if(!GestorNegocio.existeProducto(negocio, producto)){
						enlacesExistentes.get(i).getNegocios().get(j).agregarProductoPrecio(producto, precio);
						break;
					}
				}
			}
			
		}
	}

	public void agregarEvento(Enlace enlace, String nombre, Double costo){
	
		if(nombre.equalsIgnoreCase(CarAgentPerception.CORTE_CALLE)){
			cambiarDisponibilidad(enlace, false);
		}
		else{
			//BUSCO EL ENLACE EN LA LISTA DE ENLACES EXISTENTES Y AGREGO EL EVENTO
			for(int i=0; i<enlacesExistentes.size(); i++){
				if(enlacesExistentes.get(i).getNombre().equalsIgnoreCase(enlace.getNombre())){
				
					// ME FIJO SI ESE ENLACE CONTIENE LA PERCEPCION EMPTY. SI LA TIENE LA ELIMINO
					if(enlacesExistentes.get(i).getEventos().containsKey(CarAgentPerception.EMPTY_PERCEPTION)){
						enlacesExistentes.get(i).getEventos().remove(CarAgentPerception.EMPTY_PERCEPTION);
					}
					//ME FIJO SI ESE ENLACE CONTIENE YA UN EVENTO DEL MISMO TIPO DEL QUE QUIERO AGREGAR
					if(!enlacesExistentes.get(i).getEventos().containsKey(nombre)){
						enlacesExistentes.get(i).setEventos(nombre, costo);
					}else{
						//SI YA EXISTE UN EVENTO DEL MISMO TIPO DEL QUE QUIERO AGREGAR NO LO AGREGO
						System.out.println("La calle: " + enlacesExistentes.get(i).getNombre() + 
							" ya contiene un evento de " + nombre);
					}
					break;
				}
			}
		}	
	}
	
	
	public double calcularCosto(Enlace enlace){
	//CALCULA EL COSTO TOTAL DEL ENLACE. EL COSTO DE LOS EVENTOS SON UN FACTOR DE COSTO EXTRA.
	//Y EL COSTO DEL ENLACE LA DISTANCIA EN METROS DE UNA CUADRA.

		double costo = enlace.getCosto();
	
		if(CarAgentState.getModalidadSolucion().equalsIgnoreCase("Bicicleta")){
			//MODALIDAD A BICICLETA
			//AL AGENTE LE LLEVA 1 MINUTO RECORRER UN CUADRA
			
			costo= 1; //1 MINUTO
			if(enlace.getEventos().containsKey(CarAgentPerception.EVENTO_SOCIAL)){
				costo = costo + costo*(enlace.getEventos().get(CarAgentPerception.EVENTO_SOCIAL)/100);
			}
			return costo;
			
		}
		else{
			if(CarAgentState.getModalidadSolucion().equalsIgnoreCase("Mas barato")){
				//La idea es que el avanzar no cueste nada, pero en uniforme si el costo sería 0 de avanzar siempre eligiría esta
				//acción y nunca intentaría comprar. Por lo que hay que usar amplitud aquí
				return new Double(0.0);
				
			}else{
				//MODALIDAD "AUTOMOVIL".
				//Suponiendo que un auto hace 11[km/litro] entonces por metro consume 0.0000909091 [litro/m]
		    	//Suponiendo que la nafta está $50 el litro, entonces: el costo por metro de nafta seria: $0.0045454545
				//Entra en un bucle con un costo tan chiquito por eso dejo 0.45454545
				costo= costo*0.45454545;
				if(enlace.getEventos().containsKey(CarAgentPerception.CONGESTION)){
					costo = costo + costo*(enlace.getEventos().get(CarAgentPerception.CONGESTION)/100);
				}
				return costo;
			}
			
		}
		
	}
	
}


