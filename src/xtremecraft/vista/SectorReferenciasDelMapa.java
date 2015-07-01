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
		
		this.setLayout(new GridLayout(2, 8, 1, 1));
		Iterator<Class<?>> iter = vistas.keySet().iterator();
		
		while (iter.hasNext()){
			JPanel panelInfoClase = new JPanel();
			panelInfoClase.setLayout(new BoxLayout(panelInfoClase, BoxLayout.X_AXIS));
			
		    @SuppressWarnings("rawtypes")
			Class clave = iter.next();
			String nombreObjetoActual = clave.getSimpleName();
			
			Vista vistaNueva = (Vista) vistas.get(clave).newInstance();
			vistaNueva.paintComponents(getGraphics());
			vistaNueva.setPreferredSize(new Dimension(20,20));
			vistaNueva.setMaximumSize(new Dimension(20,20));
			
			JLabel etiquetaNombre = new JLabel(nombreObjetoActual);
			etiquetaNombre.setPreferredSize(new Dimension(10,250));
			
			panelInfoClase.add(vistaNueva);
			panelInfoClase.add(etiquetaNombre);
			panelInfoClase.setMaximumSize(new Dimension(30,270));
			panelInfoClase.setMinimumSize(new Dimension(30,270));
			this.add(panelInfoClase);
		}
		
	}

}
