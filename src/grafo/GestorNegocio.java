package grafo;

import java.util.ArrayList;


public class GestorNegocio {
	
	private static ArrayList<Negocio> negocioExistente = new ArrayList<Negocio>();
	
	public static boolean existeNegocio(Nodo esquina1, Nodo esquina2){
		boolean existe = false;
		for(int i=0; i< negocioExistente.size(); i++){
			if((negocioExistente.get(i).getEsquina1().getNombre().equalsIgnoreCase(esquina1.getNombre()) && negocioExistente.get(i).getEsquina2().getNombre().equalsIgnoreCase(esquina2.getNombre()))
				|| (negocioExistente.get(i).getEsquina1().getNombre().equalsIgnoreCase(esquina2.getNombre()) && negocioExistente.get(i).getEsquina2().getNombre().equalsIgnoreCase(esquina1.getNombre()))){
			
				existe = true;
			}
		}
		
		return existe;
	}
	
	//RETORNA TODOS LOS NEGOCIOS CARGADOS
	public static ArrayList<Negocio> getNegocioExistente(){
		return negocioExistente;
	}
	
}
