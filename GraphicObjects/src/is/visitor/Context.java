package is.visitor;

import is.cmd.Cmd;
import is.exception.SyntaxException;
import is.shapes.model.GraphicObject;
import is.shapes.model.GroupObject;
import is.shapes.view.GraphicObjectView;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public enum Context {

    CONTEXT;
    private final AtomicInteger ID=new AtomicInteger(0);

    private final Map<String,HashMap<String,GraphicObject>> cache = Collections.synchronizedMap(new HashMap<>());

    public int addGrapichObject(GraphicObject g){
        if(cache.isEmpty()) {
            cache.put("All", new HashMap<>());
            cache.put("Circle", new HashMap<>());
            cache.put("Rectangle", new HashMap<>());
            cache.put("Image", new HashMap<>());
            cache.put("Groups",new HashMap<>());
        }
        cache.get("All").put("id"+ID.incrementAndGet(),g);
        cache.get(g.getType()).put("id"+ID,g);
        return ID.intValue();
    }

    public int addGroup(GroupObject g){
        if(cache.isEmpty()) {
            cache.put("All", new HashMap<>());
            cache.put("Circle", new HashMap<>());
            cache.put("Rectangle", new HashMap<>());
            cache.put("Image", new HashMap<>());
            cache.put("Groups",new HashMap<>());
            throw new SyntaxException("No graphic objects were found. Try initializing some.");
        }
        cache.get("All").put("id"+ID.incrementAndGet(),g);
        cache.get(g.getType()).put("id"+ID,g);
        return ID.intValue();
    }

    public GraphicObject remove(String id){
        GraphicObject g = null;
        if(cache.isEmpty()) {
            cache.put("All", new HashMap<>());
            cache.put("Circle", new HashMap<>());
            cache.put("Rectangle", new HashMap<>());
            cache.put("Image", new HashMap<>());
            cache.put("Groups",new HashMap<>());
            return null;
        }
        if(cache.get("All").containsKey(id)){
            g = cache.get("All").get(id);
            cache.get(g.getType()).remove(id,g);
            cache.get("All").remove(id,g);
        }
        return g;
    }

    public GraphicObject getGraphicObject(String id) {
        if(cache.isEmpty()) {
            cache.put("All", new HashMap<>());
            cache.put("Circle", new HashMap<>());
            cache.put("Rectangle", new HashMap<>());
            cache.put("Image", new HashMap<>());
            cache.put("Groups",new HashMap<>());
            throw new SyntaxException("No graphic objects were found. Try initializing some.");
        }
        if(cache.get("All").containsKey(id)) return cache.get("All").get(id);
        throw new SyntaxException("No graphic objects were found whit "+id);
    }

    public HashMap<String,GraphicObject> All() {
        if(cache.isEmpty()) {
            cache.put("All", new HashMap<>());
            cache.put("Circle", new HashMap<>());
            cache.put("Rectangle", new HashMap<>());
            cache.put("Image", new HashMap<>());
            cache.put("Groups",new HashMap<>());
            throw new SyntaxException("No graphic objects were found. Try initializing some.");
        }
       return new HashMap<>(cache.get("All"));
    }

    public HashMap<String,GraphicObject> Groups() {
        if(cache.isEmpty()) {
            cache.put("All", new HashMap<>());
            cache.put("Circle", new HashMap<>());
            cache.put("Rectangle", new HashMap<>());
            cache.put("Image", new HashMap<>());
            cache.put("Groups",new HashMap<>());
            throw new SyntaxException("No graphic objects were found. Try initializing some.");
        }
        return new HashMap<>(cache.get("Groups"));
    }


    public void redo() {

    }

    public void undo() {

    }



}
