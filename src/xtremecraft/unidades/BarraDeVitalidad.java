package xtremecraft.unidades;

public class BarraDeVitalidad {
	
     int vida;
     int vidaMax;
     
     public BarraDeVitalidad(int vida){
    	 
    	 this.vida = vida;
    	 this.vidaMax = vida;
    	 
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
	
	public void regenerar(int valor){
		
		if (valor + this.vida > vidaMax){
			valor = vidaMax - this.vida;
		}
		this.vida = this.vida + valor;
		
	}
	
	public int vidaMax(){
		
		return this.vidaMax;
		
	}

}
