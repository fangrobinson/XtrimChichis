package xtremecraft.unidades;

import xtremecraft.raza.Terran;
import java.util.ArrayList;

import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Mapa;
import xtremecraft.unidades.UnidadAerea;

public class NaveCiencia extends UnidadAerea{
	
	private int energiaMax = 200;
	private int energia = 50;
	private int aumentoDeEnergiaEnTurno = 10;
	private int minerales = 100;
	private int gas = 225; 
	//private int radioMisilEMP = 3;
	//private int costoMisilEMP = 100;
	private int radioMisilEMP = 3;
	private int costoMisilEMP = 100;
	private int costoRadiacion = 100;
	
	public NaveCiencia(Terran raza){
		
		super();
		this.cobrar(raza);
		this.vitalidad = new BarraDeVitalidad(200);
		this.danio = new Danio(0,0);
		this.vision = 10;
		this.tiempoConstruccion = 10;
		this.suministro = 2;
		
	}

	public int getEnergia(){
		
		return this.energia;
		
	}
	
	public boolean estaVivo(){
		
		return (this.vitalidad.getValor() != 0);
		
	}
	
	public void aumentarEnergiaEnTurno(){
		
		if( (this.energia + this.aumentoDeEnergiaEnTurno) >= this.energiaMax ){
			this.energia = this.energiaMax;
		}else{
			this.energia += this.aumentoDeEnergiaEnTurno;
		}
		
	}

	public void pasarTiempo(){
		
		if(this.estaVivo()){
			if(!this.estaEnConstruccion()){
				this.aumentarEnergiaEnTurno();
			}else{
				this.tiempoConstruccionActual += 1;
			}
		}
		
	}

	private void descontarDeEnergia(int costoEnergia) {
		
		if(costoEnergia<this.energia) this.energia = this.energia - costoEnergia;
		else this.energia = 0;
		
	}
	
	public boolean recibirDanioMisilEMP() {
		
		this.energia = 0;
		return true;
		
	}
	
	public void atacarConMisilEMP(Mapa mapa,NaveCiencia naveAtacada){
		
		if(!this.puedoAtacar(naveAtacada)) throw new AtaqueFueraDelRangoDeVisionException();
		this.descontarDeEnergia(this.costoMisilEMP);
		naveAtacada.recibirDanioMisilEMP();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(naveAtacada,this.radioMisilEMP);
		for(int posicion=0;posicion<celdasAfectadas.size();posicion++){
			Celda celdaActual = celdasAfectadas.get(posicion);
			if( this.estaElevado() && celdaActual.getCapaSuperior().estaOcupado()){
				Ubicable unUbicable = celdaActual.getUbicableEnSuperior();
				Atacable unAtacable = (Atacable)unUbicable;
				unAtacable.recibirDanioMisilEMP();
			}else if( (!this.estaElevado()) && celdaActual.getCapaInferior().estaOcupado()){
				Ubicable unUbicable = celdaActual.getUbicableEnInferior();
				Atacable unAtacable = (Atacable)unUbicable;
				unAtacable.recibirDanioMisilEMP();
			}
		}
			
	}

	
	public void atacarConRadiacion(ArrayList<Celda>celdasAfectadas,Unidad unidad){
		
		if(!this.puedoAtacar(unidad)) throw new AtaqueFueraDelRangoDeVisionException();
		this.descontarDeEnergia(this.costoRadiacion);
		Radiacion ataqueRadioactivo = new Radiacion(celdasAfectadas);
		unidad.recibirAtaqueRadiacion(ataqueRadioactivo);
		
	}
	
	public void cobrar(Terran raza){
		raza.quitarMinerales(this.minerales);
		raza.quitarGas(this.gas);
	}

}
