package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaMarine extends VistaIdentificable{
	
	static String nombreMarine = "Marine";
	
	public VistaMarine(String estadoInicialVisible, ArrayList<String> accionesDisponibles){
		
		super(nombreMarine,estadoInicialVisible,accionesDisponibles);
		this.color = new Color(255,102,0);
	
	}

	@Override
	public void setNombre() {
		
		this.nombre = nombreMarine;
		
	}

	@Override
	public ArrayList<String> mostrarOpcionesAccion() {
		// TODO Auto-generated method stub
		return null;
	}

}
