package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaDeposito extends VistaIdentificable implements IdentificableVisible{
	
	static String nombreDeposito = "Deposito de suministros";
	
	public VistaDeposito(String estadoInicialVisible, ArrayList<String> accionesDisponibles){
		
		super(nombreDeposito,estadoInicialVisible,accionesDisponibles);
		this.color = new Color(128,128,128);
	
	}

	@Override
	public ArrayList<String> mostrarOpcionesAccion() {
		return null;
		
	}

}
