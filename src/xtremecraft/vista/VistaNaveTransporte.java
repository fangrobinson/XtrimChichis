package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaNaveTransporte extends VistaIdentificable {
	
	static String nombreNave = "Nave Transporte";
	
	public VistaNaveTransporte(){
		
		super(nombreNave);
		this.color = new Color(153,153,0);
	
	}

	@Override
	public ArrayList<String> mostrarOpcionesAccion() {
		
		ArrayList<String> opciones = new ArrayList<String>();
		opciones.add("Subir unidad");
		opciones.add("Bajar unidad");
		opciones.add("Mover");
		
		return opciones;
		
	}

	@Override
	public void setNombre() {
		
		this.nombre = nombreNave;
		
	}
	
}
