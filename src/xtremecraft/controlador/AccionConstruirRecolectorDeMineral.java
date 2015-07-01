package xtremecraft.controlador;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import xtremecraft.mapa.Coordenada;
import xtremecraft.mapa.NoSePudoOcuparElTerrenoException;
import xtremecraft.mapa.Tierra;
import xtremecraft.partida.Jugador;
import xtremecraft.partida.Partida;
import xtremecraft.raza.RecursosInsuficientesException;
import xtremecraft.vista.MapaObservable;
import xtremecraft.vista.MensajeDeError;

@SuppressWarnings("serial")
public class AccionConstruirRecolectorDeMineral extends AbstractAction{

	private Partida partida;
	private Coordenada coordenada;
	private MapaObservable mapaVista;
	
	public AccionConstruirRecolectorDeMineral(Partida partida, MapaObservable mapa, Coordenada coordenada){
		
		super("ConstruirRecolectorDeMineral");
		this.partida = partida;
		this.mapaVista = mapa;
		this.coordenada = coordenada;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Jugador jugadorActual = partida.quienJuega();
		Tierra tierraConstruccion = (Tierra) partida.getMapa().getCeldaEnFilaColumna(coordenada.fila(), coordenada.columna()).getCapaInferior();
		try{
			jugadorActual.crearRecolectorDeMineral(tierraConstruccion);	
			//this.mapa.borrarARolete();
			try {
				this.mapaVista.actualizarVistaEnCoordenada(coordenada);
			} catch (InstantiationException | IllegalAccessException e) {
				new MensajeDeError("Error interno del sistema");
			}
		}catch(RecursosInsuficientesException noHayRecursos){
			 new MensajeDeError("No se tienen suficientes recursos");
		}catch(NoSePudoOcuparElTerrenoException terrenoOcupado){
			new MensajeDeError("El terreno seleccionado no está disponible para construir");
		}
	}
}
