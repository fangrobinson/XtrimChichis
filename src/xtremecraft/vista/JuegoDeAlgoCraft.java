package xtremecraft.vista;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import javax.swing.JFrame;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.DepositoDeSuministros;
import xtremecraft.edificios.Fabrica;
import xtremecraft.edificios.PuertoEstelar;
import xtremecraft.edificios.RecolectorDeGasVespeno;
import xtremecraft.edificios.RecolectorDeMineral;
import xtremecraft.mapa.Aire;
import xtremecraft.mapa.Tierra;
import xtremecraft.recursos.MinaDeMinerales;
import xtremecraft.recursos.VolcanGasVespeno;
import xtremecraft.unidades.Espectro;
import xtremecraft.unidades.Goliat;
import xtremecraft.unidades.Marine;
import xtremecraft.unidades.NaveCiencia;
import xtremecraft.unidades.NaveTransporte;

public class JuegoDeAlgoCraft {

	JFrame ventana;
	
	public JuegoDeAlgoCraft() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		this.ventana = new VentanaDeAlgoCraft(generarVistas());
		ventana.setVisible(true);
	}
	
	public static void main(String args[]){
		
		try {
			new JuegoDeAlgoCraft();
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
	}
	
	
	private static HashMap<Class<?>, Class<?>> generarVistas() {
		
		HashMap<Class<?>, Class<?>> vistas = new HashMap<Class<?>, Class<?>>();
		
		vistas.put(Tierra.class, VistaTierra.class);
		vistas.put(Aire.class, VistaAire.class);
		vistas.put(VolcanGasVespeno.class, VistaGas.class);
		vistas.put(MinaDeMinerales.class, VistaMinerales.class);
		vistas.put(DepositoDeSuministros.class, VistaDeposito.class);
		vistas.put(RecolectorDeGasVespeno.class, VistaRecolectorDeGasVespeno.class);
		vistas.put(RecolectorDeMineral.class, VistaRecolectorDeMineral.class);
		vistas.put(Barraca.class, VistaBarraca.class);
		vistas.put(Fabrica.class, VistaFabrica.class);
		vistas.put(PuertoEstelar.class, VistaPuertoEstelar.class);
		vistas.put(Goliat.class, VistaGoliat.class);
		vistas.put(Marine.class, VistaMarine.class);
		vistas.put(Espectro.class, VistaEspectro.class);
		vistas.put(NaveCiencia.class, VistaNaveCiencia.class);
		vistas.put(NaveTransporte.class, VistaNaveTransporte.class);
		
		return vistas;
	}
	
	
}
