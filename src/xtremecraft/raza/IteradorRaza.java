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
    
	public T nextDe(@SuppressWarnings("rawtypes") Class unidadClass){	
    	for (int posicion = this.indice; posicion < this.elementosRaza.size(); posicion++){
    		T construibleActual = this.elementosRaza.get(posicion);
    		if (construibleActual.getClass() == unidadClass){
    			this.indice = posicion++;
    			return construibleActual;
    		}
    	}
        this.indice = this.elementosRaza.size();
        return null;
    }
	
	public boolean tiene(@SuppressWarnings("rawtypes") Class unidadClass){
	
		return (!(this.cuantosHayDe(unidadClass) == 0));
	}

	public boolean tieneCreados(@SuppressWarnings("rawtypes") Class unidadClass){
		
		return (!(this.cuantosHayCreadosDe(unidadClass) == 0));
	}
	
	public boolean elementoPertenece(T elemento){
		
		for(int posicion = 0; posicion < this.elementosRaza.size(); posicion++){
			T construibleActual = this.elementosRaza.get(posicion);
    		if (construibleActual == elemento){
    			return true;
    		}
    	}
		return false;
	}
	
}
