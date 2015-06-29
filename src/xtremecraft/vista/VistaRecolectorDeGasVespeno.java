package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaRecolectorDeGasVespeno extends VistaIdentificable {
	
	static String nombreRecolector = "Recolector de Gas";
	
	public VistaRecolectorDeGasVespeno(){
		
		super(nombreRecolector);
		this.color = new Color(153,0,153);
	
	}
	
	public ArrayList<String> devolverAcciones(){
		
		ArrayList<String> acciones = new ArrayList<String>();
		
		return acciones;
	}

}
