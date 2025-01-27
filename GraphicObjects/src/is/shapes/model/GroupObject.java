package is.shapes.model;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupObject{

    List<AbstractGraphicObject> group=new ArrayList<>();

    GroupObject(AbstractGraphicObject... graphicObjects){
        Collections.addAll(this.group, graphicObjects);
    }

    public List<AbstractGraphicObject> list() {
        return null;
    }

    public boolean ungroup(GraphicObject g) {return group.remove(g);}

    public void ungroupAll(GraphicObject g) {group.clear();}



}
