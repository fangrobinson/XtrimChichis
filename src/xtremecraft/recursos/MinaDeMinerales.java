package xtremecraft.recursos;

public class MinaDeMinerales extends Recurso{
	
	//atributo cantidadDeMinerales tal vez sea de utilidad para la interfaz grafica (???)
	private int cantidadDeMinerales;
	
	
	public MinaDeMinerales(int numeroDeCristales){
		
		super();
		if(numeroDeCristales<=0){
			throw new IllegalArgumentException("La cantidad de minerales en un nodo mineral debe ser un numero entero positivo.");
		}
		cantidadDeMinerales=numeroDeCristales;
		
		
	}
	
	
	public int getCantidadDeMinerales(){
		
		return this.cantidadDeMinerales;
	}


	public boolean estaSiendoExplotado() {
		
		return this.esExplotado;
		
	}
	
	public void ocuparNodo(){
		
		this.esExplotado=true;
		
	}


	public int explotar(int i) {
		
		if (i > this.cantidadDeMinerales){
			i = this.cantidadDeMinerales;
		}
		
		this.cantidadDeMinerales-=i;
		
		return i;
		
	}
	
	
	

}
