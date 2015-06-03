package xtremecraft.mapa;

import java.util.TreeMap;

import xtremecraft.interfaces.Atacable;
import xtremecraft.interfaces.Defendible;
import xtremecraft.unidades.Unidad;

public class Mapa {
	
	private TreeMap<Integer, TreeMap<Integer, Celda>> mapaAlto;
	private int alto;
	private int ancho;

	public Mapa(int cant_jugadores) {
		
		if (cant_jugadores <= 1){
			throw new IllegalArgumentException("La cantidad de jugadores debe ser un numero positivo");
		}
		this.alto = this.decidirAlto(cant_jugadores);
		this.ancho = this.decidirAncho(cant_jugadores);
		
		rellenarMapa(alto, ancho, cant_jugadores);	
	}

	private void rellenarMapa(int alto, int ancho, int cant_jugadores) {
		this.mapaAlto = new TreeMap<Integer, TreeMap<Integer, Celda>>();
		
		for(int fila =0; fila < this.alto ;fila = fila + 1) {
			this.mapaAlto.put(fila, new TreeMap<Integer, Celda>());
			for(int columna = 0; columna < this.ancho; columna = columna + 1) {
				this.mapaAlto.get(fila).put(columna, obtenerCeldaAdecuada(fila, columna, cant_jugadores));
			}
		}	
	}
	
	private int decidirAlto(int cant_jugadores) {
		return cant_jugadores * 50;
	}

	private int decidirAncho(int cant_jugadores) {
		return cant_jugadores * 50;
	}
	
	
	private Celda obtenerCeldaAdecuada(int fila, int columna, int cant_jugadores) {
		//NOTA: guardar numeros magicos en variables con nombres que tengan sentido...
		int primer_corte_ancho = 3*decidirAncho(cant_jugadores)/8;
		int primer_corte_alto = 3*decidirAlto(cant_jugadores)/8;
		int segundo_corte_ancho = 5*decidirAncho(cant_jugadores)/8;
		int segundo_corte_alto = 5*decidirAlto(cant_jugadores)/8;
		if (fila < (primer_corte_alto) || fila > (segundo_corte_alto)){
			if (columna < (primer_corte_ancho) || columna > (segundo_corte_ancho)) {
				return new Tierra(fila,columna);
			}
		}
		return new Aire(fila,columna);
	}
	/*
	private void ubicarBases(int cant_jugadores, Ubicable elemento) {
		Random randomGen = new Random();
		int jugadores_ubicados = 0;
		int randomInt;
		int randomCorrimiento;

		while (jugadores_ubicados < cant_jugadores) {
			if (jugadores_ubicados % 4 = 0) {
				randomInt = randomGen.nextInt(25) + (jugadores_ubicados/4 * 50);
			}
			// Aca genero las posiciones en las que ubicar
			// Tienen que llamar a this.mapaAlto.get(X).get(Y).ubicar(new GasVepeno); GasVespeno, Material, etc
			// Si quieren ubicar otra cosa tiran un + randomGen.nextInt(5); para que lo tire en un lugar rangom digamos.

			// Lo cambie para que ubice las cosas por defecto, pero tiene el problema de que no puede ubicar las bases porque 
			// serian de distinta raza. Recomiendo que pueda llegar a recibir una posible lista de Array con clases
			// y le tira sarasa.ubicar(array.at(i).crearBase); Por ejemplo... pero bueno. Mañana lo discutimos.

			// Quiero cambiarlo para que esto se haga de una sola pasada sin la If Wall con complejos se que se puede
			// pero ni idea si les jode o no dejarlo asi. Queria que se entienda la idea primero.g

			// Agrego documentacion:

			// Deberia recibir por parametro un array de elementos y un array de jugadores
			// Entonces cada vez que itera clava arrayDeJugadores.at(i).crearBase();
			// y alrededor con random le ubicamos lo que haya en el array de elementos,
			// por ejemplo Gas Vepeno, minerales etc.
			// Sino la llamas para ubicar gas vespeno, despues la llamas para minerales y te los clava en la loma del ojete.

			if (jugadores_ubicados % 4 = 0) {
				this.mapaAlto.get(randomInt).get(randomInt).ubicar(elemento);
			}
			if (jugadores_ubicados % 4 = 1) {
				this.mapaAlto.get(this.alto - randomInt).get(this.ancho - randomInt).ubicar(elemento);
			}
			if (jugadores_ubicados % 4 = 2) {
				this.mapaAlto.get(randomInt).get(this.ancho - randomInt).ubicar(elemento);
			}
			if (jugadores_ubicados % 4 = 3) {
				this.mapaAlto.get(this.alto - randomInt).get(randomInt).ubicar(elemento);
			}
			jugadores_ubicados = jugadores_ubicados + 1;
		}

	}
	*/
	public boolean tieneAire() {
		return true;
	}

	public boolean tieneTierra() {
		return true;
	}
	
	//NOTA: tal vez este metodo deberia ser privado. De esa forma el mapa se encarga de ocupar la celda.
	public Celda getCeldaEnFilaColumna(int fila, int columna){
		return this.mapaAlto.get(fila).get(columna);
	}
	
	
	public boolean colocarUnidad(Unidad unaUnidad,int fila, int columna){
		Celda celda= this.getCeldaEnFilaColumna(fila, columna);
		return celda.ocuparConUnidad(unaUnidad);
		
	}
	
	public double calcularDistanciaEntreCeldas(Celda celda1,Celda celda2){
		
		int x1 = celda1.getX();
		int x2 = celda2.getX();
		int y1 = celda1.getY();
		int y2 = celda2.getY();
		
		double cuadradoDeltaX = Math.pow((x2 - x1), 2);
		double cuadradoDeltaY = Math.pow((y2 - y1),2);
		double sumaCuadradosCatetos= (cuadradoDeltaX + cuadradoDeltaY);
		
		return Math.sqrt(sumaCuadradosCatetos);
		
	}
	
	public boolean celdaAtacadaEstaEnRangoDeVisionDeCeldaAtacante(Celda celdaAtacada, Celda celdaAtacante){
		if(!celdaAtacante.estaOcupada()){
			return false;
		}
		double distancia = this.calcularDistanciaEntreCeldas(celdaAtacada, celdaAtacante);
		int visionAtacante = celdaAtacante.getUnidadEnCelda().getRadioVision();
		if(distancia>visionAtacante){
			return false;
		}
		return true;
		
	}
	
	
	public boolean celdaAtacar(Celda celdaAtacante, Celda celdaAtacada){
		if(this.celdaAtacadaEstaEnRangoDeVisionDeCeldaAtacante(celdaAtacada, celdaAtacante)){
			if(!celdaAtacada.estaOcupada()){
				//atacante gasto suministro???
				//atacante.atacar(nil,'')??
				return true;
			}else{
				Defendible atacante = celdaAtacante.getUnidadEnCelda();
				Atacable atacado = celdaAtacada.getUnidadEnCelda();
				atacante.atacar(atacado, "terrestre");
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
}