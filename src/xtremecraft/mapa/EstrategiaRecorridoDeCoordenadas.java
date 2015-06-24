package xtremecraft.mapa;

import java.util.ArrayList;


public class EstrategiaRecorridoDeCoordenadas{
	
	private static int direccionesDeMovimiento = 8;
	private ArrayList<EstrategiaDeMovimiento> estrategiasDeAvance;
	private int direccionActual;
	private Coordenada coordenadaInicio;
	
	public EstrategiaRecorridoDeCoordenadas(Coordenada coordenadaOrigen){
		
		this.direccionActual = 0;
		this.coordenadaInicio = coordenadaOrigen;
		this.estrategiasDeAvance = new ArrayList<EstrategiaDeMovimiento>();
		this.estrategiasDeAvance.add(new AvanceAIzquierda());
		this.estrategiasDeAvance.add(new AvanceArribaIzquierda());
		this.estrategiasDeAvance.add(new AvanceArriba());
		this.estrategiasDeAvance.add(new AvanceArribaDerecha());
		this.estrategiasDeAvance.add(new AvanceADerecha());
		this.estrategiasDeAvance.add(new AvanceAbajoDerecha());
		this.estrategiasDeAvance.add(new AvanceAbajo());
		this.estrategiasDeAvance.add(new AvanceAbajoIzquierda());
		
	}
	
	public static int getNumeroDeDireccionesDeMovimiento(){
		
		return direccionesDeMovimiento;
		
	}
	
	public void cambiarDireccionDeMovimiento(Coordenada coordenadaOrigen){
		
		this.coordenadaInicio = coordenadaOrigen;
		if((direccionActual+1) > (direccionesDeMovimiento-1))direccionActual = 0;
		else direccionActual += 1;
		
	}
	
	public Coordenada avanzar(){
		
		this.coordenadaInicio = this.estrategiasDeAvance.get(direccionActual).avanzar(this.coordenadaInicio);
		return this.coordenadaInicio;
		
	}

}
