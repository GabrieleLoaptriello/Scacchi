package controller;




import java.util.ArrayList;

import model.AbstractPezzo;
import model.ConfigurazioneScacchiera;
import model.Pezzo;

public class Regole {

	private final ConfigurazioneScacchiera configurazione;
	private Movimenti movimenti = new Movimenti();
	
	
	public Regole(ConfigurazioneScacchiera configurazione) {
		this.configurazione = configurazione;
	}
	
	public ConfigurazioneScacchiera controlloMossa(int fromX, int fromY, int intoX, int intoY) {
		
		ConfigurazioneScacchiera result =  configurazione;
		ConfigurazioneScacchiera copia =  configurazione.creaCopia();
		Pezzo from = result.getPezzo(fromX, fromY);
		Pezzo into = result.getPezzo(intoX, intoY);
		
		if(from.getColore()==configurazione.getColoreMossa() && from.getColore()!=into.getColore() && configurazione.getPezzo(intoX, intoY).getValore()!=6){
			
			//controllo di non provocare uno scacco
			ArrayList<Integer> coordinate;
	
			
			//controllo il movimento
			coordinate = movimenti.getCoordinatePedone(result, fromX, fromY);
			
			if(into.getValore()==0){
				for(int i = 0;i<coordinate.size();i+=2)
					if(coordinate.get(i)==intoX && coordinate.get(i+1)==intoY){
						result = configurazione.scambia(fromX, fromY, intoX, intoY);
					}
			}else{ 
				for(int i = 0;i<coordinate.size();i+=2)
					if(coordinate.get(i)==intoX && coordinate.get(i+1)==intoY){
						result = configurazione.mangia(fromX, fromY, intoX, intoY);
					}
			}
			
			//controllo che se c'è uno scacco allora devo liberarmi
			
			int[] coordinateRe = (configurazione.getColoreMossa()=='n')?configurazione.getReNero():configurazione.getReBianco();

			for(int y=0;y<8;y++){
				for(int x=0;x<8;x++){
					//controllo che se sono sotto scacco devo liberarmi(controllo l'attacco degli avversari)
					if(configurazione.getPezzo(x, y).getColore()==oppostoColore(configurazione.getColoreMossa())){
						coordinate = movimenti.getCoordinatePedone(configurazione, x,y);
						for(int v=0;v<coordinate.size();v+=2){
							if(coordinate.get(v)==coordinateRe[0] && coordinate.get(v+1)==coordinateRe[1]){
								return copia;
							}
						}
					}
					
				}
			}
			if(!result.equals(copia)){
				configurazione.setColoreMossa(oppostoColore(configurazione.getColoreMossa()));
			}
			
		}
		return result;
	}
	
	private char oppostoColore(char colore){
		return (colore=='n')?'b':'n';
	}
	

	public boolean isScaccoMatto(char colore){
		
		ArrayList<Integer> coordinate = null;
		ArrayList<Integer> coordinateAlleato = null;
		ArrayList<Integer> coordinateProvaRe = null;
		ArrayList<Integer> coordinateAttaccoPedoni = new ArrayList<Integer>();
		ArrayList<Integer> coordinateScaccoRe = new ArrayList<Integer>();
		boolean scaccoMattoRe = false;
		boolean scaccoMattoAttacco = true;
		boolean scacco = false;
		
		//coordinate della posizione del re
		int[] coordinateRe = (configurazione.getColoreMossa()=='n')?configurazione.getReNero():configurazione.getReBianco();
		//coordinate attorno al re escluse quelle contententi pedine dello stesso colore
		
		//da controllare scacco matto del barbiere
		coordinateProvaRe = movimenti.getCoordinatePedone(configurazione,coordinateRe[0], coordinateRe[1]);
		ArrayList<Integer> pezziInAttacco = new ArrayList<Integer>();
		
		for(int y=0;y<8;y++){
			for(int x=0;x<8;x++){
				//se il pezzo xy è avversario
				if(configurazione.getPezzo(x, y).getColore()==oppostoColore(configurazione.getColoreMossa())){
					//prendo tutte le coordinate d'attacco del pedone xy
					coordinate = movimenti.getCoordinatePedone(configurazione, x,y);
					boolean b = true;
					for(int v=0;v<coordinate.size();v+=2){
						if(coordinate.get(v)==coordinateRe[0] && coordinate.get(v+1)==coordinateRe[1])
							scacco = true;
						for(int w=0;w<coordinateProvaRe.size();w+=2){		
						//se la coordinata d'attacco del pedone xy è uguale a una delle coordinate attorno al re
							if(coordinate.get(v)==coordinateProvaRe.get(w) && coordinate.get(v+1)==coordinateProvaRe.get(w+1) && configurazione.getPezzo(coordinateProvaRe.get(w), coordinateProvaRe.get(w+1)).getColore()!=oppostoColore(configurazione.getColoreMossa())){
								coordinateProvaRe.set(w,-1); //segno la coordinata
								coordinateProvaRe.set(w+1,-1);
								if(b){
									pezziInAttacco.add(x);
									pezziInAttacco.add(y);
									b=false;
								}
							}
						}
						
					}
				}
			}
		}
		
		for(int p=0;p<pezziInAttacco.size();p+=2){
			for(int w=0;w<coordinateProvaRe.size();w+=2){	
				if(pezziInAttacco.get(p)==coordinateProvaRe.get(w) && pezziInAttacco.get(p)==coordinateProvaRe.get(w)){
					for(int j=0;j<8;j++){
						for(int i=0;i<8;i++){
							if(configurazione.getPezzo(i, j).getColore()==oppostoColore(configurazione.getColoreMossa())){
								coordinateAlleato = movimenti.getCoordinatePedone(configurazione, i,j);
								for(int n=0;n<coordinateAlleato.size();n+=2){
									if(coordinateAlleato.get(n)==pezziInAttacco.get(p) && coordinateAlleato.get(n+1)==pezziInAttacco.get(p+1)){
										coordinateProvaRe.set(w+1,-1); //segno la coordinata
										coordinateProvaRe.set(w,-1);
									}
								}
							}
						}
					}
				}
			}
			
		}
		
		if(!pezziInAttacco.isEmpty()){
			scaccoMattoRe = true;
			for(int i:coordinateProvaRe)
				if(i!=-1)
					scaccoMattoRe = false;
		}
		
		// 
		if(scaccoMattoRe && scacco){
			for(int k=0; k<pezziInAttacco.size();k+=2){
				coordinateScaccoRe = movimenti.checkCoordinateAmmissibili(configurazione, pezziInAttacco.get(k), pezziInAttacco.get(k+1), coordinateRe[0], coordinateRe[1]);
				//rimuovo la posizione del re
				if(!coordinateScaccoRe.isEmpty()){
					if(configurazione.getPezzo(pezziInAttacco.get(k), pezziInAttacco.get(k+1)).getValore()!=2){
						coordinateScaccoRe.remove(coordinateScaccoRe.size()-1);
						coordinateScaccoRe.remove(coordinateScaccoRe.size()-1);
					}
				
				
					for(int y=0;y<8;y++){
						for(int x=0;x<8;x++){
							if(configurazione.getColoreMossa() == configurazione.getPezzo(x, y).getColore() && configurazione.getPezzo(x, y).getValore()!=6){
								coordinateAttaccoPedoni = movimenti.getCoordinatePedone(configurazione, x,y);
								for(int i=0; i<coordinateAttaccoPedoni.size();i+=2){
									for(int w=0;w<coordinateScaccoRe .size();w+=2){
										if(coordinateAttaccoPedoni.get(i)==coordinateScaccoRe.get(w)&& coordinateAttaccoPedoni.get(i+1)==coordinateScaccoRe.get(w+1)){
											scaccoMattoAttacco |= false;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		if(scaccoMattoRe && scaccoMattoAttacco && scacco)
			return true;
		else
			return false;
	}
	
	
    
	//server per il pannello del pedone
	public ConfigurazioneScacchiera scambia(int fromX, int fromY, AbstractPezzo p) {
		return configurazione.scambia(fromX, fromY, p);
		
	}
}
