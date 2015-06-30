package xtremecraft.controlador;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;

import xtremecraft.mapa.Coordenada;
import xtremecraft.mapa.NoSePudoOcuparElTerrenoException;
import xtremecraft.mapa.Tierra;
import xtremecraft.partida.Jugador;
import xtremecraft.partida.Partida;
import xtremecraft.raza.RecursosInsuficientesException;
import xtremecraft.vista.MensajeDeError;


public class AccionConstruirEdificio extends AbstractAction{
		
	private static final long serialVersionUID = 1L;
	
	private Partida partida;
	private JComboBox<String> opciones;
	private Coordenada coordenada;
		
	public AccionConstruirEdificio(Partida partida, JComboBox<String> comboBox, Coordenada coordenada){
			
		super("Construir");
		this.coordenada = coordenada;
		this.partida = partida;
		this.opciones = comboBox;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Jugador jugadorActual;
		jugadorActual = partida.quienJuega();
		Tierra tierraConstruccion = (Tierra) partida.getMapa().getCeldaEnFilaColumna(coordenada.fila(), coordenada.columna()).getCapaInferior();
		try{
			
			if (this.opciones.getSelectedItem() == "Nueva Barraca")
				jugadorActual.crearBarraca(tierraConstruccion);
			if(this.opciones.getSelectedItem() == "Nueva Fabrica")
				jugadorActual.crearFabrica(tierraConstruccion);
			if(this.opciones.getSelectedItem() == "Nuevo Deposito de suministros")
				jugadorActual.crearDepositoDeSuministros(tierraConstruccion);
				
		}catch(RecursosInsuficientesException noHayRecursos){
			
			 new MensajeDeError("No se tienen suficientes recursos");
			 
		}catch(NoSePudoOcuparElTerrenoException terrenoOcupado){
			
			new MensajeDeError("El terreno seleccionado no está disponible para construir");
			
		}
	}
	
}


