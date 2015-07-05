package xtremecraft.vista;

import java.awt.Color;

@SuppressWarnings("serial")
public class VistaGoliat extends VistaIdentificable{
	
	static String nombreGoliat = "Goliat";
	
	public VistaGoliat(){
		
		super(nombreGoliat);
		String estadoInicial= "";
		this.observableSeleccionado = new ObservableSeleccionadoInferior();
		this.observableSeleccionado.setClaseVista(this.getClass());
		this.observableSeleccionado.setNombre(this.nombre);
		this.observableSeleccionado.setEstado(estadoInicial);
		this.observableSeleccionado.setClaseVista(this.getClass());
		this.color = new Color(0,102,51);
	
	}

	@Override
	public void setNombre() {
		
		this.nombre = nombreGoliat;
		
	}
		
}
