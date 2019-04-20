import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;


import com.csvreader.CsvReader;


public class Csv implements Serializable{
	 
	    private String nodoOrigen;
	    private String nodoDestino;
	    private String nombreEvento;
	    private String esquina1;
	    private String esquina2;
	    
	    
	    public String getNodoOrigen() {
	        return nodoOrigen;
	    }
	    public void setNodoOrigen(String nodoOrigen) {
	        this.nodoOrigen = nodoOrigen;
	    }
	    
	    public String getNodoDestino() {
	        return nodoDestino;
	    }
	    public void setNodoDestino(String nodoDestino) {
	        this.nodoDestino = nodoDestino;
	    }
	    
	    
	    
	    public String getNombreEvento() {
			return nombreEvento;
		}
		public void setNombreEvento(String nombreEvento) {
			this.nombreEvento = nombreEvento;
		}
		public String getEsquina1() {
			return esquina1;
		}
		public void setEsquina1(String esquina1) {
			this.esquina1 = esquina1;
		}
		public String getEsquina2() {
			return esquina2;
		}
		public void setEsquina2(String esquina2) {
			this.esquina2 = esquina2;
		}
		public ArrayList<Csv> leer(String pathFichero, String delimitador) throws Exception {  
	    
	       CsvReader cvsReader = null;
	 
	       try {
	    	   File fichero = new File(pathFichero);
	    	   FileReader freader = new FileReader(fichero);        
	    	   cvsReader = new CsvReader(freader,delimitador.charAt(0));       
	    	   String[] headers = null;            
	    	   ArrayList<Csv> listaRegistros = new ArrayList();
	     
	    	   if(cvsReader.readHeaders()) {
	    		   headers = cvsReader.getHeaders(); 
	    	   }            
	    	   	// Leemos los registros
	    	   
	    	   String nodoInicial;
	    	   String nodoFinal;
	    	   String evento;
	    	   String esquina1;
	    	   String esquina2;
	    	   
	    	   while(cvsReader.readRecord()) {
	    		  // Podemos usar get con el nombre de la cabecera o por posición
	    		   
	    		   //PARA LA LECTURA DE ENLACES SE UTILIZAN ESTAS VARIABLES
	    		   nodoInicial = cvsReader.get(headers[0]);
	    		   nodoFinal = cvsReader.get(headers[1]);
	    		   
	    		   //PARA LA LECTURA DE EVENTOS SE UTILIZAN ESTAS VARIABLES
	    		   evento = cvsReader.get(headers[0]);
	    		   esquina1 = cvsReader.get(headers[1]);
	    		   esquina2 = cvsReader.get(headers[2]);
	    		   
	    		   Csv fila = new Csv();  		      		   	                                
	    		 
	    		   //SETEO DE VARIABLES PARA CUANDO SE LEE EL ARCHIVO ENLACES
	               fila.setNodoOrigen(nodoInicial);
	               fila.setNodoDestino(nodoFinal);
	               
	               //SETEO DE VARIABLES PARA CUANDO SE LEE EL ARCHIVO EVENTOS
	               fila.setNombreEvento(evento);
	               fila.setEsquina1(esquina1);
	               fila.setEsquina2(esquina2);
	               
	               
	               listaRegistros.add(fila);
	         
	    	   }            
	  
	        return listaRegistros;
	      
	    } catch(Exception e) {
	            throw e;
	    }  finally {
	        if(cvsReader!=null) {
	            cvsReader.close();
	        }
	    }
	  }
}
