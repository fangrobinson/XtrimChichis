package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

import xtremecraft.mapa.Mapa;

@SuppressWarnings("serial")
public class VistaGas extends Vista{
	
	static String nombreGas = "Gas Vespeno";
	static String estadoInicial = "Volumen de gas: "+Integer.toString(Mapa.getCantidadDeRecursoInicialPorTerreno());
	
	public VistaGas(){
		
		super(nombreGas,estadoInicial);
		this.color = new Color(153,0,153);
		
	}

	@Override
	public ArrayList<String> devolverAcciones() {
		
		ArrayList<String> opciones = new ArrayList<String>();
		opciones.add("Crear recolector de gas vespeno");
		
		return opciones;
		
	}
	
}
