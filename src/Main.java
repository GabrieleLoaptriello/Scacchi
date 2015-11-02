import java.awt.EventQueue;
import javax.swing.JFrame;

import view.ScacchiFrame;


public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame frame = new ScacchiFrame();
				frame.setTitle("Scacchi");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	

	}

}
