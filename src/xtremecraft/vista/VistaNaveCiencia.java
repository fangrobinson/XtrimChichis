package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

import xtremecraft.unidades.NaveCiencia;

@SuppressWarnings("serial")
public class VistaNaveCiencia extends VistaIdentificable {
	
	static String nombreNave = "Nave Ciencia";
	static String estadoInicial = "vida: "+Integer.toString(NaveCiencia.getVidaInicial());
	
	public VistaNaveCiencia(){
		
		super(nombreNave,estadoInicial);
		this.color = new Color(153,153,0);
	
	}

	@Override
	public ArrayList<String> devolverAcciones() {
		
		ArrayList<String> opciones = new ArrayList<String>();
		opciones.add("EMP");
		opciones.add("Radiacion");
		opciones.add("Mover");
		
		return opciones;
	}
	
}
