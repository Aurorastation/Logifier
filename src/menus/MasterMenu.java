package menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import main.*;

public class MasterMenu extends JPanel {

	public MasterMenu() {
		setPreferredSize(new Dimension(700, 650));

		//Credits to Rob Heiser for the Panel Holder code!
		int i = 6;
		int j = 3;
		JPanel[][] panelHolder = new JPanel[i][j];
		setLayout(new GridLayout(i, j));

		for (int m = 0; m < i; m++) {
			for (int n = 0; n < j; n++) {
				panelHolder[m][n] = new JPanel();
				panelHolder[m][n].setBackground(Color.decode("#223620"));
				add(panelHolder[m][n]);
			}
		}

		JButton start = createButton("View Full Logs");
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.viewLogs();
			}
		});
		panelHolder[1][1].add(start);

		JButton options = createButton("Search");
		options.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.viewSearchedLogs();
			}
		});
		panelHolder[2][1].add(options);

		JButton help = createButton("Help");
		help.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.OpenHelp();
			}
		});
		panelHolder[3][1].add(help);;

		JButton exit = createButton("Exit Logifier");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panelHolder[4][1].add(exit);

		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"),
				"escapePressed");
		getActionMap().put("escapePressed",
				exitEsc);

		setVisible(true);

	}

	private JButton createButton(String buttonText) {
		JButton button = new JButton(buttonText);
		button.setPreferredSize(new Dimension(200, 95));
		button.setBackground(Color.decode("#1F2E1D"));
		button.setForeground(Color.decode("#A8D1A5"));
		return button;
	}

	Action exitEsc = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};

}