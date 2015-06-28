package xtremecraft.vista;

import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import xtremecraft.edificios.DepositoDeSuministros;
import xtremecraft.mapa.Tierra;
import xtremecraft.partida.Partida;
import xtremecraft.recursos.MinaDeMinerales;
import xtremecraft.recursos.VolcanGasVespeno;

public class VentanaDeAlgoCraft  extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private HashMap<Class<?>, Class<?>> vistas;
	
	private HashMap<Class<?>, Class<?>> generarVistas() {
		
		HashMap<Class<?>, Class<?>> vistas = new HashMap<Class<?>, Class<?>>();
		
		vistas.put(Tierra.class, VistaTierra.class);
		vistas.put(VolcanGasVespeno.class, VistaGas.class);
		vistas.put(MinaDeMinerales.class, VistaMinerales.class);
		//TODO: distinguir que deposito es de cada jugador.
		vistas.put(DepositoDeSuministros.class, VistaDeposito.class);
		
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
		JPanel panelInformacion = new SectorInformacionParaElUsuario(partida);
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