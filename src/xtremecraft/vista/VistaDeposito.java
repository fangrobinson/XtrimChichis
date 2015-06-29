package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaDeposito extends VistaIdentificable implements IdentificableVisible{
	
	public VistaDeposito(){
		
		super();
		this.color = new Color(128,128,128);
	
	}

	@Override
	public ArrayList<String> mostrarOpcionesAccion() {
		return null;
		
	}
	
}
