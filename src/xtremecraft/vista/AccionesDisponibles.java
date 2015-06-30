package xtremecraft.vista;

//import javax.swing.BorderFactory;
//import javax.swing.DefaultListModel;
//import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

public class AccionesDisponibles extends JPanel implements Observer{
	
	private static final long serialVersionUID = -973667959098244571L;
	private JList<String> list;
	
	public AccionesDisponibles(MapaObservable mapaObservable){
		
		setVisible(true);
		
		mapaObservable.agregarObservadorAVistas(this);
		
		DefaultListModel<String> accionesProcesadas = new DefaultListModel<String>();
		
		this.list = new JList<String>(accionesProcesadas);
		this.list.setBackground(getBackground());
		this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.list.setLayoutOrientation(JList.VERTICAL);
		
		
		// LIST SELECTION LISTENER:
		/* public void valueChanged(ListSelectionEvent e) {
    	       if (e.getValueIsAdjusting() == false) {

        	        if (list.getSelectedIndex() == -1) {
        				//No selection, disable fire button.
            			fireButton.setEnabled(false);

        			} else {
        				//Selection, enable the fire button.
            			fireButton.setEnabled(true);
        			}
    			}
			}
		*/
		add(list);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		
		ObservableSeleccionado vistaObservable = (ObservableSeleccionado) o;
		ArrayList<String> accionesNuevas = vistaObservable.getAcciones();
		
		DefaultListModel<String> accionesProcesadas = new DefaultListModel<String>();
		for (int i = 0; i < accionesNuevas.size(); i++){
			
			accionesProcesadas.addElement(accionesNuevas.get(i));
		}
		
		this.list.setModel(accionesProcesadas);
		
		
	}
	
	//solo para prueba no es el main del juego
	/*public static void main(String args[]){
		
		JFrame frame = new JFrame("ACCIONES DISPONIBLES");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,400);
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setVisible(true);
		
		//agrego lista para probar panel:
		DefaultListModel<String> accionesPrueba = new DefaultListModel<String>();	
		accionesPrueba.addElement("Construir barraca");
		accionesPrueba.addElement("Construir deposito");
		accionesPrueba.addElement("Atacar");
		JPanel panel = new AccionesDisponibles(accionesPrueba);
		panelPrincipal.setBorder(BorderFactory.createLineBorder(panel.getBackground(),25));
		
		panelPrincipal.add(panel);
		frame.add(panelPrincipal);
		
		}
*/
}
