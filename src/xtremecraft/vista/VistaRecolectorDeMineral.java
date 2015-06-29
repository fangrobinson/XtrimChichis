package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaRecolectorDeMineral extends VistaIdentificable {
	
	static String nombreRecolector = "Recolector de Mineral";
	
	public VistaRecolectorDeMineral(String estadoInicialVisible, ArrayList<String> accionesDisponibles){
		
		super(nombreRecolector,estadoInicialVisible,accionesDisponibles);
		this.color = new Color(0,204,204);
	
	}
	
	@Override
	public ArrayList<String> mostrarOpcionesAccion() {
		
		return null;
		
	}

}
