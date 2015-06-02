package xtremecraft.unidades;

public abstract class Unidad {
	BarraDeVitalidad vitalidad;
	Daño daño;
	int vision;
	
    public void recibirDaño(int daño){
        vitalidad.recibirAtaque(daño);
    	
    }
    
    public void atacar (Unidad otraUnidad, String medio){
    	int daño = this.daño.devolverDaño(medio);
    	otraUnidad.recibirDaño(daño);
    }
    
    public int getVida(){
    	
    	return this.vitalidad.devolverValor();
    	
    }
    
    public int getRadioVision(){
    	
    	return this.vision;
    	
    }

}
