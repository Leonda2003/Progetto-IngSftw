package is.system.shapes.model;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.*;
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
        for(GraphicObject g : group.values()){
            HashSet<String> newMap = new HashSet<>(group.keySet());
            if(g instanceof GroupObject)  InnerScale((GroupObject) g, newMap,factor);
            else g.scale(factor);
        }
    }

    private void InnerScale(GroupObject group, HashSet<String> map,double factor){

        HashMap<String,GraphicObject> groupmap = group.getGroup();
        for(String id : groupmap.keySet()){
            if(!map.contains(id)){
                GraphicObject toScale = groupmap.get(id);
                if(toScale instanceof GroupObject) {
                    GroupObject otherGroup = (GroupObject) toScale;
                    map.addAll(groupmap.keySet());
                    InnerScale(otherGroup,map,factor);
                }else{
                   toScale.scale(factor);
                }
            }
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
            if(g instanceof GroupObject) sum += InnerAreaPerimeter((GroupObject) g, new HashSet<>(group.keySet()),false);
            else sum += g.area();
        }
        return sum;
    }

    @Override
    public double perimeter() {
        double sum = 0;
        HashSet<String> newMap = new HashSet<>(group.keySet());
        for(GraphicObject g : group.values()){
            if(g instanceof GroupObject) sum += InnerAreaPerimeter((GroupObject) g, newMap,true);
                else sum += g.perimeter();
        }
        return sum;
    }

    private double InnerAreaPerimeter(GroupObject group, HashSet<String> map,boolean perimeter){
        double sum = 0;

        HashMap<String,GraphicObject> groupmap = group.getGroup();
        for(String id : groupmap.keySet()){
            if(!map.contains(id)){
                GraphicObject toAdd = groupmap.get(id);
                if(toAdd instanceof GroupObject) {
                    GroupObject otherGroup = (GroupObject) toAdd;
                    map.addAll(groupmap.keySet());
                    sum += InnerAreaPerimeter(otherGroup,map,perimeter);
                }else{
                    if(perimeter) sum += toAdd.perimeter();
                    else sum += toAdd.area();
                }
            }
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
            if(g instanceof GroupObject) sb.append("\t"+
                    String.format ("[%s] [%s] dim=[%d]%n",objid, g.getType(),(int)g.getDimension().getHeight()));
            else sb.append("\t"+g.properties(objid));
        }
        return sb.toString();
    }

    private record GroupMemento( HashMap<String,GraphicObject> group) implements Memento{};

    @Override
    public Memento save() {return new GroupMemento(new HashMap<>(group));}

    @Override
    public void restore(Memento memento) {
        group = ((GroupMemento) memento).group;
        notifyListeners(new GraphicEvent(this));
    }

}
