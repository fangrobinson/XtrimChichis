package xtremecraft.controlador;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Coordenada;
import xtremecraft.mapa.Terreno;
import xtremecraft.partida.Partida;
import xtremecraft.unidades.Unidad;
import xtremecraft.vista.MapaObservable;

@SuppressWarnings("serial")
public class AccionMover extends AbstractAction {

	//private Partida partida;
	private Coordenada coordenada;
	private MapaObservable mapaVista;
		
	public AccionMover(Partida partida, MapaObservable mapa, Coordenada coordenada){
			
		super("Mover");
		//this.partida = partida;
		this.mapaVista = mapa;
		this.coordenada = coordenada;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		this.mapaVista.comenzarMovimiento(this.coordenada);
		
	}
/*
	@Override
	public void update(Observable observado, Object arg1) {
		
		ObservableSeleccionado observadoActual = (ObservableSeleccionado) observado;
		Coordenada coordenadaAMover = observadoActual.getCoordenadaActualSeleccionado();
		
		Mapa mapaReal = this.partida.getMapa();
		
		Terreno terrenoAMover = mapaReal.getCeldaEnFilaColumna(coordenadaAMover.fila(), coordenadaAMover.columna()).getCapaInferior();
		Unidad unidadAMover = (Unidad) mapaReal.getCeldaEnFilaColumna(this.coordenada.fila(), this.coordenada.columna()).getUbicableEnInferior();
		Jugador jugadorTurno = this.partida.quienJuega();
		
		if (unidadAMover.estaEnConstruccion()){
			new MensajeDeError("Esta unidad esta en construccion");
		}
		
		if (!jugadorTurno.esDeMiPropiedad(unidadAMover)){
			new MensajeDeError("La unidad que se quiere mover no es del jugador");
		}
		
		if (!unidadAMover.puedeMoverse()){
			new MensajeDeError("La unidad no puede hacer mas de un movimiento por turno");
		}
		
		try{
			unidadAMover.actualizarUbicacion(terrenoAMover);
			
			try {
			
				this.mapaVista.actualizarVistaEnCoordenada(coordenadaAMover);
				

			} catch (InstantiationException | IllegalAccessException e) {
				new MensajeDeError("Error interno del sistema");
			}
		}catch(UbicacionNoValidaException |NoSePudoOcuparElTerrenoException noPuedeVerElDestino){
			new MensajeDeError("No se puede mover a la locacion seleccionada");
		}
		
	}
*/
}