package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaBarraca extends VistaIdentificable implements IdentificableVisible{
	
	static String nombreBarraca = "Barraca";
	
	
	public VistaBarraca(){
		
		super(nombreBarraca);
		this.color = new Color(153,102,0);
	
	}

	@Override
	public void setNombre() {
		
		this.nombre = nombreBarraca;
		
	}

	@Override
	public ArrayList<String> mostrarOpcionesAccion() {
		// TODO Auto-generated method stub
		return null;
	}

}
