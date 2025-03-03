package is.system.shapes.model;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

public final class RectangleObject extends AbstractGraphicObject {

	private static final int MAX_SIZE=50000,MIN_SIZE=10;
	private Point2D position;

	private Dimension2D dim;

	public RectangleObject(Point2D pos, double w, double h) {
		if (w <= 0 || h <= 0)
			throw new IllegalArgumentException();
		dim = new Dimension();
		dim.setSize(w, h);
		position = new Point2D.Double(pos.getX(), pos.getY());
	}

	public RectangleObject(double w, double h) {
		if (w <= 0 || h <= 0)
			throw new IllegalArgumentException();
		dim = new Dimension();
		dim.setSize(w, h);
		position = new Point2D.Double(0, 0);
	}

	@Override
	public boolean contains(Point2D p) {
		double w = dim.getWidth() / 2;
		double h = dim.getHeight() / 2;
		double dx = Math.abs(p.getX() - position.getX());
		double dy = Math.abs(p.getY() - position.getY());
		return dx <= w && dy <= h;

	}

	@Override
	public double area() {
        return dim.getHeight() * dim.getWidth();
	}

	@Override
	public double perimeter() {
		return 2*(dim.getWidth())+ dim.getHeight();
	}

	@Override
	public void moveTo(Point2D p) {
		position.setLocation(p);
		notifyListeners(new GraphicEvent(this));
	}

	@Override
	public Point2D getPosition() {

		return new Point2D.Double(position.getX(), position.getY());
	}

	@Override
	public void scale(double factor) {
		if (factor <= 0)
			throw new IllegalArgumentException();

		double w = dim.getWidth() * factor;
		double h = dim.getHeight() * factor;
		if (w > MAX_SIZE) {
			w = MAX_SIZE;
			h = (int)(MAX_SIZE / (dim.getWidth()/dim.getHeight()));
		} else if (w < MIN_SIZE) {
			w = MIN_SIZE;
			h = (int)(MIN_SIZE /(dim.getWidth()/dim.getHeight()));
		}

		if (h > MAX_SIZE) {
			h = MAX_SIZE;
			w = (int) (MAX_SIZE * (dim.getWidth()/dim.getHeight()));
		} else if (h < MIN_SIZE) {
			h = MIN_SIZE;
			w = (int) (MIN_SIZE * (dim.getWidth()/dim.getHeight()));
		}
		dim.setSize(w, h);
		notifyListeners(new GraphicEvent(this));
	}

	@Override
	public Dimension2D getDimension() {
		Dimension2D d = new Dimension();
		d.setSize(dim);
		return d;
	}

	@Override
	public RectangleObject clone() {
		RectangleObject cloned = (RectangleObject) super.clone();
		cloned.position = (Point2D) position.clone();
		cloned.dim = (Dimension2D) dim.clone();
		return cloned;
	}

	@Override
	public String getType() {
		return "Rectangle";
	}

	private record RectangleMemento(Point2D pos, Dimension2D dimension) implements Memento{};
	@Override
	public Memento save() {
		Dimension dimension = new Dimension();
		dimension.setSize(dim);
		return new RectangleMemento(new Point2D.Double(position.getX(), position.getY()), dimension);
	}
	@Override
	public void restore(Memento memento) {
		position = ((RectangleMemento)(memento)).pos;
		dim = ((RectangleMemento)(memento)).dimension;
		notifyListeners(new GraphicEvent(this));
	}
}
