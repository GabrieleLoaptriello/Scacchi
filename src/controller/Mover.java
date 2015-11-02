package controller;

import model.AbstractPezzo;
import model.Model;

public class Mover {
	private final Model model;

	public Mover(Model model) {
		this.model = model;
	} 

	public boolean isScaccoMatto(char colore) {
		return new Regole(model.getConfigurazione()).isScaccoMatto(colore);
	}

	public void muovi(int fromX, int fromY, int intoX, int intoY) {
		model.setConfigurazione(new Regole(model.getConfigurazione()).controlloMossa(fromX, fromY, intoX, intoY));
	}
	
	public void scambia(int fromX, int fromY, AbstractPezzo p) {
		model.setConfigurazione(new Regole(model.getConfigurazione()).scambia(fromX, fromY, p));
	}
	
}
