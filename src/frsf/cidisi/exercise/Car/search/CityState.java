package frsf.cidisi.exercise.car.search;

import java.util.ArrayList;

import frsf.cidisi.faia.state.EnvironmentState;
import grafo.GestorNodo;
import grafo.Nodo;

/**
 * This class represents the real world state.
 */
public class CityState extends EnvironmentState {
	
	//TODO: Setup Variables
    private ArrayList<Nodo> mundo = new ArrayList();
    //private Other posicionAgente;
    
	
    public CityState() {
        
    	mundo = GestorNodo.getNodosExistentes();
    	
    	//MUESTRO TODOS LOS DATOS CARGADOS
    	
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
    			for(int j=0; j<mundo.get(i).getEnlaces().get(k).getEventos().size(); j++){
    				System.out.println(mundo.get(i).getEnlaces().get(k).getEventos().get(j).getNombre() +
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
        //TODO: Complete Method
    	/*
			// mundo = initData0;
			// posicionAgente = initData1;
        */
        this.initState();
    }

    /**
     * This method is used to setup the initial real world.
     */
    @Override
    public void initState() {

        //TODO: Complete Method
    }

    /**
     * String representation of the real world state.
     */
    @Override
    public String toString() {
        String str = "";

        //TODO: Complete Method

        return str;
    }

	//TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
	
//     public Other getmundo(){
//        return mundo;
//     }
//     public void setmundo(Other arg){
//        mundo = arg;
//     }
//     public Other getposicionAgente(){
//        return posicionAgente;
//     }
//     public void setposicionAgente(Other arg){
//        posicionAgente = arg;
//     }
	

}

