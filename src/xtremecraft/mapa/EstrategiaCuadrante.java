package xtremecraft.mapa;
import java.util.Random;

public abstract class EstrategiaCuadrante {
	public abstract int getAlto(int posicion_base);
	public abstract int getAncho(int posicion_base);
	
	protected int corrimiento(){
		Random randomGen = new Random();
		return randomGen.nextInt(10) * (-1 * randomGen.nextInt(1));
	}
}
