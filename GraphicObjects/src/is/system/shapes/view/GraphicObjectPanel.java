package is.system.shapes.view;

import is.system.shapes.model.GraphicEvent;
import is.system.shapes.model.GraphicObject;
import is.system.shapes.model.GraphicObjectListener;
import is.system.prompt.visitor.Context;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;

public class GraphicObjectPanel extends JComponent implements GraphicObjectListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8993548105090978185L;

	/**
	 * @directed true
	 */


	private HashMap<String,GraphicObject> objectsWithID = Context.CONTEXT.getAllShape();


	public GraphicObjectPanel() {
		setBackground(Color.WHITE);
		Context.CONTEXT.setGraphicPanel(this);
	}

	@Override
	public void graphicChanged(GraphicEvent e) {
		repaint();
		revalidate();

	}

	
	public GraphicObject getGraphicObjectAt(Point2D p) {

		for (String id : objectsWithID.keySet()) {
			GraphicObject go = objectsWithID.get(id);
			if (go.contains(p))
				return go;
		}
		return null;
	}




	@Override
	public Dimension getPreferredSize() {
		Dimension ps = super.getPreferredSize();
		double x = ps.getWidth();
		double y = ps.getHeight();
		for (String id : objectsWithID.keySet()) {
			GraphicObject go = objectsWithID.get(id);
			double nx = go.getPosition().getX() + go.getDimension().getWidth() / 2;
			double ny = go.getPosition().getY() + go.getDimension().getHeight() / 2;
			if (nx > x)
				x = nx;
			if (ny > y)
				y = ny;
		}
		return new Dimension((int) x, (int) y);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (String id : objectsWithID.keySet()) {
			GraphicObject go = objectsWithID.get(id);
			GraphicObjectView view = GraphicObjectViewFactory.FACTORY.createView(go);
			view.setId(id);
			view.setGroup(go.myGroup());
			view.drawGraphicObject(go, g2);
		}
	}

	public void add(GraphicObject go) {

		objectsWithID = Context.CONTEXT.getAllShape();
		go.addGraphicObjectListener(this);
		repaint();
	}

	public void remove(GraphicObject go) {

		objectsWithID = Context.CONTEXT.getAllShape();
		if (!objectsWithID.containsValue(go)) {
			repaint();
			go.removeGraphicObjectListener(this);
		}
	}

	
}
