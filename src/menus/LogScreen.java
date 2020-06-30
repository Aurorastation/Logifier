package menus;

import backend.ColorPane;
import backend.logColorArray;
import backend.logger;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public final class LogScreen extends JPanel {

	public LogScreen(String[] searchWords) {
		initLogView(searchWords);
	}

	ColorPane logScreen = new ColorPane();
	String find = "";
	int pos = 0;

	//Method used to get everything ready, which is then called in the constructor
	public void initLogView(String[] searchWords) {
		setBackground(Color.BLACK);
		setLayout(new BorderLayout());

		boolean isSearching = false;
		if(searchWords != null && searchWords.length > 0) {
			isSearching = true;
		}

		if(isSearching == true) {
			logger.populateLog(searchWords);
		}

		JScrollPane scrollPanel = new JScrollPane();
		add(scrollPanel).setPreferredSize(new Dimension(LogScreen.WIDTH, LogScreen.HEIGHT));
		scrollPanel.setFocusable(false);

		for (int i = 0; i < logger.getFileSize(isSearching); i++) {
			logScreen.append(Color.decode(logColorArray.getColor(i, isSearching)), logger.getLogString(i, isSearching));
		}
		logScreen.setEditable(false);
		scrollPanel.setViewportView(logScreen);
		scrollPanel.getViewport().getView().setBackground(Color.decode("#2B4037"));

		setVisible(true);

		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"),
				"escapePressed");
		getActionMap().put("escapePressed",
				exit);

		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE") ,
				"spacePressed");
		getActionMap().put("spacePressed", setSearch);

		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F") ,
				"fPressed");
		getActionMap().put("fPressed", searcher);

		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D") ,
				"dPressed"); // hehe depressed
		getActionMap().put("dPressed", reverseSearcher);
	}

	Action exit = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			main.main.ExitToMain();
		}
	};

	Action setSearch = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			find = JOptionPane.showInputDialog("Insert the text you wish to search for.").toLowerCase();
			pos = 0;
		}
	};

	Action searcher = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			logScreen.requestFocusInWindow();
			if (find != null && find.length() > 0) {
				Document document = logScreen.getDocument();
				int findLength = find.length();
				try {
					boolean found = false;
					if (pos + findLength > document.getLength()) {
						pos = 0;
					}
					while (pos + findLength <= document.getLength()) {
						String match = document.getText(pos, findLength).toLowerCase();
						if (match.equals(find)) {
							found = true;
							break;
						}
						pos++;
					}
					if(found) {
						Rectangle viewRect = logScreen.modelToView(pos);
						logScreen.scrollRectToVisible(viewRect);
						logScreen.setCaretPosition(pos + findLength);
						logScreen.moveCaretPosition(pos);
						pos += findLength;
					}
				} catch (BadLocationException exp) {
					exp.printStackTrace();
				}

			}
		}
	};

	Action reverseSearcher = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			logScreen.requestFocusInWindow();
			if (find != null && find.length() > 0) {
				Document document = logScreen.getDocument();
				int findLength = find.length();
				try {
					boolean found = false;
					if (pos == 0 || pos + findLength > document.getLength()) {
						pos = document.getLength() - findLength;
					}
					while (pos > 0) {
						String match = document.getText(pos, findLength).toLowerCase();
						if (match.equals(find)) {
							found = true;
							break;
						}
						pos--;
					}
					if(found) {
						Rectangle viewRect = logScreen.modelToView(pos);
						logScreen.scrollRectToVisible(viewRect);
						logScreen.setCaretPosition(pos + findLength);
						logScreen.moveCaretPosition(pos);
						pos--;
					}
				} catch (BadLocationException exp) {
					exp.printStackTrace();
				}

			}
		}
	};
}