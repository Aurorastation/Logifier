package menus;

import java.awt.BorderLayout;
import java.awt.Color;
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

    public void initHelp() {
        setLayout(new BorderLayout());

        JTextArea helpTxt = new JTextArea(" Heya, this logifier was made by Geeves, initially created in 2018, converted to darkmode in 2020."
                + "\n"
                + "\n Controls:"
                + "\n Escape - Exit to Main Menu (Will Exit program if pressed on main menu)"
                + "\n Space - Sets the text you want to search for. (Must be viewing logs, full or searched)"
                + "\n F - Go to the next instance of the text you entered during the Space operation."
                + "\n D - Go to the previous instance of the text you entered during the Space operation."
                + "\n"
                + "\n Menus:"
                + "\n View Full Logs - This displays a colour-coded version of the logfile you have put into the folder."
                + "\n Search - This searches the logs for case insensitive occurances of the text you have entered."
                + "\n"
                + "\n How to use:"
                + "\n Take your log file, called something like 'bBxty-xFvt' and rename it to 'logifier', then place it in the same folder as 'logifier.jar'"
                + "\n"
                + "\n Additional info:"
                + "\n If anything horrible happens, contact me at Geeves on discord.");
        helpTxt.setFocusable(false);
        helpTxt.setBackground(Color.decode("#223620"));
        helpTxt.setForeground(Color.decode("#A8D1A5"));
        add(helpTxt, BorderLayout.CENTER);

        JButton back = new JButton("Back");
        back.setBackground(Color.decode("#1F2E1D"));
        back.setForeground(Color.decode("#A8D1A5"));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                core.Core.ExitToMain();
            }
        });
        add(back, BorderLayout.SOUTH);

        setVisible(true);

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"),
                "escapePressed");
        getActionMap().put("escapePressed",
                exit);
    }

    Action exit = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            core.Core.ExitToMain();
        }
    };
}
