package xtremecraft.controlador;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.EdificioEnConstruccionException;
import xtremecraft.mapa.Coordenada;
import xtremecraft.partida.Jugador;
import xtremecraft.partida.Partida;
import xtremecraft.raza.BarracaNoEsDeLaRazaException;
import xtremecraft.raza.CantidadDeSuministroInsuficienteException;
import xtremecraft.raza.RecursosInsuficientesException;
import xtremecraft.unidades.Marine;
import xtremecraft.vista.MapaObservable;
import xtremecraft.vista.MensajeDeError;

@SuppressWarnings("serial")
public class AccionCrearMarine extends AbstractAction{

	private Partida partida;
	private Coordenada coordenada;
	private MapaObservable mapaVista;
	
	public AccionCrearMarine(Partida partida, MapaObservable mapa, Coordenada coordenada){
		
		super("CrearMarine");
		this.partida = partida;
		this.coordenada = coordenada;
		this.mapaVista = mapa;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Jugador jugadorActual = partida.quienJuega();
		Barraca barraca = (Barraca) partida.getMapa().getCeldaEnFilaColumna(coordenada.fila(), coordenada.columna()).getUbicableEnInferior();
		try{
			Marine marine = jugadorActual.crearMarine(barraca, this.partida.getMapa());
			Coordenada coordenadaMarine = marine.getUbicacionActual();
			try {
				this.mapaVista.actualizarVistaEnCoordenada(coordenadaMarine);
			} catch (InstantiationException | IllegalAccessException e) {
				
			}
			
		}catch(CantidadDeSuministroInsuficienteException noHaySuministros){
			new MensajeDeError("La cantidad de poblacion disponibles no es suficiente");
		}catch(RecursosInsuficientesException noHayRecursos){
			new MensajeDeError("La cantidad de recursos disponibles no es suficiente");
		}catch(EdificioEnConstruccionException edificioEnContruccion){
			new MensajeDeError("Este edificio esta en construccion aun no se puede utilizar");
		}catch(BarracaNoEsDeLaRazaException esteEdificioNoEsDelJugador){
			new MensajeDeError("Este edificio no es del jugador en turno");
		}
		
	}
}
