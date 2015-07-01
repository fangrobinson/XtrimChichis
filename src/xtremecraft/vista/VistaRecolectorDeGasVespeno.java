package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

import xtremecraft.edificios.RecolectorDeGasVespeno;

@SuppressWarnings("serial")
public class VistaRecolectorDeGasVespeno extends VistaIdentificable {
	
	static String nombreRecolector = "Recolector de Gas";
	static String estadoInicial = RecolectorDeGasVespeno.getEstadoInicial();
	
	public VistaRecolectorDeGasVespeno(){
		
		super(nombreRecolector,estadoInicial);
		this.color = new Color(153,0,153);
	
	}
	
	public ArrayList<String> devolverAcciones(){
		
		ArrayList<String> acciones = new ArrayList<String>();
		
		return acciones;
	}

}
