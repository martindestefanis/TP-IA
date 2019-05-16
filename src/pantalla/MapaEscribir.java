/*package pantalla;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import grafo.Csv;
import grafo.Grafo;
import grafo.Negocio;
import grafo.Nodo;

import java.util.ArrayList;

import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapViewOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.Polyline;
import com.teamdev.jxmaps.PolylineOptions;
import com.teamdev.jxmaps.swing.MapView;



public class MapaEscribir extends MapView {

	private Map map;
	private ArrayList<Negocio> registrosLeidos;
	private ArrayList<Negocio> listaEscribir = new ArrayList<Negocio>();
	private Negocio elemento = new Negocio();
	private LatLng latitudLongitud1;
	private LatLng latitudLongitud2;
	
	public MapaEscribir(MapViewOptions options) {
    	super(options);
    	Grafo grafo = new Grafo();
    	
    	registrosLeidos = grafo.leerNegocios();
        setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
                if (status == MapStatus.MAP_STATUS_OK) {
                	map = getMap();
                    map.setZoom(17);
                    GeocoderRequest request = new GeocoderRequest(map);
                    GeocoderRequest request2 = new GeocoderRequest(map);
                    for(int i=0; i<registrosLeidos.size();i++){
                    	elemento = new Negocio();
                    	elemento = registrosLeidos.get(i);
                    	
                    	request.setAddress(registrosLeidos.get(i).getEsquina1().getNombre() + ", Santa Fe, Argentina");
                    	//request2.setAddress(registrosLeidos.get(i).getEsquina2().getNombre() + ", Santa Fe, Argentina");
                    	getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
	                        @Override
	                        public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
	                            if (status == GeocoderStatus.OK) {
	                            	latitudLongitud1 = result[0].getGeometry().getLocation();
	                            	//System.out.println("D: " +result[1].getGeometry().getLocation());
	                            	elemento.getEsquina1().setLatitudLongitud(latitudLongitud1);
	                            	elemento.getEsquina2().setLatitudLongitud(latitudLongitud2);
	                            	
	                            	if(!listaEscribir.contains(elemento)){
	                            		listaEscribir.add(elemento);
	                            	}
	                            	System.out.println("Origen: " + listaEscribir.toString());
	                            }else{
	                            	System.out.println("F1");
	                            }
	                        }
	                    });
	                    
	                    request2.setAddress(registrosLeidos.get(i).getEsquina2().getNombre() + ", Santa Fe, Argentina");
	                    getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
	                        @Override
	                        public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
	                            if (status == GeocoderStatus.OK) {
	                            	latitudLongitud2 = result[0].getGeometry().getLocation();
	                            	System.out.println("De: " +latitudLongitud2);
	                            	elemento.getEsquina2().setLatitudLongitud(latitudLongitud2);
	                            	elemento.getEsquina1().setLatitudLongitud(latitudLongitud1);
	                            	if(!listaEscribir.contains(elemento)){
	                            		listaEscribir.add(elemento);
	                            	}
	                            	System.out.println("Destino: " + listaEscribir.toString());
	                            }else{
	                            	System.out.println("F2");
	                            }
	                        }
	                    });
	                    
                    }
                   
                }
                
            }
 
        });
        System.out.println(registrosLeidos.get(0).getEsquina1().getLatitudLongitud());
        
    }
	
	 public static void main(String[] args){
		 MapViewOptions options = new MapViewOptions();
	        options.importPlaces();
	        options.setApiKey("AIzaSyDXeR9Z3IqVz25_JKRdKjT7tLKXttLgnj4");
		 MapaEscribir mapa = new MapaEscribir(options);
		 
	 }
}*/
