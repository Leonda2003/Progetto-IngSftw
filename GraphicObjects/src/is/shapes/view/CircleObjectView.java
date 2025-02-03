package is.shapes.view;

import is.shapes.model.CircleObject;
import is.shapes.model.GraphicObject;

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
		double fontSize = Math.min(dim.getWidth() / 2, dim.getHeight() / 2);
		Font font = new Font("Arial", Font.PLAIN, (int)fontSize);
		g.setFont(font);

		FontMetrics metrics = g.getFontMetrics();
		double textX = x + (dim.getWidth() - metrics.stringWidth(id)) / 2;
		double textY = y + ((dim.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();

		g.drawString(id,(float) textX, (float) textY);
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