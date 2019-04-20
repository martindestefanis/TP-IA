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
	
	//ELIJE EL NEGOCIO DE LA LISTA DE NEGOCIOS EXISTENTES QUE TIENE EL PRECIO MAS
	//BARATO DE UN DETERMINADO PRODUCTO
	public static Nodo getNegocioObjetivo(String producto){
		
		Nodo nodoNegocioObjetivo = new Nodo();
		
		Negocio negocio = new Negocio();
		double precioProducto = 5000; //CAMBIAR VALOR SI SE MANEJAN PRECIOS CON MONTOS ELEVADOS
		
		for(int i=0; i< negocioExistente.size(); i++){
			if(negocioExistente.get(i).getProductoPrecio().containsKey(producto)
				&& negocioExistente.get(i).isAbierto()
				&& precioProducto > negocioExistente.get(i).getPrecioProducto(producto)){
				
				negocio = negocioExistente.get(i);
				precioProducto = negocioExistente.get(i).getPrecioProducto(producto);
			}
		}
		
		nodoNegocioObjetivo = GestorNodo.getNodoNegocio(negocio);
		
		return nodoNegocioObjetivo; 
	}
	
	
}
