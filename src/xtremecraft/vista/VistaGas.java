package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaGas extends Vista{
	
	static String nombreGas = "Gas Vespeno";
	
	public VistaGas(ArrayList<Vista> vistasInferiores){
		
		super(nombreGas, vistasInferiores);
		this.color = new Color(153,0,153);
		
	}
	
}
