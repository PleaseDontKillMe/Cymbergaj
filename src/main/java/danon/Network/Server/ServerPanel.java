package danon.Network.Server;


import com.google.common.collect.ImmutableList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class ServerPanel extends JFrame {
    private final java.awt.List threadsList = new java.awt.List();
    private final java.awt.List logList = new java.awt.List();
    private final Runnable onClose;

    ServerPanel(Runnable onClose) {
        this.onClose = onClose;
    }

    void updateList(ImmutableList<ServerThread> threads) {
        SwingUtilities.invokeLater(() -> {
            threadsList.removeAll();
            threads.forEach(thread -> threadsList.add(thread.toString()));
        });
    }

    void showWindow() {
        initializeWindow();
        setVisible(true);
    }

    private void initializeWindow() {
        setTitle("Server");
        setSize(new Dimension(700, 400));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onClose.run();
                e.getWindow().dispose();
            }
        });

        setLayout(new BorderLayout());
        getContentPane().add(threadsList, "West");
        getContentPane().add(logList, "Center");
        threadsList.setEnabled(false);
        logList.setEnabled(false);
    }
}
