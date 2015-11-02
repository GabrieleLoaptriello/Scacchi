package controller;

import view.View;

import model.AbstractPezzo;

public class ScacchiController implements Controller{
	
	private final Mover mover;
	private final View view;
	
	public ScacchiController(View view){
		this.mover = new Mover(view.getModel());
		this.view = view;
		view.setController(this);
	}

	public void onClick(int fromX, int fromY, int intoX, int intoY, char colore) {
		mover.muovi(fromX, fromY, intoX, intoY);
		if(mover.isScaccoMatto(colore))
			view.showEndPanel();
	}
	
	//click per la scelta della pedina
	public void scambiaPezzo(int fromX, int fromY, AbstractPezzo p) {
		mover.scambia(fromX, fromY, p);
	}
}
