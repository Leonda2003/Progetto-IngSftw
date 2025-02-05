package is.shapes.model;

import java.awt.*;
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
    public HashMap<String,GraphicObject> getGroup(){
        return new HashMap<>(group);
    }
    public void setGroup(HashMap<String,GraphicObject> group){
        this.group=group;
    }

    public void notifyTheyAreAGroup(String id) {
        for (GraphicObject g : group.values()){
            g.addGroupToMyMap(id,this);
        }
    }

    public void removeMemberFromGroup(String objid,GraphicObject g){
        group.remove(objid, g);
    }

    public void addMemberToGroup(String id, GraphicObject g){
        group.put(id,g);

    }

    public void ungroupAll(String id) {
        for(GraphicObject g : group.values()){
            g.removeGroupToMyMap(id,this);
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
        Dimension d = new Dimension();
        d.setSize(group.size(),group.size());
        return d;
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


    public boolean contains(String id) {
        return group.containsKey(id);
    }


    @Override
    public double area() {
        double sum = 0;
        for(GraphicObject g : group.values()){
            if(!g.getType().equals("Group")) sum += g.area();
        }
        return sum;
    }

    @Override
    public double perimeter() {
        double sum = 0;
        for(GraphicObject g : group.values()){
            if(!g.getType().equals("Group"))sum += g.perimeter();
        }
        return sum;
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
            if(g.getType().equals("Group")) sb.append("\t"+
                    String.format ("[%s] [%s] dim=[%d]%n",objid, g.getType(),(int)g.getDimension().getHeight()));
            else sb.append("\t"+g.properties(objid));
        }
        return sb.toString();
    }
}
