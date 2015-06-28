package xtremecraft.vista;

import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.DepositoDeSuministros;
import xtremecraft.edificios.Fabrica;
import xtremecraft.edificios.PuertoEstelar;
import xtremecraft.mapa.Aire;
import xtremecraft.mapa.Tierra;
import xtremecraft.partida.Partida;
import xtremecraft.recursos.MinaDeMinerales;
import xtremecraft.recursos.VolcanGasVespeno;
import xtremecraft.unidades.Espectro;
import xtremecraft.unidades.Goliat;
import xtremecraft.unidades.Marine;
import xtremecraft.unidades.NaveCiencia;
import xtremecraft.unidades.NaveTransporte;

public class VentanaDeAlgoCraft  extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private HashMap<Class<?>, Class<?>> vistas;
	
	private HashMap<Class<?>, Class<?>> generarVistas() {
		
		HashMap<Class<?>, Class<?>> vistas = new HashMap<Class<?>, Class<?>>();
		
		vistas.put(Tierra.class, VistaTierra.class);
		vistas.put(Aire.class, VistaAire.class);
		vistas.put(VolcanGasVespeno.class, VistaGas.class);
		vistas.put(MinaDeMinerales.class, VistaMinerales.class);
		vistas.put(DepositoDeSuministros.class, VistaDeposito.class);
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

	public VentanaDeAlgoCraft(Partida partida) throws InstantiationException, IllegalAccessException{
		
		this.vistas = this.generarVistas();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Algo Craft Game");
		setPreferredSize(new Dimension(1000, 750));
		setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		
		//Agregar aca los componentes de este Frame
		JPanel sectorJuego = new SectorJuego(partida, this.vistas);
		sectorJuego.setPreferredSize(new Dimension(1000, 600));
		setVisible(true);
		JPanel panelInformacion = new SectorInformacionParaElUsuario(partida, vistas);
		panelInformacion.setPreferredSize(new Dimension(1000, 150));
		
		//Ponerlos visibles
		sectorJuego.setVisible(true);
		panelInformacion.setVisible(true);
		this.add(sectorJuego);
		this.add(panelInformacion);
		pack();
		setResizable(false);
		setVisible(true);
		
	}

}