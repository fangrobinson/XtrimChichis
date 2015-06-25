package xtremecraft.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class InicioJuego extends JFrame{
	
	public static ArrayList<String> nombresJugadores;
	static JFrame numJugadores = new JFrame("Numero Jugadores");
	static IngresoNombres inicio;
	
	
	public static void main(String args[]){
		
		final ArrayList<String> nombresJugadores = new ArrayList<String>();
		
		inicio = new IngresoNombres(nombresJugadores,3);
		
		
	}

}
