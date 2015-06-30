package xtremecraft.unidades;

import java.util.ArrayList;

import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.partida.Jugador;

public class NaveTransporte extends UnidadAerea {
	
	ArrayList<Unidad> unidadesTransportadas;
	static int capacidadMaxima = 8;
	private int minerales = 100;
	private int gas = 100;
	private static int vidaInicial = 150;
	
	public NaveTransporte(Jugador jugador) {
		
		super(jugador);
		this.cobrar();
		this.vitalidad = new BarraDeVitalidad(vidaInicial);
		this.danio = new Danio (0,0);
		this.vision = 8;
		this.tiempoConstruccion = 5;
		this.suministro = 2;
		this.unidadesTransportadas = new ArrayList<Unidad>();
		
	}
	
	public void transportarNuevaUnidad(Unidad unidad){

		/*if((unidadesTransportadas.size() >= capacidadMaxima)){
			throw new NaveConCapacidadMaximaException();
		}*/
		if(!unidad.puedoVer(this.getUbicacionActual())){
			throw new NaveFueraDelRangoDeVisionUnidadException();
		}
		unidadesTransportadas.add(unidad);
		unidad.actualizarUbicacion(this);
		
	}
	
	public void actualizarUbicacion(Terreno terreno){
		
		if(!this.estaUbicada){
			this.terrenoActual = terreno;
			this.estaUbicada = true;
		}
		else{
			if(!this.puedoVer(terreno.getCoordenada())) throw new UbicacionNoValidaException();
			this.terrenoActual.desocupar();
			this.terrenoActual = terreno;
			this.terrenoActual.ubicar(this);
			for(int pos=0;pos<this.unidadesTransportadas.size();pos++){
				this.unidadesTransportadas.get(pos).actualizarUbicacion(this);
			}
		}
		
	 }
	
	public void bajarUnidad(Mapa mapa,Unidad unaUnidad){
		
		ArrayList<Celda> celdasAdyacentes = mapa.obtenerCeldasAdyacentesAlUbicable(this);
		int posicion = 0;
		
		for(posicion = 0;posicion<celdasAdyacentes.size();posicion++){
			Celda celdaActual = celdasAdyacentes.get(posicion);
			try{
				mapa.ubicar(unaUnidad,celdaActual);
			}catch(RuntimeException NoSePudoOcuparElTerrenoException){
				continue;
			}
		}
		//TODO: verificar si en este punto queremos lanzar la excepcion o 
		//seguir devolviendo false:
		if(posicion == celdasAdyacentes.size()) throw new UnidadNoSePudoBajarDeLaNaveException();
		
	}
	
	public ArrayList<Unidad> getUnidadesTransportadas(){
		
		return this.unidadesTransportadas;
		
	}

	public void cobrar(){
		
		this.jugador.nacion().quitarMinerales(this.minerales);
		this.jugador.nacion().quitarGas(this.gas);
		
	}

}
