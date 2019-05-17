package grafo;

import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


import com.csvreader.CsvReader;
import com.teamdev.jxmaps.LatLng;


public class Csv implements Serializable{
	 
	    private String nodoOrigen;
	    private String nodoDestino;
	    private String nombreEvento;
	    private String nombreNegocio;
	    private String producto;
	    private Double precioProducto;
	    private Boolean negocioAbierto;
	    private String esquina1;
	    private String esquina2;
	    private Double costo;
	    
	    private Double latitudOrigen;
	    private Double longitudOrigen;
	    private Double latitudDestino;
	    private Double longitudDestino;
	    
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

	    public Double getCosto() {
			return costo;
		}
		public void setCosto(Double costo) {
			this.costo = costo;
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

		public String getNombreNegocio() {
			return nombreNegocio;
		}
		public void setNombreNegocio(String nombreNegocio) {
			this.nombreNegocio = nombreNegocio;
		}
				
		public String getProducto() {
			return producto;
		}
		public void setProducto(String producto) {
			this.producto = producto;
		}
		public Double getPrecioProducto() {
			return precioProducto;
		}
		public void setPrecioProducto(Double precioProducto) {
			this.precioProducto = precioProducto;
		}
			
		public Boolean getNegocioAbierto() {
			return negocioAbierto;
		}
		public void setNegocioAbierto(boolean negocioAbierto) {
			this.negocioAbierto = negocioAbierto;
		}
					
		public Double getLatitudOrigen() {
			return latitudOrigen;
		}
		public void setLatitudOrigen(Double latitudOrigen) {
			this.latitudOrigen = latitudOrigen;
		}
		public Double getLongitudOrigen() {
			return longitudOrigen;
		}
		public void setLongitudOrigen(Double longitudOrigen) {
			this.longitudOrigen = longitudOrigen;
		}
		public Double getLatitudDestino() {
			return latitudDestino;
		}
		public void setLatitudDestino(Double latitudDestino) {
			this.latitudDestino = latitudDestino;
		}
		public Double getLongitudDestino() {
			return longitudDestino;
		}
		public void setLongitudDestino(Double longitudDestino) {
			this.longitudDestino = longitudDestino;
		}
		public ArrayList<Csv> leerEnlaces(String pathFichero, String delimitador) throws Exception {  
	    
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
	    	   String costoDistancia;
	    	   String latitudOrigen;
	    	   String longitudOrigen;
	    	   String latitudDestino;
	    	   String longitudDestino;
	    	   
	    	   while(cvsReader.readRecord()) {
	    		  // Podemos usar get con el nombre de la cabecera o por posición
	    		   
	    		   //PARA LA LECTURA DE ENLACES SE UTILIZAN ESTAS VARIABLES
	    		   nodoInicial = cvsReader.get("Nodo Origen");
	    		   nodoFinal = cvsReader.get("Nodo Destino");
	    		   costoDistancia = cvsReader.get("Distancia");
	    		   latitudOrigen = cvsReader.get("Latitud Origen");
	    		   longitudOrigen = cvsReader.get("Longitud Origen");
	    		   latitudDestino = cvsReader.get("Latitud Destino");
	    		   longitudDestino = cvsReader.get("Longitud Destino");
	    		   
	    		   //this.latitud = Double.valueOf(latitud);
	    		   //this.longitud = Double.valueOf(longitud);
	    		   
	    		   Csv fila = new Csv();  		      		   	                                
	    		 
	    		   //SETEO DE VARIABLES PARA CUANDO SE LEE EL ARCHIVO ENLACES
	               fila.setNodoOrigen(nodoInicial);
	               fila.setNodoDestino(nodoFinal);
	               fila.setCosto(Double.parseDouble(costoDistancia));
	               fila.setLatitudOrigen(Double.valueOf(latitudOrigen));
	               fila.setLongitudOrigen(Double.valueOf(longitudOrigen));
	               fila.setLatitudDestino(Double.valueOf(latitudDestino));
	               fila.setLongitudDestino(Double.valueOf(longitudDestino));
	               
	               //fila.setLatitudLongitud(new LatLng(this.latitud,this.longitud));
	               
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
		
		public ArrayList<Csv> leerEventos(String pathFichero, String delimitador) throws Exception {  
		    
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
		    	   	    	   
		    	   String evento;
		    	   String esquina1;
		    	   String esquina2;
		    	   String costoEvento;	    	   
		    	   
		    	   while(cvsReader.readRecord()) {
		    		  // Podemos usar get con el nombre de la cabecera o por posición	    		   
		    		   
		    		   //PARA LA LECTURA DE EVENTOS SE UTILIZAN ESTAS VARIABLES
		    		   evento = cvsReader.get("Evento");
		    		   esquina1 = cvsReader.get("Esquina 1");
		    		   esquina2 = cvsReader.get("Esquina 2");
		    		   costoEvento = cvsReader.get("Costo");
		    		   
		    		   Csv fila = new Csv();  		      		   	                                
		               
		               //SETEO DE VARIABLES PARA CUANDO SE LEE EL ARCHIVO EVENTOS
		               fila.setNombreEvento(evento);
		               fila.setEsquina1(esquina1);
		               fila.setEsquina2(esquina2);
		               fila.setCosto(Double.parseDouble(costoEvento));	               
		               
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
		
		public ArrayList<Csv> leerNegocios(String pathFichero, String delimitador) throws Exception {  
		    
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
		    	   
		    	   String nombreNegocio;
		    	   String producto;
		    	   String precioProducto;
		    	   String negocioAbierto;
		    	   String esquina1;
		    	   String esquina2;
		    	   
		    	   while(cvsReader.readRecord()) {
		    		  // Podemos usar get con el nombre de la cabecera o por posición
	    		   
		    		   //PARA LA LECTURA DE NEGOCIOS SE UTILIZAN ESTAS VARIABLES
		    		   nombreNegocio = cvsReader.get("Nombre");
		    		   esquina1 = cvsReader.get("Esquina1");
		    		   esquina2 = cvsReader.get("Esquina2");
		    		   producto = cvsReader.get("Producto");
		    		   precioProducto = cvsReader.get("Precio");
		    		   negocioAbierto = cvsReader.get("Abierto");
		    		   
		    		   Csv fila = new Csv();  		      		   	                                
		               
		               //SETEO DE VARIABLES PARA CUANDO SE LEE EL ARCHIVO NEGOCIOS
		               fila.setNombreNegocio(nombreNegocio);
		               fila.setEsquina1(esquina1);
		               fila.setEsquina2(esquina2);
		               fila.setProducto(producto);
		               fila.setPrecioProducto(Double.parseDouble(precioProducto));
		               fila.setNegocioAbierto(Boolean.parseBoolean(negocioAbierto));
		               
		               
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
