
import java.util.ArrayList;

public class GestorNodo {

	private static ArrayList<Nodo> nodosExistentes = new ArrayList<Nodo>();

	public static ArrayList<Nodo> getNodosExistentes() {
		return nodosExistentes;
	}

	public static void setNodosExistentes(ArrayList<Nodo> existentes) {
		nodosExistentes = existentes;
	}

	
	public static void agregarNodo(Nodo nuevoNodo){
		//AGREGAR CODIGO PARA QUE ESCRIBA EN EL CSV
		nodosExistentes.add(nuevoNodo);
		
	}
	
	public static Nodo crearNodo(String nombreNodo){
		Nodo nodo = new Nodo();
		nodo.setNombre(nombreNodo);
		return nodo;
	}
	
	public static boolean existeNodo(Nodo nodo){
		boolean existe = false;
		
		for(int i=0; i < nodosExistentes.size(); i++){
			if(nodosExistentes.get(i).getNombre().equalsIgnoreCase(nodo.getNombre())){
				existe = true;
				i = nodosExistentes.size();
			}
		}
		
		return existe;
	}
	
	public static void agregarEnlaceANodo(Nodo nodo, Enlace enlace){
		for(int i = 0; i < nodosExistentes.size(); i++){
			if(nodosExistentes.get(i).getNombre().equalsIgnoreCase(nodo.getNombre())){
				nodosExistentes.get(i).getEnlaces().add(enlace);
				i = nodosExistentes.size();
			}
		}
		
	}
	
	public static Nodo getNodoNegocio(Negocio negocio){
		Nodo nodoNegocio = new Nodo();
		for(int i=0; i< nodosExistentes.size(); i++){
			if(nodosExistentes.get(i).getNombre().equalsIgnoreCase(negocio.getNombre())){
				nodoNegocio.setNombre(negocio.getNombre());
				nodoNegocio.setEnlaces(nodosExistentes.get(i).getEnlaces());
				i = nodosExistentes.size();
			}
		}
		
		return nodoNegocio;
	}
}
