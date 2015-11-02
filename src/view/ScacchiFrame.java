package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.ScacchiController;

import model.ScacchiModel;
import model.Scacchiera;

public class ScacchiFrame extends JFrame{

	private ScacchiModel model = new ScacchiModel(new Scacchiera());
	private ScacchiPanel scacchiPanel;
	
	public ScacchiFrame() {
		setTitle("Scacchiera");
		View view = addPedine();
		new ScacchiController(view);
		addControlButtons();
		pack();
	}

	private void addControlButtons() {
		JButton nuovaPartita = new JButton("Nuova Partita");
		nuovaPartita.addActionListener(new java.awt.event.ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			int result = JOptionPane.showOptionDialog(getRootPane(), "Volete veramente iniziare una nuova partita?", "Nuova Partita",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,null);
			if(result == 0){
				restart();
			}
		}

		});
		JPanel panel = new JPanel();
		panel.add(nuovaPartita);
		add(panel, BorderLayout.NORTH);
	}
	
	private View addPedine() {
		scacchiPanel = new ScacchiPanel(model, this);
		add(scacchiPanel, BorderLayout.CENTER);
		return scacchiPanel;
	}

	private static final long serialVersionUID = 1L;
	
	protected void restart(){
		model = new ScacchiModel(new Scacchiera());
		remove(scacchiPanel);
		new ScacchiController(addPedine());				
		scacchiPanel.cancellaBordi();
	}
	
	
}
