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
	
	public ArrayList<String> devolverAcciones(){
		
		ArrayList<String> acciones = new ArrayList<String>();
		
		return acciones;
	}

}
