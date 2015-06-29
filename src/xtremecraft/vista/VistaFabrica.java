package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

import xtremecraft.edificios.Fabrica;

@SuppressWarnings("serial")
public class VistaFabrica extends VistaIdentificable implements IdentificableVisible{
	
	static String nombreFabrica = "Fabrica";
	static String estadoInicial = "vida: "+Integer.toString(Fabrica.getVidaInicial());

	public VistaFabrica(){
		
		super(nombreFabrica,estadoInicial);
		this.color = new Color(173,156,121);
	
	}
		
	public ArrayList<String> devolverAcciones(){
		
		ArrayList<String> acciones = new ArrayList<String>();
		acciones.add("Crear goliat");
		return acciones;
	}

}
