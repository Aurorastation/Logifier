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

    private final JButton start;
    private final JButton options;
    private final JButton exit;
    private final JButton help;

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
                panelHolder[m][n].setBackground(Color.ORANGE);
                add(panelHolder[m][n]);
            }
        }

        start = new JButton("View Full Logs");
        start.setPreferredSize(new Dimension(200, 95));
        start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                main.viewLogs();
            }
        });

        panelHolder[1][1].add(start);

        options = new JButton("Search");
        options.setPreferredSize(new Dimension(200, 95));
        options.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                main.viewSearchedLogs();
            }
        });

        panelHolder[2][1].add(options);

        help = new JButton("Help");
        help.setPreferredSize(new Dimension(200, 95));
        help.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                main.OpenHelp();
            }
        });

        panelHolder[3][1].add(help);;

        exit = new JButton("Exit Logifier");
        exit.setPreferredSize(new Dimension(200, 95));
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panelHolder[4][1].add(exit);

        getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"),
                "escapePressed");
        getActionMap().put("escapePressed",
                exitEsc);

        setVisible(true);

    }

    Action exitEsc = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };

}
