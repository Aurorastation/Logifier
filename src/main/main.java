package main;

import backend.logger;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import menus.HelpScreen;
import menus.LogScreen;
import menus.MasterMenu;

public class main {

	private static JFrame base;
	private static MasterMenu menu;

	public static void main(String[] args) {
		Initialize();
	}

	//This sets up the JFrame we will be placing every component of the program on.
	//It also adds the "MasterMenu" onto the base JFrame.
	private static void Initialize() {
		logger.populateLog();
		base = new JFrame();
		base.setPreferredSize(new Dimension(700, 650));
		base.setTitle("Geeves: The Logifier!");
		base.setVisible(true);
		base.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		base.setLocationRelativeTo(null);  
		menu = new MasterMenu();
		base.add(menu, 0);
		base.pack();
	}

	//Called by MasterMenu to add the GameScreen onto the JFrame
	public static void viewLogs() {
		menu.setVisible(false);
		JPanel log = new LogScreen();
		base.add(log);
		base.pack();
	}

	//Called by MasterMenu to add the OptionScreen onto the JFrame
	public static void viewSearchedLogs() {
		menu.setVisible(false);
		if (JOptionPane.showConfirmDialog(null, "Yes: Search for two phrases. No: Search for one phrase.", "Search Amount Selection",
		JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			String message = JOptionPane.showInputDialog(null, "Please insert the first word, ckey or phrase you are looking for.");
			String message2 = JOptionPane.showInputDialog(null, "Please insert the second word, ckey or phrase you are looking for.");
			if (message == null) {
				message = "";
				JOptionPane.showMessageDialog(null, "No input for first search, displaying full logs. Please wait.");
			}
			if (message2 == null) {
				message2 = "";
				JOptionPane.showMessageDialog(null, "No input for second search, displaying full logs. Please wait.");
			}
			JPanel log = new LogScreen(message, message2);
			base.add(log);
			base.pack();
		} else {
			String message = JOptionPane.showInputDialog(null, "Please insert the word, ckey or phrase you are looking for.");
			if (message == null){
				message = "";
				JOptionPane.showMessageDialog(null, "No input for search, displaying full logs. Please wait.");
			}
			JPanel log = new LogScreen(message);
			base.add(log);
			base.pack();
		}
	}

	////Called by MasterMenu to add the HelpScreen onto the JFrame
	public static void OpenHelp() {
		menu.setVisible(false);
		JPanel help = new HelpScreen();
		base.add(help);
		base.pack();
	}

	//This is often called in conjunction with KillScreen, it opens a new
	//JFrame when the old one dies, so that the program can continue functioning.
	public static void ExitToMain() {
		base.dispose();
		base = new JFrame();
		base.setPreferredSize(new Dimension(700, 650));
		base.setTitle("Geeves: Re-logified!");
		base.setVisible(true);
		base.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		base.setLocationRelativeTo(null);
		menu = new MasterMenu();
		base.add(menu, 0);
		base.pack();
	}

}
