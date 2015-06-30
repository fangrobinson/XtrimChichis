package xtremecraft.vista;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import xtremecraft.mapa.Coordenada;
import xtremecraft.partida.Partida;

public class SectorSeleccionInformacion extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	private JComboBox<String> accionesDisponiblesJugador;
	private AccionesDisponibles panelAccionesDisponibles;
	private Partida partida;
	private MapaObservable mapa;
	
	public SectorSeleccionInformacion(MapaObservable mapaObservable, Partida partida){
		
		this.mapa = mapaObservable;
		setLayout(new BoxLayout(this ,BoxLayout.Y_AXIS));
		setBackground(new Color(255,255,255));
		
		mapaObservable.agregarObservadorAVistas(this);
		
		JPanel panelSeleccionObjeto = new SectorEstadoObjetoSeleccionado(mapaObservable,partida.getMapa());
		
		this.add(panelSeleccionObjeto);
		
		this.panelAccionesDisponibles = new AccionesDisponibles();
		setBackground(new Color(255,255,255));
		panelAccionesDisponibles.setVisible(true);
		
		this.add(panelAccionesDisponibles);
		
		setVisible(true);
		

	}

	@Override
	public void update(Observable observado, Object arg1) {
		
		ObservableSeleccionado observadoActual = (ObservableSeleccionado) observado;
		Coordenada coordenada = observadoActual.getCoordenadaActualSeleccionado();
		//original:
		//JComboBox opciones = observadoActual.getAccionesDisponibles();
		//prueba:
		String[] opcionesPrueba = {"Construir barraca", "Construir deposito"}; 
		this.accionesDisponiblesJugador = new JComboBox<String>(opcionesPrueba);
		Class<?> claseControlador = observadoActual.getClaseControladorDeAccion();
		AbstractAction controlador;
		try {
			////pasarle el mapaObservable al controlador para que lo actualize??
			controlador = (AbstractAction) claseControlador.getDeclaredConstructor(Partida.class,JComboBox.class,Coordenada.class).newInstance(this.partida,this.accionesDisponiblesJugador,coordenada);
			this.accionesDisponiblesJugador.addActionListener(controlador);
			
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.panelAccionesDisponibles.actualizarPanel(this.accionesDisponiblesJugador);
	
		
	}
	
}
