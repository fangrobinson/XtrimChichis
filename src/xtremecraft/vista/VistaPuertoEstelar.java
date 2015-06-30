package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

import xtremecraft.edificios.PuertoEstelar;

@SuppressWarnings("serial")
public class VistaPuertoEstelar extends VistaIdentificable implements IdentificableVisible{
	
	static String nombrePuerto = "Puerto Estelar";
	static String estadoInicial = "vida: "+Integer.toString(PuertoEstelar.getVidaInicial());
	
	public VistaPuertoEstelar(){
		
		super(nombrePuerto,estadoInicial);
		this.color = new Color(125,156,190);
	
	}

	@Override
	public void setNombre() {
		
		this.nombre = nombrePuerto;
		
	}
	
	public ArrayList<String> devolverAcciones(){
		
		ArrayList<String> acciones = new ArrayList<String>();
		acciones.add("Crear nave transporte");
		acciones.add("Crear nave ciencia");
		acciones.add("Crear espectro");
		return acciones;
	}

}
