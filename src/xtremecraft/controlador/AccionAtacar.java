package xtremecraft.controlador;

import java.awt.event.ActionEvent;
//import java.util.Observable;
//import java.util.Observer;

import javax.swing.AbstractAction;

import xtremecraft.mapa.Coordenada;
//import xtremecraft.mapa.Mapa;
//import xtremecraft.partida.Jugador;
import xtremecraft.partida.Partida;
import xtremecraft.partida.SeleccionadoNoEsPropiedadDelJugadorException;
//import xtremecraft.unidades.Atacable;
//import xtremecraft.unidades.AtaqueFueraDelRangoDeVisionException;
//import xtremecraft.unidades.Defendible;
//import xtremecraft.unidades.YaSeSeleccionoUnAtaqueException;
import xtremecraft.vista.MapaObservable;
import xtremecraft.vista.MensajeDeError;
//import xtremecraft.vista.ObservableSeleccionado;
import xtremecraft.vista.SectorAccionesDisponibles;

@SuppressWarnings("serial")
public class AccionAtacar extends AbstractAction{

	private Partida partida;
	private Coordenada coordenada;
	private MapaObservable mapaVista;
	private SectorAccionesDisponibles sector;
		
	public AccionAtacar(Partida partida, MapaObservable mapa, Coordenada coordenada, SectorAccionesDisponibles sectorAccionesDisponibles){
			
		super("Atacar");
		this.partida = partida;
		this.coordenada = coordenada;
		this.mapaVista = mapa;
		this.coordenada = coordenada;
		this.sector = sectorAccionesDisponibles;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			this.partida.validarQueUbicableElegidoEsDelJugador(this.coordenada);
			
			boolean unidadEnConstruccion = this.partida.unidadSeleccionadaEstaEnConstruccion(this.coordenada);
			
			boolean unidadSeleccionadaPuedeAtacar = this.partida.unidadSeleccionadaPuedeAtacar(this.coordenada);
			
			if ( unidadEnConstruccion || !unidadSeleccionadaPuedeAtacar ){
				new MensajeDeError("Esta unidad esta en construccion o ya realizó un ataque en el turno actual.");
			}else{
				this.mapaVista.comenzarAtaque(this.coordenada);
			}
			
		} catch (SeleccionadoNoEsPropiedadDelJugadorException e) {
			new MensajeDeError("La unidad que quiere atacar no es del jugador");
		}finally{
			this.sector.removeAll();
		}
		this.mapaVista.comenzarAtaque(this.coordenada);		
	}
	
	/*@Override
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
		}finally{
			this.sector.removeAll();
		}
			
		try {
			
			this.mapaVista.actualizarVistaEnCoordenada(coordenada);
			
		} catch (InstantiationException | IllegalAccessException e) {
			new MensajeDeError("Error interno del sistema");	
		}		
		
	}*/

}
