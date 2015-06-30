package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

import xtremecraft.unidades.NaveTransporte;

@SuppressWarnings("serial")
public class VistaNaveTransporte extends VistaIdentificable {
	
	static String nombreNave = "Nave Transporte";
	static String estadoInicial = "vida: "+Integer.toString(NaveTransporte.getVidaInicial());
	
	public VistaNaveTransporte(){
		
		super(nombreNave,estadoInicial);
		this.color = new Color(153,153,0);
	
	}

	@Override
	public ArrayList<String> devolverAcciones() {
		
		ArrayList<String> opciones = new ArrayList<String>();
		opciones.add("Subir unidad");
		opciones.add("Bajar unidad");
		opciones.add("Mover");
		
		return opciones;
		
	}

}
