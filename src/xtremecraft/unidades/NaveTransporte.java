package xtremecraft.unidades;

public class NaveTransporte extends UnidadAerea {

	public NaveTransporte() {

		this.vitalidad = new BarraDeVitalidad(150);
		this.danio = new Danio (0,0);
		this.vision = 8;
	}

}
