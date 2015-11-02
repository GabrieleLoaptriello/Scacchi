package controller;


import static org.junit.Assert.*;
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

public class RegoleTest {
	
	private void configura(Pezzo[][] pezzo){
		pezzo[0][0] = new Torre('n');
		pezzo[0][1] = new Cavallo('n');
		pezzo[0][2] = new Alfiere('n');
		pezzo[0][3] = new Regina('n');
		pezzo[0][4] = new Re('n');
		pezzo[0][5] = new Alfiere('n');
		pezzo[0][6] = new Cavallo('n');
		pezzo[0][7] = new Torre('n');
		for(int x=0; x<8; x++){
			pezzo[1][x] = new Pedone('n');
			pezzo[2][x] = new Vuoto();
			pezzo[3][x] = new Vuoto();
			pezzo[4][x] = new Vuoto();
			pezzo[5][x] = new Vuoto();
			pezzo[6][x] = new Pedone('b');
		}
		pezzo[7][0] = new Torre('b');
		pezzo[7][1] = new Cavallo('b');
		pezzo[7][2] = new Alfiere('b');
		pezzo[7][3] = new Regina('b');
		pezzo[7][4] = new Re('b');
		pezzo[7][5] = new Alfiere('b');
		pezzo[7][6] = new Cavallo('b');
		pezzo[7][7] = new Torre('b');
	}
	
	/**
	 * Test per verificare il matto dello stolto
	 */
	@Test
	public void testRegoleScaccoMattoStolto() {
		Pezzo[][] pezziScacchiera = new Pezzo[8][8];
		configura(pezziScacchiera);
		pezziScacchiera[6][5] = new Vuoto();
		pezziScacchiera[6][6] = new Vuoto();
		pezziScacchiera[5][5] = new Pedone('b');
		pezziScacchiera[4][6] = new Pedone('b');
		pezziScacchiera[4][7] = new Regina('n');

		ConfigurazioneScacchiera configurazione = new Scacchiera(pezziScacchiera);
		configurazione.setColoreMossa('b');
		
		Regole test = new Regole(configurazione);
		assertTrue(test.isScaccoMatto('b'));
	}
	
	/**
	 * Test per veridficare il matto del barbiere
	 */
	@Test
	public void testRegoleScaccoMattoBarbiere() {
		Pezzo[][] pezziScacchiera = new Pezzo[8][8];
		configura(pezziScacchiera);
		pezziScacchiera[0][1] = new Vuoto();
		pezziScacchiera[0][6] = new Vuoto();
		pezziScacchiera[1][4] = new Vuoto();
		pezziScacchiera[6][4] = new Vuoto();
		pezziScacchiera[7][3] = new Vuoto();
		pezziScacchiera[7][5] = new Vuoto();
		pezziScacchiera[2][2] = new Cavallo('n');
		pezziScacchiera[2][5] = new Cavallo('n');
		pezziScacchiera[3][4] = new Pedone('n');
		pezziScacchiera[1][5] = new Regina('b');
		pezziScacchiera[4][2] = new Alfiere('b');
		pezziScacchiera[4][4] = new Pedone('b');

		ConfigurazioneScacchiera configurazione = new Scacchiera(pezziScacchiera);
		configurazione.setColoreMossa('n');
		
		Regole test = new Regole(configurazione);
		assertTrue(test.isScaccoMatto('n'));
	}
	
	/**
	 * Test per veridficare il matto di Boden
	 */
	@Test
	public void testRegoleScaccoMattoBoden() {
		Pezzo[][] pezziScacchiera = new Pezzo[8][8];
		configura(pezziScacchiera);
		pezziScacchiera[0][0] = new Vuoto();
		pezziScacchiera[0][1] = new Vuoto();
		pezziScacchiera[0][3] = new Vuoto();
		pezziScacchiera[0][5] = new Vuoto();
		pezziScacchiera[0][6] = new Vuoto();
		pezziScacchiera[1][3] = new Vuoto();
		pezziScacchiera[1][4] = new Vuoto();
		pezziScacchiera[1][5] = new Vuoto();
		pezziScacchiera[6][1] = new Vuoto();
		pezziScacchiera[6][2] = new Vuoto();
		pezziScacchiera[6][4] = new Vuoto();
		pezziScacchiera[6][6] = new Vuoto();
		pezziScacchiera[7][0] = new Vuoto();
		pezziScacchiera[7][1] = new Vuoto();
		pezziScacchiera[7][4] = new Vuoto();
		pezziScacchiera[7][5] = new Vuoto();
		pezziScacchiera[7][6] = new Vuoto();
		
		pezziScacchiera[2][2] = new Cavallo('n');
		pezziScacchiera[5][0] = new Alfiere('n');
		pezziScacchiera[3][5] = new Alfiere('n');
		pezziScacchiera[0][4] = new Torre('n');
		pezziScacchiera[0][2] = new Re('n');
		
		pezziScacchiera[3][3] = new Alfiere('b');
		pezziScacchiera[5][2] = new Pedone('b');
		pezziScacchiera[4][5] = new Pedone('b');
		pezziScacchiera[5][4] = new Alfiere('b');
		pezziScacchiera[5][5] = new Regina('b');
		pezziScacchiera[6][3] = new Cavallo('b');
		pezziScacchiera[7][3] = new Torre('b');
		pezziScacchiera[7][2] = new Re('b');

		ConfigurazioneScacchiera configurazione = new Scacchiera(pezziScacchiera);
		configurazione.setColoreMossa('b');
		
		Regole test = new Regole(configurazione);
		assertTrue(test.isScaccoMatto('b'));
	}
	
	/**
	 * Test per veridficare il matto di Legal
	 */
	@Test
	public void testRegoleScaccoMattoLuegal() {
		Pezzo[][] pezziScacchiera = new Pezzo[8][8];
		configura(pezziScacchiera);
		
		pezziScacchiera[0][1] = new Vuoto();
		pezziScacchiera[0][2] = new Vuoto();
		pezziScacchiera[0][4] = new Vuoto();
		pezziScacchiera[1][3] = new Vuoto();
		
		pezziScacchiera[2][2] = new Cavallo('n');
		pezziScacchiera[2][3] = new Pedone('n');
		pezziScacchiera[0][0] = new Torre('n');
		pezziScacchiera[1][4] = new Re('n');
		pezziScacchiera[7][3] = new Alfiere('n');
		
		pezziScacchiera[1][5] = new Alfiere('b');
		pezziScacchiera[3][3] = new Cavallo('b');
		pezziScacchiera[3][4] = new Cavallo('b');
		pezziScacchiera[4][4] = new Pedone('b');

		ConfigurazioneScacchiera configurazione = new Scacchiera(pezziScacchiera);
		configurazione.setColoreMossa('n');
		
		Regole test = new Regole(configurazione);
		boolean b = test.isScaccoMatto('n');
		assertTrue(b);
		
	}

}
