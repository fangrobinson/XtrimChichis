package xtremecraft.vista;

import java.awt.Color;

@SuppressWarnings("serial")
public class VistaEspectro extends VistaUnidadDefendible{
	
	static String nombreEspectro = "Espectro";
	
	public VistaEspectro(){
		
		super(nombreEspectro);
		this.color = new Color(0,102,255);
	
	}

	@Override
	public void setNombre() {
		
		this.nombre = nombreEspectro;
		
	}

}
