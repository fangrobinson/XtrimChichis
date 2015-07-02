package xtremecraft.controlador;

import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;

import xtremecraft.mapa.Coordenada;
import xtremecraft.mapa.Mapa;
import xtremecraft.partida.Jugador;
import xtremecraft.partida.Partida;
import xtremecraft.partida.SeleccionadoNoEsPropiedadDelJugadorException;
import xtremecraft.unidades.Atacable;
import xtremecraft.unidades.AtaqueFueraDelRangoDeVisionException;
import xtremecraft.unidades.Defendible;
import xtremecraft.unidades.YaSeSeleccionoUnAtaqueException;
import xtremecraft.vista.MapaObservable;
import xtremecraft.vista.MensajeDeError;
import xtremecraft.vista.ObservableSeleccionado;

@SuppressWarnings("serial")
public class AccionAtacar extends AbstractAction implements Observer{

	private Partida partida;
	private Coordenada coordenada;
	private MapaObservable mapaVista;
		
	public AccionAtacar(Partida partida, MapaObservable mapa, Coordenada coordenada){
			
		super("Atacar");
		this.partida = partida;
		this.coordenada = coordenada;
		this.mapaVista = mapa;
		this.coordenada = coordenada;
		
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
		
		}catch(AtaqueFueraDelRangoDeVisionException ataqueFueraDeRango){
			new MensajeDeError("El atacado esta fuera del rango del atacante");
		}catch(SeleccionadoNoEsPropiedadDelJugadorException elAtacanteNoEsPropio){
			new MensajeDeError("No se selecciono a un agresor propio");
		}catch(YaSeSeleccionoUnAtaqueException masDeUnAtaquePorTurno){
			new MensajeDeError("No se puede atacar más de una vez por turno");	
		}catch(ClassCastException noSePuedeCastear){
			new MensajeDeError("No se selecciono a una victima valida");
		}
			
		try {
			
			this.mapaVista.actualizarVistaEnCoordenada(coordenada);
			
		} catch (InstantiationException | IllegalAccessException e) {
			new MensajeDeError("Error interno del sistema");	
		}
		
		
		
	}

}
