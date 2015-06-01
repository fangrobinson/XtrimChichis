package xtremecraft.raza;
import java.util.ArrayList;


import xtremecraft.edificios.CentroDeMineral;
import xtremecraft.unidades.Unidad;

public class Terran extends Raza {
	
	private boolean estaViva;
	private ArrayList<Unidad> unidadesTerran;
	private ArrayList<CentroDeMineral> centrosDeMineralTerran;
		
	public Terran(){
		
		this.estaViva=true;
		this.centrosDeMineralTerran=new ArrayList<CentroDeMineral>();
		this.unidadesTerran=new ArrayList<Unidad>();
				
	}

	public boolean estaViva() {
		
		return this.estaViva;
		
	}

	public void agregarCentroMineral(CentroDeMineral nuevoCentroMineral) {
		
		this.centrosDeMineralTerran.add(nuevoCentroMineral);
		
	}



	

}
