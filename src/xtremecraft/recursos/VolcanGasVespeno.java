package xtremecraft.recursos;

import java.util.ArrayList;

import xtremecraft.edificios.Recolector;

public class VolcanGasVespeno extends Recurso{
	
	public VolcanGasVespeno(int volumenGasVespeno){
		
		super();
		if(volumenGasVespeno<=0){
			throw new VolumenGasVespenoNegativoException();
		}
		this.cantidadDeRecurso = volumenGasVespeno;
	}
	
	public int getVolumenDeGasVespeno(){
		
		return this.cantidadDeRecurso;
	}

	public void ocuparVolcan(){
		
		this.esExplotado=true;
		
	}
	
	public boolean puedeSerExtraidoPor(Recolector nuevoRecolector) {
		
		return nuevoRecolector.puedeExtraerGasVespeno();
		
	}
	

	private String generarEstadoImprimible(){
		
		return "Cantidad de mineral: " + Integer.toString(this.cantidadDeRecurso);
		
	}
	
	@Override
	public String getEstadoImprimible() {
		
		return this.generarEstadoImprimible();
	}	

	public ArrayList<String> devolverAcciones(){
		
		ArrayList<String> acciones = new ArrayList<String>();
		acciones.add("Crear recolector de gas vespeno");
		return acciones;

	}

}
