package is.system.shapes.view;

import is.system.shapes.model.GraphicObject;

import java.awt.Graphics2D;
import java.awt.geom.Dimension2D;

public interface GraphicObjectView {

	void drawGraphicObject(GraphicObject go, Graphics2D g,boolean showInfo);
	void setId(String id);
	void setGroup(String group);
	void drowInfo(Graphics2D g, Dimension2D dim, double x, double y);

}
