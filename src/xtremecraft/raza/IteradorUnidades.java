package xtremecraft.raza;

import java.util.ArrayList;

import xtremecraft.unidades.Unidad;

public class IteradorUnidades {
	

	private ArrayList<Unidad> listaUnidades;
	
	public IteradorUnidades(ArrayList<Unidad> lista){
		
		this.listaUnidades = lista;
	}
	
	public int cuantosHayDe(@SuppressWarnings("rawtypes") Class unidadClass){
		int i = 0;
		int cant = 0;
		Unidad unidadActual = null;
		while(i < this.listaUnidades.size()){
			unidadActual = this.listaUnidades.get(i);
			if (unidadActual.getClass() == unidadClass){
				if(unidadActual.estaEnConstruccion()){
					cant++;
				}	
			}
			i++;
		}
		return cant;
	}
	
	public boolean arregloPosee(Unidad unidad) {
		
		boolean bool = false;
		int i = 0;
		Unidad obj = null;
		while (i < this.listaUnidades.size()){
			obj = (Unidad) this.listaUnidades.get(i);
			if (obj == unidad){
				bool = true;
			}
			i++;
		}
		return bool;
	}

}
