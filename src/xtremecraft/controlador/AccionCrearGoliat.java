package xtremecraft.controlador;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import xtremecraft.edificios.Fabrica;
import xtremecraft.mapa.Coordenada;
import xtremecraft.partida.Jugador;
import xtremecraft.partida.Partida;
import xtremecraft.raza.CantidadDeSuministroInsuficienteException;
import xtremecraft.raza.RecursosInsuficientesException;
import xtremecraft.vista.MapaObservable;
import xtremecraft.vista.MensajeDeError;

@SuppressWarnings("serial")
public class AccionCrearGoliat extends AbstractAction{

	private Partida partida;
	private Coordenada coordenada;
	
	public AccionCrearGoliat(Partida partida, MapaObservable mapa){
		
		super("CrearGoliat");
		this.partida = partida;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Jugador jugadorActual = partida.quienJuega();
		Fabrica fabrica = (Fabrica) partida.getMapa().getCeldaEnFilaColumna(coordenada.fila(), coordenada.columna()).getUbicableEnInferior();
		try{
			jugadorActual.crearGoliat(fabrica, this.partida.getMapa());
		}catch(CantidadDeSuministroInsuficienteException noHaySuministros){
			new MensajeDeError("La cantidad de poblacion disponibles no es suficiente");
		}catch(RecursosInsuficientesException noHayRecursos){
			new MensajeDeError("La cantidad de recursos disponibles no es suficiente");
		}
		
	}
}
