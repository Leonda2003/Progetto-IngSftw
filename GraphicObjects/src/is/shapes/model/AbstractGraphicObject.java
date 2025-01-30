package is.shapes.model;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractGraphicObject implements GraphicObject, Cloneable {



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
