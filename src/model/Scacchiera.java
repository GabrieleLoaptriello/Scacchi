package model;

public class Scacchiera extends AbstractConfigurazioneScacchiera{
	
	private Pezzo[][] scacchiera;
	private static char coloreMossa;
	
	public Scacchiera(Pezzo[][] scacchiera){
		this.scacchiera = scacchiera;
	}
	
	public Scacchiera(ConfigurazioneScacchiera conf){
		this.scacchiera = new Pezzo[8][8];
		
		for(int x=0; x<8; x++)
			for(int y=0; y<8; y++)
				set(x,y, conf.getPezzo(x, y));
	}
	
	
	public Scacchiera(){
		coloreMossa = 'b';
		this.scacchiera = new Pezzo[8][8];
		
		set(0,0,new Torre('n'));
		set(1,0,new Cavallo('n'));
		set(2,0,new Alfiere('n'));
		set(3,0,new Regina('n'));
		set(4,0,new Re('n'));
		set(5,0,new Alfiere('n'));
		set(6,0,new Cavallo('n'));
		set(7,0,new Torre('n'));
		for(int x=0; x<8; x++){
			set(x,1,new Pedone('n'));
			set(x,2,new Vuoto());
			set(x,3,new Vuoto());
			set(x,4,new Vuoto());
			set(x,5,new Vuoto());
			set(x,6,new Pedone('b'));
		}
		set(0,7,new Torre('b'));
		set(1,7,new Cavallo('b'));
		set(2,7,new Alfiere('b'));
		set(3,7,new Regina('b'));
		set(4,7,new Re('b'));
		set(5,7,new Alfiere('b'));
		set(6,7,new Cavallo('b'));
		set(7,7,new Torre('b'));
	}
	
	public Pezzo getPezzo(int x, int y){
		return scacchiera[y][x];
	}
	
	private void set(int x, int y, Pezzo pezzo){
		scacchiera[y][x] = pezzo;
	}
	
	public ConfigurazioneScacchiera scambia(int fromX, int fromY, int intoX, int intoY){
		
		Scacchiera result = new Scacchiera(scacchiera);
		
		Pezzo b = getPezzo(intoX, intoY);
		Pezzo a = getPezzo(fromX, fromY);
		
		result.set(intoX, intoY, a);
		result.set(fromX, fromY, b);
						
		return result;
		
	}
	
	public ConfigurazioneScacchiera scambia(int fromX, int fromY, AbstractPezzo p){
		Scacchiera result = new Scacchiera(scacchiera);
		result.set(fromX, fromY, p);
		return result;
	}
	
	public ConfigurazioneScacchiera mangia(int fromX, int fromY, int intoX, int intoY){
		
		Scacchiera result = new Scacchiera(scacchiera);
		Pezzo fromPezzo = getPezzo(fromX, fromY);
		result.set(intoX, intoY, fromPezzo);
		result.set(fromX, fromY, new Vuoto());
		
		return result;
		
	}

	@Override
	public int[] getReBianco() {
		int[] result = {-1,-1};
		for(int y=0; y<8;y++){
			for(int x=0;x<8;x++)
				if(getPezzo(x,y).equals(new Re('b'))){
					result[0]=x;
					result[1]=y;
				}
		}
		return result;
	}
	
	public int[] getReNero() {
		int[] result = {-1,-1};
		for(int y=0; y<8;y++){
			for(int x=0;x<8;x++)
				if(getPezzo(x,y).equals(new Re('n'))){
					result[0]=x;
					result[1]=y;
				}
		}
		return result;
	}
	public void setColoreMossa(char colore){
		coloreMossa = colore;
	}
	
	public char getColoreMossa(){
		return coloreMossa;
	}
	
	@Override
	public ConfigurazioneScacchiera creaCopia() {
		return new Scacchiera(this);
	}

	@Override
	public ConfigurazioneScacchiera resettaScacchiera() {
		return new Scacchiera();
	}
}
