package xtremecraft.vista;

//import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.TreeMap;



//import javax.swing.JFrame;
import javax.swing.JPanel;

import xtremecraft.edificios.DepositoDeSuministros;
import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.partida.Identificable;
import xtremecraft.partida.Partida;
import xtremecraft.recursos.MinaDeMinerales;
import xtremecraft.recursos.VolcanGasVespeno;

public class MapaObservable extends JPanel{
	// implements MouseListener {
	
	private static final long serialVersionUID = 7787529771808926374L;
	
	private Mapa modeloReal;
	private HashMap<Class<?>, Class<?>> vistas;
	
	public MapaObservable(){};
	
	//public MapaObservable(Mapa mapa, int x, int y){
	public MapaObservable(Partida partida) throws InstantiationException, IllegalAccessException{
		
		//this.addMouseListener(this);
		Mapa mapa = partida.getMapa();
		this.modeloReal = mapa;
		this.vistas = this.generarVistas();
		setBounds(mapa.ancho(), mapa.alto(), 800, 800);
		this.setLayout(new GridLayout(mapa.ancho(), mapa.alto()));
		
				
		TreeMap<Integer, TreeMap<Integer, Celda>> mapaIterable = this.modeloReal.devolverMapaEstatico();
		for (int i = 0; i < this.modeloReal.ancho(); i++){
			for (int j = 0; j < this.modeloReal.alto(); j++){
				Celda celda = mapaIterable.get(i).get(j);
				Terreno terrenoInferior = celda.getCapaInferior();
				Class<?> vistaClase = null;
				Vista vistaNueva = null;
				
				if(terrenoInferior.estaOcupado()){
					Identificable identificable = (Identificable)terrenoInferior.getUbicableEnTerreno();
					int numero =identificable.getJugador().getNumeroDeJugador();
					vistaClase = this.vistas.get(terrenoInferior.getUbicableEnTerreno().getClass());
					IdentificableVisible identificableVisible = (IdentificableVisible) vistaClase.newInstance();
					identificableVisible.setJugador(numero);
					vistaNueva = (Vista) identificableVisible;
				}else{
					if (!terrenoInferior.tieneRecursos()){
						vistaClase = this.vistas.get(terrenoInferior.getClass());
						vistaNueva = (Vista) vistaClase.newInstance();
					}else{
						vistaClase = this.vistas.get(terrenoInferior.getRecurso().getClass());
						vistaNueva = (Vista) vistaClase.newInstance();
					}
				}

				vistaNueva.setCoordenada(terrenoInferior.getCoordenada());

				vistaNueva.paintComponents(getGraphics());
				add(vistaNueva);
				
			}
		}
		
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
