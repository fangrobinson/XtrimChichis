package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaGoliat extends VistaIdentificable{
	
	static String nombreGoliat = "Goliat";
	
	public VistaGoliat(String estadoInicialVisible, ArrayList<String> accionesDisponibles){
		
		super(nombreGoliat,estadoInicialVisible,accionesDisponibles);
		this.color = new Color(0,102,51);
	
	}

	@Override
	public void setNombre() {
		
		this.nombre = nombreGoliat;
		
	}

	@Override
	public ArrayList<String> mostrarOpcionesAccion() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
