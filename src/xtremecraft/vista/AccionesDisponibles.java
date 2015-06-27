package xtremecraft.vista;

import java.util.ArrayList;

import javax.swing.JPanel;

public class AccionesDisponibles extends JPanel{
	
	private static final long serialVersionUID = -973667959098244571L;
	
	public AccionesDisponibles(ArrayList<String> accionesPrueba){
		
		/*setLayout(new BorderLayout());
		ListModel acciones = new DefaultListModel();
		 
		

		
		
		for(int posicion=0;posicion<accionesPrueba.size();posicion++){
			//acciones.addElement("Element"+posicion);
		}
		JList list = new JList(acciones);
		list.setLayoutOrientation(JList.VERTICAL);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 80));
		*/
		/* LIST SELECTION LISTENER:
		 * public void valueChanged(ListSelectionEvent e) {
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
		/*add(list);
		
	}
	
	//solo para prueba no es el main del juego
	public static void main(String args[]){
		
		JFrame frame = new JFrame("ACCIONES DISPONIBLES");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,400);
		ArrayList<String> accionesPrueba = new ArrayList<String>();
		
		JPanel panel = new AccionesDisponibles(accionesPrueba);
		
		frame.add(panel);
	*/	
	}

}
