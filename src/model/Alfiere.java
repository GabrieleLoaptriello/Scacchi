package model;

public class Alfiere extends AbstractPezzo{

	public Alfiere(char colore){
		super(colore,3);
	}
	
	public boolean isPrimaMossa(){
		return false;
	}
	
	public void setPrimaMossa(){
	}
}
