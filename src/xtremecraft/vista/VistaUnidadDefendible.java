package xtremecraft.vista;

import java.util.ArrayList;


@SuppressWarnings("serial")
public abstract class VistaUnidadDefendible extends VistaIdentificable {
	
	public VistaUnidadDefendible(String nombre,String estadoInicial){
		
		super(nombre,estadoInicial);
		
	}
	
	@Override
	public ArrayList<String> devolverAcciones() {
		
		ArrayList<String> opciones = new ArrayList<String>();
		opciones.add("Atacar");
		opciones.add("Mover");
		
		return opciones;
	}

}
