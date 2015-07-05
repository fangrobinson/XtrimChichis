package xtremecraft.controlador;

import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.AbstractAction;

import xtremecraft.edificios.EdificioEnConstruccionException;
import xtremecraft.edificios.PuertoEstelar;
import xtremecraft.mapa.Coordenada;
import xtremecraft.partida.Jugador;
import xtremecraft.partida.Partida;
import xtremecraft.partida.SeleccionadoNoEsPropiedadDelJugadorException;
import xtremecraft.raza.CantidadDeSuministroInsuficienteException;
import xtremecraft.raza.RecursosInsuficientesException;
import xtremecraft.unidades.NaveTransporte;
import xtremecraft.vista.MapaObservable;
import xtremecraft.vista.MensajeDeError;
import xtremecraft.vista.SectorAccionesDisponibles;

@SuppressWarnings("serial")
public class AccionCrearNaveTransporte extends AbstractAction{

	private Partida partida;
	private Coordenada coordenada;
	private MapaObservable mapaVista;
	private SectorAccionesDisponibles sector;
	
	public AccionCrearNaveTransporte(Partida partida, MapaObservable mapa, Coordenada coordenada, SectorAccionesDisponibles sector){
		
		super("CrearNaveTransporte");
		this.partida = partida;
		this.coordenada = coordenada;
		this.mapaVista = mapa;
		this.sector = sector;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		Jugador jugadorActual = partida.quienJuega();
		PuertoEstelar puertoEstelar = (PuertoEstelar) partida.getMapa().getCeldaEnFilaColumna(coordenada.fila(), coordenada.columna()).getUbicableEnInferior();
		
		try{
		
			NaveTransporte naveTransporte = jugadorActual.crearNaveTransporte(puertoEstelar, this.partida.getMapa());
			Coordenada coordenadaNaveTransporte = naveTransporte.getUbicacionActual();
			
			try {
			
				this.mapaVista.actualizarVistaEnCoordenada(coordenadaNaveTransporte);
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
		}catch(EdificioEnConstruccionException edificioEnContruccion){
			new MensajeDeError("Este edificio esta en construccion aun no se puede utilizar");
		}catch(CantidadDeSuministroInsuficienteException noHaySuministros){
			new MensajeDeError("La cantidad de poblacion disponibles no es suficiente");
		}catch(RecursosInsuficientesException noHayRecursos){
			new MensajeDeError("La cantidad de recursos disponibles no es suficiente");
		}catch (SeleccionadoNoEsPropiedadDelJugadorException e) {
			new MensajeDeError("Este edificio no pertenece al jugador actual");
		}
		
	}
}
