package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaDeposito extends VistaIdentificable implements IdentificableVisible{
	
	static String nombreDeposito = "Deposito de suministros";
	
	public VistaDeposito(){
		
		super(nombreDeposito);
		this.color = new Color(128,128,128);
	
	}

	@Override
	public ArrayList<String> mostrarOpcionesAccion() {
		return null;
		
	}

	@Override
	public void setNombre() {
		
		this.nombre = nombreDeposito;
		
	}
	
}
