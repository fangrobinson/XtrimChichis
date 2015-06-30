package xtremecraft.controlador;

import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;

import xtremecraft.mapa.Coordenada;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.partida.ElAtacanteNoEsDelJugadorException;
import xtremecraft.partida.Jugador;
import xtremecraft.partida.Partida;
import xtremecraft.unidades.UbicacionNoValidaException;
import xtremecraft.unidades.Unidad;
import xtremecraft.vista.MapaObservable;
import xtremecraft.vista.MensajeDeError;
import xtremecraft.vista.ObservableSeleccionado;

@SuppressWarnings("serial")
public class AccionMover extends AbstractAction implements Observer{

	private Partida partida;
	private Coordenada coordenada;
	private MapaObservable mapaVista;
		
	public AccionMover(Partida partida, MapaObservable mapa, Coordenada coordenada){
			
		super("Mover");
		this.coordenada = coordenada;
		this.partida = partida;
		this.mapaVista = mapa;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		this.mapaVista.agregarObservadorAVistas(this);
		
	}

	@Override
	public void update(Observable observado, Object arg1) {
		
		ObservableSeleccionado observadoActual = (ObservableSeleccionado) observado;
		Coordenada coordenadaAMover = observadoActual.getCoordenadaActualSeleccionado();
		
		Mapa mapaReal = this.partida.getMapa();
		
		Terreno terrenoAMover = mapaReal.getCeldaEnFilaColumna(coordenadaAMover.fila(), coordenadaAMover.columna()).getCapaInferior();
		Unidad unidadAMover = (Unidad) mapaReal.getCeldaEnFilaColumna(this.coordenada.fila(), this.coordenada.columna()).getUbicableEnInferior();
		Jugador jugadorTurno = this.partida.quienJuega();
		
		if (!jugadorTurno.esDeMiPropiedad(unidadAMover)){
			new MensajeDeError("La unidad que se quiere mover no es del jugador");
		}
		
		try{
			unidadAMover.actualizarUbicacion(terrenoAMover);
		}catch(UbicacionNoValidaException noSePuedeCastear){
			new MensajeDeError("No se puede mover a la locacion seleccionada");
		}catch(ElAtacanteNoEsDelJugadorException elAtacanteNoEsPropio){
			new MensajeDeError("No se selecciono a un agresor propio");
		}
		
		
	}

}