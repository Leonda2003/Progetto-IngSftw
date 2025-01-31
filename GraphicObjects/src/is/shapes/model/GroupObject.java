package is.shapes.model;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GroupObject extends AbstractGraphicObject{

    private HashMap<String,GraphicObject> group;

    public GroupObject(HashMap<String,GraphicObject> group){
        this.group = group;
    }

    public void notifyGroup(String id) {
        for (GraphicObject g : group.values()){
            g.addGroup(id,this);
        }
    }

    public HashMap<String,GraphicObject> getGroup(){
        return new HashMap<>(group);
    }

    public void removeMember(String objid){
        group.remove(objid);
    }

    public void addMember(String id, GraphicObject g){
        group.put(id,g);
    }

    public void ungroup(String id) {
        for(GraphicObject g : group.values()){
            g.removeFromGroup(id);
        }
    }

    @Override
    public void moveTo(Point2D p) {
        for(GraphicObject go : group.values()){
            go.moveTo(p);
        }

    }

    @Override
    public void moveTo(double x, double y) {
        for(GraphicObject go : group.values()){
            go.moveTo(x,y);
        }
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
        for(GraphicObject go : group.values()){
            go.scale(factor);
        }

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

    @Override
    public String properties(String id) {
        String info = String.format ("[%s] [%s] dim=[%d]%n",id, this.getType(),this.group.size()) ;
        StringBuilder sb = new StringBuilder();
        sb.append(info);
        for(String objid: group.keySet()){
            GraphicObject g = group.get(objid);

            sb.append("\t"+g.properties(objid));
        }
        sb.append("\n");
        return sb.toString();
    }
}
