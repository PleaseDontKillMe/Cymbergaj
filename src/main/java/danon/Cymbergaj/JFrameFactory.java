package danon.Cymbergaj;


import danon.Cymbergaj.Model.WindowClosingListener;

import javax.swing.*;
import java.awt.*;

public class JFrameFactory {

    public static JFrame create(Settings settings, WindowClosingListener listener)
    {
        JFrame frame = new JFrame();

        frame.setSize(new Dimension(settings.size.getWidth(), settings.size.getHeight()));
        frame.setTitle(settings.windowTitle);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.addWindowListener(listener);

        return frame;
    }
}