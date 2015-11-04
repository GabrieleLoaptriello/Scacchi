package controller;

import model.AbstractPezzo;

public interface Controller {
	void onClick(int fromX, int fromY, int intoX, int intoY, char colore);
	void scambiaPezzo(int fromX, int fromY, AbstractPezzo p);
}
