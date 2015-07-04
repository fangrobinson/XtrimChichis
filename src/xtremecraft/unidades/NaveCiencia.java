package xtremecraft.unidades;

import xtremecraft.partida.Jugador;
import xtremecraft.mapa.Mapa;

public class NaveCiencia extends UnidadAerea{
	
	private int energiaMax = 200;
	private int energia = 50;
	private int aumentoDeEnergiaEnTurno = 10;
	private int minerales = 100;
	private int gas = 225; 
//	private int radioMisilEMP = 3;
//	private int costoMisilEMP = 100;
	private int costoRadiacion = 100;
	private static int vidaInicial = 200;
	private static int radioDeAlcance = 2;
	private static int danioIrradiado = 10;
	
	public NaveCiencia(Jugador unJugador){
		
		super(unJugador);
		this.cobrar();
		this.vitalidad = new BarraDeVitalidad(vidaInicial);
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
//	
//	public void atacarConMisilEMP(Mapa mapa,NaveCiencia naveAtacada){
//		
//		if(!this.puedoVer(naveAtacada.getUbicacionActual())) throw new AtaqueFueraDelRangoDeVisionException();
//		if(!this.puedoAtacar()) throw new YaSeSeleccionoUnAtaqueException();
//		this.descontarDeEnergia(this.costoMisilEMP);
//		naveAtacada.recibirDanioMisilEMP();
//		ArrayList<Celda> celdasAfectadas = mapa.obtenerCeldasEnRadio(naveAtacada,this.radioMisilEMP);
//		this.atacarCeldas(celdasAfectadas);
//		this.puedeAtacar = false;
//			
//	}
	
//	private void atacarCeldas(ArrayList<Celda> celdasAfectadas) {
//		
//		for(int posicion=0;posicion<celdasAfectadas.size();posicion++){
//			Celda celdaActual = celdasAfectadas.get(posicion);
//			if( this.estaElevado() && celdaActual.getCapaSuperior().estaOcupado()){
//				Ubicable unUbicable = celdaActual.getUbicableEnSuperior();
//				Atacable unAtacable = (Atacable)unUbicable;
//				unAtacable.recibirDanioMisilEMP();
//			}else if( (!this.estaElevado()) && celdaActual.getCapaInferior().estaOcupado()){
//				Ubicable unUbicable = celdaActual.getUbicableEnInferior();
//				Atacable unAtacable = (Atacable)unUbicable;
//				unAtacable.recibirDanioMisilEMP();
//			}
//		}
//		
//	}

	public void atacarConRadiacion(Mapa mapa,Unidad unidad){
		
		if(!this.puedoVer(unidad.getUbicacionActual())) throw new AtaqueFueraDelRangoDeVisionException();
		if(!this.puedoAtacar()) throw new YaSeSeleccionoUnAtaqueException();
		this.descontarDeEnergia(this.costoRadiacion);
		Radiacion ataqueRadioactivo = new Radiacion(mapa, radioDeAlcance, danioIrradiado);
		unidad.recibirDanio(ataqueRadioactivo);
		this.puedeAtacar = false;
		
	}
	
	public void cobrar(){
		
		this.jugador.nacion().quitarMinerales(this.minerales);
		this.jugador.nacion().quitarGas(this.gas);
		
	}
	
}
