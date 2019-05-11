package grafo;


import java.util.ArrayList;

import frsf.cidisi.exercise.car.search.CarAgentPerception;
import frsf.cidisi.exercise.car.search.CarAgentState;


public class GestorEnlace {
	
	private static ArrayList<Enlace> enlacesExistentes = new ArrayList<Enlace>();

	public static ArrayList<Enlace> getEnlacesExistentes() {
		return enlacesExistentes;
	}
	
	public static Enlace crearEnlace(Nodo nodoOrigen, Nodo nodoDestino){
		Enlace enlace = new Enlace();
		enlace.setNodoOrigen(nodoOrigen);
		enlace.setNodoDestino(nodoDestino);
		enlace.setNombre();
		
		return enlace;
	}
	
	public static void agregarEnlace(Enlace enlace){
		//VERIFICAR EXISTENCIA
		enlacesExistentes.add(enlace);
	}
	
	public static boolean existeEnlace(Enlace enlace){
		boolean existe = false;
		
		for(int i=0; i<enlacesExistentes.size(); i++){
			if(enlacesExistentes.get(i).getNombre().equalsIgnoreCase(enlace.getNombre())){
				existe = true;
				i = enlacesExistentes.size();
			}
		}
		
		return existe;
	}
	
	public static void agregarNegocio(Enlace enlace, Negocio negocio){
		for(int i=0; i<enlacesExistentes.size();i++){
			if(enlacesExistentes.get(i).getNombre().equalsIgnoreCase(enlace.getNombre())){
				enlacesExistentes.get(i).setNegocios(negocio);
				i = enlacesExistentes.size();
			}
		}
	}
		
	public static void cambiarDisponibilidad(Enlace enlace, Boolean disponibilidad){
		for(int i=0; i<enlacesExistentes.size(); i++){
			if(enlacesExistentes.get(i).getNombre().equalsIgnoreCase(enlace.getNombre())){
				enlacesExistentes.get(i).setDisponible(disponibilidad);
				i = enlacesExistentes.size();
			}
		}
	}		
	public static void agregarProducto(Negocio negocio, String producto, Double precio){
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

	public static void agregarEvento(Enlace enlace, String nombre, Double costo){
	
		if(nombre.equalsIgnoreCase(CarAgentPerception.CORTE_CALLE)){
			GestorEnlace.cambiarDisponibilidad(enlace, false);
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
	
	
	public static double calcularCosto(Enlace enlace){
	//CALCULA EL COSTO TOTAL DEL ENLACE. EL COSTO DE LOS EVENTOS SON UN FACTOR DE TIEMPO DE DEMORA.
	//Y EL COSTO DEL ENLACE ES EL TIEMPO PROMEDIO QUE LE LLEVA AL AGENTE DE RECORRER UNA CUADRA.
		double costo = enlace.getCosto();
		
		//PARA EL CASO DE QUE TRABAJEMOS CON VARIOS EVENTOS POR ENLACE
		
		
		if(CarAgentState.getModalidadSolucion().equalsIgnoreCase("A pie")){
			//MODALIDAD A PIE
			if(enlace.getEventos().containsKey(CarAgentPerception.EVENTO_SOCIAL)){
				costo = costo + costo*(enlace.getEventos().get(CarAgentPerception.EVENTO_SOCIAL)/100);
			}
		}
		else{
			//MODALIDAD "AUTOMOVIL". SI SE AGREGAN MAS MODALIDADES SE DEBE AGREGAR SENTENCIAS IF-ELSE
			if(enlace.getEventos().containsKey(CarAgentPerception.CONGESTION)){
				costo = costo + costo*(enlace.getEventos().get(CarAgentPerception.CONGESTION)/100);
			}	
		}
		
		//PARA EL CASO DE QUE TRABAJEMOS CON UN SOLO EVENTO POR ENLACE
		
		/*costo = enlace.getCosto();
		if(enlace.getEvento()==2){
			costo = costo + costo*0.6;
		}*/
		
		return costo;
	}
	
}


