package is.visitor;

import is.cmd.Cmd;
import is.exception.SyntaxException;
import is.shapes.model.GraphicObject;
import is.shapes.model.GroupObject;
import is.shapes.prompt.GraphicObjectCommandPrompt;
import is.shapes.view.GraphicObjectPanel;
import is.shapes.view.GraphicObjectView;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public enum Context {

    CONTEXT;
    private final AtomicInteger ID=new AtomicInteger(0);
    private GraphicObjectCommandPrompt graphicObjectCommandPrompt=null;
    private final Map<String,HashMap<String,GraphicObject>> cache = Collections.synchronizedMap(new HashMap<>());

    public void setGraphicObjectPanel(GraphicObjectCommandPrompt graphicObjectCommandPrompt) {
        this.graphicObjectCommandPrompt = graphicObjectCommandPrompt;
    }

    public int addGrapichObject(GraphicObject g){
        inizialize();
        cache.get("All").put("id"+ID.incrementAndGet(),g);
        cache.get(g.getType()).put("id"+ID,g);
        if(write()){
            String s = "new "+g.getType()+" object created with id"+ID;
            graphicObjectCommandPrompt.write(s);
        }
        return ID.intValue();
    }

    public int addGroup(GroupObject g){
        if(inizialize()) {throw new SyntaxException("No graphic objects were found. Try initializing some.");}
        return addGrapichObject(g);
    }

    public GraphicObject remove(String id){
        GraphicObject g = null;
        if(inizialize()) {return null;}
        if(cache.get("All").containsKey(id)){
            g = cache.get("All").get(id);
            cache.get(g.getType()).remove(id,g);
            cache.get("All").remove(id,g);
            if(write()){
                String s = "removed the "+g.getType()+" with "+id;
                graphicObjectCommandPrompt.write(s);
            }
        }
        return g;
    }

    public GraphicObject getGraphicObject(String id) {
        if(inizialize()) {throw new SyntaxException("No graphic objects were found. Try initializing some.");}
        if(cache.get("All").containsKey(id)) return cache.get("All").get(id);
        throw new SyntaxException("No graphic objects were found whit "+id);
    }

    public HashMap<String,GraphicObject> getType(String type) {
        if(inizialize()) {throw new SyntaxException("No graphic objects were found. Try initializing some.");}
        if(cache.containsKey(type)) { return new HashMap<>(cache.get(type));}
        throw new SyntaxException("No graphic objects were found whit type "+type);
    }




    public HashMap<String,GraphicObject> All() {
        if(inizialize()) {throw new SyntaxException("No graphic objects were found. Try initializing some.");}
       return new HashMap<>(cache.get("All"));
    }

    public HashMap<String,GraphicObject> Groups() {
        if(inizialize()) {throw new SyntaxException("No graphic objects were found. Try initializing some.");}
        return new HashMap<>(cache.get("Groups"));
    }


    public HashMap<String,GraphicObject> getAllShape(){
        inizialize();
        HashMap<String,GraphicObject> allShape = new HashMap<>(cache.get("Circle"));
        allShape.putAll(cache.get("Rectangle"));
        allShape.putAll(cache.get("Image"));
        return allShape;
    }

    private boolean inizialize(){
        if(cache.isEmpty()) {
            cache.put("All", new HashMap<>());
            cache.put("Circle", new HashMap<>());
            cache.put("Rectangle", new HashMap<>());
            cache.put("Image", new HashMap<>());
            cache.put("Groups",new HashMap<>());
            return true;
        }
        return false;
    }

    public void removeLastAdded(){
        String id = "id"+getID();
        ID.decrementAndGet();
        remove(id);
    }


    public GraphicObject addRemoved(String id, GraphicObject go){
        if(cache.get("All").containsKey(id)) throw new SyntaxException("IMPOSSIBILE ANNULARE RICREARE L'OGGETTO CON "+ID+"PERCHE' CE NE STA QUALCHE ALTRO CON LO STESSO ID");
        cache.get("All").put(id,go);
        cache.get(go.getType()).put(id,go);
        if(write()){
            String s = "added again the "+go.getType()+" with "+id;
            graphicObjectCommandPrompt.write(s);
        }
        return go;
    }

    private boolean write(){
        return (graphicObjectCommandPrompt!=null);
    }


    public int getID() {
        return ID.intValue();
    }

    public void NotContainShape(String id){
        if(!(cache.get("Circle").containsKey(id) ||
                cache.get("Rectangle").containsKey(id) || cache.get("Image").containsKey(id)))
            throw new SyntaxException("No graphic objects were found whit "+id);
    }

    public void redo() {

    }

    public void undo() {

    }



}
