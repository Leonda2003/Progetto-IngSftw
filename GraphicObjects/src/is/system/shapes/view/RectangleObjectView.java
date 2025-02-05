package is.system.shapes.view;

import is.system.shapes.model.GraphicObject;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class RectangleObjectView implements GraphicObjectView {

	private String id;
	private String group;
	@Override
	public void drawGraphicObject(GraphicObject go, Graphics2D g) {
		Point2D position = go.getPosition();
		Dimension2D dim = go.getDimension();
		double x = position.getX() - dim.getWidth() / 2.0;
		double y = position.getY() - dim.getHeight() / 2.0;
		g.draw(new Rectangle2D.Double(x, y, dim.getWidth(), dim.getHeight()));

		double fontSizeID = Math.min(dim.getWidth() / 2, dim.getHeight() / 2);
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
