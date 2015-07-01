package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

import xtremecraft.edificios.Barraca;

@SuppressWarnings("serial")
public class VistaBarraca extends VistaIdentificable implements IdentificableVisible{
	
	static String nombreBarraca = "Barraca";
	static String estadoInicial = "vida: "+Integer.toString(Barraca.getVidaInicial());
	
	public VistaBarraca(){
		
		super(nombreBarraca,estadoInicial);
		this.color = new Color(153,12,0);
	
	}

	@Override
	public void setNombre() {
		
		this.nombre = nombreBarraca;
		
	}
	
	public ArrayList<String> devolverAcciones(){
		
		ArrayList<String> acciones = new ArrayList<String>();
		acciones.add("Crear marine");
		return acciones;
	}

}
