package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaEspectro extends VistaIdentificable{
	
	static String nombreEspectro = "Espectro";
	
	public VistaEspectro(ArrayList<Vista> vistasInferiores){
		
		super(nombreEspectro, vistasInferiores);
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
