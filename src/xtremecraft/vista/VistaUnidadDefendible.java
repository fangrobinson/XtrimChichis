package xtremecraft.vista;

import java.util.ArrayList;


@SuppressWarnings("serial")
public abstract class VistaUnidadDefendible extends VistaIdentificable {
	
	public VistaUnidadDefendible(String nombre,String estadoInicialVisible, ArrayList<String> accionesDisponibles){
		
		super(nombre,estadoInicialVisible,accionesDisponibles);
		
	}
	
	@Override
	public ArrayList<String> mostrarOpcionesAccion() {
		
		ArrayList<String> opciones = new ArrayList<String>();
		opciones.add("Atacar");
		opciones.add("Mover");
		
		return opciones;
	}

}
