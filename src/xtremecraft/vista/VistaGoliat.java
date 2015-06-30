package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

import xtremecraft.unidades.Goliat;

@SuppressWarnings("serial")
public class VistaGoliat extends VistaIdentificable{
	
	static String nombreGoliat = "Goliat";
	static String estadoInicial = "vida: "+Integer.toString(Goliat.getVidaInicial());
	
	public VistaGoliat(){
		
		super(nombreGoliat,estadoInicial);
		this.color = new Color(0,102,51);
	
	}

	@Override
	public void setNombre() {
		
		this.nombre = nombreGoliat;
		
	}
	 
    public ArrayList<String> devolverAcciones(){
		
		ArrayList<String> acciones = new ArrayList<String>();
		acciones.add("Moverse");
		acciones.add("Atacar");
		return acciones;
	}
	
}
