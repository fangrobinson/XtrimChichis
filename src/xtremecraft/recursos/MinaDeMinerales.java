package xtremecraft.recursos;

import java.util.ArrayList;

import xtremecraft.edificios.Recolector;
import xtremecraft.mapa.Coordenada;

public class MinaDeMinerales extends Recurso{
	
	public MinaDeMinerales(int numeroDeCristales){
		
		super();
		if(numeroDeCristales<=0){
			throw new NumeroDeCristalesNegativoException();
		}
		this.cantidadDeRecurso=numeroDeCristales;		
		
	}
	
	public int getCantidadDeMinerales(){
		
		return this.cantidadDeRecurso;
		
	}
	
	public void ocuparMinaDeMineral(){
		
		this.esExplotado=true;
		
	}

	
	public boolean puedeSerExtraidoPor(Recolector nuevoRecolector) {
		
		return nuevoRecolector.puedeExtraerMineral();
		
	}

	public Coordenada getUbicacionActual() {

		return null;
		
	}
	
	public ArrayList<String> devolverAcciones(){
		
		ArrayList<String> acciones = new ArrayList<String>();
		acciones.add("Crear recolector de minerales");
		return acciones;
	}

}
