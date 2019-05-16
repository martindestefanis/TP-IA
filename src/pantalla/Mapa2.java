package pantalla;

import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.MapViewOptions;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.Polyline;
import com.teamdev.jxmaps.PolylineOptions;
import com.teamdev.jxmaps.swing.MapView;
import com.teamdev.jxmaps.Icon;

import grafo.Enlace;
import grafo.Nodo;


import java.util.ArrayList;


public class Mapa2 extends MapView {
	private ArrayList<Nodo> esquinasVisitadas = new ArrayList<Nodo>();
	private ArrayList<Nodo> mundo = new ArrayList<Nodo>();
	private Map map;
	private LatLng[] listaPuntos = new LatLng[esquinasVisitadas.size()];
	private ArrayList<LatLng> lista = new ArrayList<LatLng>();
	private Icon icono = new Icon();
	private GeocoderResult resultOrigen;
	private GeocoderResult resultDestino;
	private GeocoderResult resultNegocio = new GeocoderResult();
	
	private LatLng latitudLongitud;
	private double latitudPromedio;
	private double longitudPromedio;
	private double factorMovimiento;
	
    public Mapa2(MapViewOptions options, final ArrayList<Nodo> esquinasVisitadas, final ArrayList<Nodo> mundo) {
    	super(options);
    	this.esquinasVisitadas=esquinasVisitadas;
    	this.mundo=mundo;
    	
        setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
                if (status == MapStatus.MAP_STATUS_OK) {
                	map = getMap();
                    map.setZoom(17);
                    GeocoderRequest request = new GeocoderRequest(map);
                    for(int i=0; i<esquinasVisitadas.size();i++){
                    	request.setAddress(esquinasVisitadas.get(i).getNombre() + ", Santa Fe, Argentina");
	                    getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
	                        @Override
	                        public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
	                            if (status == GeocoderStatus.OK) {
	                            	
	                              	//CREO LOS MARCADORES PARA LA POSICION 
	                            	Marker marker = new Marker(map);
	                            	marker.setIcon(icono);
	                                marker.setPosition(result[0].getGeometry().getLocation());
	                                marker.setTitle(result[0].getFormattedAddress());
	                                marker.setClickable(true);
	                                final InfoWindow window = new InfoWindow(map);
	 	                            window.setContent(result[0].getFormattedAddress());
	 	                            window.open(map, marker);
	                            	lista.add(result[0].getGeometry().getLocation());
	                                map.setCenter(result[0].getGeometry().getLocation());
	                           
	                                listaPuntos = lista.toArray(listaPuntos);
	                                Polyline linea = new Polyline(map);
	        						linea.setPath(listaPuntos);
	        						linea.setVisible(true);
	        						PolylineOptions opciones = new PolylineOptions();
	                            }
	                        }
	                    });
                    }
                    //marcadoresNegocios();
                   
                }
                
            }
 
        });
        
        
    }
    
    public void marcadoresNegocios(){
    	icono.loadFromFile("..\\TP-IA\\src\\pantalla\\iconos\\Negocio.png");
    	for(int i=0; i<mundo.size(); i++){
			for(int j=0; j<mundo.get(i).getEnlaces().size();j++){
				for(int k=0; k< mundo.get(i).getEnlaces().get(j).getNegocios().size();k++){
					if(!mundo.get(i).getEnlaces().get(j).getNegocios().get(k).isAbierto()){
						icono.loadFromFile("..\\TP-IA\\src\\pantalla\\iconos\\Negocio Cerrado.png");
					}
					System.out.println("ENTRO");
						//resultNegocio.getGeometry().setLocation(calcularLatitudLongitud(mundo.get(i).getEnlaces().get(j),k));
						calcularLatitudLongitud(mundo.get(i).getEnlaces().get(j),k);
						
	                     //window.open(map);
	                	//lista.add(result[0].getGeometry().getLocation());
	                    //map.setCenter(result[0].getGeometry().getLocation());
		        	
		        	/*
		            getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
		                @Override
		                public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
		                    if (status == GeocoderStatus.OK) {
		                    	
		                      	//CREO LOS MARCADORES PARA LA POSICION 
		                    	Marker marker = new Marker(map);
		                    	marker.setIcon(icono);
		                        marker.setPosition(result[0].getGeometry().getLocation());
		                        //marker.setTitle(result[0].getFormattedAddress());
		                        marker.setVisible(true);
		                        final InfoWindow window = new InfoWindow(map);
		                        window.setContent(result[0].getFormattedAddress());
		                        window.open(map, marker);
		                         //window.open(map);
		                    	lista.add(result[0].getGeometry().getLocation());
		                        map.setCenter(result[0].getGeometry().getLocation());
		                   
		                        listaPuntos = lista.toArray(listaPuntos);
		                        Polyline linea = new Polyline(map);
								linea.setPath(listaPuntos);
								linea.setVisible(true);
								PolylineOptions opciones = new PolylineOptions();
								opciones.setStrokeColor("RED");
		                    }
		                }
		            });*/
				}
				resultNegocio.getGeometry().setLocation(latitudLongitud);
	        	System.out.println(resultNegocio.getGeometry().getLocation().toString());
	        	//CREO LOS MARCADORES PARA LA POSICION 
            	Marker marker = new Marker(map);
            	marker.setIcon(icono);
                marker.setPosition(latitudLongitud);
                marker.setTitle("NEGOCIO");
                marker.setVisible(true);
                final InfoWindow window = new InfoWindow(map);
                //window.setContent(request.getAddress());
                window.open(map, marker);
			}
		}
    		
    }
    
    private void calcularLatitudLongitud(Enlace enlace, int factor){

		this.factorMovimiento = factor/10;		
		GeocoderRequest requestOrigen = new GeocoderRequest();
		GeocoderRequest requestDestino = new GeocoderRequest();

		requestOrigen.setAddress(enlace.getNodoOrigen().getNombre() + ", Santa Fe, Argentina");
		System.out.println("REQUEST O: " + requestOrigen.getAddress());
		requestDestino.setAddress(enlace.getNodoDestino().getNombre()+ ", Santa Fe, Argentina");
		System.out.println("REQUEST D: " + requestDestino.getAddress());
		
		getServices().getGeocoder().geocode(requestOrigen, new GeocoderCallback(map){
			@Override
            public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
               if (status == GeocoderStatus.OK) {
                	resultOrigen = new GeocoderResult();
                	resultOrigen = result[0];
                	//System.out.println(resultOrigen.toString());
                	System.out.println("RO: "+ resultOrigen.getGeometry().getLocation());
                	latitudPromedio = resultOrigen.getGeometry().getLocation().getLat() + resultDestino.getGeometry().getLocation().getLat();
            		System.out.println("LAT PROM: " + latitudPromedio);
            		latitudPromedio /= 2;
            		latitudPromedio += factorMovimiento;
            		longitudPromedio = resultOrigen.getGeometry().getLocation().getLng() + resultDestino.getGeometry().getLocation().getLng();
            		longitudPromedio /= 2;
            		longitudPromedio += factorMovimiento;
            		latitudLongitud = new LatLng(latitudPromedio,longitudPromedio);
            		System.out.println("CALCULAR: " +latitudLongitud.toString());
               }else{
            	    System.out.println("FALLA");
               }
            }
		});
		System.out.println("SIGUE");
		getServices().getGeocoder().geocode(requestDestino, new GeocoderCallback(map){
			@Override
            public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
                if (status == GeocoderStatus.OK) {
                	resultDestino = result[0];
                	System.out.println("RD: " + result[0].getGeometry().getLocation());
                	
                }
            }
		});
		System.out.println("SEG");		
    }
    
    
}