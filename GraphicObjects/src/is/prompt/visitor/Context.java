package is.prompt.visitor;

import is.exception.SyntaxException;
import is.shapes.model.GraphicObject;
import is.shapes.model.GroupObject;
import is.prompt.GraphicObjectPromptPanel;
import is.shapes.view.GraphicObjectPanel;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public enum Context {

    CONTEXT;
    private final AtomicInteger ID=new AtomicInteger(0);
    private GraphicObjectPromptPanel graphicObjectPromptPanel;
    private boolean promptPanel = true;
    private GraphicObjectPanel graphicObjectPanel;
    private boolean graphicPanel = true;

    private final String offset="   ";
    private final Map<String,HashMap<String,GraphicObject>> cache = Collections.synchronizedMap(new HashMap<>());

    public void setGraphicObjectPromptPanel(GraphicObjectPromptPanel graphicObjectPromptPanel) {
        if(promptPanel){
            this.graphicObjectPromptPanel = graphicObjectPromptPanel;
            this.promptPanel=false;
        }
    }

    public void setGraphicPanel(GraphicObjectPanel graphicObjectPanel) {
        if(graphicPanel){
            this.graphicObjectPanel = graphicObjectPanel;
            graphicPanel = false;
        }
    }

    protected GraphicObjectPanel getGraphicObjectPanel(){
        return graphicObjectPanel;
    }


    public int addGrapichObject(GraphicObject g){
        inizialize();
        cache.get("All").put("id"+ID.incrementAndGet(),g);
        cache.get(g.getType()).put("id"+ID,g);
        if(g.getType().equals("Group")){
            ((GroupObject) g).notifyGroup("id"+ID);
        }
        if(write()){
            String s = offset+"new "+g.getType()+" object created with id"+ID;
            graphicObjectPromptPanel.write(s);
        }
        return ID.intValue();
    }

    public int addGroup(GroupObject g){
        if(inizialize()) {throw new SyntaxException(offset+"No graphic objects were found. Try initializing some.");}
        return addGrapichObject(g);
    }

    public GraphicObject remove(String id){
        GraphicObject g = null;
        if(inizialize()) {return null;}
        if(cache.get("All").containsKey(id)){
            g = cache.get("All").get(id);
            if(g.getType().equals("Group")){
                GroupObject group = (GroupObject) g;
                return removeAllGroup(id,group);
            }
            cache.get(g.getType()).remove(id,g);
            cache.get("All").remove(id,g);
            g.removeFromGroup(id);
            if(write()){
                String s = offset+"removed the "+g.getType()+" with "+id;
                graphicObjectPromptPanel.write(s);
            }
        }
        return g;
    }

    private GraphicObject removeAllGroup(String id,GroupObject group){
        for(String objid : group.getGroup().keySet()){
            remove(objid);
            cache.get(group.getType()).remove(id,group);
            cache.get("All").remove(id,group);
            }
        if(write()){
            String s = offset+"removed the all the group with"+id;
            graphicObjectPromptPanel.write(s);
        }
        return group;
    }

    public GroupObject removeGroup(String id){
        GroupObject group = null;
        if(cache.get("Group").containsKey(id)){
            group =(GroupObject) cache.get("All").get(id);
            for(GraphicObject g : group.getGroup().values()){
                g.removeGroup(id,group);
            }
            cache.get(group.getType()).remove(id,group);
            cache.get("All").remove(id,group);
        }
        if(write()){
            String s = offset+"removed the the group with"+id;
            graphicObjectPromptPanel.write(s);
        }
        return group;
    }

    public GraphicObject getGraphicObject(String id) {
        if(inizialize()) {throw new SyntaxException(offset+"No graphic objects were found. Try initializing some.");}
        if(cache.get("All").containsKey(id)) return cache.get("All").get(id);
        throw new SyntaxException(offset+"No graphic objects were found whit "+id);
    }

    public GroupObject getGroupObject(String id) {
        if(inizialize()) {throw new SyntaxException(offset+"No graphic objects were found. Try initializing some.");}
        if(cache.get("Group").containsKey(id)) return (GroupObject) cache.get("Group").get(id);
        throw new SyntaxException(offset+"No group objects were found whit "+id);
    }

    public HashMap<String,GraphicObject> getType(String type) {
        if(inizialize()) {throw new SyntaxException(offset+"No graphic objects were found. Try initializing some.");}
        if(cache.containsKey(type)) { return new HashMap<>(cache.get(type));}
        throw new SyntaxException(offset+"No graphic objects were found whit type "+type);
    }




    public HashMap<String,GraphicObject> All() {
        if(inizialize()) {throw new SyntaxException(offset+"No graphic objects were found. Try initializing some.");}
       return new HashMap<>(cache.get("All"));
    }

    public HashMap<String,GraphicObject> Groups() {
        if(inizialize()) {throw new SyntaxException(offset+"No graphic objects were found. Try initializing some.");}
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
            cache.put("Group",new HashMap<>());
            return true;
        }
        return false;
    }

    public void removeLastAdded(){
        String id = "id"+getID();
        ID.decrementAndGet();
        remove(id);
    }

    public void removeLastAddedGroup(){
        String id = "id"+getID();
        ID.decrementAndGet();
        removeGroup(id);
    }


    public GraphicObject addRemoved(String id, GraphicObject go){
        if(cache.get("All").containsKey(id)) throw new SyntaxException("IMPOSSIBILE ANNULARE RICREARE L'OGGETTO CON "+ID+"PERCHE' CE NE STA QUALCHE ALTRO CON LO STESSO ID");
        cache.get("All").put(id,go);
        cache.get(go.getType()).put(id,go);
        go.readToGroup(id);
        if(go.getType().equals("Group")){
            ((GroupObject) go).notifyGroup(id);
        }
        if(write()){
            String s = offset+"added again the "+go.getType()+" with "+id;
            graphicObjectPromptPanel.write("\n"+s);
        }
        return go;
    }

    public GraphicObject addAllRemovedGroup(String id, GraphicObject go){
        if(cache.get("All").containsKey(id)) throw new SyntaxException("IMPOSSIBILE ANNULARE RICREARE L'OGGETTO CON "+ID+"PERCHE' CE NE STA QUALCHE ALTRO CON LO STESSO ID");
        cache.get("All").put(id,go);
        cache.get(go.getType()).put(id,go);
        if(go.getType().equals("Group")){
            GroupObject group = (GroupObject) go;
            HashMap<String,GraphicObject> members = group.getGroup();
            for(String objid : members.keySet()){
                addAllRemovedGroup(objid,members.get(objid));
            }

        }
        if(write()){
            String s = offset+"added again the "+go.getType()+" with "+id;
            graphicObjectPromptPanel.write("\n"+s);
        }
        return go;
    }

    private boolean write(){
        return (graphicObjectPromptPanel !=null);
    }


    public int getID() {
        return ID.intValue();
    }

    public void NotContainShape(String id){
        if(!cache.get("All").containsKey(id)/*(cache.get("Circle").containsKey(id) ||
                cache.get("Rectangle").containsKey(id) || cache.get("Image").containsKey(id))*/)
            throw new SyntaxException(offset+"No graphic objects were found whit "+id);
    }




}
