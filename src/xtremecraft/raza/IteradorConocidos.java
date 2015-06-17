package xtremecraft.raza;

import java.util.ArrayList;

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
	
}
