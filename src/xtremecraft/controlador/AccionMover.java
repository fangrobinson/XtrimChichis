package xtremecraft.controlador;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import xtremecraft.mapa.Coordenada;
import xtremecraft.partida.Partida;
import xtremecraft.partida.SeleccionadoNoEsPropiedadDelJugadorException;
import xtremecraft.vista.MapaObservable;
import xtremecraft.vista.MensajeDeError;

@SuppressWarnings("serial")
public class AccionMover extends AbstractAction {

	private Coordenada coordenada;
	private MapaObservable mapaVista;
	private Partida partida;
		
	public AccionMover(Partida partida, MapaObservable mapa, Coordenada coordenada){
			
		super("Mover");
		this.mapaVista = mapa;
		this.partida = partida;
		this.coordenada = coordenada;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		try {
			this.partida.validarQueUbicableElegidoEsDelJugador(this.coordenada);
		} catch (SeleccionadoNoEsPropiedadDelJugadorException e) {
			new MensajeDeError("La unidad que se quiere mover no es del jugador");
		}
		
		this.mapaVista.comenzarMovimiento(this.coordenada);
		
	}

}