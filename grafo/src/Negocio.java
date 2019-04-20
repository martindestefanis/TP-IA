import java.util.HashMap;


public class Negocio {

	private String nombre;
	private HashMap<String,Double> productoPrecio;
	private Nodo esquina1, esquina2;
	private boolean abierto;
	
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public HashMap<String, Double> getProductoPrecio() {
		return this.productoPrecio;
	}
	
	public double getPrecioProducto(String producto) {
		return this.productoPrecio.get(producto);
	}
	
	
	public void setProductoPrecio(HashMap<String, Double> productoPrecio) {
		this.productoPrecio = productoPrecio;
	}
	
	public Nodo getEsquina1(){
		return this.esquina1;
	}
	
	public void setEsquina1(Nodo esquina){
		this.esquina1 = esquina;
	}
	
	public void setEsquina2(Nodo esquina){
		this.esquina2 = esquina;
	}
	
	public Nodo getEsquina2(){
		return this.esquina2;
	}
	
	public boolean isAbierto() {
		return abierto;
	}

	public void setAbierto(boolean abierto) {
		this.abierto = abierto;
	}

	
	
	
}
