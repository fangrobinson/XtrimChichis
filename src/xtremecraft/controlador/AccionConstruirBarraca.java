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
public class AccionConstruirBarraca extends AbstractAction{

	private Partida partida;
	private Coordenada coordenada;
	private MapaObservable mapa;
	
	public AccionConstruirBarraca(Partida partida, MapaObservable mapa, Coordenada coordenada){		
		
		super("ConstruirBarraca");
		this.partida = partida;
		this.mapa = mapa;
		this.coordenada = coordenada;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Jugador jugadorActual = partida.quienJuega();
		Tierra tierraConstruccion = (Tierra) partida.getMapa().getCeldaEnFilaColumna(coordenada.fila(), coordenada.columna()).getCapaInferior();
		try{
			jugadorActual.crearBarraca(tierraConstruccion);	
			try {
				this.mapa.actualizarVistaEnCoordenada(coordenada);
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}catch(RecursosInsuficientesException noHayRecursos){
			 new MensajeDeError("No se tienen suficientes recursos");
		}catch(NoSePudoOcuparElTerrenoException terrenoOcupado){
			new MensajeDeError("El terreno seleccionado no está disponible para construir");
		}
	}
	
}
