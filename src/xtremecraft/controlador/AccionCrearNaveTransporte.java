package xtremecraft.controlador;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import xtremecraft.edificios.EdificioEnConstruccionException;
import xtremecraft.edificios.PuertoEstelar;
import xtremecraft.mapa.Coordenada;
import xtremecraft.partida.Jugador;
import xtremecraft.partida.Partida;
import xtremecraft.raza.CantidadDeSuministroInsuficienteException;
import xtremecraft.raza.RecursosInsuficientesException;
import xtremecraft.vista.MapaObservable;
import xtremecraft.vista.MensajeDeError;

@SuppressWarnings("serial")
public class AccionCrearNaveTransporte extends AbstractAction{

	private Partida partida;
	private Coordenada coordenada;
	private MapaObservable mapaVista;
	
	public AccionCrearNaveTransporte(Partida partida, MapaObservable mapa, Coordenada coordenada){
		
		super("CrearNaveTransporte");
		this.partida = partida;
		this.coordenada = coordenada;
		this.mapaVista = mapa;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Jugador jugadorActual = partida.quienJuega();
		PuertoEstelar puertoEstelar = (PuertoEstelar) partida.getMapa().getCeldaEnFilaColumna(coordenada.fila(), coordenada.columna()).getUbicableEnInferior();
		try{
			jugadorActual.crearNaveTransporte(puertoEstelar, this.partida.getMapa());
			
			try {
				this.mapaVista.actualizarVistaEnCoordenada(coordenada);
			} catch (InstantiationException | IllegalAccessException e) {
				new MensajeDeError("Error interno del sistema");
			}
			
		}catch(CantidadDeSuministroInsuficienteException noHaySuministros){
			new MensajeDeError("La cantidad de poblacion disponibles no es suficiente");
		}catch(RecursosInsuficientesException noHayRecursos){
			new MensajeDeError("La cantidad de recursos disponibles no es suficiente");
		}catch(EdificioEnConstruccionException edificioEnContruccion){
			new MensajeDeError("Este edificio esta en construccion aun no se puede utilizar");
		}
		
	}
}
