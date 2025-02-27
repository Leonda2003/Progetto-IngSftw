package is.system.shapes.model;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractGraphicObject implements GraphicObject, Cloneable {


	/*____________________________LISTENER_______________________________*/

	private  List<GraphicObjectListener> listeners = new LinkedList<>();

	@Override
	public void addGraphicObjectListener(GraphicObjectListener l) {
		if (listeners.contains(l))
			return;
		listeners.add(l);
	}

	@Override
	public void removeGraphicObjectListener(GraphicObjectListener l) {
		listeners.remove(l);
	}


	protected void notifyListeners(GraphicEvent e) {
		for (GraphicObjectListener gol : listeners)
			gol.graphicChanged(e);
	}

	/*____________________________LISTENER_______________________________*/

	/*______________________________GROUP________________________________*/

	private HashMap<String,GroupObject> mygroups = new HashMap<>();

	@Override
	public void addGroupToMyMap(String id,GroupObject group){
		this.mygroups.put(id,group);
	}

	@Override
	public void removeGroupToMyMap(String id,GroupObject group){
		this.mygroups.remove(id,group);
	}

	@Override
	public void removeMeFromAllMyGroups(String objid){
		for(String id :mygroups.keySet()){
			mygroups.get(id).removeMemberFromGroup(objid,this);
		}
	}


	@Override
	public void addMeToAllMyOldGroups(String id){
		for(GroupObject group : mygroups.values()) group.addMemberToGroup(id,this);
	}

	@Override
	public String myGroup() {
		if(mygroups.isEmpty()) return "";
		StringBuilder sb = new StringBuilder();
		sb.append("grp");
		for(String id : mygroups.keySet()) sb.append(" "+id);
		return sb.toString();
	}

	/*______________________________GROUP________________________________*/


	@Override
	public GraphicObject clone() {
		try {
			AbstractGraphicObject go = (AbstractGraphicObject) super.clone();
			go.listeners = new LinkedList<>();
			go.mygroups = new HashMap<>();

			return go;

		} catch (CloneNotSupportedException e) {
			throw new Error(e);
		}
	}

	@Override
	public String properties(String id) {
		return String.format ("[%s] [%s] pos=[%f,%f] dim=[%f,%f]%n",id, this.getType(), this
						.getPosition().getX(), this.getPosition().getY(), this
						.getDimension().getWidth(),
				this.getDimension().getHeight());
	}

}
