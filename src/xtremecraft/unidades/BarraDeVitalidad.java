package xtremecraft.unidades;

public class BarraDeVitalidad {
     int vida;
     
     public BarraDeVitalidad(int vida){
    	 this.vida = vida;
     }

	public void recibirAtaque(int i) {
		if (i>this.vida){
			i = this.vida;
		}
		this.vida-=i;
		
	}

	public int devolverValor() {
		return this.vida;
	}
	
}
