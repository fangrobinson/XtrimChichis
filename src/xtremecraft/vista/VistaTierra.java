package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaTierra extends Vista{
	
	static String nombreTerreno = "Terreno Tierra";
	static String estadoInicial = "No Ocupado";
	
	public VistaTierra(){
		
		super(nombreTerreno,estadoInicial);
		this.color = new Color(146,113,75);
		
	}

	@Override
	public ArrayList<String> devolverAcciones() {
		
		ArrayList<String> opciones = new ArrayList<String>();
		opciones.add("Crear barraca");
		opciones.add("Crear fabrica");
		opciones.add("Crear deposito de suministros");
		opciones.add("Crear puerto estelar");
		
		
		return opciones;
	}

}
