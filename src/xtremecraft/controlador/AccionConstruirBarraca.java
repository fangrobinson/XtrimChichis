package xtremecraft.controlador;

import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.AbstractAction;

import xtremecraft.mapa.Coordenada;
import xtremecraft.mapa.NoSePudoOcuparElTerrenoException;
import xtremecraft.mapa.Tierra;
import xtremecraft.partida.Jugador;
import xtremecraft.partida.Partida;
import xtremecraft.raza.RecursosInsuficientesException;
import xtremecraft.vista.MapaObservable;
import xtremecraft.vista.MensajeDeError;
import xtremecraft.vista.SectorAccionesDisponibles;

@SuppressWarnings("serial")
public class AccionConstruirBarraca extends AbstractAction{

	private Partida partida;
	private Coordenada coordenada;
	private MapaObservable mapaVista;
	private SectorAccionesDisponibles sector;
	
	public AccionConstruirBarraca(Partida partida, MapaObservable mapa, Coordenada coordenada, SectorAccionesDisponibles sector){		
		
		super("ConstruirBarraca");
		this.partida = partida;
		this.mapaVista = mapa;
		this.coordenada = coordenada;
		this.mapaVista = mapa;
		this.sector = sector;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		Jugador jugadorActual = partida.quienJuega();
		Tierra tierraConstruccion = (Tierra) partida.getMapa().getCeldaEnFilaColumna(coordenada.fila(), coordenada.columna()).getCapaInferior();
		
		try{
			
			jugadorActual.crearBarraca(tierraConstruccion, this.partida.getMapa());	

			try {
				
				this.mapaVista.actualizarVistaEnCoordenada(coordenada);
				this.sector.removeAll();
				
			}catch (InstantiationException | IllegalAccessException e) {
				new MensajeDeError("Error interno del sistema");
			}catch (InvocationTargetException e) {
				new MensajeDeError("Error interno del sistema");
				e.printStackTrace();
			}catch (NoSuchMethodException e) {
				new MensajeDeError("Error interno del sistema");
				e.printStackTrace();
			}catch (SecurityException e) {
				new MensajeDeError("Error interno del sistema");
				e.printStackTrace();
			}
		}catch(RecursosInsuficientesException noHayRecursos){
			new MensajeDeError("No se tienen suficientes recursos");
		}catch(NoSePudoOcuparElTerrenoException terrenoOcupado){
			new MensajeDeError("El terreno seleccionado no está disponible para construir");
		}
		
	}
}
	
