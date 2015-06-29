package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaFabrica extends VistaIdentificable implements IdentificableVisible{
	
	static String nombreFabrica = "Fabrica";

	public VistaFabrica(String estadoInicialVisible, ArrayList<String> accionesDisponibles){
		
		super(nombreFabrica,estadoInicialVisible,accionesDisponibles);
		this.color = new Color(173,156,121);
	
	}
	
	@Override
	public ArrayList<String> mostrarOpcionesAccion() {
		ArrayList<String> opciones = new ArrayList<String>();
		opciones.add("Crear goliat");
		
		return opciones;
	}

}
