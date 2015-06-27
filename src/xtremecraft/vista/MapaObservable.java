package xtremecraft.vista;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import xtremecraft.edificios.DepositoDeSuministros;
import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.recursos.MinaDeMinerales;
import xtremecraft.recursos.VolcanGasVespeno;

public class MapaObservable extends JPanel{
	// implements MouseListener {
	
	private static final long serialVersionUID = 7787529771808926374L;
	
	private Mapa modeloReal;
	private HashMap<Class<?>, Class<?>> vistas;
	
	public MapaObservable(){};
	
	//public MapaObservable(Mapa mapa, int x, int y){
	public MapaObservable(Mapa mapa) throws InstantiationException, IllegalAccessException{
		
		//this.addMouseListener(this);
		
		this.modeloReal = mapa;
		this.vistas = this.generarVistas();
		this.setLayout(new GridLayout(this.modeloReal.ancho(),this.modeloReal.alto()));
		
				
		TreeMap<Integer, TreeMap<Integer, Celda>> mapaIterable = this.modeloReal.devolverMapaEstatico();
		for (int i = 0; i < this.modeloReal.ancho(); i++){
			for (int j = 0; j < this.modeloReal.alto(); j++){
				Celda celda = mapaIterable.get(i).get(j);
				Terreno terrenoInferior = celda.getCapaInferior();
				Class<?> vistaClase = null;
				if(terrenoInferior.estaOcupado()){
					vistaClase = this.vistas.get(terrenoInferior.getUbicableEnTerreno().getClass());
				}else{
					if (!terrenoInferior.tieneRecursos()){
						vistaClase = this.vistas.get(terrenoInferior.getClass());
					}else{
						vistaClase = this.vistas.get(terrenoInferior.getRecurso().getClass());
					}
				}
										
				Vista vistaNueva = (Vista) vistaClase.newInstance();
				vistaNueva.paintComponents(getGraphics());
				add(vistaNueva);
				
			}
		}
		
		 
		//setSize(800,800);
		
	}
	
	private HashMap<Class<?>, Class<?>> generarVistas() {
		
		HashMap<Class<?>, Class<?>> vistas = new HashMap<Class<?>, Class<?>>();
		
		vistas.put(Tierra.class, VistaTierra.class);
		vistas.put(VolcanGasVespeno.class, VistaGas.class);
		vistas.put(MinaDeMinerales.class, VistaMinerales.class);
		//TODO: distinguir que deposito es de cada jugador.
		vistas.put(DepositoDeSuministros.class, VistaDeposito.class);
		
		return vistas;
	}
	/*
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
	*/
}
