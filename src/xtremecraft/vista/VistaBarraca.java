package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaBarraca extends VistaIdentificable implements IdentificableVisible{
	
	static String nombreBarraca = "Barraca";
	
	public VistaBarraca(){
		
		super(nombreBarraca);
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
