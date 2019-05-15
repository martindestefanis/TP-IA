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

import grafo.Nodo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HelloWorld extends MapView {
	private ArrayList<Nodo> esquinasVisitadas = new ArrayList<Nodo>();
	private ArrayList<Nodo> mundo = new ArrayList<Nodo>();
	private Map map;
    public HelloWorld(MapViewOptions options, final ArrayList<Nodo> esquinasVisitadas, final ArrayList<Nodo> mundo) {
    	super(options);
    	this.esquinasVisitadas=esquinasVisitadas;
    	this.mundo=mundo;
    	final LatLng[] listaPuntos = new LatLng[esquinasVisitadas.size()];
     	
        setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
                if (status == MapStatus.MAP_STATUS_OK) {
                	map = getMap();
                    map.setZoom(5.0);
                    GeocoderRequest request = new GeocoderRequest(map);	
                    for(int i=0; i<esquinasVisitadas.size(); i++){
                     	request.setAddress(esquinasVisitadas.get(i).getNombre() + ",Santa Fe");
                     }	
                    
                    getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
                        @Override
                        public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
                            if (status == GeocoderStatus.OK) {
                            	 
                            	for(int i=0; i<result.length; i++){
                                	listaPuntos[i]= result[i].getGeometry().getLocation();
                                	System.out.println(listaPuntos[i]);
                                }
                            	
                            	Polyline linea = new Polyline(map);
            					linea.setPath(listaPuntos);
            					linea.setVisible(true);
            					PolylineOptions opciones = new PolylineOptions();
            					opciones.setStrokeColor("RED");
            					opciones.setStrokeOpacity(0.7);
            					linea.setOptions(opciones);
            					System.out.println("salio");
            					
                                map.setCenter(result[0].getGeometry().getLocation());
                                Marker marker = new Marker(map);
                                marker.setPosition(result[0].getGeometry().getLocation());

                                final InfoWindow window = new InfoWindow(map);
                                window.setContent("Hello, World!");
                                window.open(map, marker);
                            }
                        }
                    });
                    
                }
                
            }
            
        });
        
        
    }
    
}