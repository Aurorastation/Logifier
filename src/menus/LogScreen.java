
package menus;

import backend.ColorPane;
import backend.logColorArray;
import backend.logger;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

public final class LogScreen extends JPanel {

    public LogScreen() {
        initLogView();
    }
     
    public LogScreen(String search) {
         initSearchedView(search);
    }

    //Method used to get everything ready, which is then called in the constructor
    public void initLogView() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(700, 600));
        
        JScrollPane scrollPanel = new JScrollPane();
        add(scrollPanel).setPreferredSize(new Dimension(700, 600));
        scrollPanel.setFocusable(false);
        
        ColorPane logScreen = new ColorPane();
            for (int i = 0; i < logger.getFileSize(); i++) {
                if (logColorArray.getType(i).equalsIgnoreCase("BASIC")){
                    logScreen.append(Color.black, logger.getLogString(i));
                } else if (logColorArray.getType(i).equalsIgnoreCase("ADMIN")){
                    logScreen.append(Color.red, logger.getLogString(i));
                } else if (logColorArray.getType(i).equalsIgnoreCase("OOC")){
                    logScreen.append(Color.blue, logger.getLogString(i));
                } else if (logColorArray.getType(i).equalsIgnoreCase("ATTACK")){
                    logScreen.append(Color.MAGENTA, logger.getLogString(i));
                } else if (logColorArray.getType(i).equalsIgnoreCase("IGNORE")){
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
    }
    
    public void initSearchedView(String search) {
        logger.populateSearch(search);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(700, 600));
        
        JScrollPane scrollPanel = new JScrollPane();
        add(scrollPanel).setPreferredSize(new Dimension(700, 600));
        scrollPanel.setFocusable(false);
        
        ColorPane logScreen = new ColorPane();
            for (int i = 0; i < logger.getSearchedSize(); i++) {
                if (logColorArray.getSType(i).equalsIgnoreCase("BASIC")){
                    logScreen.append(Color.black, logger.getSearchedString(i));
                } else if (logColorArray.getSType(i).equalsIgnoreCase("ADMIN")){
                    logScreen.append(Color.red, logger.getSearchedString(i));
                } else if (logColorArray.getSType(i).equalsIgnoreCase("OOC")){
                    logScreen.append(Color.blue, logger.getSearchedString(i));
                } else if (logColorArray.getSType(i).equalsIgnoreCase("ATTACK")){
                    logScreen.append(Color.MAGENTA, logger.getSearchedString(i));
                } else if (logColorArray.getSType(i).equalsIgnoreCase("IGNORE")){
                    logScreen.append(Color.gray, logger.getSearchedString(i));
                }
            }
        logScreen.setEditable(false);
        scrollPanel.setViewportView(logScreen);
        
        setVisible(true);

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
    }};
}
