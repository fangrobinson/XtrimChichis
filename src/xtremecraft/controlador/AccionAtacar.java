package xtremecraft.controlador;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import xtremecraft.mapa.Coordenada;
import xtremecraft.partida.Partida;
import xtremecraft.partida.SeleccionadoNoEsPropiedadDelJugadorException;
import xtremecraft.vista.MapaObservable;
import xtremecraft.vista.MensajeDeError;

@SuppressWarnings("serial")
public class AccionAtacar extends AbstractAction{

	private Partida partida;
	private Coordenada coordenada;
	private MapaObservable mapaVista;
		
	public AccionAtacar(Partida partida, MapaObservable mapa, Coordenada coordenada){
			
		super("Atacar");
		this.partida = partida;
		this.coordenada = coordenada;
		this.mapaVista = mapa;
		this.coordenada = coordenada;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			this.partida.validarQueUbicableElegidoEsDelJugador(this.coordenada);
			
			boolean unidadEnConstruccion = this.partida.unidadSeleccionadaEstaEnConstruccion(this.coordenada);
			
			boolean unidadSeleccionadaPuedeAtacar = this.partida.unidadSeleccionadaPuedeAtacar(this.coordenada);
			
			if ( unidadEnConstruccion || !unidadSeleccionadaPuedeAtacar ){
				new MensajeDeError("Esta unidad esta en construccion o ya realizó un ataque en el turno actual.");
			}else{
				this.mapaVista.comenzarAtaque(this.coordenada);
			}
			
		} catch (SeleccionadoNoEsPropiedadDelJugadorException e) {
			new MensajeDeError("La unidad que quiere atacar no es del jugador");
		}
		
	}
	
}
