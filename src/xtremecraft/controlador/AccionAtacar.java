package xtremecraft.controlador;

import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;

import xtremecraft.mapa.Coordenada;
import xtremecraft.mapa.Mapa;
import xtremecraft.partida.ElAtacanteNoEsDelJugadorException;
import xtremecraft.partida.Jugador;
import xtremecraft.partida.Partida;
import xtremecraft.unidades.Atacable;
import xtremecraft.unidades.Defendible;
import xtremecraft.vista.MapaObservable;
import xtremecraft.vista.MensajeDeError;
import xtremecraft.vista.ObservableSeleccionado;

@SuppressWarnings("serial")
public class AccionAtacar extends AbstractAction implements Observer{

	private Partida partida;
	private Coordenada coordenada;
	private MapaObservable mapaVista;
		
	public AccionAtacar(Partida partida, MapaObservable mapa, Coordenada coordenada){
			
		super("Construir");
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
		Coordenada coordenadaAtacado = observadoActual.getCoordenadaActualSeleccionado();
		
		Mapa mapaReal = this.partida.getMapa();
		Defendible atacante = (Defendible) mapaReal.getCeldaEnFilaColumna(this.coordenada.fila(), this.coordenada.columna()).getUbicableEnInferior();
		
		try{
			Atacable atacado = (Atacable) mapaReal.getCeldaEnFilaColumna(coordenadaAtacado.fila(), coordenadaAtacado.columna()).getUbicableEnInferior();
			
			Jugador jugadorTurno = this.partida.quienJuega();
			jugadorTurno.atacar(atacante, atacado);
			
		}catch(ClassCastException noSePuedeCastear){
			new MensajeDeError("No se selecciono a una victima valida");
		}catch(ElAtacanteNoEsDelJugadorException elAtacanteNoEsPropio){
			new MensajeDeError("No se selecciono a un agresor propio");
		}
		
		
	}

}
