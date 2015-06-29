package xtremecraft.vista;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

public class VentanaDeAlgoCraft  extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private HashMap<Class<?>, Class<?>> vistas;

	public VentanaDeAlgoCraft(Partida partida) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		this.vistas = this.generarVistas();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Algo Craft Game");
		setPreferredSize(new Dimension(1200, 950));
		
        JMenuBar menuBarra = crearBarraDeMenu();
        setJMenuBar(menuBarra);

		setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		
		JPanel sectorJuego = new SectorJuego(partida, this.vistas);
		sectorJuego.setPreferredSize(new Dimension(1000, 600));
		setVisible(true);
		JPanel panelInformacion = new SectorInformacionParaElUsuario(partida, vistas);
		panelInformacion.setPreferredSize(new Dimension(1000, 100));
		
		sectorJuego.setVisible(true);
		panelInformacion.setVisible(true);
		this.add(sectorJuego);
		this.add(panelInformacion);
		pack();
		setResizable(false);
		setVisible(true);
		
	}

	private static JMenuBar crearBarraDeMenu() {
        JMenuBar barraDeMenu = new JMenuBar();

        JMenu menuArchivo = new JMenu("Archivo");
        JMenu menuAbout = new JMenu("Sobre Nosotros");
        barraDeMenu.add(menuArchivo);
        barraDeMenu.add(menuAbout);
        
        JMenuItem nuevoJuego = new JMenuItem("Nuevo Juego");
        JMenuItem salir = new JMenuItem("Salir");
        salir.addActionListener(new OpcionSalirListener());
        menuArchivo.add(nuevoJuego);
        menuArchivo.add(salir);
        
		return barraDeMenu;
		
	}
	

	static class OpcionSalirListener implements ActionListener{
		
        public void actionPerformed(ActionEvent evento) {
        	System.exit(0);
        }
		
	}
	
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
}