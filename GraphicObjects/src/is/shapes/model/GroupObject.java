package is.shapes.model;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class GroupObject{

    List<AbstractGraphicObject> group;

    GroupObject(AbstractGraphicObject... graphicObjects){
        this.group=new ArrayList<>();
        for(AbstractGraphicObject g : graphicObjects) this.group.add(g);
    }


    public List<AbstractGraphicObject> list() {
        return null;
    }

    public boolean ungroup() {
        return true;
    }


    public float area() {
        return 0;
    }

    public float perimeter() {
        return 0;
    }

}
