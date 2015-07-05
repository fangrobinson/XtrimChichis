package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaNaveTransporte extends VistaIdentificable {
	
	static String nombreNave = "Nave Transporte";
	
	public VistaNaveTransporte(ArrayList<Vista> vistasInferiores){
		
		super(nombreNave, vistasInferiores);
		this.color = new Color(153,153,0);
	
	}

}
