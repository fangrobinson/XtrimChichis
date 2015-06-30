package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

import xtremecraft.unidades.Marine;

@SuppressWarnings("serial")
public class VistaMarine extends VistaIdentificable{
	
	static String nombreMarine = "Marine";
	static String estadoInicial = "vida: "+Integer.toString(Marine.getVidaInicial());
	
	public VistaMarine(){
		
		super(nombreMarine,estadoInicial);
		this.color = new Color(255,102,0);
	
	}

	@Override
	public void setNombre() {
		
		this.nombre = nombreMarine;
		
	}
	
    public ArrayList<String> devolverAcciones(){
		
		ArrayList<String> acciones = new ArrayList<String>();
		acciones.add("Moverse");
		acciones.add("Atacar");
		return acciones;
	}

}
