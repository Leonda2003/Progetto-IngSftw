package is.system.shapes.model;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

public final  class CircleObject extends AbstractGraphicObject {

	private static final int MAX_RADIUS=25000,MIN_RADIUS=5;

	private Point2D position;

	private double radius;

	public CircleObject(Point2D pos, double r) {
        if (r <= 0)
			throw new IllegalArgumentException();
		position = new Point2D.Double(pos.getX(), pos.getY());
		radius = r;

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
		double r = radius * factor;
		if (r > MAX_RADIUS) {
			radius = MAX_RADIUS;
		} else if (r < MIN_RADIUS) {
			radius = MIN_RADIUS;
		} else {
			radius = r;
		}

		System.out.println("factor: "+factor);
		System.out.println("radius: "+radius);
		notifyListeners(new GraphicEvent(this));
	}


	@Override
	public Dimension2D getDimension() {
		Dimension d = new Dimension();
		d.setSize(2 * radius, 2 * radius);
		return d;
	}

	@Override
	public boolean contains(Point2D p) {
		return (position.distance(p) <= radius);

	}

	@Override
	public double area() {
		return Math.PI*radius*radius;
	}

	@Override
	public double perimeter() {return 2*radius*Math.PI;}

	@Override
	public CircleObject clone() {
		CircleObject cloned = (CircleObject) super.clone();
		cloned.position = (Point2D) position.clone();
		return cloned;
	}

	@Override
	public String getType() {

		return "Circle";
	}

	public double getRadius() {
		return radius;
	}


	private record CircleMemento(Point2D pos, double r) implements Memento{};
	@Override
	public Memento save() {
		return new CircleMemento(new Point2D.Double(position.getX(),position.getY()),radius);
	}
	@Override
	public void restore(Memento memento) {
		position = ((CircleMemento) memento).pos;
		radius = ((CircleMemento) memento).r;
		notifyListeners(new GraphicEvent(this));
	}


}
