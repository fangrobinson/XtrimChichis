package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

import xtremecraft.edificios.RecolectorDeMineral;

@SuppressWarnings("serial")
public class VistaRecolectorDeMineral extends VistaIdentificable {
	
	static String nombreRecolector = "Recolector de Mineral";
	static String estadoInicial = "vida: "+Integer.toString(RecolectorDeMineral.getVidaInicial());
	
	public VistaRecolectorDeMineral(){
		
		super(nombreRecolector,estadoInicial);
		this.color = new Color(0,204,204);
	
	}
	
	public ArrayList<String> devolverAcciones(){
		
		ArrayList<String> acciones = new ArrayList<String>();
		
		return acciones;
	}

}
