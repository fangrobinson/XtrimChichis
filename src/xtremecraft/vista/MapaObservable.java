package xtremecraft.vista;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.JPanel;

import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.recursos.MinaDeMinerales;
import xtremecraft.recursos.VolcanGasVespeno;

@SuppressWarnings("serial")
public class MapaObservable extends JPanel implements MouseListener {
	
	private Mapa modeloReal;
	private HashMap<Class<?>, Class<?>> vistas;
	
	public MapaObservable(){};
	
	public MapaObservable(Mapa mapa, int x, int y){
		
		this.addMouseListener(this);
		this.setVisible(true);
		this.setBounds(x, y, mapa.ancho(), mapa.alto());
		this.setLayout(new GridLayout(mapa.ancho(), mapa.alto()));
		
		this.modeloReal = mapa;
		this.vistas = this.generarVistas();
		
	}
	
	public void mostrarMapa() throws InstantiationException, IllegalAccessException{
		
		TreeMap<Integer, TreeMap<Integer, Celda>> mapaIterable = this.modeloReal.devolverMapaEstatico();
		for (int i = 0; i < this.modeloReal.ancho(); i++){
			for (int j = 0; j < this.modeloReal.alto(); j++){
				Celda celda = mapaIterable.get(i).get(j);
				Terreno terrenoInferior = celda.getCapaInferior();
				Class<?> vistaClase = null;
				if (!terrenoInferior.tieneRecursos()){
					vistaClase = this.vistas.get(terrenoInferior);
				}else{
					vistaClase = this.vistas.get(terrenoInferior.getRecurso());
				}
				Vista vistaNueva = (Vista) vistaClase.newInstance();
				this.add(vistaNueva);
				
			}
		}
		
		
	}

	private HashMap<Class<?>, Class<?>> generarVistas() {
		
		HashMap<Class<?>, Class<?>> vistas = new HashMap<Class<?>, Class<?>>();
		
		vistas.put(Tierra.class, VistaTierra.class);
		vistas.put(VolcanGasVespeno.class, VistaGas.class);
		vistas.put(MinaDeMinerales.class, VistaMinerales.class);
		
		return vistas;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
