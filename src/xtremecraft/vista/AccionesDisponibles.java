package xtremecraft.vista;

//import javax.swing.BorderFactory;
//import javax.swing.DefaultListModel;
//import javax.swing.JFrame;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

public class AccionesDisponibles extends JPanel{
	
	private static final long serialVersionUID = -973667959098244571L;
	
	public AccionesDisponibles(ArrayList<String> acciones){
		
		setVisible(true);
		
		DefaultListModel<String> accionesProcesadas = new DefaultListModel<String>();
		
		for (int i = 0; i < acciones.size(); i++){
			accionesProcesadas.addElement(acciones.get(i));
		}
		
		JList<String> list = new JList<String>(accionesProcesadas);
		list.setBackground(getBackground());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		
		
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
