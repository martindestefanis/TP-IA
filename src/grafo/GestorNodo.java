package grafo;


import java.util.ArrayList;

public class GestorNodo {

	private ArrayList<Nodo> nodosExistentes = new ArrayList<Nodo>();
	
	public ArrayList<Nodo> getNodosExistentes() {
		return nodosExistentes;
		
	}

	public void setNodosExistentes(ArrayList<Nodo> existentes) {
		nodosExistentes = existentes;
	}
	
	public static Nodo obtenerNodo(ArrayList<Nodo> mundo, String nombreNodo){
		Nodo nodo = new Nodo();
		for(int i=0; i< mundo.size(); i++){
			if(mundo.get(i).getNombre().equalsIgnoreCase(nombreNodo)){
				nodo = mundo.get(i);
				}
	       }
		
		return nodo;
	}

	public void agregarNodo(Nodo nuevoNodo){
		//AGREGAR CODIGO PARA QUE ESCRIBA EN EL CSV
		this.getNodosExistentes().add(nuevoNodo);
		
	}
	
	public static Nodo crearNodo(String nombreNodo){
		Nodo nodo = new Nodo();
		nodo.setNombre(nombreNodo);
		return nodo;
	}
	
	public boolean existeNodo(Nodo nodo){
		boolean existe = false;
		
		for(int i=0; i < nodosExistentes.size(); i++){
			if(nodosExistentes.get(i).getNombre().equalsIgnoreCase(nodo.getNombre())){
				existe = true;
				i = nodosExistentes.size();
			}
		}
		
		return existe;
	}
	
	public void agregarEnlaceANodo(Nodo nodo, Enlace enlace){
		for(int i = 0; i < this.getNodosExistentes().size(); i++){
			if(this.getNodosExistentes().get(i).getNombre().equalsIgnoreCase(nodo.getNombre())){
				this.getNodosExistentes().get(i).getEnlaces().add(enlace);
				i = this.getNodosExistentes().size();
			}
		}
		
	}
	public void agregarNegocio(Enlace enlace, Negocio negocio){
		for(int i=0; i<this.getNodosExistentes().size();i++){
			if(this.getNodosExistentes().get(i).getNombre().equalsIgnoreCase(enlace.getNodoOrigen().getNombre())){
				for(int j=0; j<this.getNodosExistentes().get(i).getEnlaces().size(); j++){
					if(this.getNodosExistentes().get(i).getEnlaces().get(j).getNombre().equalsIgnoreCase(enlace.getNombre())){
						
						this.getNodosExistentes().get(i).getEnlaces().get(j).setNegocios(negocio);
						i = this.getNodosExistentes().size();
						break;
					}
				}
			}
		}
	}
	
	public void agregarProducto(Enlace enlace, Negocio negocio, String producto, double precio){
		//RECORRO LISTA DE NODOS EXISTENTES
		for(int i=0; i< this.getNodosExistentes().size(); i++){
			//System.out.println(this.getNodosExistentes().get(i).getNombre());
			//POR CADA NODO RECORRO SU LISTA DE ENLACES
			for(int j=0; j< this.getNodosExistentes().get(i).getEnlaces().size(); j++){
				//SI EL ENLACE ES IGUAL AL ENLACE EN EL QUE TENGO QUE AGREGAR EL PRODUCTO RECORRO SU LISTA DE NEGOCIOS
				if(this.getNodosExistentes().get(i).getEnlaces().get(j).getNombre().equalsIgnoreCase(enlace.getNombre())){
					for(int k=0; k<this.getNodosExistentes().get(i).getEnlaces().get(j).getNegocios().size(); k++){
						//BUSCO EL NEGOCIO EN EL ENLACE SELECCIONADO
						if(this.getNodosExistentes().get(i).getEnlaces().get(j).getNegocios().get(k).getNombre().equalsIgnoreCase(negocio.getNombre())){
							//SI EL NEGOCIO NO TIENE EL PRODUCTO YA AGREGADO SE LO AGREGO. CASO CONTRARIO LO IGNORO Y MUESTRO POR PANTALLA
							if(!this.getNodosExistentes().get(i).getEnlaces().get(j).getNegocios().get(k).getProductoPrecio().containsKey(producto)){
								this.getNodosExistentes().get(i).getEnlaces().get(j).getNegocios().get(k).agregarProductoPrecio(producto, precio);
							}
							else{
								//System.out.println("El negocio: " + negocio.getNombre() + " en la calle " + enlace.getNombre()
									//	+ " ya contiene el producto " + producto);
							}
							
						}
					}
				}
				
			}
		}
	}
}
