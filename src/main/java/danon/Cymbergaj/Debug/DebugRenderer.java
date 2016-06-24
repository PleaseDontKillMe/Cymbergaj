package danon.Cymbergaj.Debug;

import danon.Cymbergaj.View.Renderer.Renderable;

import java.awt.*;


public class DebugRenderer implements Renderable {
    private DebugExport export;

    public DebugRenderer(DebugExport export) {
        this.export = export;
    }

    @Override
    public void renderOn(Graphics2D canvas) {

        canvas.setColor(Color.red);
        canvas.fillOval(export.a.getX()-3, export.a.getY()-3, 6, 6);

        canvas.setColor(Color.green);
        canvas.fillOval(export.b.getX()-3, export.b.getY()-3, 6, 6);

        canvas.setColor(Color.blue);
        canvas.fillOval(export.ship.getX()-3, export.ship.getY()-3, 6, 6);

        if (export.distance1 != null && export.distance2 != null) {
            canvas.setColor(Color.black);
            danon.Cymbergaj.Geometry.Point p1 = export.a.find(export.distance1, export.fireball.getDirection());
            danon.Cymbergaj.Geometry.Point p2 = export.a.find(export.distance2, export.fireball.getDirection());
            canvas.fillOval(p1.getX()-3, p1.getY()-3, 6, 6);
            canvas.fillOval(p2.getX()-3, p2.getY()-3, 6, 6);
        }

        canvas.drawString("distance:  " + dToS(export.distance), 20, 50);
        canvas.drawString("distance1:  " + dToS(export.distance1), 20, 70);
        canvas.drawString("distance2: " + dToS(export.distance2), 20, 90);
        canvas.drawString("distance: " + dToS(export.actualDistance), 20, 110);

        canvas.setColor(Color.black);
    }

    public String dToS(Double d) {
        return d != null ? d.toString() : "";
    }
}
