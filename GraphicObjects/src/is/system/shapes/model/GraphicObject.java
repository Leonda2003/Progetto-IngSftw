package is.system.shapes.model;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

public interface GraphicObject {

	void addGraphicObjectListener(GraphicObjectListener l);

	void removeGraphicObjectListener(GraphicObjectListener l);

	void moveTo(Point2D p);

	default void moveTo(double x, double y){
		moveTo(new Point2D.Double(x, y));
	}

	Point2D getPosition();

	Dimension2D getDimension();

	void scale(double factor);

	boolean contains(Point2D p);

	double area();

	double perimeter();

	String getType();

	String properties(String id);

	void addGroupToMyMap(String id,GroupObject group);

	void removeGroupToMyMap(String id,GroupObject group);

	void removeMeFromAllMyGroups(String objid);

	void addMeToAllMyOldGroups(String id);

	String myGroup();
}
