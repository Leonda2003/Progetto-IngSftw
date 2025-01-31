package is.shapes.model;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractGraphicObject implements GraphicObject, Cloneable {


	private HashMap<String,GroupObject> group = new HashMap<>();

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

	public void addGroup(String id,GroupObject group){
		this.group.put(id,group);
	}

	public void removeGroup(String id,GroupObject group){
		this.group.remove(id,group);
	}

	@Override
	public void removeFromGroup(String objid){
		for(String id :group.keySet()){
			group.get(id).removeMember(objid);
		}
	}

	@Override
	public void readToGroup(String id){
		for(GroupObject group : group.values()) group.addMember(id,this);
	}

	@Override
	public GraphicObject clone() {
		try {
			AbstractGraphicObject go = (AbstractGraphicObject) super.clone();
			go.listeners = new LinkedList<>();
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
