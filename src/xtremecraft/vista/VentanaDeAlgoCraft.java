package xtremecraft.vista;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import xtremecraft.partida.Partida;

public class VentanaDeAlgoCraft extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private HashMap<Class<?>, Class<?>> vistas;
	private SectorJuego sectorJuego;
	private Partida partida;
	
	public VentanaDeAlgoCraft(HashMap<Class<?>, Class<?>> vistas) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		this.vistas = vistas;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Algo Craft Game");
		setPreferredSize(new Dimension(1200, 950));
		
        JMenuBar menuBarra = crearBarraDeMenu(this);
        setJMenuBar(menuBarra);

		setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		pack();
		setResizable(false);
		setVisible(true);

	}

	private void agregarSectoresDeLaVentana(){
		//Agregar aca los componentes de este Frame
		this.sectorJuego = new SectorJuego(this.partida, this.vistas);
		sectorJuego.setPreferredSize(new Dimension(1000, 600));
		setVisible(true);
		JPanel panelInformacion = new SectorInformacionParaElUsuario(this.partida, vistas);
		panelInformacion.setPreferredSize(new Dimension(1000, 100));
		
		sectorJuego.setVisible(true);
		panelInformacion.setVisible(true);
		this.add(sectorJuego);
		this.add(panelInformacion);
		pack();
	}

	private static JMenuBar crearBarraDeMenu(VentanaDeAlgoCraft ventana) {
        JMenuBar barraDeMenu = new JMenuBar();

        JMenu menuArchivo = new JMenu("Archivo");
        JMenu menuAbout = new JMenu("Sobre Nosotros");
        barraDeMenu.add(menuArchivo);
        barraDeMenu.add(menuAbout);
        
        JMenuItem nuevoJuego = new JMenuItem("Nuevo Juego");
        JMenuItem salir = new JMenuItem("Salir");
        nuevoJuego.addActionListener(new OpcionNuevoJuegoListener(ventana));
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
	
	
	static class OpcionNuevoJuegoListener implements ActionListener{
		
		VentanaDeAlgoCraft ventana;
		
		public OpcionNuevoJuegoListener(VentanaDeAlgoCraft ventana){
			this.ventana = ventana;
		}
		
        public void actionPerformed(ActionEvent evento) {
        	new PedirJugadores(ventana);
        	this.ventana.agregarSectoresDeLaVentana();
        }
		
	}


	public void agregarPartida(ArrayList<String> nombresJugadores) {
		
		this.partida = new Partida(nombresJugadores);
		
	}

}