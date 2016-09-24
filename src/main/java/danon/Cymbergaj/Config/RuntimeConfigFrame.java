package danon.Cymbergaj.Config;

import javax.swing.*;
import java.awt.*;

public class RuntimeConfigFrame extends JFrame {
    private JCheckBox networkCheckBox;
    private JTextField hostName;
    private JTextField userName;
    private GetConfigListener listener;

    public void getRuntimeConfig(GetConfigListener onGetConfig) {
        this.listener = onGetConfig;
        initFrame();
    }

    private void initFrame() {
        addComponents();

        this.setTitle("Configuration");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void addComponents() {
        this.setLayout(new BorderLayout());

        JLabel userNameLabel = new JLabel("Username:");
        userName = new JTextField("Guest");
        networkCheckBox = new JCheckBox("Network Game");
        JLabel hostNameLabel = new JLabel("Hostname:");
        hostName = new JTextField("localhost");
        hostName.setEnabled(false);

        networkCheckBox.addActionListener(event -> hostName.setEnabled(networkCheckBox.isSelected()));

        JButton okButton = new JButton("OK");
        okButton.addActionListener(event -> {
            listener.getConfig(makeRuntimeConfig());
            this.dispose();
        });

        JPanel fieldsPane = new JPanel(new GridLayout(6, 1));
        fieldsPane.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        fieldsPane.add(userNameLabel);
        fieldsPane.add(userName);
        fieldsPane.add(networkCheckBox);
        fieldsPane.add(hostNameLabel);
        fieldsPane.add(hostName);

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 30, 20, 30));
        buttonPane.add(okButton);

        this.add(fieldsPane, BorderLayout.CENTER);
        this.add(buttonPane, BorderLayout.PAGE_END);
        this.pack();
    }

    private RuntimeConfig makeRuntimeConfig() {
        return new RuntimeConfig(userName.getText(), networkCheckBox.isSelected(), hostName.getText());
    }
}
