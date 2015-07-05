package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaMarine extends VistaIdentificable{
	
	static String nombreMarine = "Marine";
	
	public VistaMarine(ArrayList<Vista> vistasInferiores){
		
		super(nombreMarine, vistasInferiores);
		this.color = new Color(255,102,0);
	
	}

	@Override
	public void setNombre() {
		
		this.nombre = nombreMarine;
		
	}
	
    public ArrayList<String> devolverAcciones(){
		
		ArrayList<String> acciones = new ArrayList<String>();
		acciones.add("Moverse");
		acciones.add("Atacar");
		return acciones;
	}

}
