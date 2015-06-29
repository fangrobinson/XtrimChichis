package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaEspectro extends VistaIdentificable{
	
	static String nombreEspectro = "Espectro";
	
	public VistaEspectro(){
		
		super(nombreEspectro);
		this.color = new Color(0,102,255);
	
	}

	@Override
	public void setNombre() {
		
		this.nombre = nombreEspectro;
		
	}

	@Override
	public ArrayList<String> mostrarOpcionesAccion() {
		// TODO Auto-generated method stub
		return null;
	}

}
