package xtremecraft.raza;

import java.util.ArrayList;

import xtremecraft.edificios.Edificio;
import xtremecraft.unidades.Unidad;

public class IteradorConocidos {
	private ArrayList<?> lista;
	
	public IteradorConocidos(ArrayList<?> lista){
		this.lista = lista;
	}

	public int cuantosHayDe(Object instancia){
		int i = 0;
		int cant = 0;
		Object obj = null;
		while(i < this.lista.size()){
			obj = this.lista.get(i);
			if (obj.getClass() == instancia.getClass()){
				cant++;
			}
			i++;
		}
		return cant;
	}
	
	public boolean arregloTiene(Object instancia){
		boolean bool = false;
		int i = 0;
		Object obj = null;
		while (i < this.lista.size()){
			obj = this.lista.get(i);
			if (obj.getClass() == instancia.getClass()){
				bool = true;
			}
			i++;
		}
		return bool;
	}

	public boolean arregloPosee(Edificio edif) {
		boolean bool = false;
		int i = 0;
		Edificio obj = null;
		while (i < this.lista.size()){
			obj = (Edificio) this.lista.get(i);
			if (obj == edif){
				bool = true;
			}
			i++;
		}
		return bool;
	}
	
	public boolean arregloPosee(Unidad unid) {
		boolean bool = false;
		int i = 0;
		Unidad obj = null;
		while (i < this.lista.size()){
			obj = (Unidad) this.lista.get(i);
			if (obj == unid){
				bool = true;
			}
			i++;
		}
		return bool;
	}
	
}
