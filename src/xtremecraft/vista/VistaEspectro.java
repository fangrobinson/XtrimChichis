package xtremecraft.vista;

import java.awt.Color;

@SuppressWarnings("serial")
public class VistaEspectro extends VistaIdentificable{
	
	static String nombreEspectro = "Espectro";
	
	public VistaEspectro(){
		
		super(nombreEspectro);
		String estadoInicial= "";
		this.observableSeleccionado = new ObservableSeleccionadoSuperior();
		this.observableSeleccionado.setClaseVista(this.getClass());
		this.observableSeleccionado.setNombre(this.nombre);
		this.observableSeleccionado.setEstado(estadoInicial);
		this.observableSeleccionado.setClaseVista(this.getClass());
		this.color = new Color(0,102,255);
	
	}

	@Override
	public void setNombre() {
		
		this.nombre = nombreEspectro;
		
	}

}
