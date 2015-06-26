package xtremecraft.mapa;

import java.util.ArrayList;

public class EstrategiaUbicacionDosJugadores extends EstrategiaUbicacion{

	@Override
	public ArrayList<Coordenada> getCoordenadasDeUbicacion() {
		
		ArrayList<Coordenada> coordenadasUbicacion = new ArrayList<Coordenada>();
		Coordenada primeraCoordenada = new Coordenada(4,4);
		Coordenada segundaCoordenada = new Coordenada(20,20);
		
		coordenadasUbicacion.add(primeraCoordenada);
		coordenadasUbicacion.add(segundaCoordenada);
		
		return coordenadasUbicacion;
	}

}
