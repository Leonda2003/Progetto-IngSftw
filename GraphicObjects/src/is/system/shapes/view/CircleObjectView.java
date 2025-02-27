package is.system.shapes.view;

import is.system.shapes.model.CircleObject;
import is.system.shapes.model.GraphicObject;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class CircleObjectView implements GraphicObjectView {

	private String id;
	private String group;
	@Override
	public void drawGraphicObject(GraphicObject go, Graphics2D g) {
		CircleObject co = (CircleObject) go;
		Point2D position = co.getPosition();
		Dimension2D dim = go.getDimension();
		double r = co.getRadius();
		double x = position.getX() - r;
		double y = position.getY() - r;
		g.draw(new Ellipse2D.Double(x, y, r * 2.0, r * 2.0));

		double fontSizeID = dim.getWidth() / 3;
		Font fontID = new Font("Arial", Font.PLAIN, (int)fontSizeID);
		g.setFont(fontID);
		FontMetrics metricsID = g.getFontMetrics();
		double textX = x + (dim.getWidth() - metricsID.stringWidth(id)) / 2;
		double textY = y + ((dim.getHeight() - metricsID.getHeight()) / 2) + metricsID.getAscent();

		g.drawString(id,(float) textX, (float) textY);

		double fontSizeGroup = fontSizeID * 0.3;
		Font fontGroup = new Font("Arial", Font.PLAIN, (int) fontSizeGroup);
		g.setFont(fontGroup);
		FontMetrics metricsGroup = g.getFontMetrics(fontGroup);
		double textXGroupText = x + (dim.getWidth() - metricsGroup.stringWidth(group)) / 2;
		double textYGroupText = textY + metricsGroup.getHeight();

		g.drawString(group, (float) textXGroupText, (float) textYGroupText);

	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void setGroup(String group) {
		this.group = group;

	}
}