package xtremecraft.unidades;

import java.util.ArrayList;

import xtremecraft.mapa.Celda;
import xtremecraft.mapa.Mapa;
import xtremecraft.unidades.UnidadAerea;

public class NaveCiencia extends UnidadAerea{
	
	private int energiaMax = 200;
	private int energia = 50;
	private int aumentoDeEnergiaEnTurno = 10;
	private int radioMisilEMP = 3;
	private int costoMisilEMP = 100;
	//private int costoRadiacion = 100;
	
	public NaveCiencia(){
		
		super();
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
	
	public boolean recibirDanioMisilEMP() {
		
		this.energia = 0;
		return true;
		
	}
	
	public void atacarConMisilEMP(Mapa mapa,NaveCiencia naveAtacada){
		
		if(!this.puedoAtacar(naveAtacada)) throw new AtaqueFueraDelRangoDeVisionException();
		this.energia -= this.costoMisilEMP;
		naveAtacada.recibirDanioMisilEMP();
		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(naveAtacada,this.radioMisilEMP);
		for(int posicion=0;posicion<celdasAfectadas.size();posicion++){
			Celda celdaActual = celdasAfectadas.get(posicion);
			if( this.estaElevado() && celdaActual.getCapaSuperior().estaOcupado()){
				Ubicable unUbicable = celdaActual.getUbicableEnSuperior();
				Atacable unAtacable = (Atacable)unUbicable;
				unAtacable.recibirDanioMisilEMP();
			}if( (!this.estaElevado()) && celdaActual.getCapaInferior().estaOcupado()){
				Ubicable unUbicable = celdaActual.getUbicableEnInferior();
				Atacable unAtacable = (Atacable)unUbicable;
				unAtacable.recibirDanioMisilEMP();
			}
		}
			
	}
	
	/*public void atacarConRadiacion(Mapa mapa,Unidad unidad){
		
		this.energia -= this.costoRadiacion;
		unidad.recibirDanio(mapa,unidad.danio.devolverDanio(this.estaElevado));
		mapa.liberarEspacioCorrespondienteA(unidad);
		//TODO: atacar a las celdas a distancia 1 de la unidad.
	}
	*/
	
	
}
