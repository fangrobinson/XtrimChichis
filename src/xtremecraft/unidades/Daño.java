package xtremecraft.unidades;

public class Daño {
	int aire;
	int tierra;

	public Daño(int dañoAire, int dañoTierra){
		this.aire = dañoAire;
		this.tierra = dañoTierra;
	}
	
	public int devolverDañoTierra(){
		return this.tierra;
	}
	
	public int devolverDañoAire(){
		return this.aire;
	}
	
	public int devolverDaño(String medio){
		if (medio == "aire"){
			return this.devolverDañoAire();
		}
		return this.devolverDañoTierra();
	}
}
