package xtremecraft.vista;

import java.util.HashMap;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SectorReferenciasDelMapa extends JPanel {
	
	public SectorReferenciasDelMapa(HashMap<Class<?>, Class<?>> vistas) throws InstantiationException, IllegalAccessException{
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Iterator<Class<?>> iter = vistas.keySet().iterator();
		
		while (iter.hasNext()){
			JPanel panelInfoClase = new JPanel();
			panelInfoClase.setLayout(new BoxLayout(panelInfoClase, BoxLayout.X_AXIS));
			
		    @SuppressWarnings("rawtypes")
			Class clave = iter.next();
			String nombreObjetoActual = clave.toString();
			
			Vista vistaNueva = (Vista) vistas.get(clave).newInstance();
			vistaNueva.paintComponents(getGraphics());
			
			panelInfoClase.add(vistaNueva);
			panelInfoClase.add(new JLabel(nombreObjetoActual));
			
			this.add(panelInfoClase);
		}
		
	}

}
