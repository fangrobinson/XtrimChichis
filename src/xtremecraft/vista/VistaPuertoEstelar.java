package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaPuertoEstelar extends VistaIdentificable implements IdentificableVisible{
	
	static String nombrePuerto = "Puerto Estelar";
	
	public VistaPuertoEstelar(String estadoInicialVisible, ArrayList<String> accionesDisponibles){
		
		super(nombrePuerto,estadoInicialVisible,accionesDisponibles);
		this.color = new Color(125,156,190);
	
	}

	@Override
	public void setNombre() {
		
		this.nombre = nombrePuerto;
		
	}

	@Override
	public ArrayList<String> mostrarOpcionesAccion() {
		// TODO Auto-generated method stub
		return null;
	}

}
