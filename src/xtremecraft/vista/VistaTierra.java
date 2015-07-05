package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaTierra extends Vista{
	
	static String nombreTerreno = "Terreno Tierra";
	
	public VistaTierra(ArrayList<Vista> vistasInferiores){
		
		super(nombreTerreno, vistasInferiores);
		this.color = new Color(153,102,0);
		
	}

}
