package xtremecraft.vista;

import javax.swing.JComboBox;

import javax.swing.JPanel;

public class AccionesDisponibles extends JPanel{
	
	private static final long serialVersionUID = -973667959098244571L;
	
	
	public AccionesDisponibles(){
		
		setVisible(true);
		
		/*
		DefaultListModel<String> accionesProcesadas = new DefaultListModel<String>();
		
		this.list = new JList<String>(accionesProcesadas);
		this.list.setBackground(getBackground());
		this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.list.setLayoutOrientation(JList.VERTICAL);
		*/
		

		
	}

	
	public void actualizarPanel(JComboBox<String> accionesDisponiblesJugador) {
		
		accionesDisponiblesJugador.setVisible(true);
		this.add(accionesDisponiblesJugador);
		this.setVisible(true);
		
	}
	
}
