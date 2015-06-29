package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaNaveCiencia extends VistaIdentificable {
	
	static String nombreNave = "Nave Ciencia";
	
	public VistaNaveCiencia(){
		
		super(nombreNave);
		this.color = new Color(153,153,0);
	
	}

	@Override
	public ArrayList<String> mostrarOpcionesAccion() {
		
		ArrayList<String> opciones = new ArrayList<String>();
		opciones.add("EMP");
		opciones.add("Radiacion");
		opciones.add("Mover");
		
		return opciones;
	}

	@Override
	public void setNombre() {
		
		this.nombre = nombreNave;
		
	}

}
