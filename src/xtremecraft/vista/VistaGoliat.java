package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaGoliat extends VistaIdentificable{
	
	static String nombreGoliat = "Goliat";
	
	public VistaGoliat(ArrayList<Vista> vistasInferiores){
		
		super(nombreGoliat, vistasInferiores);
		this.color = new Color(0,102,51);
	
	}

	@Override
	public void setNombre() {
		
		this.nombre = nombreGoliat;
		
	}
		
}
