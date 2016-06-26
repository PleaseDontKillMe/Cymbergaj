package danon.Cymbergaj.Config;

import javax.swing.*;
import java.awt.*;

public class RuntimeConfigFrame extends JFrame {

    private JCheckBox checkBox;
    private JTextField hostName;
    private JTextField userName;
    private GetConfigListener listener;

    public void getRuntimeConfig(GetConfigListener onGetConfig) {
        this.listener = onGetConfig;
        initFrame();
    }

    private void initFrame() {
        addComponents();

        this.setTitle("Konfiguracja");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void addComponents() {
        this.setLayout(new BorderLayout());

        checkBox = new JCheckBox("I'm a server");
        JLabel hostNameLabel = new JLabel("Hostname:");
        hostName = new JTextField("localhost");
        JLabel userNameLabel = new JLabel("Username:");
        userName = new JTextField("Guest");

        checkBox.addActionListener(event -> hostName.setEnabled(!checkBox.isSelected()));

        JButton okButton = new JButton("OK");
        okButton.addActionListener(event -> {
            listener.getConfig(makeRuntimeConfig());
            this.dispose();
        });

        JPanel fieldsPane = new JPanel(new GridLayout(6, 1));
        fieldsPane.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        fieldsPane.add(checkBox);
        fieldsPane.add(hostNameLabel);
        fieldsPane.add(hostName);
        fieldsPane.add(userNameLabel);
        fieldsPane.add(userName);

        JPanel buttonPane = new JPanel();
        fieldsPane.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        buttonPane.add(okButton);

        this.add(fieldsPane, BorderLayout.CENTER);
        this.add(buttonPane, BorderLayout.PAGE_END);
        this.pack();
    }

    private RuntimeConfig makeRuntimeConfig() {
        return new RuntimeConfig(hostName.getText(), userName.getText(), ! checkBox.isSelected());
    }
}
