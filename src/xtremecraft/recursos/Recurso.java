package xtremecraft.recursos;

public abstract class Recurso {
	
	protected boolean esExplotado;
	protected int cantidadDeRecurso;
	
	public Recurso(){
		
		esExplotado=false;
		
	}
	
	public boolean estaSiendoExplotado() {
		
		return this.esExplotado;
		
	}
	
	public int explotar(int i) {
		
		if (i > this.cantidadDeRecurso){
			i = this.cantidadDeRecurso;
		}
		
		this.cantidadDeRecurso-=i;
		
		return i;
		
	}

}
