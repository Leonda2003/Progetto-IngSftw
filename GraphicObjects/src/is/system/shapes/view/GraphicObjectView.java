package is.system.shapes.view;

import is.system.shapes.model.GraphicObject;

import java.awt.Graphics2D;

public interface GraphicObjectView {

	void drawGraphicObject(GraphicObject go, Graphics2D g);
	void setId(String id);
	void setGroup(String group);

}
