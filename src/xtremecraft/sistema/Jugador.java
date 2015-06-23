package xtremecraft.sistema;

import xtremecraft.edificios.Barraca;
import xtremecraft.edificios.Fabrica;
import xtremecraft.edificios.PuertoEstelar;
import xtremecraft.edificios.RecolectorDeGasVespeno;
import xtremecraft.edificios.RecolectorDeMineral;
import xtremecraft.mapa.Mapa;
import xtremecraft.mapa.Terreno;
import xtremecraft.mapa.Tierra;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Atacable;
import xtremecraft.unidades.Defendible;
import xtremecraft.unidades.Espectro;
import xtremecraft.unidades.Goliat;
import xtremecraft.unidades.Marine;
import xtremecraft.unidades.NaveCiencia;
import xtremecraft.unidades.NaveTransporte;
import xtremecraft.unidades.Ubicable;

public class Jugador {
	private String nombre;
	private Terran nacion;
	private Jugador siguienteJugador;
	private boolean esMiTurno;
	
	public Jugador (String nombre, Tierra tierra) throws NombreMuyCortoException{
		
		if (nombre.length() < 4){
			throw new NombreMuyCortoException();
		}
		this.nombre = nombre;
		this.nacion = new Terran(tierra);
		this.siguienteJugador = null;
		this.esMiTurno = false;
		
	}
	
	public String nombre(){
		return this.nombre;
	}
	
	public Terran nacion(){
		return this.nacion;
	}

	public boolean estaEnJuego(){
		
		return this.nacion.estaViva();
		
	}
	
	public boolean esDeMiPropiedad(Ubicable ubicable){
		
		return this.nacion.esDeMiPropiedad(ubicable);
		
	}
	

	public void atacar(Defendible atacante, Atacable atacado){
		
		Ubicable atacanteUbicado = (Ubicable) atacante;
		
		if (!this.esDeMiPropiedad(atacanteUbicado)){
			throw new ElAtacanteNoEsDelJugadorException();
		}
		
		atacante.atacar(atacado);
	}
	
	//metodos de creacion
	
	public Barraca crearBarraca(Terreno unTerreno){
		
		return this.nacion.crearBarraca(unTerreno);
		
	}
	
	public Fabrica crearFabrica(Terreno unTerreno){
		
		return this.nacion.crearFabrica(unTerreno);
		
	}
	
	public PuertoEstelar crearPuertoEstelar(Terreno unTerreno){
		
		return this.nacion.crearPuertoEstelar(unTerreno);
		
	}
	
	public RecolectorDeGasVespeno crearRecolectorDeGasVespeno(Terreno unTerreno){
		
		return this.nacion.crearRecolectorDeGasVespeno(unTerreno);
		
	}
	
	public RecolectorDeMineral crearRecolectorDeMineral(Terreno unTerreno){
		
		return this.nacion.crearRecolectorDeMineral(unTerreno);
		
	}
	
	public Marine crearMarine(Barraca unaBarraca, Mapa unMapa){
		
		return (Marine) this.nacion.crearMarine(unaBarraca, unMapa);
		
	}
	
	public Goliat crearGoliat(Fabrica unaFabrica, Mapa unMapa){
		
		return (Goliat) this.nacion.crearGoliat(unaFabrica, unMapa);
		
	}
	
	public Espectro crearEspectro(PuertoEstelar unPuerto, Mapa unMapa){
		
		return (Espectro) this.nacion.crearEspectro(unPuerto, unMapa);
		
	}
	
	public NaveCiencia crearNaveCiencia(PuertoEstelar unPuerto, Mapa unMapa){
		
		return (NaveCiencia) this.nacion.crearNaveCiencia(unPuerto, unMapa);
		
	}
	
	public NaveTransporte crearNaveTransporte(PuertoEstelar unPuerto, Mapa unMapa){
		
		return (NaveTransporte) this.nacion.crearNaveTransporte(unPuerto, unMapa);
		
	}

	public void setJugadorSiguiente(Jugador jugador) {
		
		this.siguienteJugador = jugador;
		
	}

	public void setTurno() {
		
		this.esMiTurno = true;
		
	}

	public void pasarTurno() {
		
		if(this.esMiTurno){
			this.esMiTurno = false;
			this.siguienteJugador.setTurno();
		}
		
	}

	public boolean tieneTurno() {
		
		return this.esMiTurno;
		
	}
	
}
