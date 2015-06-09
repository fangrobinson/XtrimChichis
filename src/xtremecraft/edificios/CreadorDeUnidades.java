package xtremecraft.edificios;

import xtremecraft.raza.Terran;

public class CreadorDeUnidades extends Edificio{
	
	public static int vida = 100;
	
	protected CreadorDeUnidades(int fila,int columna){
		
		super(fila,columna,vida);
		
	}

	public static CreadorDeUnidades nuevoEdificioCreadorDeUnidades(Terran razaTerran, int fila, int columna){
		
		CreadorDeUnidades edificioCreadorDeUnidades = new CreadorDeUnidades(fila,columna);
		razaTerran.agregarEdificioCreadorDeUnidades(edificioCreadorDeUnidades);
		return edificioCreadorDeUnidades;
		
	}
}
