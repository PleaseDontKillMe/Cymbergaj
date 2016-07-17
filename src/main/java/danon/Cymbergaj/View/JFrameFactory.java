package danon.Cymbergaj.View;


import danon.Cymbergaj.Geometry.Size;
import danon.Cymbergaj.Model.Settings;

import javax.swing.*;
import java.awt.*;

class JFrameFactory {

    static JFrame create(Settings settings)
    {
        JFrame frame = new JFrame();
        Size size = settings.getSize();
        frame.setSize(new Dimension(size.getWidth(), size.getHeight()));
        frame.setTitle(settings.getWindowTitle());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        return frame;
    }
}
