package xtremecraft.mapa;

import java.util.ArrayList;

public class EstrategiaUbicacionGasVespeno extends EstrategiaUbicacion {

	@Override
	public ArrayList<Coordenada> getCoordenadasDeUbicacion() {
		
		ArrayList<Coordenada> coordenadasUbicacionGasVespeno = new ArrayList<Coordenada>();
		
		coordenadasUbicacionGasVespeno.add( new Coordenada(1,10) );
		coordenadasUbicacionGasVespeno.add( new Coordenada (4,16) );
		coordenadasUbicacionGasVespeno.add( new Coordenada(7,8) );
		coordenadasUbicacionGasVespeno.add( new Coordenada(7,18) );
		coordenadasUbicacionGasVespeno.add( new Coordenada(8,3) );
		coordenadasUbicacionGasVespeno.add( new Coordenada(11,23) );
		coordenadasUbicacionGasVespeno.add( new Coordenada(15,15) );
		coordenadasUbicacionGasVespeno.add( new Coordenada(16,2) );
		coordenadasUbicacionGasVespeno.add( new Coordenada(17,19) );
		coordenadasUbicacionGasVespeno.add( new Coordenada(18,5) );
		coordenadasUbicacionGasVespeno.add( new Coordenada(19,10) );
		coordenadasUbicacionGasVespeno.add( new Coordenada(22,15) );
			
		return coordenadasUbicacionGasVespeno;
		
	}

}
