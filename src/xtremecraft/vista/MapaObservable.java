package xtremecraft.vista;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;

import javax.swing.JPanel;

import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Coordenada;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.NoSePudoOcuparElTerrenoException;
import xtremecraft.mapa.Terreno;
import xtremecraft.partida.Identificable;
import xtremecraft.partida.Jugador;
import xtremecraft.partida.Partida;
import xtremecraft.partida.SeleccionadoNoEsPropiedadDelJugadorException;
import xtremecraft.unidades.Atacable;
import xtremecraft.unidades.AtaqueFueraDelRangoDeVisionException;
import xtremecraft.unidades.Defendible;
import xtremecraft.unidades.IdentificableUbicable;
import xtremecraft.unidades.NaveCiencia;
import xtremecraft.unidades.Ubicable;
import xtremecraft.unidades.UbicacionNoValidaException;
import xtremecraft.unidades.Unidad;
import xtremecraft.unidades.YaSeSeleccionoUnAtaqueException;
import xtremecraft.unidades.YaSeSeleccionoUnMovimientoException;

@SuppressWarnings("serial")
public class MapaObservable extends JPanel implements Observer{
	
	private Partida partida;
	private SectorJuego sector;
	private HashMap<Class<?>, Class<?>> vistas;
	private TreeMap<Integer, TreeMap<Integer, Vista>> mapaVisible;
	private Coordenada coordenadaUltimoClickeado;
	private boolean estrategiaDeMovimientoIniciada;
	private boolean estrategiaDeAtaqueIniciada;
	private Coordenada coordenadaOrigenEstrategia;

	public MapaObservable(){};
	
	public MapaObservable(SectorJuego sectorJuego, Partida partida, HashMap<Class<?>, Class<?>> vistas) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		Mapa mapa = partida.getMapa();
		
		this.sector = sectorJuego;
		this.vistas = vistas;
		this.partida = partida;
		this.mapaVisible = new TreeMap<Integer, TreeMap<Integer, Vista>> ();
		this.estrategiaDeMovimientoIniciada = false;
		
		setBounds(mapa.ancho(), mapa.alto(), 800, 800);
		this.setLayout(new GridLayout(mapa.ancho(), mapa.alto()));
				
		TreeMap<Integer, TreeMap<Integer, Celda>> mapaIterable = this.partida.getMapa().devolverMapaEstatico();
		
		for (int i = 0; i < this.partida.getMapa().ancho(); i++){
			this.mapaVisible.put(i,new TreeMap<Integer,Vista>());
			for (int j = 0; j < this.partida.getMapa().alto(); j++){
				Celda celda = mapaIterable.get(i).get(j);
				Terreno terrenoInferior = celda.getCapaInferior();
				Class<?> vistaClase;
				Vista vistaNueva = null;
				Observable observable = null;
				Identificable identificable ;
				//TODO: refactor considerar cambios a identificable.
				if (!terrenoInferior.tieneRecursos()){
					
					vistaClase = this.vistas.get(terrenoInferior.getClass());
					vistaNueva = (Vista) vistaClase.newInstance();
					identificable = (Identificable) terrenoInferior;
					vistaNueva.setEstadoImprimible(identificable.getEstadoImprimible());
					observable = (Observable)terrenoInferior;
					
				}
				else if(terrenoInferior.tieneRecursos()){
					
					vistaClase = this.vistas.get(terrenoInferior.getRecurso().getClass());
					vistaNueva = (Vista)vistaClase.newInstance();
					identificable = (Identificable) terrenoInferior.getRecurso();
					vistaNueva.setEstadoImprimible(identificable.getEstadoImprimible());
					observable = (Observable)terrenoInferior.getRecurso();
					
				}
				if(terrenoInferior.estaOcupado()){
					
					IdentificableUbicable identificableUbicable = (IdentificableUbicable) terrenoInferior.getUbicableEnTerreno();
					int numero = identificableUbicable.getNumeroJugador();
					vistaClase = this.vistas.get(terrenoInferior.getUbicableEnTerreno().getClass());
					IdentificableVisible identificableVisible = (IdentificableVisible) vistaClase.newInstance();
					identificableVisible.setJugador(numero);
					vistaNueva.setEstadoImprimible(identificableUbicable.getEstadoImprimible());
					vistaNueva = (Vista) identificableVisible;
					observable = (Observable)terrenoInferior.getUbicableEnTerreno();
				
				}	
					
				
				observable.addObserver(vistaNueva);
				vistaNueva.setCoordenada(terrenoInferior.getCoordenada());
				this.mapaVisible.get(i).put(j, vistaNueva);
				
				vistaNueva.paintComponents(getGraphics());
				vistaNueva.setMaximumSize(new Dimension(25,25));
				add(vistaNueva);
				
			}
		}
		this.agregarObservadorAVistas(this);
		
	}
	
	public void actualizarVistaEnCoordenada(Coordenada coordenada) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		Vista vistaCelda = this.mapaVisible.get(coordenada.fila()).get(coordenada.columna());
		this.mapaVisible.get(coordenada.fila()).remove(coordenada.columna());
		int n = this.getComponentZOrder(vistaCelda);
		this.remove(n);
		
		Celda celdaReal = this.partida.getMapa().getCeldaEnFilaColumna(coordenada.fila(), coordenada.columna());
		Terreno terrenoInferior = celdaReal.getCapaInferior();
		Terreno terrenoSuperior = celdaReal.getCapaSuperior();
		Class<?> vistaClase;
		Vista vistaNueva = null;
		Observable observable = null;
		Identificable identificable;
		ArrayList<Vista> vistasInferiores = new ArrayList<Vista>();
	
		if (!terrenoInferior.tieneRecursos()){
			
			vistaClase = this.vistas.get(terrenoInferior.getClass());
			vistaNueva = (Vista)vistaClase.newInstance();
			observable = (Observable)terrenoInferior;
			identificable = (Identificable) terrenoInferior;
			vistaNueva.setEstadoImprimible(identificable.getEstadoImprimible());
			vistasInferiores.add(vistaNueva);
			
		}else {
			
			vistaClase = this.vistas.get(terrenoInferior.getRecurso().getClass());
			vistaNueva = (Vista)vistaClase.newInstance();
			observable = (Observable)terrenoInferior.getRecurso();
			identificable = (Identificable) terrenoInferior.getRecurso();
			vistaNueva.setEstadoImprimible(identificable.getEstadoImprimible());
			vistasInferiores.add(vistaNueva);
			
		}
		if(terrenoInferior.estaOcupado()){
			
			Identificable identificableUbicable = (Identificable)terrenoInferior.getUbicableEnTerreno();
			int numero = identificableUbicable.getNumeroJugador();
			vistaClase = this.vistas.get(terrenoInferior.getUbicableEnTerreno().getClass());
			IdentificableVisible identificableVisible = (IdentificableVisible)vistaClase.newInstance();
			identificableVisible.setJugador(numero);
			vistaNueva = (Vista) identificableVisible;
			vistaNueva.setEstadoImprimible(identificableUbicable.getEstadoImprimible());
			observable = (Observable)terrenoInferior.getUbicableEnTerreno();
			vistaNueva.setMaximumSize(new Dimension(10,10));
			vistasInferiores.add(vistaNueva);
			
		}
		if (terrenoSuperior.estaOcupado()){
			
			Identificable identificableUbicable = (Identificable)terrenoSuperior.getUbicableEnTerreno();
			int numero = identificableUbicable.getNumeroJugador();
			vistaClase = this.vistas.get(terrenoSuperior.getUbicableEnTerreno().getClass());
			IdentificableVisible identificableVisible = (IdentificableVisible)vistaClase.newInstance();
			identificableVisible.setJugador(numero);
			vistaNueva = (Vista) identificableVisible;
			vistaNueva.setEstadoImprimible(identificableUbicable.getEstadoImprimible());
			observable = (Observable)terrenoSuperior.getUbicableEnTerreno();
			vistaNueva.setMaximumSize(new Dimension(10,10));
			vistaNueva.setVistasInferiores(vistasInferiores);
			
		}
		
		observable.addObserver(vistaNueva);
		
		vistaNueva.setCoordenada(terrenoInferior.getCoordenada());
		vistaNueva.paintComponents(getGraphics());
		vistaNueva.setMaximumSize(new Dimension(25,25));
		
		this.add(vistaNueva, n);
		this.mapaVisible.get(coordenada.fila()).put(coordenada.columna(),vistaNueva);
		this.sector.agregarObservadoresDeVistas(vistaNueva);
		this.agregarObservadorAVista(vistaNueva, this);
		
		revalidate();
		repaint();			
		
	}
	
	public void agregarObservadorAVistas(Observer observador){
		
		for (int i = 0; i < this.partida.getMapa().ancho(); i++){
			for (int j = 0; j < this.partida.getMapa().alto(); j++){
				Vista vistaActual = this.mapaVisible.get(i).get(j);
				this.agregarObservadorAVista(vistaActual, observador);
			}
		}
		
	}
	
	public void agregarObservadorAVista(Vista nuevaVistaObservada, Observer nuevoObservador){
		
		nuevaVistaObservada.agregarObservador(nuevoObservador);
		
	}
	
	public TreeMap<Integer, TreeMap<Integer, Vista>> getVistas() {
		
		return this.mapaVisible;
		
	}

	@Override
	public void update(Observable obs, Object arg1) {
		
		ObservableSeleccionado observable = (ObservableSeleccionado) obs;
		this.coordenadaUltimoClickeado = observable.getCoordenadaActualSeleccionado();
		if(this.estrategiaDeMovimientoIniciada){
			
			Celda celdaOrigen = this.partida.getMapa().getCeldaEnFilaColumna(this.coordenadaOrigenEstrategia.fila(), this.coordenadaOrigenEstrategia.columna());
			Celda celdaDestino = this.partida.getMapa().getCeldaEnFilaColumna(this.coordenadaUltimoClickeado.fila(), this.coordenadaUltimoClickeado.columna());
			
			Terreno terrenoDestino = observable.devolverTerrenoDestinoParaMovimiento(celdaDestino);
			Ubicable unidadAMover= observable.devolverUbicableParaMovimiento(celdaOrigen);
//			Terreno terrenoDestino = celdaDestino.getCapaInferior();
//			Unidad unidadAMover = (Unidad) celdaOrigen.getUbicableEnInferior();

			try{
				unidadAMover.actualizarUbicacion(terrenoDestino);
				try{
					this.actualizarVistaEnCoordenada(this.coordenadaOrigenEstrategia);
					this.actualizarVistaEnCoordenada(this.coordenadaUltimoClickeado);
					this.estrategiaDeMovimientoIniciada = false;
				}catch (InstantiationException | IllegalAccessException | IllegalArgumentException  e) {
					new MensajeDeError("Error interno del sistema");
				} catch (InvocationTargetException e) {
					new MensajeDeError("Error interno del sistema");
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					new MensajeDeError("Error interno del sistema");
					e.printStackTrace();
				} catch (SecurityException e) {
					new MensajeDeError("Error interno del sistema");
					e.printStackTrace();
				}
			}catch(UbicacionNoValidaException | NoSePudoOcuparElTerrenoException destinoInvalido){
				try{
					terrenoDestino = celdaDestino.getCapaSuperior();
					unidadAMover.actualizarUbicacion(terrenoDestino);
				}
				catch(UbicacionNoValidaException | NoSePudoOcuparElTerrenoException destinoInvalidoo){
					this.estrategiaDeMovimientoIniciada = false;
					new MensajeDeError("No se puede mover a la locacion seleccionada");
				}
				
			}catch(YaSeSeleccionoUnMovimientoException u) {
				new MensajeDeError("La unidad que se quiere mover ya se movió");
			}
			
		}if(this.estrategiaDeAtaqueIniciada){
			
			Celda celdaAtacante = this.partida.getMapa().getCeldaEnFilaColumna(this.coordenadaOrigenEstrategia.fila(), this.coordenadaOrigenEstrategia.columna());
			Celda celdaAtacado = this.partida.getMapa().getCeldaEnFilaColumna(this.coordenadaUltimoClickeado.fila(), this.coordenadaUltimoClickeado.columna());
			
			Defendible atacante = (Defendible) celdaAtacante.getUbicableEnInferior();
			
			
			
			Ubicable ubicableAtacado = null;
			try{
				
				Atacable atacado = (Atacable) celdaAtacado.getUbicableEnInferior();
				ubicableAtacado = (Ubicable) atacado;
				Jugador jugadorTurno = this.partida.quienJuega();
				
				//TODO: refactoring urgente
				
				if (atacante.getClass() == NaveCiencia.class){
					
					NaveCiencia naveAtacante = (NaveCiencia) atacante;
					Unidad unidadAtacada = (Unidad) atacado;
					
					jugadorTurno.atacarConRadiacion(naveAtacante, unidadAtacada, this.partida.getMapa());
				}else{
					jugadorTurno.atacar(atacante, atacado);
				}
				
			
			}catch(AtaqueFueraDelRangoDeVisionException ataqueFueraDeRango){
				new MensajeDeError("El atacado esta fuera del rango del atacante");
			}catch(SeleccionadoNoEsPropiedadDelJugadorException elAtacanteNoEsPropio){
				new MensajeDeError("No se selecciono a un agresor propio");
			}catch(YaSeSeleccionoUnAtaqueException masDeUnAtaquePorTurno){
				new MensajeDeError("No se puede atacar más de una vez por turno");	
			}catch(ClassCastException noSePuedeCastear){
				new MensajeDeError("No se selecciono a una victima valida");
			}
			
			try {
				this.actualizarVistaEnCoordenada(this.coordenadaOrigenEstrategia);
				this.actualizarVistaEnCoordenada(this.coordenadaUltimoClickeado);
				this.estrategiaDeAtaqueIniciada = false;
				
			}catch (InstantiationException | IllegalAccessException | IllegalArgumentException  e) {
				new MensajeDeError("Error interno del sistema");
			} catch (InvocationTargetException e) {
				new MensajeDeError("Error interno del sistema");
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				new MensajeDeError("Error interno del sistema");
				e.printStackTrace();
			} catch (SecurityException e) {
				new MensajeDeError("Error interno del sistema");
				e.printStackTrace();
			}
			if (ubicableAtacado != null){
				
				ubicableAtacado.getJugador().verificarSigueEnJuego();
				Jugador ganador = this.partida.jugadorGanador();

				if(ganador != null){
					new MensajeGanador(ganador.nombre());
				}
				
			}

		}
		
	}

	public void comenzarMovimiento(Coordenada coordenadaInicioMovimiento) {
		
		this.estrategiaDeMovimientoIniciada = true;
		this.coordenadaOrigenEstrategia = coordenadaInicioMovimiento;
		
	}

	public void comenzarAtaque(Coordenada coordenadaInicioAtaque) {

		this.estrategiaDeAtaqueIniciada = true;
		this.coordenadaOrigenEstrategia = coordenadaInicioAtaque;
		
	}

	public void pasarTurno() {
		
		this.estrategiaDeAtaqueIniciada = false;
		this.estrategiaDeMovimientoIniciada = false;
		
	}		

}
	

