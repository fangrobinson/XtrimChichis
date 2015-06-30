package xtremecraft.controlador;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import xtremecraft.mapa.Coordenada;
import xtremecraft.mapa.NoSePudoOcuparElTerrenoException;
import xtremecraft.mapa.Tierra;
import xtremecraft.partida.Jugador;
import xtremecraft.partida.Partida;
import xtremecraft.raza.RazaNoTieneBarracasException;
import xtremecraft.raza.RecursosInsuficientesException;
import xtremecraft.vista.MensajeDeError;

@SuppressWarnings("serial")
public class AccionConstruirFabrica extends AbstractAction{

	private Partida partida;
	private Coordenada coordenada;
	
	public AccionConstruirFabrica(Partida partida, Coordenada coordenada){
		
		super("ConstruirFabrica");
		this.coordenada = coordenada;
		this.partida = partida;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Jugador jugadorActual = partida.quienJuega();
		Tierra tierraConstruccion = (Tierra) partida.getMapa().getCeldaEnFilaColumna(coordenada.fila(), coordenada.columna()).getCapaInferior();
		try{
			jugadorActual.crearFabrica(tierraConstruccion);	
		}catch(RecursosInsuficientesException noHayRecursos){
			 new MensajeDeError("No se tienen suficientes recursos");
		}catch(NoSePudoOcuparElTerrenoException terrenoOcupado){
			new MensajeDeError("El terreno seleccionado no está disponible para construir");
		}catch(RazaNoTieneBarracasException noHayBarracas){
			new MensajeDeError("Se necesita una barraca para crear una fabrica");
		}
	}
	
}