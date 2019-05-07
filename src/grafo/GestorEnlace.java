package grafo;


import java.util.ArrayList;


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
	
	public static void agregarEvento(Enlace enlace, Evento evento){
		for(int i=0; i<enlacesExistentes.size();i++){
			if(enlacesExistentes.get(i).getNombre().equalsIgnoreCase(enlace.getNombre())){
				enlacesExistentes.get(i).setEventos(evento);
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
	
	public static boolean existeEvento(Enlace enlace, String nombreEvento){
		boolean existe = false;
		for(int i=0; i<enlacesExistentes.size(); i++){
			if(enlacesExistentes.get(i).getNombre().equalsIgnoreCase(enlace.getNombre())){
				for(int j=0; j<enlacesExistentes.get(i).getEventos().size(); j++){
					if(enlacesExistentes.get(i).getEventos().get(j).getNombre().equalsIgnoreCase(nombreEvento)){
						existe = true;
						j = enlacesExistentes.get(i).getEventos().size();
						i = enlacesExistentes.size();
					}
				}
			}
		}
		return existe;
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
	
}


