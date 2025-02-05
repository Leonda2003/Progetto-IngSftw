package is.system.shapes.view;

import is.system.shapes.model.GraphicObject;
import is.system.shapes.model.ImageObject;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

public class ImageObjectView implements GraphicObjectView {

	private String id;
	private String group;
	@Override
	public void drawGraphicObject(GraphicObject go, Graphics2D g) {
		ImageObject io = (ImageObject) go;
		Dimension2D dim = io.getDimension();
		Point2D position = io.getPosition();
		Image image = io.getImage();
		int w = (int) (dim.getWidth());
		int h = (int) (dim.getHeight());
		int x = (int) (position.getX()) - w / 2;
		int y = (int) (position.getY()) - h / 2;
		g.drawImage(image, x, y, w, h, null);

		double fontSizeID = Math.min(dim.getWidth() / 10, dim.getHeight() / 10);
		Font fontID = new Font("Arial", Font.PLAIN, (int)fontSizeID);
		g.setFont(fontID);
		FontMetrics metricsID = g.getFontMetrics();
		double textX = x - metricsID.stringWidth(id);
		double textY = y + (metricsID.getHeight()) + metricsID.getAscent();

		g.drawString(id,(float) textX, (float) textY);

		double fontSizeGroup = fontSizeID * 0.3;
		Font fontGroup = new Font("Arial", Font.PLAIN, (int) fontSizeGroup);
		g.setFont(fontGroup);
		FontMetrics metricsGroup = g.getFontMetrics(fontGroup);
		double textXGroupText = textX;
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
