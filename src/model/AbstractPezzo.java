package model;

public abstract class AbstractPezzo implements Pezzo {
	
	//private int posX;
	//private int posY;
	
	private char colore;
	private int valore;
	
	public AbstractPezzo(char colore, int valore){
		this.colore = colore;
		this.valore = valore;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Pezzo) {
			if(((Pezzo) other).getColore() == this.getColore() && ((Pezzo) other).getValore() == this.getValore())
				return true;
			else
				return false;
				
		}else
			return false;
	}
	
	public boolean stessoColore(Pezzo p) {
		if(p.getColore() == this.getColore())
			return true;
		else
			return false;
	}
	
	
	public String toString(){
		return valore+"|"+colore+" ";
		
	}
	

	
	public char getColore(){
		return this.colore;
	}
	
	public int getValore(){
		return this.valore;
	}
	
}
