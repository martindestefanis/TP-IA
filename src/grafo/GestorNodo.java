package grafo;


import java.util.ArrayList;

public class GestorNodo {

	private static ArrayList<Nodo> nodosExistentes = new ArrayList<Nodo>();
	
	
	

	public static ArrayList<Nodo> getNodosExistentes() {
		return nodosExistentes;
		
	}

	public static void setNodosExistentes(ArrayList<Nodo> existentes) {
		nodosExistentes = existentes;
	}
	
	public static Nodo obtenerNodo(String nombreNodo){
		Nodo nodo = new Nodo();
		for(int i=0; i< nodosExistentes.size(); i++){
			if(nodosExistentes.get(i).getNombre().equalsIgnoreCase(nombreNodo)){
				nodo = nodosExistentes.get(i);
				}
	       }
		
		return nodo;
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
	public static void agregarNegocio(Enlace enlace, Negocio negocio){
		for(int i=0; i<nodosExistentes.size();i++){
			if(nodosExistentes.get(i).getNombre().equalsIgnoreCase(enlace.getNodoOrigen().getNombre())){
				for(int j=0; j<nodosExistentes.get(i).getEnlaces().size(); j++){
					if(nodosExistentes.get(i).getEnlaces().get(j).getNombre().equalsIgnoreCase(enlace.getNombre())){
						
						nodosExistentes.get(i).getEnlaces().get(j).setNegocios(negocio);
						i = nodosExistentes.size();
						break;
					}
				}
			}
		}
	}
}
