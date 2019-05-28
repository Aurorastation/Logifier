package menus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class HelpScreen extends JPanel {

	public HelpScreen() {
		initHelp();
	}

	//Method used to get everything ready, which is then called in the constructor
	public void initHelp() {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(700, 200));

		//BorderLayout used here because the Help TextArea did not fit into my standard panelHolder set-up
		setLayout(new BorderLayout());

		//Sets up the Help TextArea menu. Hardcoded because no controls are customizable yet.
		JTextArea helpTxt = new JTextArea(" Heya, this logifier has been made by Geeves."
				+ "\n Controls:"
				+ "\n Escape - Exit to Main Menu (Will Exit program if pressed on main menu)"
				+ "\n Space - Sets the text you want to go to next. (Must be viewing logs, full or searched)"
				+ "\n F - Go to the next instance of the text you entered during the Space operation."
				+ "\n Menus:"
				+ "\n View Full Logs - This displays a colour-coded version of the logfile you have put into the folder."
				+ "\n Search - This searches the logs for case insensitive occurances of the text you have entered."
				+ "\n How to use:"
				+ "\n Take your log file, called something like 'bBxty-xFvt' and rename it to 'logifier', then place it in the same folder as 'logifier.jar'"
				+ "\n Additional info:"
				+ "\n If anything horrible happens, contact me at Geeves#4318");
		helpTxt.setFocusable(false);
		add(helpTxt, BorderLayout.CENTER);

		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.main.ExitToMain();
				setVisible(false);
			}
		});
		add(back, BorderLayout.SOUTH);

		setVisible(true);

		//Keybindings
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"),
				"escapePressed");
		getActionMap().put("escapePressed",
				exit);
	}

	Action exit = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			main.main.ExitToMain();
			setVisible(false);
		}
	};
}
