package grafo;


import java.util.ArrayList;


public class GestorEnlace {
	
	private static ArrayList<Enlace> enlacesExistentes = new ArrayList<Enlace>();

	public ArrayList<Enlace> getEnlacesExistentes() {
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
}
