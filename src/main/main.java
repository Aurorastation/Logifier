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
		JPanel log = new LogScreen();
		base.add(log);
	}

	//Called by MasterMenu to add the OptionScreen onto the JFrame
	public static void viewSearchedLogs() {
		JPanel log;
		String message = JOptionPane.showInputDialog(null, "Please insert the first word, ckey or phrase you are looking for. Leave blank to get full logs. Press cancel to cancel search entirely.");
		if(message == null) {
			return;
		}
		else if(message.isEmpty() == true) {
			log = new LogScreen();
		}
		else
		{
			String message2 = JOptionPane.showInputDialog(null, "Please insert the second word, ckey or phrase you are looking for. Leave blank if you don't want to search for a second word. Press cancel to cancel search entirely.");
			if(message2 == null) {
				return;
			}
			else if(message2.isEmpty() == true)
			{
				log = new LogScreen(message);
			}
			else {
				log = new LogScreen(message, message2);
			}
		}
		menu.setVisible(false);
		base.add(log);
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