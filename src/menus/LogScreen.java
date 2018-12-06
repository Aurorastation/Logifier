package menus;

import backend.ColorPane;
import backend.logColorArray;
import backend.logger;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public final class LogScreen extends JPanel {

    public LogScreen() {
        initLogView();
    }

    public LogScreen(String search) {
        initSearchedView(search);
    }

    ColorPane logScreen = new ColorPane();
    String find = "";
    int pos = 0;

    //Method used to get everything ready, which is then called in the constructor
    public void initLogView() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(700, 600));
        setLayout(new BorderLayout());

        JScrollPane scrollPanel = new JScrollPane();
        add(scrollPanel).setPreferredSize(new Dimension(700, 600));
        scrollPanel.setFocusable(false);

        for (int i = 0; i < logger.getFileSize(); i++) {
            if (logColorArray.getType(i).equalsIgnoreCase("BASIC")) {
                logScreen.append(Color.black, logger.getLogString(i));
            } else if (logColorArray.getType(i).equalsIgnoreCase("ADMIN")) {
                logScreen.append(Color.red, logger.getLogString(i));
            } else if (logColorArray.getType(i).equalsIgnoreCase("OOC")) {
                logScreen.append(Color.blue, logger.getLogString(i));
            } else if (logColorArray.getType(i).equalsIgnoreCase("ATTACK")) {
                logScreen.append(Color.MAGENTA, logger.getLogString(i));
            } else if (logColorArray.getType(i).equalsIgnoreCase("IGNORE")) {
                logScreen.append(Color.gray, logger.getLogString(i));
            }
        }
        logScreen.setEditable(false);
        scrollPanel.setViewportView(logScreen);

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
    }

    public void initSearchedView(String search) {
        if (search == null) {
            main.main.ExitToMain();
            setVisible(false);
            return;
        }
        setLayout(new BorderLayout());

        logger.populateSearch(search);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(700, 600));

        JScrollPane scrollPanel = new JScrollPane();
        add(scrollPanel).setPreferredSize(new Dimension(700, 600));
        scrollPanel.setFocusable(false);

        for (int i = 0; i < logger.getSearchedSize(); i++) {
            if (logColorArray.getSType(i).equalsIgnoreCase("BASIC")) {
                logScreen.append(Color.black, logger.getSearchedString(i));
            } else if (logColorArray.getSType(i).equalsIgnoreCase("ADMIN")) {
                logScreen.append(Color.red, logger.getSearchedString(i));
            } else if (logColorArray.getSType(i).equalsIgnoreCase("OOC")) {
                logScreen.append(Color.blue, logger.getSearchedString(i));
            } else if (logColorArray.getSType(i).equalsIgnoreCase("ATTACK")) {
                logScreen.append(Color.MAGENTA, logger.getSearchedString(i));
            } else if (logColorArray.getSType(i).equalsIgnoreCase("IGNORE")) {
                logScreen.append(Color.gray, logger.getSearchedString(i));
            }
        }
        logScreen.setEditable(false);
        scrollPanel.setViewportView(logScreen);

        setVisible(true);

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"),
                "escapePressed");
        getActionMap().put("escapePressed", exit);

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE") ,
                "spacePressed");
        getActionMap().put("spacePressed", setSearch);
        
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F") ,
                "fPressed");
        getActionMap().put("fPressed", searcher);
    }

    Action exit = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            main.main.ExitToMain();
            setVisible(false);
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


                    if (found) {

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
}
