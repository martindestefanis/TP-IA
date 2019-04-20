
import java.util.ArrayList;
import java.util.HashMap;

public class GestorEnlace {
	
	private static ArrayList<Enlace> enlacesExistentes = new ArrayList<Enlace>();

	public ArrayList<Enlace> getEnlacesExistentes() {
		return enlacesExistentes;
	}
	
	public static Enlace agregarEnlace(Nodo nodoInicial, Nodo nodoFinal){
		//VERIFICAR EXISTENCIA	
		//FALTA AGREGAR EL CODIGO PARA ESCRIBIR EN EL CSV
		
		evento nombreEvento = null;
		evento nombreEvento2 = null;
		nombreEvento = nombreEvento.valueOf(nodoInicial.getNombre());
		nombreEvento2 = nombreEvento2.valueOf(nodoFinal.getNombre());
		
		Enlace nuevoEnlace = crearEnlace(nodoInicial,nodoFinal);
		nuevoEnlace.setCosto(GestorCosto.obtenerDistancia(nodoInicial.getNombre(),nodoFinal.getNombre()));
		
		//POR DEFECTO SUPONEMOS QUE EL ENLACE ESTARA DISPONIBLE SI NO ES ASÍ EL SWICHT CAMBIARA SU ESTADO
		//SE ANALIZA SI ALGUNO DE LOS NODOS QUE PERTENECEN AL ENLACE TIENEN ALGUN EVENTO
		nuevoEnlace.setDisponible(true);
		switch(nombreEvento){
			case corteCalle:
			case planBacheo:
				nuevoEnlace.setDisponible(false);
				break;
		}
		switch(nombreEvento2){
			case corteCalle:
			case planBacheo:
				nuevoEnlace.setDisponible(false);
				break;
		}
		
		return nuevoEnlace;
		
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
	
	public static void eliminarEnlace(Nodo nodoInicial, Nodo nodoFinal){
		for(int i = 0; i<enlacesExistentes.size(); i++){
			if(enlacesExistentes.get(i).getNodoOrigen().getNombre().equalsIgnoreCase(nodoInicial.getNombre())
				&& enlacesExistentes.get(i).getNodoDestino().getNombre().equalsIgnoreCase(nodoFinal.getNombre())){
				
				enlacesExistentes.get(i).setDisponible(false);
				i = enlacesExistentes.size();
			}
		}
	}
	
	private enum evento{
		corteCalle,
		planBacheo
	}
	
	public static boolean existeEnlace(Enlace enlace){
		boolean existe = false;
		for(int i=0; i < enlacesExistentes.size(); i++){
			if(enlacesExistentes.get(i).getNombre().equalsIgnoreCase(enlace.getNombre())){
				existe = true;
				i = enlacesExistentes.size();
			}
		}
		
		return existe;
	}
	
	public static void cambiarDisponibilidad(Enlace enlace, boolean disponibilidad){
			
		for(int i=0; i< enlacesExistentes.size(); i++){
			if(enlacesExistentes.get(i).getNombre().equalsIgnoreCase(enlace.getNombre())){
				enlacesExistentes.get(i).setDisponible(disponibilidad);
				i = enlacesExistentes.size();
			}
		}
		
	}
	
}
