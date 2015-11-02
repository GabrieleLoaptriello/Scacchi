package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Alfiere;
import model.Cavallo;
import model.ConfigurazioneScacchiera;
import model.Pedone;
import model.Pezzo;
import model.Re;
import model.Regina;
import model.Scacchiera;
import model.Torre;
import model.Vuoto;

import org.junit.Test;

public class MovimentiTest {
	
	
	Movimenti testMovimenti = new Movimenti();
	
	private void resettaScacchiera(Pezzo[][] pezzo){
		for(int x=0; x<8; x++)
			for(int y=0; y<8; y++)
				pezzo[x][y] = new Vuoto();
	}

	/**
	 * test dei movimenti possibili per un pedone nero alla prima mossa
	 */
	@Test
	public void testCoordinatePedoneNeroPrimaMossa() {
		Pezzo[][] pezziScacchiera = new Pezzo[8][8];
		resettaScacchiera(pezziScacchiera);
		pezziScacchiera[1][1]=new Pedone('n');
		ConfigurazioneScacchiera configurazione = new Scacchiera(pezziScacchiera);
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		result.add(1); //x
		result.add(3); //y
		result.add(1); //x
		result.add(2); //y
		
		ArrayList<Integer> test = testMovimenti.getCoordinatePedone(configurazione, 1, 1);
		assertEquals(test, result);
	}
	
	/**
	 * test dei movimenti possibili per un pedone nero
	 */
	@Test
	public void testCoordinatePedoneNero() {
		Pezzo[][] pezziScacchiera = new Pezzo[8][8];
		resettaScacchiera(pezziScacchiera);
		pezziScacchiera[2][1]=new Pedone('n');
		ConfigurazioneScacchiera configurazione = new Scacchiera(pezziScacchiera);
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		result.add(1); //x
		result.add(3); //y
		
		ArrayList<Integer> test = testMovimenti.getCoordinatePedone(configurazione, 1, 2);
		assertEquals(test, result);
	}
	
	/**
	 * test dei movimenti possibili per un pedone bianco alla prima mossa
	 */
	@Test
	public void testCoordinatePedoneBiancoPrimaMossa() {
		Pezzo[][] pezziScacchiera = new Pezzo[8][8];
		resettaScacchiera(pezziScacchiera);
		pezziScacchiera[6][1]=new Pedone('b');
		ConfigurazioneScacchiera configurazione = new Scacchiera(pezziScacchiera);
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		result.add(1); //x
		result.add(4); //y
		result.add(1); //x
		result.add(5); //y
		
		ArrayList<Integer> test = testMovimenti.getCoordinatePedone(configurazione, 1, 6);
		assertEquals(test, result);
	}
	
	/**
	 * Test dei mobimenti possibili per un pedone bianco
	 */
	@Test
	public void testCoordinatePedoneBianco() {
		Pezzo[][] pezziScacchiera = new Pezzo[8][8];
		resettaScacchiera(pezziScacchiera);
		pezziScacchiera[5][1]=new Pedone('b');
		ConfigurazioneScacchiera configurazione = new Scacchiera(pezziScacchiera);
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		result.add(1); //x
		result.add(4); //y
		
		ArrayList<Integer> test = testMovimenti.getCoordinatePedone(configurazione, 1,5);
		assertEquals(test, result);
	}
	
	/**
	 * Test dei movimenti possibili per una Torre
	 */
	@Test
	public void testCoordinateTorreBianco() {
		Pezzo[][] pezziScacchiera = new Pezzo[8][8];
		resettaScacchiera(pezziScacchiera);
		pezziScacchiera[7][7]=new Torre('b');
		ConfigurazioneScacchiera configurazione = new Scacchiera(pezziScacchiera);
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		result.add(7);
		result.add(6); 
		result.add(7);
		result.add(5);
		result.add(7);
		result.add(4);
		result.add(7);
		result.add(3);
		result.add(7);
		result.add(2);
		result.add(7);
		result.add(1);
		result.add(7);
		result.add(0);
		result.add(6); 
		result.add(7);
		result.add(5); 
		result.add(7);
		result.add(4); 
		result.add(7);
		result.add(3); 
		result.add(7);
		result.add(2); 
		result.add(7);
		result.add(1); 
		result.add(7);
		result.add(0); 
		result.add(7);
		
		ArrayList<Integer> test = testMovimenti.getCoordinatePedone(configurazione, 7,7);
		assertEquals(test, result);
	}
	
	/**
	 * Test dei movimenti possibili per un cavallo
	 */
	@Test
	public void testCoordinateCavalloNero() {
		Pezzo[][] pezziScacchiera = new Pezzo[8][8];
		resettaScacchiera(pezziScacchiera);
		pezziScacchiera[5][5]=new Cavallo('n');
		ConfigurazioneScacchiera configurazione = new Scacchiera(pezziScacchiera);
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		result.add(3);
		result.add(4); 
		result.add(4);
		result.add(3);
		result.add(6);
		result.add(3);
		result.add(7);
		result.add(4);
		result.add(7);
		result.add(6);
		result.add(6);
		result.add(7);
		result.add(4);
		result.add(7);
		result.add(3); 
		result.add(6);
		
		ArrayList<Integer> test = testMovimenti.getCoordinatePedone(configurazione, 5,5);
		assertEquals(test, result);
	}
	
	/**
	 * Test dei movimenti possibili per un alfiere
	 */
	@Test
	public void testCoordinateAlfiereBianco() {
		Pezzo[][] pezziScacchiera = new Pezzo[8][8];
		resettaScacchiera(pezziScacchiera);
		pezziScacchiera[7][7]=new Alfiere('b');
		ConfigurazioneScacchiera configurazione = new Scacchiera(pezziScacchiera);
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		result.add(6);
		result.add(6); 
		result.add(5);
		result.add(5);
		result.add(4);
		result.add(4);
		result.add(3);
		result.add(3);
		result.add(2);
		result.add(2);
		result.add(1);
		result.add(1);
		result.add(0);
		result.add(0);
		
		ArrayList<Integer> test = testMovimenti.getCoordinatePedone(configurazione, 7,7);
		assertEquals(test, result);
	}
	
	
	/**
	 * Test dei movimenti possibili per la regina dove un pedone è sul
	 * fascio di attacco laterale, limitando quindi i movimenti della regina
	 * oltre il pedone
	 */
	@Test
	public void testCoordinateReginaNera() {
		Pezzo[][] pezziScacchiera = new Pezzo[8][8];
		resettaScacchiera(pezziScacchiera);
		pezziScacchiera[5][5]=new Regina('n');
		pezziScacchiera[6][6]=new Pedone('b');
		ConfigurazioneScacchiera configurazione = new Scacchiera(pezziScacchiera);
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(4);
		result.add(4);
		result.add(3);
		result.add(3);
		result.add(2);
		result.add(2);
		result.add(1);
		result.add(1);
		result.add(0);
		result.add(0);
		result.add(6);
		result.add(4);
		result.add(7);
		result.add(3);
		result.add(4);
		result.add(6);
		result.add(3);
		result.add(7);
		result.add(6);
		result.add(6); 
		result.add(5);
		result.add(4); 
		result.add(5);
		result.add(3); 
		result.add(5);
		result.add(2); 
		result.add(5);
		result.add(1); 
		result.add(5);
		result.add(0); 
		result.add(6);
		result.add(5); 
		result.add(7);
		result.add(5); 
		result.add(5);
		result.add(6);
		result.add(5);
		result.add(7); 
		result.add(4);
		result.add(5); 
		result.add(3);
		result.add(5);
		result.add(2);
		result.add(5); 
		result.add(1);
		result.add(5);
		result.add(0);
		result.add(5); 
		
		ArrayList<Integer> test = testMovimenti.getCoordinatePedone(configurazione, 5,5);
		assertEquals(test, result);
	}
	
	/**
	 * Test delle coordinate di un re 
	 */
	@Test
	public void testCoordinateReNero() {
		Pezzo[][] pezziScacchiera = new Pezzo[8][8];
		resettaScacchiera(pezziScacchiera);
		pezziScacchiera[1][1]=new Re('n');
		pezziScacchiera[7][1]=new Torre('b');
		ConfigurazioneScacchiera configurazione = new Scacchiera(pezziScacchiera);
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		result.add(0);
		result.add(0);
		result.add(1);
		result.add(0);
		result.add(2);
		result.add(0);
		result.add(0);
		result.add(1);
		result.add(2);
		result.add(1);
		result.add(0);
		result.add(2);
		result.add(1);
		result.add(2);
		result.add(2);
		result.add(2);
		
		ArrayList<Integer> test = testMovimenti.getCoordinatePedone(configurazione, 1,1);
		assertEquals(test, result);
	}
}
