package com.votanstudios.checksum.view;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class SwingApp extends JFrame {

	public static void init() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SwingApp app = new SwingApp();
				app.setVisible(true);
			}
		});
		
	}

}
