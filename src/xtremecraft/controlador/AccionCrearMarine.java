package xtremecraft.controlador;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import xtremecraft.edificios.Barraca;
import xtremecraft.mapa.Coordenada;
import xtremecraft.partida.Jugador;
import xtremecraft.partida.Partida;
import xtremecraft.raza.CantidadDeSuministroInsuficienteException;
import xtremecraft.vista.MensajeDeError;

@SuppressWarnings("serial")
public class AccionCrearMarine extends AbstractAction{

	private Partida partida;
	private Coordenada coordenada;
	
	public AccionCrearMarine(Partida partida, Coordenada coordenada){
		
		super("CrearMarine");
		this.coordenada = coordenada;
		this.partida = partida;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Jugador jugadorActual = partida.quienJuega();
		Barraca barraca = (Barraca) partida.getMapa().getCeldaEnFilaColumna(coordenada.fila(), coordenada.columna()).getUbicableEnInferior();
		try{
			jugadorActual.crearMarine(barraca, this.partida.getMapa());
		}catch(CantidadDeSuministroInsuficienteException noHaySuministros){
			new MensajeDeError("La cantidad de poblacion disponibles no es suficiente");
		}
		
	}
}
