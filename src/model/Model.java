package model;

import view.View;

public interface Model {
	// metodi di richiesta di stato
	public Pezzo getPezzo(int x, int y);
	public ConfigurazioneScacchiera getConfigurazione();
	
	//metodi di modifica dello stato e view
	public void setConfigurazione(ConfigurazioneScacchiera configurazione);
	public void setView(View listener);
}
