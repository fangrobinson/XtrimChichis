package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaTierra extends Vista{
	
	public VistaTierra(){
		
		this.color = new Color(153,102,0);
		
	}

	@Override
	public ArrayList<String> mostrarOpcionesAccion() {
		
		ArrayList<String> opciones = new ArrayList<String>();
		opciones.add("Crear barraca");
		opciones.add("Crear fabrica");
		opciones.add("Crear deposito de suministros");
		opciones.add("Crear puerto estelar");
		
		
		return opciones;
	}

}
