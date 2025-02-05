package is.system.prompt.visitor;

import is.system.exception.SyntaxException;
import is.system.shapes.model.GraphicObject;
import is.system.shapes.model.GroupObject;
import is.system.prompt.GraphicObjectPromptPanel;
import is.system.shapes.view.GraphicObjectPanel;

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


    /*_________________________SETTING GRAPHIC PANEL AND PROMPT PANEL_________________________ */

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


    /*_________________________ADDING METHODS_________________________ */
    /**
     *This method handles adding a graphical object to the cache.
     * If the object is a group, it notifies all members of the group that they are now part of it.
     * @param g is the graphical object
     * @return returns the ID number assigned to the new object in the cache.
     */
    public int addGraphicObject(GraphicObject g){
        inizialize();
        cache.get("All").put("id"+ID.incrementAndGet(),g);
        if(g.getType().equals("Group")){
            ((GroupObject) g).notifyTheyAreAGroup("id"+ID);
        }
        cache.get(g.getType()).put("id"+ID,g);
        String s = "new "+g.getType()+" object created with id"+ID;
        write(s);

        return ID.intValue();
    }


    /**
     * This method, which is called after deleting an object,
     * re-adds the object or the entire group of objects that had been previously removed.
     * @param id The ID to reassign to that object, which it had before its removal.
     * @param go The object to re-add.
     * @return The added graphical object.
     */
    public GraphicObject addAllRemoved(String id, GraphicObject go){
        if(cache.get("All").containsKey(id)){
            for(GraphicObject g : cache.get("Group").values()){
                GroupObject group = (GroupObject) g;
                if(group.contains(id)) return go;
            }
            throw new SyntaxException("IMPOSSIBILE RICREARE L'OGGETTO CON "+ID+"PERCHE' CE NE STA QUALCHE ALTRO CON LO STESSO ID");
        }
        cache.get("All").put(id,go);
        cache.get(go.getType()).put(id,go);
        go.addMeToAllMyOldGroups(id);
        StringBuilder sb = new StringBuilder();
        sb.append("added again the "+go.getType()+" with "+id);
        if(go.getType().equals("Group")){
            GroupObject group = (GroupObject) go;
            HashMap<String,GraphicObject> members = group.getGroup();
            for(String objid : members.keySet()){
                addAllRemoved(objid,members.get(objid)).getType();
            }
        }
        write(sb.toString());
        return go;
    }

    /**
     * This method re-adds the last removed GroupObject after an ungroup operation.
     * @param id The ID to reassign to that group, which it had before its removal.
     * @param go The group object to re-add.
     * @return The added group object.
     */
    public GroupObject addRemovedGroup(String id, GroupObject go){
        if(cache.get("All").containsKey(id)) throw new SyntaxException("IMPOSSIBILE RICREARE L'OGGETTO CON "+ID+"PERCHE' CE NE STA QUALCHE ALTRO CON LO STESSO ID");
        cache.get("All").put(id,go);
        cache.get(go.getType()).put(id,go);
        go.addMeToAllMyOldGroups(id);
        go.notifyTheyAreAGroup(id);
        String s = "added again the "+go.getType()+" with "+id;
        write(s);
        return go;
    }

    /*_________________________REMOVING METHODS_________________________ */
    /**
     * This method removes a graphical object.
     * If the object is a group, it also removes all graphical objects that are part of it.
     * @param id The ID of the graphical object.
     * @return Returns the removed object.
     */
    public GraphicObject remove(String id){
        if(inizialize()) {throw new SyntaxException("No graphic objects were found. Try initializing some.");}
        GraphicObject g = null;
        if(cache.get("All").containsKey(id)){
            g = cache.get("All").get(id);
            if(g.getType().equals("Group")){
                GroupObject group = (GroupObject) g;
                return removeGroup(id,group);
            }
            cache.get(g.getType()).remove(id,g);
            cache.get("All").remove(id,g);
            g.removeMeFromAllMyGroups(id);
            String s = "removed the "+g.getType()+" with "+id;
            write(s);
        }
        return g;
    }


    private GroupObject removeGroup(String id, GroupObject group){
        if(cache.get("Group").containsKey(id)){
            HashMap<String,GraphicObject> map = group.getGroup();
            for(String objid : map.keySet()){
                remove(objid);
            }

            cache.get(group.getType()).remove(id,group);
            cache.get("All").remove(id,group);
            group.removeMeFromAllMyGroups(id);
            String s = "removed the the group with "+id;
            write(s);
            group.setGroup(map);
            return group;
        }
        throw new SyntaxException("No group objects were found with "+id);
    }

    /**
     * This method removes only the group, leaving its components intact.
     * @param id the id group to ungroup
     * @param group
     * @return the ungrouped group
     */
    public GroupObject removeTheGroup(String id,GroupObject group){
        if(cache.get(group.getType()).containsKey(id)) {
            group.ungroupAll(id);
            cache.get(group.getType()).remove(id, group);
            cache.get("All").remove(id, group);
            group.removeMeFromAllMyGroups(id);
            String s = "removed the the group with" + id;
            write(s);
            return group;
        }
        throw new SyntaxException("No graphic objects were found whit "+id);
    }


    public void removeLastAdded(){
        String id = "id"+getID();
        ID.decrementAndGet();
        remove(id);
    }

    public void removeLastAddedGroup(GroupObject group){
        String id = "id"+getID();
        ID.decrementAndGet();
        removeTheGroup(id,group);
    }



    /*_________________________GETTING METHODS_________________________ */

    public GraphicObject getGraphicObject(String id) {
        if(inizialize()) {throw new SyntaxException("No graphic objects were found. Try initializing some.");}
        if(cache.get("All").containsKey(id)) return cache.get("All").get(id);
        throw new SyntaxException("No graphic objects were found whit "+id);
    }

    public GroupObject getGroupObject(String id) {
        if(inizialize()) {throw new SyntaxException("No graphic objects were found. Try initializing some.");}
        if(cache.get("Group").containsKey(id)) return (GroupObject) cache.get("Group").get(id);
        throw new SyntaxException("No group objects were found whit "+id);
    }

    public HashMap<String,GraphicObject> getType(String type) {
        if(inizialize()) {throw new SyntaxException(offset+"No graphic objects were found. Try initializing some.");}
        if(cache.containsKey(type)) { return new HashMap<>(cache.get(type));}
        throw new SyntaxException("No graphic objects were found whit type "+type);
    }


    public HashMap<String,GraphicObject> getAllShape(){
        inizialize();
        HashMap<String,GraphicObject> allShape = new HashMap<>(cache.get("Circle"));
        allShape.putAll(cache.get("Rectangle"));
        allShape.putAll(cache.get("Image"));
        return allShape;
    }

    /*_________________________SUPPORT METHODS_________________________ */

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

    public void NotContainShape(String id){
        if(!cache.get("All").containsKey(id)) throw new SyntaxException("No graphic objects were found whit "+id);
    }

    public void write(String s){
        if(graphicObjectPromptPanel !=null) graphicObjectPromptPanel.write(s);
    }

    public void clearLine(){
        if(graphicObjectPromptPanel !=null) graphicObjectPromptPanel.clearLine();
    }

    public int getID() {
        return ID.intValue();
    }




}
