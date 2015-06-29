package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

import xtremecraft.mapa.Mapa;

@SuppressWarnings("serial")
public class VistaMinerales extends Vista {
	
	static String nombreMarine = "Mineral";
	static String estadoInicial = "Cantidad de Mineral: "+Integer.toString(Mapa.getCantidadDeRecursoInicialPorTerreno());
	
	public VistaMinerales(){
		
		super(nombreMarine,estadoInicial);
		this.nombre = nombreMarine;
		this.color = new Color(0,204,204);
		
	}

	@Override
	public ArrayList<String> devolverAcciones() {
		
		ArrayList<String> opciones = new ArrayList<String>();
		opciones.add("Crear recolector de mineral");
		
		return opciones;
	}

}
