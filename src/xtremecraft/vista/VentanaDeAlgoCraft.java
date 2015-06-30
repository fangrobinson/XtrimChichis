package xtremecraft.vista;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.DepositoDeSuministros;
import xtremecraft.edificios.Fabrica;
import xtremecraft.edificios.PuertoEstelar;
import xtremecraft.edificios.RecolectorDeGasVespeno;
import xtremecraft.edificios.RecolectorDeMineral;
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

public class VentanaDeAlgoCraft extends JFrame implements MouseListener{
	
	private static final long serialVersionUID = 1L;
	private HashMap<Class<?>, Class<?>> vistas;
	private SectorJuego sectorJuego;
	
	private HashMap<Class<?>, Class<?>> generarVistas() {
		
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

	public VentanaDeAlgoCraft(Partida partida) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		this.vistas = this.generarVistas();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Algo Craft Game");
		setPreferredSize(new Dimension(1200, 950));
		setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		
		//Agregar aca los componentes de este Frame
		this.sectorJuego = new SectorJuego(partida, this.vistas);
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

	@Override
	public void mouseClicked(MouseEvent e) {
		this.sectorJuego.mouseClicked(e);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}