package xtremecraft.raza;

import java.util.ArrayList;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.Edificio;
import xtremecraft.edificios.Fabrica;

public class IteradorEdificios extends IteradorRaza<Edificio> {
	
	
	public IteradorEdificios(ArrayList<Edificio> lista){
		
		this.elementosRaza = lista;
	}
	
	
	public Barraca getBarraca(){
		
	    Barraca barraca = (Barraca) this.nextDe(Barraca.class);
	    if (barraca == null){
	    	throw new RazaNoTieneBarracasException();
	    }
	    return barraca;
		
	}
	
	public Fabrica getFabrica(){
		
		Fabrica fabrica = (Fabrica) this.nextDe(Fabrica.class);
	    if (fabrica == null){
	    	throw new RazaNoTieneFabricasException();
	    }
	    return fabrica;
		
	}

	
}
