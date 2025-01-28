package is.shapes.model;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupObject extends AbstractGraphicObject{

    List<AbstractGraphicObject> group=new ArrayList<>();

    GroupObject(AbstractGraphicObject... graphicObjects){
        Collections.addAll(this.group, graphicObjects);
    }

    public List<AbstractGraphicObject> list() {
        return null;
    }

    public boolean ungroup(GraphicObject g) {return group.remove(g);}

    public void ungroupAll(GraphicObject g) {group.clear();}



    @Override
    public void moveTo(Point2D p) {

    }

    @Override
    public void moveTo(double x, double y) {

    }

    @Override
    public Point2D getPosition() {
        return null;
    }

    @Override
    public Dimension2D getDimension() {
        return null;
    }

    @Override
    public void scale(double factor) {

    }

    @Override
    public boolean contains(Point2D p) {
        return false;
    }

    @Override
    public double area() {
        return 0;
    }

    @Override
    public double perimeter() {
        return 0;
    }

    @Override
    public String getType() {
        return "Group";
    }
}
