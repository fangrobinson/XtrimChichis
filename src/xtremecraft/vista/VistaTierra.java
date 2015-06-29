package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaTierra extends Vista{
	
	static String nombreTerreno = "Terreno Tierra";
	
	public VistaTierra(){
		
		super(nombreTerreno);
		this.color = new Color(153,102,0);
		//this.nombre = nombreTerreno;
		
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
