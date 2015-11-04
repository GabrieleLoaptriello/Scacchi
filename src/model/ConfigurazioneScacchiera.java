package model;


public interface ConfigurazioneScacchiera {
	
	Pezzo getPezzo(int x, int y);
	
	public ConfigurazioneScacchiera scambia(int fromX, int fromY, int intoX, int intoY);
	public ConfigurazioneScacchiera mangia(int fromX, int fromY, int intoX, int intoY);
	public ConfigurazioneScacchiera scambia(int fromX, int fromY, AbstractPezzo p);
	
	public int[] getReBianco();
	public int[] getReNero();
	public ConfigurazioneScacchiera creaCopia();
	
	public void setColoreMossa(char colore);
	public char getColoreMossa();

	public ConfigurazioneScacchiera resettaScacchiera();
	
	
}
