package danon.Network.Server;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

class ServerPanel extends JFrame {
    private final java.awt.List list = new java.awt.List();
    private final Runnable onClose;

    ServerPanel(Runnable onClose) {
        this.onClose = onClose;
    }

    void updateList(List<ServerThread> threads) {
        SwingUtilities.invokeLater(() -> {
            list.removeAll();
            threads.forEach(thread -> list.add(thread.toString()));
        });
    }

    void showWindow() {
        initializeWindow();
        setVisible(true);
    }

    private void initializeWindow() {
        setTitle("Server");
        setSize(new Dimension(300, 400));
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
        getContentPane().add(list, "Center");
        list.setEnabled(false);
    }


}