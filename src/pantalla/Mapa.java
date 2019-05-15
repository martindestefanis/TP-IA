package pantalla;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import frsf.cidisi.exercise.car.search.CarAgentPerception;
import grafo.Enlace;
import grafo.Nodo;

import javax.swing.JFrame;

import com.teamdev.jxmaps.examples.PlacesSearchExample;
import com.teamdev.jxmaps.swing.MapView;
import com.teamdev.jxmaps.*;

public class Mapa extends MapView{
	
	private Map map;
	private LatLng[] listaPuntos;
	private Icon icono = new Icon();
	private ArrayList<Nodo> esquinasVisitadas = new ArrayList<Nodo>();
	private ArrayList<Nodo> mundo = new ArrayList<Nodo>();
	private ArrayList<ArrayList<LatLng>> listaNegocios = new ArrayList<ArrayList<LatLng>>();
	private ArrayList<ArrayList<LatLng>> listaEventos = new ArrayList<ArrayList<LatLng>>();
	
	public Mapa(ArrayList<Nodo> esquinasVisitadas, ArrayList<Nodo> mundo){
		
		this.esquinasVisitadas = esquinasVisitadas;
		this.mundo = mundo;
		
		JFrame frame = new JFrame("Mapa");
	
		listaPuntos = cargarPuntosMapa(esquinasVisitadas);
		listaNegocios = marcadoresNegocios();
		listaEventos = marcadoresEventos();
		
		setOnMapReadyHandler(new MapReadyHandler(){
			public void onMapReady(MapStatus status){
				if(status == MapStatus.MAP_STATUS_OK){
					map = getMap();
					
					MapOptions mapOptions = new MapOptions();
					MapTypeControlOptions controlOptions = new MapTypeControlOptions();
					mapOptions.setMapTypeControlOptions(controlOptions);
					map.setOptions(mapOptions);
					
					map.setCenter(new LatLng(-31.610798, -60.674077));
					map.setZoom(15);
									
					Polyline linea = new Polyline(map);
					linea.setPath(listaPuntos);
					linea.setVisible(true);
					PolylineOptions opciones = new PolylineOptions();
					opciones.setStrokeColor("RED");
					opciones.setStrokeOpacity(0.7);
					linea.setOptions(opciones);
					
					
					//MARCADOR PARA LA POSICION INICIAL
					Marker posicionInicial = new Marker(map);
			    	posicionInicial.setTitle("Posicion Inicial");
			    	posicionInicial.setPosition(listaPuntos[0]);
			    	
			    	//MARCADOR PARA LA POSICION FINAL
			    	Marker posicionFinal = new Marker(map);
					posicionFinal.setTitle("Posicion Final");
					icono.loadFromFile("..\\TP-IA\\src\\pantalla\\iconos\\Posicion Final.png");
					posicionFinal.setIcon(icono);
					Collections.reverse(Arrays.asList(listaPuntos));
					posicionFinal.setPosition(listaPuntos[0]);	
					
					//CARGO MARCADORES DE NEGOCIOS ABIERTOS (POSICION 0 DE LA LISTA DE NEGOCIOS)
					icono.loadFromFile("..\\TP-IA\\src\\pantalla\\iconos\\Negocio.png");
					for(int i=0; i<listaNegocios.get(0).size(); i++){
						Marker marcador = new Marker(map);
						marcador.setPosition(listaNegocios.get(0).get(i));
						marcador.setIcon(icono);
						marcador.setTitle("NEGOCIO ABIERTO");
					}
					
					//CARGO MARCADORES DE NEGOCIOS CERRADOS (POSICION 1 DE LA LISTA DE NEGOCIOS)
					icono.loadFromFile("..\\TP-IA\\src\\pantalla\\iconos\\Negocio Cerrado.png");
					for(int i=0; i<listaNegocios.get(1).size(); i++){
						Marker marcador = new Marker(map);
						marcador.setPosition(listaNegocios.get(1).get(i));
						marcador.setIcon(icono);
						marcador.setTitle("NEGOCIO CERRADO");
					}
					
					//CARGO MARCADORES DE CONGESTION (POSICION O DE LA LISTA DE EVENTOS)
					icono.loadFromFile("..\\TP-IA\\src\\pantalla\\iconos\\Congestion.png");
					for(int i=0; i<listaEventos.get(0).size(); i++){
						Marker marcador = new Marker(map);
						marcador.setPosition(listaEventos.get(0).get(i));
						marcador.setIcon(icono);
						marcador.setTitle("CONGESTION");
					}
					//CARGO MARCADORES DE EVENTO SOCIAL (POSICION 1 DE LA LISTA DE EVENTOS)
					icono.loadFromFile("..\\TP-IA\\src\\pantalla\\iconos\\Evento Social.png");
					for(int i=0; i<listaEventos.get(1).size(); i++){
						Marker marcador = new Marker(map);
						marcador.setPosition(listaEventos.get(1).get(i));
						marcador.setIcon(icono);
						marcador.setTitle("EVENTO SOCIAL");
					}
					//CARGO MARCADORES DE CORTE CALLE (POSICION 2 DE LA LISTA DE EVENTOS)
					icono.loadFromFile("..\\TP-IA\\src\\pantalla\\iconos\\Corte Calle.png");
					for(int i=0; i<listaEventos.get(2).size(); i++){
						Marker marcador = new Marker(map);
						marcador.setPosition(listaEventos.get(2).get(i));
						marcador.setIcon(icono);
						marcador.setTitle("CORTE DE CALLE");
					}
				}
			}
		});
		
		frame.add(this,BorderLayout.CENTER);
		frame.setSize(700, 500);
		frame.setVisible(true);
	}

	public LatLng[] cargarPuntosMapa(ArrayList<Nodo> nodosVisitados){
		LatLng[] posiciones = new LatLng[nodosVisitados.size()];
		
		for(int i=0 ; i<nodosVisitados.size(); i++){
			posiciones[i] = nodosVisitados.get(i).getLatitudLongitud();
		}

		return posiciones;
	}

	public ArrayList<ArrayList<LatLng>> marcadoresNegocios(/*ArrayList<Nodo> mundo*/){
		ArrayList<LatLng> listaNegociosCerrados = new ArrayList<LatLng>();
		ArrayList<LatLng> listaNegociosAbiertos = new ArrayList<LatLng>();
		ArrayList<ArrayList<LatLng>> listaNegocios = new ArrayList<ArrayList<LatLng>>();
		for(int i=0; i<mundo.size(); i++){
			for(int j=0; j<mundo.get(i).getEnlaces().size();j++){
				for(int k=0; k< mundo.get(i).getEnlaces().get(j).getNegocios().size();k++){
					if(!mundo.get(i).getEnlaces().get(j).getNegocios().get(k).isAbierto()){
						listaNegociosCerrados.add(calcularLatitudLongitud(mundo.get(i).getEnlaces().get(j),k));
					}
					else{
						listaNegociosAbiertos.add(calcularLatitudLongitud(mundo.get(i).getEnlaces().get(j),k));
					}
				}
			}
		}
		listaNegocios.add(listaNegociosAbiertos);
		listaNegocios.add(listaNegociosCerrados);
		return listaNegocios;
	}
	
	public ArrayList<ArrayList<LatLng>> marcadoresEventos(){
		ArrayList<LatLng> listaCongestion = new ArrayList<LatLng>();
		ArrayList<LatLng> listaEventoSocial = new ArrayList<LatLng>();
		ArrayList<LatLng> listaCorteCalle = new ArrayList<LatLng>();
		ArrayList<ArrayList<LatLng>> listaEventos = new ArrayList<ArrayList<LatLng>>();
		ArrayList<String> eventos = new ArrayList<String>();
		eventos.add("Congestion");
		eventos.add("Evento Social");
		eventos.add("Corte Calle");
			
		for(int i=0; i<mundo.size(); i++){
			for(int j=0; j<mundo.get(i).getEnlaces().size();j++){
				for(int k=0; k<eventos.size();k++){
						if(eventos.get(k).equalsIgnoreCase("Congestion") && mundo.get(i).getEnlaces().get(j).getEventos().containsKey(eventos.get(k))){
							listaCongestion.add(calcularLatitudLongitud(mundo.get(i).getEnlaces().get(j),2));
						}else{
							if(eventos.get(k).equalsIgnoreCase("Evento Social") && mundo.get(i).getEnlaces().get(j).getEventos().containsKey(eventos.get(k))){
								listaEventoSocial.add(calcularLatitudLongitud(mundo.get(i).getEnlaces().get(j),3));
							}
							else{
								if(eventos.get(k).equalsIgnoreCase("Corte Calle") && mundo.get(i).getEnlaces().get(j).getEventos().containsKey(eventos.get(k))){
									listaCorteCalle.add(calcularLatitudLongitud(mundo.get(i).getEnlaces().get(j),4));
								}
							}
						}
					
    			}
			}
		}
		
		listaEventos.add(listaCongestion);
		listaEventos.add(listaEventoSocial);
		listaEventos.add(listaCorteCalle);
		return listaEventos;
	}
	
	private LatLng calcularLatitudLongitud(Enlace enlace, int factorMovimiento){
		LatLng latitudLongitud;
		LatLng nodoOrigenLatLng = new LatLng(0.0,0.0);
		double latitudPromedio;
		double longitudPromedio;
		double factor = factorMovimiento/8;
		
		for(int i=0; i<mundo.size(); i++){
			for(int j=0; j<mundo.get(i).getEnlaces().size();j++){
				if(mundo.get(i).getEnlaces().get(j).getNodoDestino().getNombre().equalsIgnoreCase(enlace.getNodoOrigen().getNombre())){
					nodoOrigenLatLng = mundo.get(i).getEnlaces().get(j).getNodoDestino().getLatitudLongitud();
				}
			}
			
		}
		latitudPromedio = nodoOrigenLatLng.getLat()+enlace.getNodoDestino().getLatitudLongitud().getLat();
		latitudPromedio = latitudPromedio/2;
		//latitudPromedio = latitudPromedio + factor;
		
		longitudPromedio = nodoOrigenLatLng.getLng()+enlace.getNodoDestino().getLatitudLongitud().getLng();
		longitudPromedio = longitudPromedio/2;
		//longitudPromedio = longitudPromedio + factor;
		
		latitudLongitud = new LatLng(latitudPromedio,longitudPromedio);
		
		return latitudLongitud;
	}

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mapa mapa = new Mapa("IA");
		
	}*/
	
	

}