package xtremecraft.raza;

import java.util.ArrayList;

import xtremecraft.unidades.Unidad;

public class IteradorUnidades {
	

	private ArrayList<Unidad> listaUnidades;
	
	public IteradorUnidades(ArrayList<Unidad> lista){
		
		this.listaUnidades = lista;
	}
	
	public int cuantosHayDe(@SuppressWarnings("rawtypes") Class unidadClass){
		
		int cant = 0;
		Unidad unidadActual = null;
		for(int posicion = 0; posicion < this.listaUnidades.size(); posicion++){
			unidadActual = this.listaUnidades.get(posicion);
			if (unidadActual.getClass() == unidadClass){
				if(unidadActual.estaEnConstruccion()){
					cant++;
				}	
			}
		}
		return cant;
	}
	
	public boolean arregloPosee(Unidad unidad) {
		
		boolean bool = false;
		Unidad obj = null;
		for(int posicion = 0; posicion < this.listaUnidades.size(); posicion++){
			obj = (Unidad) this.listaUnidades.get(posicion);
			if (obj == unidad){
				bool = true;
			}
		}
		return bool;
	}

}
