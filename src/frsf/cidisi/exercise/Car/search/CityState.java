package frsf.cidisi.exercise.car.search;

import java.util.ArrayList;
import java.util.Iterator;

import frsf.cidisi.faia.state.EnvironmentState;
import grafo.Csv;
import grafo.Enlace;
import grafo.Evento;
import grafo.GestorEnlace;
import grafo.GestorNodo;
import grafo.Grafo;
import grafo.Negocio;
import grafo.Nodo;

/**
 * This class represents the real world state.
 */
public class CityState extends EnvironmentState {
	
	//TODO: Setup Variables
    private ArrayList<Nodo> mundo = new ArrayList<Nodo>();
    private Posicion posicionAgente;

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
    	mundo = Grafo.iniciarMundo();
    	
    	//CARGO LAS PERCEPCIONES EN BASE A LOS EVENTOS LEIDOS
    	
    	//MUESTRO TODOS LOS DATOS CARGADOS
    	posicionAgente = new Posicion(null,GestorNodo.obtenerNodo(mundo,"Juan Castelli y Antonia Godoy"));
    	
    	/*System.out.println("\t\t\t\t------- MUNDO DEL AMBIENTE -------");
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
    	
    	System.out.println("\t\t\t\t------- Eventos --------");
    	
    	for(int i=0; i<mundo.size(); i++){
    		for(int k = 0; k<mundo.get(i).getEnlaces().size(); k++){
    			for(String key : mundo.get(i).getEnlaces().get(k).getEventos().keySet()){
    				System.out.println(key +
    		    	"----> Calle: " + mundo.get(i).getEnlaces().get(k).getNombre());
    			}
    		}
    		
    	}
   	
    	System.out.println("\t\t\t\t------- Estados de los enlaces ---------");
    	
    	for(int i=0; i<mundo.size(); i++){
    		for(int j=0; j<mundo.get(i).getEnlaces().size(); j++){
    			System.out.println(mundo.get(i).getEnlaces().get(j).getNombre() + "----> " +
    				String.valueOf(mundo.get(i).getEnlaces().get(j).isDisponible()));
    		}
    	}
    	


    	System.out.println("\t\t\t\t------- MUNDO AGENTE ----------");
    	
    	System.out.println("\t\t\t\t------- Eventos --------");
    	
    	for(int i=0; i<agState.getmundo().size(); i++){
    		for(int k = 0; k<agState.getmundo().get(i).getEnlaces().size(); k++){
    			for(String key : agState.getmundo().get(i).getEnlaces().get(k).getEventos().keySet()){
    				System.out.println(key +
    		    	"----> Calle: " + agState.getmundo().get(i).getEnlaces().get(k).getNombre());
    			}
    		}
    		
    	}*/
    	
    	/*for(int i=0; i<agState.getmundo().size();i++){
    		for(int j=0; j<agState.getmundo().get(i).getEnlaces().size();j++){
    			for(String key : agState.getmundo().get(i).getEnlaces().get(j).getEventos().keySet()){
    				System.out.println(agState.getmundo().get(i).getEnlaces().get(j).getEventos().get(key));
    			}
    		}
    	}*/
    	
    	
    	/*for(int i=0; i<mundo.size(); i++){
    		for(int k = 0; k<mundo.get(i).getEnlaces().size(); k++){
    			mundo.get(i).getEnlaces().get(k).setEvento(CarAgentPerception.EMPTY_PERCEPTION);
    		}
    	}*/
    	
    	//traigo todos los eventos del excel
    /*	ArrayList<Evento> listaEventos = new ArrayList<Evento>();
    	try {
			listaEventos = leerEventos();
			
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		//Coloco cada percepción en el enlace que iría (hay que hacer que las coloque de a una o en cada iteración)
		
		/*for(int i=0; i<mundo.size(); i++){
			for(int k = 0; k<mundo.get(i).getEnlaces().size(); k++){
				for(int z=0; z<listaEventos.size(); z++){
					if(mundo.get(i).getEnlaces().get(k).getNodoOrigen().getNombre().equalsIgnoreCase(listaEventos.get(z).getEnlace().getNodoOrigen().getNombre())
							&& mundo.get(i).getEnlaces().get(k).getNodoDestino().getNombre().equalsIgnoreCase(listaEventos.get(z).getEnlace().getNodoDestino().getNombre()))
					{
						if(mundo.get(i).getEnlaces().get(k).getEvento() == 0){
							
							if(listaEventos.get(z).getNombre().equalsIgnoreCase("Corte calle")){
								mundo.get(i).getEnlaces().get(k).setEvento(CarAgentPerception.CORTE_CALLE);
							}
							else{
								mundo.get(i).getEnlaces().get(k).setEvento(CarAgentPerception.CONGESTION);
							}
						}
						else{
							System.out.println("Ya existe un evento en la calle: " + mundo.get(i).getEnlaces().get(k).getNombre());
						}
					}
				}
			}
			
		}*/
    	
        //TODO: Complete Method


    }
    
    public void cargarPercepciones(ArrayList<Nodo> mundoAmbiente){
    	ArrayList<Evento> listaEventos = new ArrayList<Evento>();
    	listaEventos = Grafo.leerEventos();
    	
    	//SETEO TODOS LOS ENLACES CON PERCEPCIONES EMPTY
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
   						//SI EL ENLACE CONTIENE UNA PERCEPCION DE EMPY LA ELIMINO
   						if(mundoAmbiente.get(j).getEnlaces().get(k).getEventos().containsKey(CarAgentPerception.EMPTY_PERCEPTION)){
   							mundoAmbiente.get(j).getEnlaces().get(k).getEventos().remove(CarAgentPerception.EMPTY_PERCEPTION);
   						
   						}else{
   							//SI EL ENLACE NO CONTIENE YA UN EVENTO DEL MISMO TIPO LO AGREGA. CASO CONTRARIO LO IGNORA Y MUESTRA POR PANTALLA
   							if(!mundoAmbiente.get(j).getEnlaces().get(k).getEventos().containsKey(listaEventos.get(i).getNombre())){
   								mundoAmbiente.get(j).getEnlaces().get(k).setEventos(listaEventos.get(i).getNombre(),listaEventos.get(i).getCosto());
   							}
   							else{
   								//System.out.println("La calle " + mundoAmbiente.get(j).getEnlaces().get(k).getNombre() +
   								//" ya contiene un evento de " + mundoAmbiente.get(j).getEnlaces().get(k).getEventos().get(listaEventos.get(i).getNombre()));
   							}
   							
   						}
   					}
   				}
   			}
   		}
   		
   		
   		/*for(int i=0; i<mundoAmbiente.size(); i++){
			for(int k = 0; k<mundoAmbiente.get(i).getEnlaces().size(); k++){
				for(int z=0; z<listaEventos.size(); z++){
					if(mundoAmbiente.get(i).getEnlaces().get(k).getNodoOrigen().getNombre().equalsIgnoreCase(listaEventos.get(z).getEnlace().getNodoOrigen().getNombre())
							&& mundoAmbiente.get(i).getEnlaces().get(k).getNodoDestino().getNombre().equalsIgnoreCase(listaEventos.get(z).getEnlace().getNodoDestino().getNombre()))
					{
						if(mundoAmbiente.get(i).getEnlaces().get(k).getEvento() == 0){
							
							if(listaEventos.get(z).getNombre().equalsIgnoreCase("Corte calle")){
								mundo.get(i).getEnlaces().get(k).setEvento(CarAgentPerception.CORTE_CALLE);
							}
							else{
								mundo.get(i).getEnlaces().get(k).setEvento(CarAgentPerception.CONGESTION);
							}
						}
						else{
							System.out.println("Ya existe un evento en la calle: " + mundo.get(i).getEnlaces().get(k).getNombre());
						}
					}
				}
			}
			
		}*/
   		
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

	public void setPosicionAgente(Posicion posicionAgente) {
		this.posicionAgente = posicionAgente;
	}

	public void setPosicionAgente(Enlace enlace, Nodo nodoDestino) {
		 this.posicionAgente.setEnlaceRecorrido(enlace);
    	 this.posicionAgente.setNodoActual(nodoDestino);
		
	}
}

