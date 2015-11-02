package controller;

import java.util.ArrayList;

import model.ConfigurazioneScacchiera;
import model.Pezzo;

public class Movimenti {
		
	public ArrayList<Integer> getCoordinatePedone(ConfigurazioneScacchiera configurazione, int x, int y) {

		Pezzo p = configurazione.getPezzo(x, y);
		ArrayList<Integer> result = new ArrayList<Integer>();

		switch (p.getValore()) {
		case 1:
			if (p.getColore() == 'b')
				result = coordinatePedoneBianco(x, y, configurazione);
			else
				result = coordinatePedoneNero(x, y, configurazione);
			break;
		case 2:
			result = coordinateCavallo(x, y);
			break;
		case 3:
			result = coordinateAlfiere(x, y, configurazione);
			break;
		case 4:
			result = coordinateTorre(x, y, configurazione);
			break;
		case 5:
			result = coordinateRegina(x, y, configurazione);
			break;
		case 6:
			result = coordinateRe(x, y, configurazione);
			break;
		}

		return result;
	}

	private  void addCoordinate(int x, int y, ArrayList<Integer> array) {
		array.add(x);
		array.add(y);
	}

	private  ArrayList<Integer> coordinatePedoneNero(int x, int y,ConfigurazioneScacchiera configurazione) {
		ArrayList<Integer> result = new ArrayList<Integer>();

		if(y==1 && configurazione.getPezzo(x, y + 2).getValore()==0){
			if (configurazione.getPezzo(x, y + 2).getValore() == 0){
				addCoordinate(x, y + 2, result);
			}
		}
		
		if (y + 1 <= 7) {

			if (configurazione.getPezzo(x, y + 1).getValore() == 0)
				addCoordinate(x, y + 1, result);

			if (x - 1 >= 0)
				if (configurazione.getPezzo(x - 1, y + 1).getColore() == 'b')
					addCoordinate(x - 1, y + 1, result);

			if (x + 1 <= 7)
				if (configurazione.getPezzo(x + 1, y + 1).getColore() == 'b')
					addCoordinate(x + 1, y + 1, result);

		}

		return result;
	}

	private  ArrayList<Integer> coordinatePedoneBianco(int x, int y,	ConfigurazioneScacchiera configurazione) {
		ArrayList<Integer> result = new ArrayList<Integer>();

		if(y==6 && configurazione.getPezzo(x, y - 2).getValore()==0){
			if(configurazione.getPezzo(x, y - 2).getValore() == 0){
				addCoordinate(x, y - 2, result);
			}
		}
		if (y - 1 >= 0) {
			
			if (configurazione.getPezzo(x, y - 1).getValore() == 0) {
				addCoordinate(x, y - 1, result);

			}
			if (x - 1 >= 0)
				if (configurazione.getPezzo(x - 1, y - 1).getColore() == 'n')
					addCoordinate(x - 1, y - 1, result);

			if (x + 1 <= 7)
				if (configurazione.getPezzo(x + 1, y - 1).getColore() == 'n')
					addCoordinate(x + 1, y - 1, result);
		}

		return result;
	}

	private  ArrayList<Integer> coordinateCavallo(int x, int y) {
		ArrayList<Integer> result = new ArrayList<Integer>();

		if (x - 2 >= 0 && y - 1 >= 0) {
			addCoordinate(x - 2, y - 1, result);
		}

		if (x - 1 >= 0 && y - 2 >= 0) {
			addCoordinate(x - 1, y - 2, result);
		}

		if (x + 1 <= 7 && y - 2 >= 0) {
			addCoordinate(x + 1, y - 2, result);
		}

		if (x + 2 <= 7 && y - 1 >= 0) {
			addCoordinate(x + 2, y - 1, result);
		}

		if (x + 2 <= 7 && y + 1 <= 7) {
			addCoordinate(x + 2, y + 1, result);
		}

		if (x + 1 <= 7 && y + 2 <= 7) {
			addCoordinate(x + 1, y + 2, result);
		}

		if (x - 1 >= 0 && y + 2 <= 7) {
			addCoordinate(x - 1, y + 2, result);
		}

		if (x - 2 >= 0 && y + 1 <= 7) {
			addCoordinate(x - 2, y + 1, result);
		}

		return result;
	}

	private  ArrayList<Integer> coordinateAlfiere(int x, int y,
			ConfigurazioneScacchiera configurazione) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.addAll(diagonale1(x, y, configurazione));
		result.addAll(diagonale2(x, y, configurazione));
		result.addAll(diagonale3(x, y, configurazione));
		result.addAll(diagonale4(x, y, configurazione));
		return result;
	}

	private  ArrayList<Integer> coordinateTorre(int x, int y,
			ConfigurazioneScacchiera configurazione) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.addAll(asse1(x, y, configurazione));
		result.addAll(asse2(x, y, configurazione));
		result.addAll(asse3(x, y, configurazione));
		result.addAll(asse4(x, y, configurazione));
		return result;
	}

	private  ArrayList<Integer> coordinateRegina(int x, int y,
			ConfigurazioneScacchiera configurazione) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.addAll(diagonale1(x, y, configurazione));
		result.addAll(diagonale2(x, y, configurazione));
		result.addAll(diagonale3(x, y, configurazione));
		result.addAll(diagonale4(x, y, configurazione));

		result.addAll(asse1(x, y, configurazione));
		result.addAll(asse2(x, y, configurazione));
		result.addAll(asse3(x, y, configurazione));
		result.addAll(asse4(x, y, configurazione));
		return result;
	}

	private  ArrayList<Integer> coordinateRe(int x, int y, ConfigurazioneScacchiera configurazione) {
		int xMax, xMin, yMax, yMin;
		ArrayList<Integer> result = new ArrayList<Integer>();
		char colore = configurazione.getPezzo(x, y).getColore();

		xMax = yMax = 1;
		xMin = yMin = -1;

		if (x == 0) {
			xMin = 0;
		}
		if (x == 7) {
			xMax = 0;
		}
		if (y == 0) {
			yMin = 0;
		}
		if (y == 7) {
			yMax = 0;
		}

		for (int j = yMin; j <= yMax; j++)
			for (int i = xMin; i <= xMax; i++)
				if (configurazione.getPezzo(x + i, y + j).getColore() != colore)
					addCoordinate(x + i, y + j, result);
		// rimuovo le scie di scacco
		return result;
	}

	// alto sx
	private  ArrayList<Integer> diagonale1(int x, int y,
			ConfigurazioneScacchiera configurazione) {
		int x1 = x;
		int y1 = y;
		ArrayList<Integer> result = new ArrayList<Integer>();

		while ((x1 - 1) >= 0 && (y1 - 1) >= 0) {

			if (configurazione.getPezzo(x1 - 1, y1 - 1).getColore()  != ' ') {
					addCoordinate(x1 - 1, y1 - 1, result);
					break;
				
			}
			addCoordinate(--x1, --y1, result);
		}
		return result;
	}

	// alto dx
	private  ArrayList<Integer> diagonale2(int x, int y,
			ConfigurazioneScacchiera configurazione) {
		int x1 = x;
		int y1 = y;
		ArrayList<Integer> result = new ArrayList<Integer>();

		while ((x1 + 1) <= 7 && (y1 - 1) >= 0) {
			if (configurazione.getPezzo(x1 + 1, y1 - 1).getColore() != ' ') {
					addCoordinate(x1 + 1, y1 - 1, result);
					break;
			}

			addCoordinate(++x1, --y1, result);
		}
		return result;
	}

	// basso sx
	private  ArrayList<Integer> diagonale3(int x, int y,
			ConfigurazioneScacchiera configurazione) {
		int x1 = x;
		int y1 = y;
		ArrayList<Integer> result = new ArrayList<Integer>();

		while ((x1 - 1) >= 0 && (y1 + 1) <= 7) {

			if (configurazione.getPezzo(x1 - 1, y1 + 1).getColore() != ' ') {
					addCoordinate(x1 - 1, y1 + 1, result);
					break;
				
			}
			addCoordinate(--x1, ++y1, result);
		}
		return result;
	}

	// basso dx
	private  ArrayList<Integer> diagonale4(int x, int y,
			ConfigurazioneScacchiera configurazione) {
		int x1 = x;
		int y1 = y;
		ArrayList<Integer> result = new ArrayList<Integer>();

		while ((x1 + 1) <= 7 && (y1 + 1) <= 7) {

			if (configurazione.getPezzo(x1 + 1, y1 + 1).getColore() != ' ') {
					addCoordinate(x1 + 1, y1 + 1, result);
					break;
				
			}
			addCoordinate(++x1, ++y1, result);
		}
		return result;
	}

	// su
	private  ArrayList<Integer> asse1(int x, int y,
			ConfigurazioneScacchiera configurazione) {
		int y1 = y;
		ArrayList<Integer> result = new ArrayList<Integer>();

		while ((y1 - 1) >= 0) {

			if (configurazione.getPezzo(x, y1 - 1).getColore() != ' ') {
					addCoordinate(x, y1 - 1, result);
					break;
				
			}
			addCoordinate(x, --y1, result);
		}
		return result;
	}

	// dx
	private  ArrayList<Integer> asse2(int x, int y,
			ConfigurazioneScacchiera configurazione) {
		int x1 = x;
		ArrayList<Integer> result = new ArrayList<Integer>();

		while ((x1 + 1) <= 7) {

			if (configurazione.getPezzo(x1 + 1, y).getColore() != ' ') {
					addCoordinate(x1 + 1, y, result);
					break;
				
			}
			addCoordinate(++x1, y, result);
		}
		return result;
	}

	// giu
	private  ArrayList<Integer> asse3(int x, int y,
			ConfigurazioneScacchiera configurazione) {
		int y1 = y;
		ArrayList<Integer> result = new ArrayList<Integer>();

		while ((y1 + 1) <= 7) {

			if (configurazione.getPezzo(x, y1 + 1).getColore()  != ' ') {
					addCoordinate(x, y1 + 1, result);
					break;
			}
			addCoordinate(x, ++y1, result);
		}
		return result;
	}

	// sinistra
	private  ArrayList<Integer> asse4(int x, int y,
			ConfigurazioneScacchiera configurazione) {
		int x1 = x;
		ArrayList<Integer> result = new ArrayList<Integer>();

		while ((x1 - 1) >= 0) {

			if (configurazione.getPezzo(x1 - 1, y).getColore() != ' ') {
					addCoordinate(x1 - 1, y, result);
					break;
			}
			addCoordinate(--x1, y, result);
		}
		return result;
	}
	
	/**
	 * Metodo che calcola le coordinate ammissibili per lo spostamento del re
	 * @param pedX
	 * @param pedY
	 * @param reX
	 * @param reY
	 * @return
	 */
	public  ArrayList<Integer> checkCoordinateAmmissibili(ConfigurazioneScacchiera configurazione, int pedX, int pedY, int reX, int reY){
		int xMin = Math.min(pedX, reX);
		int xMax = Math.max(pedX, reX);
		int yMin;
		int yMax;
		if(xMin == pedX){
			yMin = pedY;
			yMax = reY;
		}else {
			yMin = reY;
			yMax = pedY;
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		if(configurazione.getPezzo(pedX, pedY).getValore()==2){
			ArrayList<Integer> cavallo = getCoordinatePedone(configurazione, pedX, pedY);
			ArrayList<Integer> re = getCoordinatePedone(configurazione, reX,reY);
			for(int v=0;v<cavallo.size();v+=2)
				for(int w=0;w<re.size();w+=2)
					if(cavallo.get(v)==re.get(w) && cavallo.get(v+1)==re.get(w+1)){
						result.add(re.get(w));
						result.add(re.get(w+1));
					}
		}else if(pedX != reX && pedY != reY){ //regina, alfiere 
			for(int i=xMin;i<=xMax;i++){
				result.add(i);
				result.add(yMin++);
				
			}
		}else if(pedX == reX && pedY != reY){ //regina torre
			for(int j=yMin;j<=yMax;j++){
				result.add(pedX);
				result.add(j);
			}
		}else if(pedX != reX && pedY == reY){ //regina torre
			for(int i=xMin;i<=xMax;i++){
				result.add(i);
				result.add(pedY);
			}
		}
		
		return result;
	}


}
