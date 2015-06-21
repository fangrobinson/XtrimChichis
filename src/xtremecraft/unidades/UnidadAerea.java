package xtremecraft.unidades;


public abstract class UnidadAerea extends Unidad{

	protected UnidadAerea() {
		super();
	}
	
	public boolean puedeUbicarseEnTierra() {
		return true;
	}

	public boolean puedeUbicarseEnAire() {
		return true;
	}
	
	
	//TODO: revisar lo de elevar x actualizacion en mapa. Tal vez como actualizar ubicacion recibe un terreno 
	//el usuario deberia elegir en el menu si el terreno que quiere es el aereo o el terrestre y utilizamos
	//actualizar ubicacion directamente.
	
	/*public boolean elevar(){
		
		this.estaElevado = true;
		return true;
		
	}
	*/

}
