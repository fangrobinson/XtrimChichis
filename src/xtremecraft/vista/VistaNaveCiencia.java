package xtremecraft.vista;

import java.awt.Color;

@SuppressWarnings("serial")
public class VistaNaveCiencia extends VistaIdentificable {
	
	static String nombreNave = "Nave Ciencia";
	
	public VistaNaveCiencia(){
		
		super(nombreNave);
		String estadoInicial= "";
		this.observableSeleccionado = new ObservableSeleccionadoSuperior();
		this.observableSeleccionado.setClaseVista(this.getClass());
		this.observableSeleccionado.setNombre(this.nombre);
		this.observableSeleccionado.setEstado(estadoInicial);
		this.observableSeleccionado.setClaseVista(this.getClass());
		this.color = new Color(153,153,0);
	
	}

}
