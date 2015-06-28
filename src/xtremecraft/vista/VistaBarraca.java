package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaBarraca extends VistaEdificio implements IdentificableVisible{
	
	
	public VistaBarraca(){
		
		this.color = new Color(153,102,0);
	
	}
	
	@Override
	public ArrayList<String> mostrarOpcionesAccion() {
		ArrayList<String> opciones = new ArrayList<String>();
		opciones.add("Crear marine");
		
		return opciones;
	}

}
