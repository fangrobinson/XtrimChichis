package xtremecraft.recursos;

public class VolcanGasVespeno extends Recurso{
	
	public VolcanGasVespeno(int volumenGasVespeno){
		
		super();
		if(volumenGasVespeno<=0){
			throw new IllegalArgumentException("El volumen de Gas Vespeno contenido en un volcan debe ser un numero entero positivo.");
		}
		this.cantidadDeRecurso = volumenGasVespeno;
	}
	
	public int getVolumenDeGasVespeno(){
		
		return this.cantidadDeRecurso;
	}

	public void ocuparVolcan(){
		
		this.esExplotado=true;
		
	}
	
	public boolean tieneGasVespeno(){
		
		return true;
		
	}
	
	

}
