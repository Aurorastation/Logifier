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
		logger.populateLog(null);
		base = new JFrame();
		base.setPreferredSize(new Dimension(700, 650));
		base.setTitle("Geeves: Logifier at Night!");
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
		JPanel log = new LogScreen(null);
		base.add(log);
	}

	//Called by MasterMenu to add the OptionScreen onto the JFrame
	public static void viewSearchedLogs() {
		JPanel log = null;
		String[] searchTerms = new String[10];
		int searchNum = 0;
		do {
			searchTerms[searchNum] = JOptionPane.showInputDialog(null, "Please enter the word you wish to search for. Leave blank to search using the entered terms. Press cancel to abort searching entirely.\nLimit: 10 words Current word: " + (searchNum + 1));
			if(searchTerms[searchNum] == null) {
				return;
			}
			else if(searchTerms[searchNum].isEmpty() == true) {
				log = new LogScreen(searchTerms);
			}
			searchNum++;
			if(searchNum == searchTerms.length) {
				log = new LogScreen(searchTerms);
			}
		} while (log == null);
		if(log != null) {
			menu.setVisible(false);
			base.add(log);
		}
	}

	////Called by MasterMenu to add the HelpScreen onto the JFrame
	public static void OpenHelp() {
		menu.setVisible(false);
		JPanel help = new HelpScreen();
		base.add(help);
	}

	public static void ExitToMain() {
		menu = new MasterMenu();
		base.add(menu, 0);
	}
}