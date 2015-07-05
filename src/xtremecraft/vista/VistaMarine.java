package xtremecraft.vista;

import java.awt.Color;

@SuppressWarnings("serial")
public class VistaMarine extends VistaIdentificable{
	
	static String nombreMarine = "Marine";
	
	public VistaMarine(){
		
		super(nombreMarine);
		String estadoInicial= "";
		this.observableSeleccionado = new ObservableSeleccionadoInferior();
		this.observableSeleccionado.setClaseVista(this.getClass());
		this.observableSeleccionado.setNombre(this.nombre);
		this.observableSeleccionado.setEstado(estadoInicial);
		this.observableSeleccionado.setClaseVista(this.getClass());
		this.color = new Color(255,102,0);
	
	}

	@Override
	public void setNombre() {
		
		this.nombre = nombreMarine;
		
	}
	

}
