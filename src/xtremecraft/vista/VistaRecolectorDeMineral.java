package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaRecolectorDeMineral extends VistaIdentificable {
	
	static String nombreRecolector = "Recolector de Mineral";
	
	public VistaRecolectorDeMineral(ArrayList<Vista> vistasInferiores){
		
		super(nombreRecolector, vistasInferiores);
		this.color = new Color(0,204,204);
	
	}
	
}
