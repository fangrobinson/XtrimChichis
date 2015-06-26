package xtremecraft.mapa;

import java.util.ArrayList;

public class EstrategiaUbicacionCuatroJugadores extends EstrategiaUbicacion{

	@Override
	public ArrayList<Coordenada> getCoordenadasDeUbicacion() {
		
		ArrayList<Coordenada> coordenadasUbicacion = new ArrayList<Coordenada>();
		Coordenada primeraCoordenada = new Coordenada(4,4);
		Coordenada segundaCoordenada = new Coordenada(20,20);
		Coordenada tercerCoordenada = new Coordenada(20,4);
		Coordenada cuartaCoordenada = new Coordenada(4,20);
		
		coordenadasUbicacion.add(primeraCoordenada);
		coordenadasUbicacion.add(segundaCoordenada);
		coordenadasUbicacion.add(tercerCoordenada);
		coordenadasUbicacion.add(cuartaCoordenada);
		
		return coordenadasUbicacion;
	}

}
