package xtremecraft.unidades;

public abstract class Unidad {
	BarraDeVitalidad vitalidad;
	Daño daño;
	
    public void recibirDaño(int daño){
        vitalidad.recibirAtaque(daño);
    	
    }
    
    public void atacar (Unidad otraUnidad, String medio){
    	int daño = this.daño.devolverDaño(medio);
    	otraUnidad.recibirDaño(daño);
    }

}
