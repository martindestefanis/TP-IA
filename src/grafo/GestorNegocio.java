package grafo;

import java.util.ArrayList;


public class GestorNegocio {
	
	private static ArrayList<Negocio> negocioExistente = new ArrayList<Negocio>();
	
	public static Negocio crearNegocio(String nombre, Nodo esquina1, Nodo esquina2, Boolean abierto){
		Negocio negocio = new Negocio();
		negocio.setNombre(nombre);
		negocio.setEsquina1(esquina1);
		negocio.setEsquina2(esquina2);
		negocio.setAbierto(abierto);
		
		return negocio;
	}
	
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
	
	public static void agregarNegocio(Negocio negocio){
		negocioExistente.add(negocio);	
	}
	
	public static boolean existeNegocio(Negocio negocio){
		
	//PARA QUE ESTE METODO FUNCIONE SE DEBE HABER VALIDADO ANTERIORMENTE QUE LA ESQUINA ORIGEN SEA ESQUINA1
	// Y QUE LA ESQUINA DESTINO SEA ESQUINA2
		boolean existe = false;
		for(int i=0; i<negocioExistente.size(); i++){
			
			if(negocioExistente.get(i).getEsquina1().getNombre().equalsIgnoreCase(negocio.getEsquina1().getNombre()) &&
				negocioExistente.get(i).getEsquina2().getNombre().equalsIgnoreCase(negocio.getEsquina2().getNombre())
				&& negocioExistente.get(i).getNombre().equalsIgnoreCase(negocio.getNombre())){
				existe = true;
				i = negocioExistente.size();
			}
		}
		return existe;
	}
	
	public static void agregarProducto(Negocio negocio, String producto, Double costo){
		for(int i=0; i<negocioExistente.size(); i++){
			
			//VERIFICA QUE EL NOMBRE, NODO ORIGEN Y NODO DESTINO SEAN IGUALES PARA OBTENER ASI EL NEGOCIO CORRESPONDIENTE
			if(negocioExistente.get(i).getEsquina1().getNombre().equalsIgnoreCase(negocio.getEsquina1().getNombre()) &&
					negocioExistente.get(i).getEsquina2().getNombre().equalsIgnoreCase(negocio.getEsquina2().getNombre())
					&& negocioExistente.get(i).getNombre().equalsIgnoreCase(negocio.getNombre())){
				//VERIFICA LA EXISTENCIA DEL PRODUCTO EN ESE NEGOCIO SELECCIONADO
				if(!existeProducto(negocioExistente.get(i),producto)){
					negocioExistente.get(i).agregarProductoPrecio(producto, costo);
				}
				else{
					//System.out.println("El producto: " + producto + " ya existe en el negocio " + negocioExistente.get(i).getNombre());
					
				}
					
					
			}
		}
	}
		
	public static boolean existeProducto(Negocio negocio, String producto){
		boolean existe = false;
		
		if(negocio.getProductoPrecio().containsKey(producto)){
			existe = true;
		}
		
		return existe;
	}
	
	
}
