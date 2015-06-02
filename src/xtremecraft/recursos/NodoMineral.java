package xtremecraft.recursos;

public class NodoMineral {
	
	//atributo cantidadDeMinerales tal vez sea de utilidad para la interfaz grafica (???)
	private int cantidadDeMinerales;
	private boolean esExplotado;
	
	public NodoMineral(int numeroDeCristales){
		
		if(numeroDeCristales<=0){
			throw new IllegalArgumentException("La cantidad de minerales en un nodo mineral debe ser un numero entero positivo.");
		}
		cantidadDeMinerales=numeroDeCristales;
		esExplotado=false;
		
	}
	
	
	public int getCantidadDeMinerales(){
		
		return this.cantidadDeMinerales;
	}


	public boolean estaSiendoExplotado() {
		
		return esExplotado;
		
	}
	
	public void ocuparNodo(){
		
		this.esExplotado=true;
		
	}
	
	

}
