package is.system.shapes.model;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;

public final class ImageObject extends AbstractGraphicObject {

	private static double MAX_FACTOR = 1000,MIN_FACTOR=0.01;
	private double factor = 1.0;
	private final Image image;

	private Point2D position;

	public Image getImage() {
		return image;
	}

	public ImageObject(ImageIcon img, Point2D pos) {
		position = new Point2D.Double(pos.getX(), pos.getY());
		image = img.getImage();
	}

	@Override
	public boolean contains(Point2D p) {
		double w = (factor * image.getWidth(null)) / 2;
		double h = (factor * image.getHeight(null)) / 2;
		double dx = Math.abs(p.getX() - position.getX());
		double dy = Math.abs(p.getY() - position.getY());
		return dx <= w && dy <= h;
	}


	@Override
	public void moveTo(Point2D p) {
		position.setLocation(p);
		notifyListeners(new GraphicEvent(this));
	}

	@Override
	public ImageObject clone() {
		ImageObject cloned = (ImageObject) super.clone();
		cloned.position = (Point2D) position.clone();
		return cloned;
	}

	@Override
	public Point2D getPosition() {

		return new Point2D.Double(position.getX(), position.getY());
	}

	@Override
	public void scale(double factor) {
		if (factor <= 0)
			throw new IllegalArgumentException();
		this.factor *= factor;
		if((this.factor)>= MAX_FACTOR) this.factor = MAX_FACTOR;
		else if((this.factor)<= MIN_FACTOR) this.factor = MIN_FACTOR;

		notifyListeners(new GraphicEvent(this));
	}

	@Override
	public Dimension2D getDimension() {
		Dimension dim = new Dimension();
		dim.setSize(factor * image.getWidth(null),
				factor * image.getHeight(null));
		return dim;
	}

	@Override
	public String getType() {

		return "Image";
	}

	@Override
	public double area() {
		double width = factor * image.getWidth(null);
		double height = factor * image.getHeight(null);
		return width * height;
	}

	@Override
	public double perimeter() {
		double width = factor * image.getWidth(null);
		double height = factor * image.getHeight(null);
		return 2 * (width + height);
	}

	private record ImageMemento(Point2D pos, double f) implements Memento{};
	@Override
	public Memento save() {
		return new ImageMemento(new Point2D.Double(position.getX(),position.getY()),factor);
	}
	@Override
	public void restore(Memento memento) {
		position = ((ImageMemento) memento).pos;
		factor = ((ImageMemento) memento).f;
		notifyListeners(new GraphicEvent(this));
	}

}
