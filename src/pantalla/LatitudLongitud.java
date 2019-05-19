package pantalla;

import grafo.Csv;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

public class LatitudLongitud{
	
	public void escribirCoordenadas(){
		String path = "..\\TP-IA\\src\\grafo\\termina.csv";
		String delimitador = ";";
		
		String nodoOrigenLeido;
		String nodoDestinoLeido;
		
		String latitud;
		String longitud;
		ArrayList<String> latitudLongitud = new ArrayList<String>();
		
		LatLng latLng;
		
		double lat;
		double lng;

		String[] nombreNodoSeparado;
		String nombreNodoUrl;
		String url;
		String key = "AIzaSyDXeR9Z3IqVz25_JKRdKjT7tLKXttLgnj4";
		
		try{
			ArrayList<Csv> registrosLeidos = leerNodos(path, delimitador);
			System.out.println("LEYO");
			for(int i=0; i<registrosLeidos.size();i++){
				
				//LATITUD Y LONGITUD NODO ORIGEN
				nodoOrigenLeido = registrosLeidos.get(i).getNodoOrigen();
				nombreNodoSeparado = nodoOrigenLeido.split(" ");
				nombreNodoUrl = nombreNodoSeparado[0];
				for(int j=1; j<nombreNodoSeparado.length;j++){
					nombreNodoUrl = nombreNodoUrl + "+" + nombreNodoSeparado[j];
					
				}
				url = "https://maps.googleapis.com/maps/api/geocode/json?address=" +
					nombreNodoUrl + ",+Santa+Fe,+Argentina&key=" + key;
				latitudLongitud = getCoordenadasDeEstaDireccion(url);
				lat = Double.valueOf(latitudLongitud.get(0));
				lng = Double.valueOf(latitudLongitud.get(1));
				registrosLeidos.get(i).setLatitudOrigen(lat);
				registrosLeidos.get(i).setLongitudOrigen(lng);
				System.out.println("NODO ORIGEN: " + lat);
				System.out.println("NODO ORIGEN: " + lng);
				
				//LATITUD Y LONGITUD NODO DESTINO
				nodoDestinoLeido = registrosLeidos.get(i).getNodoDestino();
				nombreNodoSeparado = nodoDestinoLeido.split(" ");
				nombreNodoUrl = nombreNodoSeparado[0];
				for(int j=1; j<nombreNodoSeparado.length;j++){
					nombreNodoUrl = nombreNodoUrl + "+" + nombreNodoSeparado[j];
					
				}
				url = "https://maps.googleapis.com/maps/api/geocode/json?address=" +
					nombreNodoUrl + ",+Santa+Fe,+Argentina&key=" + key;
				latitudLongitud = getCoordenadasDeEstaDireccion(url);
				lat = Double.valueOf(latitudLongitud.get(0));
				lng = Double.valueOf(latitudLongitud.get(1));
				registrosLeidos.get(i).setLatitudDestino(lat);
				registrosLeidos.get(i).setLongitudDestino(lng);
				System.out.println("NODO DESTINO: " + lat);
				System.out.println("NODO DESTINO: " + lng);
			}//FIN DEL PIMER FOR
			
			   FileWriter fwriter = new FileWriter(path);
		        
		        // Creamos la clase que nos permite escribir en el fichero CSV.
		        CsvWriter writercsv = new CsvWriter(fwriter,delimitador.charAt(0));
		        
		        // Escribimos las cabeceras.
		        writercsv.write("Nodo Origen");
		        writercsv.write("Nodo Destino");
		        writercsv.write("Distancia");
		        writercsv.write("Latitud Origen");
		        writercsv.write("Longitud Origen");
		        writercsv.write("Latitud Destino");
		        writercsv.write("Longitud Destino");
		        writercsv.endRecord();
		        for(int i=0; i<registrosLeidos.size(); i++){
		        	writercsv.write(registrosLeidos.get(i).getNodoOrigen());
		        	writercsv.write(registrosLeidos.get(i).getNodoDestino());
		        	writercsv.write(String.valueOf(registrosLeidos.get(i).getCosto()));
		        	
		        	writercsv.write(String.valueOf(registrosLeidos.get(i).getLatitudOrigen()));
		        	writercsv.write(String.valueOf(registrosLeidos.get(i).getLongitudOrigen()));
		        	
		        	writercsv.write(String.valueOf(registrosLeidos.get(i).getLatitudDestino()));
		        	writercsv.write(String.valueOf(registrosLeidos.get(i).getLongitudDestino()));
			        
			        writercsv.endRecord();
			        
		        }
		        writercsv.close();
		}catch(Exception e) {
	            System.out.println("Fallo leer archivo enlaces");
	    }
		
		
	}
	
	private ArrayList<Csv> leerNodos(String path, String delimitador){
		CsvReader cvsReader = null;
		ArrayList<Csv> listaRegistros = new ArrayList<Csv>();
	    
		try {
	    	   File fichero = new File(path);
	    	   FileReader freader = new FileReader(fichero);        
	    	   cvsReader = new CsvReader(freader,delimitador.charAt(0));       
	    	   String[] headers = null;            
	    	   
	     
	    	   if(cvsReader.readHeaders()) {
	    		   headers = cvsReader.getHeaders(); 
	    	   }            
	    	   	// Leemos los registros
	    	   
	    	   String nodoInicial;
	    	   String nodoFinal;
	    	   String distancia;
	    	   
	    	   while(cvsReader.readRecord()) {
	    		  // Podemos usar get con el nombre de la cabecera o por posición
	    		   
	    		   //PARA LA LECTURA DE ENLACES SE UTILIZAN ESTAS VARIABLES
	    		   nodoInicial = cvsReader.get("Nodo Origen");
	    		   nodoFinal = cvsReader.get("Nodo Destino");
	    		   distancia = cvsReader.get("Distancia");
	    		   
	    		   Csv fila = new Csv();  		      		   	                                
	    		 
	    		   //SETEO DE VARIABLES PARA CUANDO SE LEE EL ARCHIVO ENLACES
	               fila.setNodoOrigen(nodoInicial);
	               fila.setNodoDestino(nodoFinal);
	               fila.setCosto(Double.parseDouble(distancia));
	               
	               listaRegistros.add(fila);
	         
	    	   }            
	  
	        return listaRegistros;
	      
	    } catch(Exception e) {
	            System.out.println("Falla la lectura de nodos");
	    }  finally {
	        if(cvsReader!=null) {
	            cvsReader.close();
	        }
	    }
		return listaRegistros;
	}

    public ArrayList<String> getCoordenadasDeEstaDireccion(String urlToRead) {
    	
    	ArrayList<String> coordenadas = new ArrayList<String>();
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        String result = "";
        try {
            url = new URL(urlToRead);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result += line;
            }
            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //En el result viene los datos con una estructura llamada JSON (que es eso de las llaves { y dentro propiedades:valores, etc...
        //aqui abajo voy navegando por el objeto result y transformandolo hasta que llego a la "location" y ahi leo las propiedades lat y lng
        HashMap properties = new Gson().fromJson(result, HashMap.class);
        List resultados = (List) properties.get("results");
        LinkedTreeMap informacion = (LinkedTreeMap) resultados.get(0); //solo debe venir un elemento y estara en la posicion 0
        LinkedTreeMap geometryInfo = (LinkedTreeMap) informacion.get("geometry");
        LinkedTreeMap locationInfo = (LinkedTreeMap) geometryInfo.get("location");
        coordenadas.add(String.valueOf(locationInfo.get("lat")));
        coordenadas.add(String.valueOf(locationInfo.get("lng")));
        
        System.out.println("La latitud es: " + locationInfo.get("lat")); //LAtitud
        //double numero = Double.valueOf((String)locationInfo.get("lat"));
        //System.out.println(numero);
        System.out.println("la longitud es: " +locationInfo.get("lng")); //longitud
        //return "La latitud es: " + locationInfo.get("lat")+ ", "+"la longitud es: " +locationInfo.get("lng");
        return coordenadas;
    }



    public static void main(String args[])
    {
        LatitudLongitud latLng = new LatitudLongitud();
        latLng.escribirCoordenadas();
        //String direccion = "https://maps.googleapis.com/maps/api/geocode/json?address=Juan+Castelli+y+Tacuari,+Santa+Fe,+Argentina&key=AIzaSyDXeR9Z3IqVz25_JKRdKjT7tLKXttLgnj4";
        //String direccion =  "http://maps.googleapis.com/maps/api/geocode/json?address=Santa+Fe&key=AIzaSyDXeR9Z3IqVz25_JKRdKjT7tLKXttLgnj4";
        //System.out.println(latLng.getCoordenadasDeEstaDireccion(direccion));
    }
}
