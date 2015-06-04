package xtremecraft.unidades;

import xtremecraft.interfaces.Atacable;
import xtremecraft.interfaces.Defendible;
import xtremecraft.mapa.Coordenada;

public abstract class Unidad implements Atacable,Defendible{
	BarraDeVitalidad vitalidad;
	Daño daño;
	int vision;
	Coordenada coordenadas;
	
    public void recibirDaño(int daño){
    	
        vitalidad.recibirAtaque(daño);
    	
    }
    
    public void atacar (Atacable atacado, String medio){
    	
    	int daño = this.daño.devolverDaño(medio);
    	atacado.recibirDaño(daño);
    }
    
    public int getVida(){
    	
    	return this.vitalidad.devolverValor();
    	
    }
    
    public int getRadioVision(){
    	
    	return this.vision;
    	
    }

}
