package xtremecraft.controlador;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

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
	
	public AccionCrearNaveTransporte(Partida partida, MapaObservable mapa, Coordenada coordenada){
		
		super("CrearNaveTransporte");
		this.partida = partida;
		this.coordenada = coordenada;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Jugador jugadorActual = partida.quienJuega();
		PuertoEstelar puertoEstelar = (PuertoEstelar) partida.getMapa().getCeldaEnFilaColumna(coordenada.fila(), coordenada.columna()).getUbicableEnInferior();
		try{
			jugadorActual.crearNaveTransporte(puertoEstelar, this.partida.getMapa());
		}catch(CantidadDeSuministroInsuficienteException noHaySuministros){
			new MensajeDeError("La cantidad de poblacion disponibles no es suficiente");
		}catch(RecursosInsuficientesException noHayRecursos){
			new MensajeDeError("La cantidad de recursos disponibles no es suficiente");
		}
		
	}
}