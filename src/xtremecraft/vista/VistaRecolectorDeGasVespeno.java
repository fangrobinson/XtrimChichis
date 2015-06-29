package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaRecolectorDeGasVespeno extends VistaIdentificable {
	
	static String nombreRecolector = "Recolector de Gas";
	
	public VistaRecolectorDeGasVespeno(String estadoInicialVisible, ArrayList<String> accionesDisponibles){
		
		super(nombreRecolector,estadoInicialVisible,accionesDisponibles);
		this.color = new Color(153,0,153);
	
	}
	
	@Override
	public ArrayList<String> mostrarOpcionesAccion() {
		return null;
	}

}
