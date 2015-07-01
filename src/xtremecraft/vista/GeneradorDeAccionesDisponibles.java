package xtremecraft.vista;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractAction;

import xtremecraft.controlador.AccionAtacar;
import xtremecraft.controlador.AccionConstruirBarraca;
import xtremecraft.controlador.AccionConstruirDepositoDeSuministros;
import xtremecraft.controlador.AccionConstruirFabrica;
import xtremecraft.controlador.AccionConstruirPuertoEstelar;
import xtremecraft.controlador.AccionConstruirRecolectorDeGasVespeno;
import xtremecraft.controlador.AccionConstruirRecolectorDeMineral;
import xtremecraft.controlador.AccionCrearEspectro;
import xtremecraft.controlador.AccionCrearGoliat;
import xtremecraft.controlador.AccionCrearMarine;
import xtremecraft.controlador.AccionCrearNaveCiencia;
import xtremecraft.controlador.AccionCrearNaveTransporte;
import xtremecraft.controlador.AccionMover;
import xtremecraft.partida.Partida;
import xtremecraft.unidades.Espectro;

public class GeneradorDeAccionesDisponibles {
		
	public static HashMap<Class<?>, ArrayList<AbstractAction>> generarAcciones(Partida partida,MapaObservable mapaObservable) {
			
		HashMap<Class<?>, ArrayList<AbstractAction>> acciones = new HashMap<Class<?>, ArrayList<AbstractAction>>();
			
		ArrayList<AbstractAction> accionesUnidades = new ArrayList<AbstractAction>();
		accionesUnidades.add(new AccionMover(partida, mapaObservable));
		accionesUnidades.add(new AccionAtacar(partida, mapaObservable));
			
		ArrayList<AbstractAction> accionesTierra = new ArrayList<AbstractAction>();
		accionesTierra.add(new AccionConstruirDepositoDeSuministros(partida, mapaObservable));
		accionesTierra.add(new AccionConstruirBarraca(partida, mapaObservable));
		accionesTierra.add(new AccionConstruirFabrica(partida, mapaObservable));
		accionesTierra.add(new AccionConstruirPuertoEstelar(partida, mapaObservable));
		
		ArrayList<AbstractAction> accionesMineral = new ArrayList<AbstractAction>();
		accionesMineral.add(new AccionConstruirRecolectorDeMineral(partida, mapaObservable));
		
		ArrayList<AbstractAction> accionesGasVespeno = new ArrayList<AbstractAction>();
		accionesGasVespeno.add(new AccionConstruirRecolectorDeGasVespeno(partida, mapaObservable));
			
		ArrayList<AbstractAction> accionesBarraca = new ArrayList<AbstractAction>();
		accionesBarraca.add(new AccionCrearMarine(partida, mapaObservable));
		
		ArrayList<AbstractAction> accionesFabrica = new ArrayList<AbstractAction>();
		accionesFabrica.add(new AccionCrearGoliat(partida, mapaObservable));
			
		ArrayList<AbstractAction> accionesPuertoEstelar = new ArrayList<AbstractAction>();
		accionesPuertoEstelar.add(new AccionCrearEspectro(partida, mapaObservable));
		accionesPuertoEstelar.add(new AccionCrearNaveTransporte(partida, mapaObservable));
		accionesPuertoEstelar.add(new AccionCrearNaveCiencia(partida, mapaObservable));
			
		acciones.put(VistaTierra.class, accionesTierra);
		acciones.put(VistaAire.class, new ArrayList<AbstractAction>());
		acciones.put(VistaGas.class, accionesGasVespeno);
		acciones.put(VistaMinerales.class, accionesMineral);
		acciones.put(VistaDeposito.class, new ArrayList<AbstractAction>());
		acciones.put(VistaRecolectorDeGasVespeno.class, new ArrayList<AbstractAction>());
		acciones.put(VistaRecolectorDeMineral.class,new ArrayList<AbstractAction>());
		acciones.put(VistaBarraca.class, accionesBarraca);
		acciones.put(VistaFabrica.class, accionesFabrica);
		acciones.put(VistaPuertoEstelar.class, accionesPuertoEstelar);
		acciones.put(VistaGoliat.class, accionesUnidades);
		acciones.put(VistaMarine.class, accionesUnidades);
		acciones.put(Espectro.class, accionesUnidades);
		//acciones.put(NaveCiencia.class, accionesUnidades);
		//acciones.put(NaveTransporte.class, accionesUnidades);
			
			
		return acciones;
			
	}
}
