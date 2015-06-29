package xtremecraft.vista;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.JPanel;

import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.partida.Identificable;
import xtremecraft.partida.Partida;

@SuppressWarnings("serial")
public class MapaObservableAereo extends JPanel{
	
	private Mapa modeloReal;
	private HashMap<Class<?>, Class<?>> vistas;
	
	public MapaObservableAereo(){};
	
	public MapaObservableAereo(Partida partida, HashMap<Class<?>, Class<?>> vistas) throws InstantiationException, IllegalAccessException{
		
		//this.addMouseListener(this);
		Mapa mapa = partida.getMapa();
		this.modeloReal = mapa;
		this.vistas = vistas;
		setBounds(mapa.ancho(), mapa.alto(), 800, 800);
		this.setLayout(new GridLayout(mapa.ancho(), mapa.alto()));
		
				
		TreeMap<Integer, TreeMap<Integer, Celda>> mapaIterable = this.modeloReal.devolverMapaEstatico();
		for (int i = 0; i < this.modeloReal.ancho(); i++){
			for (int j = 0; j < this.modeloReal.alto(); j++){
				Celda celda = mapaIterable.get(i).get(j);
				Terreno terrenoSuperior = celda.getCapaSuperior();
				Class<?> vistaClase = null;
				Vista vistaNueva = null;
				
				if(terrenoSuperior.estaOcupado()){
					Identificable identificable = (Identificable)terrenoSuperior.getUbicableEnTerreno();
					int numero =identificable.getJugador().getNumeroDeJugador();
					vistaClase = this.vistas.get(terrenoSuperior.getUbicableEnTerreno().getClass());
					IdentificableVisible identificableVisible = (IdentificableVisible) vistaClase.newInstance();
					identificableVisible.setJugador(numero);
					vistaNueva = (Vista) identificableVisible;
				}else{
					vistaClase = this.vistas.get(terrenoSuperior.getClass());
					vistaNueva = (Vista) vistaClase.newInstance();
				}

				vistaNueva.setCoordenada(terrenoSuperior.getCoordenada());

				vistaNueva.paintComponents(getGraphics());
				add(vistaNueva);
				
			}
		}
		
	}
	
}

