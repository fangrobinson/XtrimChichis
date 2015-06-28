package xtremecraft.vista;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SectorReferenciasDelMapa extends JPanel {
	
	public SectorReferenciasDelMapa(HashMap<Class<?>, Class<?>> vistas) throws InstantiationException, IllegalAccessException{
		
		this.setLayout(new GridLayout(3, 5));
		Iterator<Class<?>> iter = vistas.keySet().iterator();
		
		while (iter.hasNext()){
			JPanel panelInfoClase = new JPanel();
			panelInfoClase.setLayout(new BoxLayout(panelInfoClase, BoxLayout.X_AXIS));
			
		    @SuppressWarnings("rawtypes")
			Class clave = iter.next();
			String nombreObjetoActual = clave.getSimpleName();
			
			Vista vistaNueva = (Vista) vistas.get(clave).newInstance();
			vistaNueva.paintComponents(getGraphics());
			vistaNueva.setPreferredSize(new Dimension(24,24));
			vistaNueva.setMaximumSize(new Dimension(24,24));
			
			JLabel etiquetaNombre = new JLabel(nombreObjetoActual);
			etiquetaNombre.setPreferredSize(new Dimension(24,200));
			
			panelInfoClase.add(vistaNueva);
			panelInfoClase.add(etiquetaNombre);
			
			this.add(panelInfoClase);
		}
		
	}

}
