package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import model.AbstractPezzo;
import model.Alfiere;
import model.Cavallo;
import model.Model;
import model.Pedone;
import model.Regina;
import model.Torre;
import controller.Controller;
import controller.Movimenti;

public class ScacchiPanel extends JPanel implements View {
	
	private Movimenti movimenti = new Movimenti();

	private static final long serialVersionUID = 1L;
	private final Model model;
	private Controller controller;
	private final JButton[][] buttons = new JButton[8][8];
	private LinkedList<Integer> posizioni = new LinkedList<Integer>();
	private boolean primoClick = true;
	private JFrame frame;
	private boolean scaccoMossa = false;
	private AbstractPezzo p;
	private boolean bottoneColor = true;
	JButton firstButton = null;

	public ScacchiPanel(Model model, JFrame frame) {
		this.model = model;
		this.frame = frame;
		createButtons();
		model.setView(this);
	}

	private void createButtons() {
		setLayout(new GridLayout(8, 8));
		for (int y = 0; y < 8; y++)
			for (int x = 0; x < 8; x++)
				add(buttons[x][y] = mkButton(x, y, model.getPezzo(x, y).getValore(), model.getPezzo(x, y).getColore()));
	}

	// ATTENZIONE FINAL
	private JButton mkButton(final int fromX, final int fromY, final int value, final char color) {

		final JButton button = new JButton(immaginePezzo(value, color));
		button.setDisabledIcon(immaginePezzo(value, color));
		button.setPreferredSize(new Dimension(85, 85));

		if (fromX == 0)
			bottoneColor = !bottoneColor;
		if (bottoneColor) {
			button.setBackground(Color.white);
			bottoneColor = false;
		} else {
			button.setBackground(Color.black);
			bottoneColor = true;
		}

		button.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		this.add(button);
		button.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				// disabilita i bottoni vuoti
				if (controller != null) {
					if (posizioni.isEmpty() && primoClick && !scaccoMossa && model.getPezzo(fromX, fromY).getValore() != 0 && model.getPezzo(fromX, fromY).getColore()== model.getConfigurazione().getColoreMossa()) {
						cancellaBordi();
						posizioni.add(fromX);
						posizioni.add(fromY);
						primoClick = false;
						firstButton = button;

						button.setBorder(new CompoundBorder(new EtchedBorder(),new LineBorder(Color.RED, 3)));
						coloraBordi(fromX, fromY);

					} else if (!posizioni.isEmpty() && model.getPezzo(posizioni.getFirst(),	posizioni.getLast()).getColore() == model.getPezzo(fromX, fromY).getColore()&& !primoClick) { // elimina il doppio click su una
						// pedina dello stesso colore

						cancellaBordi();
						if (!posizioni.isEmpty()) {
							posizioni.removeFirst();
							posizioni.removeLast();
						}
						posizioni.add(fromX);
						posizioni.add(fromY);

						firstButton = button;

						button.setBorder(new CompoundBorder(new EtchedBorder(),new LineBorder(Color.RED, 3)));
						coloraBordi(fromX, fromY);

					} else {
						
						if (!posizioni.isEmpty()&& model.getConfigurazione().getPezzo(fromX, fromY).getColore() != model.getConfigurazione().getColoreMossa()) {

							int x1 = posizioni.getFirst();
							int y1 = posizioni.getLast();
							controller.onClick(x1, y1, fromX, fromY, oppostoColore(model.getConfigurazione().getColoreMossa()));
							if(model.getConfigurazione().getPezzo(fromX, fromY).getValore()==1 && ((model.getConfigurazione().getColoreMossa()=='n' && fromY==7)|| (model.getConfigurazione().getColoreMossa()=='b' && fromY==0)))
								pedinaDaRecuperare(fromX,fromY, model.getConfigurazione().getColoreMossa());
							posizioni.removeFirst();
							posizioni.removeLast();
							primoClick = true;
							cancellaBordi();
						}
					}
				}
			}
		});
		return button;
	}

	protected void cancellaBordi() {
		for (int y = 0; y < 8; y++)
			for (int x = 0; x < 8; x++) {
				buttons[x][y].setBorder(null);
				buttons[x][y].setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
			}
	}

	private void coloraBordi(int fromX, int fromY) {
		ArrayList<Integer> coordinate = movimenti.getCoordinatePedone(model.getConfigurazione(), fromX, fromY);
		for (int v = 0; v < coordinate.size(); v += 2) {
			int x = coordinate.get(v);
			int y = coordinate.get(v + 1);
			if (model.getConfigurazione().getPezzo(x, y).getColore() != model.getConfigurazione().getColoreMossa()) {
				buttons[x][y].setBorder(new CompoundBorder(new EtchedBorder(),new LineBorder(Color.RED, 3)));
				buttons[x][y].setEnabled(true);
			}
		}
	}

	@Override
	public Model getModel() {
		return model;
	}

	protected ImageIcon immaginePezzo(int value, char color) {
		if (color == 'b') {
			switch (value) {
			case 0:
				return null;
			case 1:
				return new ImageIcon(getClass().getResource("/img/pedoneb.png"));
			case 2:
				return new ImageIcon(getClass().getResource("/img/cavallob.png"));
			case 3:
				return new ImageIcon(getClass().getResource("/img/alfiereb.png"));
			case 4:
				return new ImageIcon(getClass().getResource("/img/torreb.png"));
			case 5:
				return new ImageIcon(getClass().getResource("/img/reginab.png"));
			case 6:
				return new ImageIcon(getClass().getResource("/img/reb.png"));
			default:
				return null;
			}
		} else {
			switch (value) {
			case 0:
				return null;
			case 1:
				return new ImageIcon(getClass().getResource("/img/pedonen.png"));
			case 2:
				return new ImageIcon(getClass().getResource("/img/cavallon.png"));
			case 3:
				return new ImageIcon(getClass().getResource("/img/alfieren.png"));
			case 4:
				return new ImageIcon(getClass().getResource("/img/torren.png"));
			case 5:
				return new ImageIcon(getClass().getResource("/img/reginan.png"));
			case 6:
				return new ImageIcon(getClass().getResource("/img/ren.png"));
			default:
				return null;
			}
		}
	}

	private char oppostoColore(char c) {
		return (c == 'b' ? 'n' : 'b');
	}

	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void onConfigurationChange() {

		for (int y = 0; y < 8; y++)
			for (int x = 0; x < 8; x++)
				buttons[x][y].setIcon(immaginePezzo(model.getPezzo(x, y).getValore(), model.getPezzo(x, y).getColore()));

	}
	
	public void pedinaDaRecuperare(int fromX, int fromY, char colore) {
		JPanel pannello = new JPanel(new GridLayout(1, 4));
		cancellaBordi();
		for (int x = 2; x < 6; x++)
			pannello.add(crea(fromX, fromY, x, colore));
		JOptionPane.showOptionDialog(getRootPane(), pannello, "Scegli una pedina",
				JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION,
				null, null, null);
	}
	
	private JButton crea(final int fromX, final int fromY, final int value, final char colore) {
		final JButton button = new JButton(immaginePezzo(value, colore));
		button.setBackground(Color.white);
		button.addActionListener(new java.awt.event.ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				switch(value){
				case 1: 
					p = new Pedone(colore);
					break;
				case 2:
					p = new Cavallo(colore);
					break;
				case 3:
					p = new Alfiere(colore);
					break;
				case 4:
					p = new Torre(colore);
					break;
				case 5:
					p = new Regina(colore);
					break;
				}
				
				controller.scambiaPezzo(fromX, fromY, p);
				setEnabled(true);
				
			}
			
		});
		return button;
	}

	@Override
	public void showEndPanel() {
		cancellaBordi();
		int scaccoReX;
		int scaccoReY;
		
		if(model.getConfigurazione().getColoreMossa()=='b'){
			scaccoReX = model.getConfigurazione().getReBianco()[0];
			scaccoReY = model.getConfigurazione().getReBianco()[1];
		}else{
			scaccoReX = model.getConfigurazione().getReNero()[0];
			scaccoReY = model.getConfigurazione().getReNero()[1];
		}
		Color sfondoRe = buttons[scaccoReX][scaccoReY].getBackground();
		buttons[scaccoReX][scaccoReY].setBackground(Color.RED);
		
		int result = JOptionPane.showOptionDialog(getRootPane(), "I "+(model.getConfigurazione().getColoreMossa()=='b'?"neri":"bianchi")+" hanno vinto.\n"+"Vuoi iniziare una nuova partita?"
	, "SCACCO MATTO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,null);
		
		buttons[scaccoReX][scaccoReY].setBackground(sfondoRe);
		if(result == 0){
			((ScacchiFrame) frame).restart();
		}else
			System.exit(0);
	}
}
