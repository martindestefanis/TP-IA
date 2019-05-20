package frsf.cidisi.exercise.car.search;

import java.util.ArrayList;

import frsf.cidisi.faia.state.EnvironmentState;
import grafo.Enlace;
import grafo.Evento;
import grafo.GestorNodo;
import grafo.Grafo;
import grafo.Nodo;

/**
 * This class represents the real world state.
 */
public class CityState extends EnvironmentState {
	
	//TODO: Setup Variables
    private ArrayList<Nodo> mundo = new ArrayList<Nodo>();
    private Posicion posicionAgente;
    static Grafo grafo = new Grafo();
	private CarAgentState agState = new CarAgentState();
    
	
    public CityState() {
        this.initState();
    }
    
    public CityState clone() {
    	CityState aux = new CityState();
    	aux.mundo = (ArrayList<Nodo>) mundo.clone();
    	aux.posicionAgente = posicionAgente.Clone();
        return aux;
    }

    /**
     * This method is used to setup the initial real world.
     */
    @Override
    public void initState() {
    	
    	//INICIO EL MUNDO DEL AMBIENTE CON LAS CALLES Y NEGOCIOS
    	mundo = grafo.iniciarMundo();
    	
    	//CARGO LAS PERCEPCIONES EN BASE A LOS EVENTOS LEIDOS
    	cargarPercepciones(mundo);
    	    	
    	//SETEO LA POSICION ACTUAL DEL AGENTE EN EL ESTADO DEL AMBIENTE
    	posicionAgente = new Posicion(null,GestorNodo.obtenerNodo(mundo,"Juan Castelli y Antonia Godoy"));
    	
    	//REALIZO UNA MUESTRA DE TODOS LOS DATOS DEL MUNDO DEL AMBIENTE Y DEL AGENTE
  /*  	System.out.println("\t\t\t\t------- MUNDO DEL AMBIENTE -------");
    	System.out.println("\t\t\t\t------- Nodos y sus enlaces -------");
    	for(int i=0; i< mundo.size(); i++){
	    	   for(int j=0; j<mundo.get(i).getEnlaces().size(); j++){
	    		   System.out.println(mundo.get(i).getNombre() + " ---> " + mundo.get(i).getEnlaces().get(j).getNombre());
	    	   }
	    	   System.out.println("FINALIZAN LOS ENLACES DEL NODO " + mundo.get(i).getNombre());
	       }
    	
    	System.out.println("\t\t\t\t------- Negocios --------");
    	for(int i=0; i<mundo.size(); i++){
    		for(int k = 0; k<mundo.get(i).getEnlaces().size(); k++){
    			for(int j=0; j<mundo.get(i).getEnlaces().get(k).getNegocios().size(); j++){
    				System.out.println(mundo.get(i).getEnlaces().get(k).getNegocios().get(j).getNombre() +
    		    	"----> Calle: " + mundo.get(i).getEnlaces().get(k).getNombre());
    			}
    		}
    		
    	}
    	
    	System.out.println("\t\t\t\t------ Negocio y productos -------");
    	for(int i=0; i<mundo.size(); i++){
    		for(int j=0; j<mundo.get(i).getEnlaces().size(); j++){
    			for(int k=0; k<mundo.get(i).getEnlaces().get(j).getNegocios().size(); k++){
    				System.out.println("Negocio: " + mundo.get(i).getEnlaces().get(j).getNegocios().get(k).getNombre() + 
    					" Producto: " + mundo.get(i).getEnlaces().get(j).getNegocios().get(k).getProductoPrecio().toString());
    				
    			}
    		}
    	}
 */   	
    	System.out.println("\t\t\t\t------- Eventos --------");
    	
    	for(int i=0; i<mundo.size(); i++){
    		for(int k = 0; k<mundo.get(i).getEnlaces().size(); k++){
    			for(String key : mundo.get(i).getEnlaces().get(k).getEventos().keySet()){
    				System.out.println(key +
    		    	"----> Calle: " + mundo.get(i).getEnlaces().get(k).getNombre());
    			}
    		}
    		
    	}
  /* 	
    	System.out.println("\t\t\t\t------- Estados de los enlaces ---------");
    	
    	for(int i=0; i<mundo.size(); i++){
    		for(int j=0; j<mundo.get(i).getEnlaces().size(); j++){
    			System.out.println(mundo.get(i).getEnlaces().get(j).getNombre() + "----> " + 
    				String.valueOf(mundo.get(i).getEnlaces().get(j).isDisponible()));
    		}
    	}
   */ 	
    

    	System.out.println("\t\t\t\t------- MUNDO AGENTE INICIAL ----------");
    	
    	System.out.println("\t\t\t\t------- Eventos --------");
    	
    	for(int i=0; i<agState.getmundo().size(); i++){
    		for(int k = 0; k<agState.getmundo().get(i).getEnlaces().size(); k++){
    			for(String key : agState.getmundo().get(i).getEnlaces().get(k).getEventos().keySet()){
        			if(!key.equalsIgnoreCase("Sin percepcion")){
        				System.out.println(key +
        	        		    "----> Calle: " + agState.getmundo().get(i).getEnlaces().get(k).getNombre());
        			}
    				
        		}
    		}
    		
    	}
    	
    }
    
    public void cargarPercepciones(ArrayList<Nodo> mundoAmbiente){
    	ArrayList<Evento> listaEventos = new ArrayList<Evento>();
    	listaEventos = grafo.leerEventos();
    	
    	//SETEO TODOS LOS ENLACES CON PERCEPCIONES EMPTY Y COSTO 0
   		for(int i=0; i<mundoAmbiente.size(); i++){
			for(int j=0; j<mundoAmbiente.get(i).getEnlaces().size(); j++){
				mundoAmbiente.get(i).getEnlaces().get(j).setEventos(CarAgentPerception.EMPTY_PERCEPTION,0);
			}
		}
   		
   		for(int i=0; i<listaEventos.size(); i++){
   			for(int j=0; j<mundoAmbiente.size(); j++){
   				for(int k=0; k<mundoAmbiente.get(j).getEnlaces().size(); k++){
   					//ENCUENTRO EL ENLACE AL QUE LE TENGO QUE SETEAR LA PERCEPCION DE EVENTO
   					if(mundoAmbiente.get(j).getEnlaces().get(k).getNodoOrigen().getNombre().equalsIgnoreCase(listaEventos.get(i).getEnlace().getNodoOrigen().getNombre())
   						&& mundoAmbiente.get(j).getEnlaces().get(k).getNodoDestino().getNombre().equalsIgnoreCase(listaEventos.get(i).getEnlace().getNodoDestino().getNombre())){
   						//SI EL ENLACE CONTIENE UNA PERCEPCION DE EMPTY LA ELIMINO
   						if(mundoAmbiente.get(j).getEnlaces().get(k).getEventos().containsKey(CarAgentPerception.EMPTY_PERCEPTION)){
   							mundoAmbiente.get(j).getEnlaces().get(k).getEventos().remove(CarAgentPerception.EMPTY_PERCEPTION);
   						
   						}
   						//SI EL ENLACE NO CONTIENE YA UN EVENTO DEL MISMO TIPO LO AGREGA. CASO CONTRARIO LO IGNORA Y MUESTRA POR PANTALLA
   						if(!mundoAmbiente.get(j).getEnlaces().get(k).getEventos().containsKey(listaEventos.get(i).getNombre())){
   							//SI SE TRATA DE UN EVENTO DE CORTE DE CALLE SE CAMBIA LA DISPONIBILIDAD DEL ENLACE
   							if(listaEventos.get(i).getNombre().equalsIgnoreCase(CarAgentPerception.CORTE_CALLE)){
   								mundoAmbiente.get(j).getEnlaces().get(k).setDisponible(false);
   							}
   							mundoAmbiente.get(j).getEnlaces().get(k).setEventos(listaEventos.get(i).getNombre(),listaEventos.get(i).getCosto());
   							
   						}
   						else{
   							System.out.println("La calle " + mundoAmbiente.get(j).getEnlaces().get(k).getNombre() +
   							" ya contiene un evento de " + mundoAmbiente.get(j).getEnlaces().get(k).getEventos().get(listaEventos.get(i).getNombre()));
   						
   						}
   					}
   				}
   			}
   		}
	
    }

    @Override
	public String toString() {
		return "CityState [agState=" + agState + ", mundo=" + mundo
				+ ", posicionAgente=" + posicionAgente
				+ ", getPosicionAgente()=" + getPosicionAgente() + "]";
	}
    
    public Posicion getPosicionAgente() {
		return posicionAgente;
	}
    
    public ArrayList<Enlace> getEnlaces(){
    	ArrayList<Enlace> listaEnlace = new ArrayList<Enlace>();
    	for(int i =0; i<mundo.size();i++){
    		for(int j=0; j<mundo.get(i).getEnlaces().size();j++){
    			listaEnlace.add(mundo.get(i).getEnlaces().get(j));
    		}
    	}
    	return listaEnlace;
    }

	public void setPosicionAgente(Posicion posicionAgente) {
		this.posicionAgente = posicionAgente;
	}

	public void setPosicionAgente(Enlace enlace, Nodo nodoDestino) {
		 this.posicionAgente.setEnlaceRecorrido(enlace);
    	 this.posicionAgente.setNodoActual(nodoDestino);
		
	}
	public ArrayList<Nodo> getMundo(){
		return this.mundo;
	}
	
}

