package xtremecraft.vista;

import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import xtremecraft.mapa.Mapa;

@SuppressWarnings("serial")
public class SectorEstadoObjetoSeleccionado extends JPanel implements Observer{

	private MapaObservable mapaObservable;
	
	private JLabel titulo = new JLabel("ESTADO ACTUAL SELECCIONADO"); 
	private JLabel nombreObservable = new JLabel("");
	private JLabel estadoObservable = new JLabel("");
	private JLabel coordenadaObservable = new JLabel("");
	
	public SectorEstadoObjetoSeleccionado(MapaObservable mapaObservable, Mapa mapa) {
		
		
		setLayout(new BoxLayout(this ,BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(20,20,10,10));
		
		this.mapaObservable = mapaObservable;
		this.mapaObservable.agregarObservadorAVistas(this);
				
		this.add(titulo);
		this.add(nombreObservable);
		this.add(coordenadaObservable);
		this.add(estadoObservable);
		
		
		setVisible(true);
		
	}
	
	@Override
	public void update(Observable observableSeleccionado, Object arg1) {
		
		ObservableSeleccionado vista = (ObservableSeleccionado) observableSeleccionado;
		nombreObservable.setText(vista.getNombre());
		coordenadaObservable.setText(vista.getCoordenadaImprimible());
		estadoObservable.setText(vista.getEstado());
		
	}

}
