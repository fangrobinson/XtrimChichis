package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

import xtremecraft.unidades.Espectro;

@SuppressWarnings("serial")
public class VistaEspectro extends VistaIdentificable{
	
	static String nombreEspectro = "Espectro";
	static String estadoInicial = "vida: "+Integer.toString(Espectro.getVidaInicial());
	
	public VistaEspectro(){
		
		super(nombreEspectro,estadoInicial);
		this.color = new Color(0,102,255);
	
	}

	@Override
	public void setNombre() {
		
		this.nombre = nombreEspectro;
		
	}
	
    public ArrayList<String> devolverAcciones(){
		
		ArrayList<String> acciones = new ArrayList<String>();
		acciones.add("Moverse");
		acciones.add("Atacar");
		return acciones;
	}

}
