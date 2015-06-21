package xtremecraft.raza;

import java.util.ArrayList;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.Edificio;
import xtremecraft.edificios.Fabrica;

public class IteradorEdificios {
	
	private ArrayList<Edificio> listaEdificios;
	
	public IteradorEdificios(ArrayList<Edificio> lista){
		
		this.listaEdificios = lista;
	}
	
	public int cuantosHayConstruidos(@SuppressWarnings("rawtypes") Class edificio) {
		
		int cant = 0;
		Edificio edificioActual = null;
		for(int posicion=0;posicion<this.listaEdificios.size();posicion++){
			edificioActual = this.listaEdificios.get(posicion);
			if (edificioActual.getClass() == edificio){
					cant++;
			}	
			posicion++;
		}
		return cant;
		
	}
	
	public int cuantosHayDe(Edificio edificio){
		
		int cant = 0;
		Edificio edificioActual = null;
		for(int posicion=0;posicion<this.listaEdificios.size();posicion++){
			edificioActual = this.listaEdificios.get(posicion);
			if (edificioActual.getClass() == edificio.getClass()){
				if(edificioActual.estaEnConstruccion()){
					cant++;
				}	
			}
			posicion++;
		}
		return cant;
		
	}
	
	public Barraca getBarraca(){
		
		Edificio edificioActual = null;
		
		for(int posicion = 0; posicion < this.listaEdificios.size(); posicion++){
			edificioActual = this.listaEdificios.get(posicion);
			if (edificioActual.getClass() == Barraca.class){
				Barraca barraca = (Barraca) edificioActual;
				if(!barraca.estaEnConstruccion()){
					return barraca;
				}
			}
		}
		throw new RazaNoTieneBarracasException();
		
	}
	
	public Fabrica getFabrica(){
		
		Edificio EdificioActual = null;

		for(int posicion = 0; posicion < this.listaEdificios.size(); posicion++){
			EdificioActual = this.listaEdificios.get(posicion);
			if (EdificioActual.getClass() == Fabrica.class){
				Fabrica fabrica = (Fabrica)EdificioActual;
				if(!fabrica.estaEnConstruccion()){
					return fabrica;
				}
			}
		}
		throw new RazaNoTieneFabricasException();
		
	}
	
	public boolean arregloTiene(Edificio unEdificio){
		
		boolean bool = false;
		int i = 0;
		Object obj = null;
		while (i < this.listaEdificios.size()){
			obj = this.listaEdificios.get(i);
			if (obj.getClass() == unEdificio.getClass()){
				Edificio edificio = (Edificio) obj;
				if(!edificio.estaEnConstruccion()){
					bool = true;
				}
			}
			i++;
		}
		return bool;
		
	}

	public boolean arregloPosee(Edificio edificio) {
		
		boolean bool = false;
		int i = 0;
		Edificio obj = null;
		while (i < this.listaEdificios.size()){
			obj = (Edificio) this.listaEdificios.get(i);
			if (obj == edificio){
				bool = true;
			}
			i++;
		}
		return bool;
		
	}

}
