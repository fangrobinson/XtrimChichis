package xtremecraft.controlador;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import xtremecraft.edificios.EdificioEnConstruccionException;
import xtremecraft.edificios.Fabrica;
import xtremecraft.mapa.Coordenada;
import xtremecraft.partida.Jugador;
import xtremecraft.partida.Partida;
import xtremecraft.partida.SeleccionadoNoEsPropiedadDelJugadorException;
import xtremecraft.raza.CantidadDeSuministroInsuficienteException;
import xtremecraft.raza.RecursosInsuficientesException;
import xtremecraft.unidades.Goliat;
import xtremecraft.vista.MapaObservable;
import xtremecraft.vista.MensajeDeError;
import xtremecraft.vista.SectorAccionesDisponibles;

@SuppressWarnings("serial")
public class AccionCrearGoliat extends AbstractAction{

	private Partida partida;
	private Coordenada coordenada;
	private MapaObservable mapaVista;
	private SectorAccionesDisponibles sector;
	
	public AccionCrearGoliat(Partida partida, MapaObservable mapa, Coordenada coordenada, SectorAccionesDisponibles sectorAccionesDisponibles){
		
		super("CrearGoliat");
		this.partida = partida;
		this.coordenada = coordenada;
		this.mapaVista = mapa;
		this.sector = sectorAccionesDisponibles;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
			
		Jugador jugadorActual = partida.quienJuega();
		Fabrica fabrica = (Fabrica) partida.getMapa().getCeldaEnFilaColumna(coordenada.fila(), coordenada.columna()).getUbicableEnInferior();
		
		try{
		
			Goliat goliat = jugadorActual.crearGoliat(fabrica, this.partida.getMapa());
			Coordenada coordenadaGoliat = goliat.getUbicacionActual();
			
			try {
			
				this.mapaVista.actualizarVistaEnCoordenada(coordenadaGoliat);
			
			} catch (InstantiationException | IllegalAccessException e) {
				new MensajeDeError("Error interno del sistema");
			}
			
		}catch(CantidadDeSuministroInsuficienteException noHaySuministros){
			new MensajeDeError("La cantidad de poblacion disponibles no es suficiente");
		}catch(RecursosInsuficientesException noHayRecursos){
			new MensajeDeError("La cantidad de recursos disponibles no es suficiente");
		}catch(EdificioEnConstruccionException edificioEnContruccion){
			new MensajeDeError("Este edificio esta en construccion aun no se puede utilizar");
		}catch (SeleccionadoNoEsPropiedadDelJugadorException e) {
			new MensajeDeError("Este edificio no pertenece al jugador actual");
		}finally{
			this.sector.removeAll();
		}
	}
}
