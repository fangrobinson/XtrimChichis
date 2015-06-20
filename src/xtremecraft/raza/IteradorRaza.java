package xtremecraft.raza;

import java.util.ArrayList;

import xtremecraft.edificios.Construible;


abstract public class IteradorRaza<T extends Construible> {
	
	
	protected ArrayList<T> elementosRaza;
	protected int indice;
	
    public int cuantosHayCreadosDe(@SuppressWarnings("rawtypes") Class unidadClass){
		
		int cant = 0;
		for(int posicion = 0; posicion < this.elementosRaza.size(); posicion++){
			Construible construibleActual = this.elementosRaza.get(posicion);
			if (construibleActual.getClass() == unidadClass){
				if(construibleActual.estaEnConstruccion()){
					cant++;
				}	
			}
		}
		return cant;
	}
    
    public int cuantosHayDe(@SuppressWarnings("rawtypes") Class unidadClass){
		
		int cant = 0;
		for(int posicion = 0; posicion < this.elementosRaza.size(); posicion++){
			Construible construibleActual = this.elementosRaza.get(posicion);
			if (construibleActual.getClass() == unidadClass){
				cant++;	
			}
		}
		return cant;
	}
    
    @SuppressWarnings("unchecked")
	public T nextDe(@SuppressWarnings("rawtypes") Class unidadClass){
    	
    	for (int posicion = this.indice; posicion < this.elementosRaza.size(); posicion++){
    		Construible construibleActual = this.elementosRaza.get(posicion);
    		if (construibleActual.getClass() == unidadClass){
    			this.indice = posicion++;
    			return (T) construibleActual;
    		}
    	}
        this.indice = this.elementosRaza.size();
        return null;
    }

}
