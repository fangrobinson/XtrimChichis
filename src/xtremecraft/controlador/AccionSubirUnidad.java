package xtremecraft.controlador;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import xtremecraft.mapa.Coordenada;
import xtremecraft.partida.Partida;
import xtremecraft.vista.MapaObservable;
import xtremecraft.vista.SectorAccionesDisponibles;

@SuppressWarnings("serial")
public class AccionSubirUnidad extends AbstractAction{
	
	private Partida partida;
	private Coordenada coordenada;
	private MapaObservable mapaVista;
	private SectorAccionesDisponibles sector;
	
	public AccionSubirUnidad(Partida partida, MapaObservable mapa, Coordenada coordenada, SectorAccionesDisponibles sector){
			
		super("SubirUnidad");
		this.partida = partida;
		this.coordenada = coordenada;
		this.mapaVista = mapa;
		this.coordenada = coordenada;
		this.sector = sector;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
