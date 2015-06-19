package xtremecraft.unidades;

public class NaveTransporte extends UnidadAerea {

	public NaveTransporte() {
		
		super();
		this.vitalidad = new BarraDeVitalidad(150);
		this.danio = new Danio (0,0);
		this.vision = 8;
		this.tiempoConstruccion = 5;
		this.suministro = 2;
		
	}

}
