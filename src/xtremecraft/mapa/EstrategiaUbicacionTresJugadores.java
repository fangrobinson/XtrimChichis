package xtremecraft.mapa;

import java.util.ArrayList;

public class EstrategiaUbicacionTresJugadores extends EstrategiaUbicacion {

	@Override
	public ArrayList<Coordenada> getCoordenadasDeUbicacion() {
		
		ArrayList<Coordenada> coordenadasUbicacion = new ArrayList<Coordenada>();
		Coordenada primeraCoordenada = new Coordenada(4,5);
		Coordenada segundaCoordenada = new Coordenada(20,5);
		Coordenada tercerCoordenada = new Coordenada(12,19);
		
		coordenadasUbicacion.add(primeraCoordenada);
		coordenadasUbicacion.add(segundaCoordenada);
		coordenadasUbicacion.add(tercerCoordenada);
		
		return coordenadasUbicacion;
	}

}
