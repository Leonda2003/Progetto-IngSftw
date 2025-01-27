package is.shapes.view;

import is.cmd.CmdHandler;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.specificCmd.NewObjectCmd;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class CreateObjectAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5399493440620423134L;
	AbstractGraphicObject prototype;
	GraphicObjectPanel panel;
	CmdHandler ch;

	public CreateObjectAction(AbstractGraphicObject prototype,
			GraphicObjectPanel panel, CmdHandler ch) {
		super();
		this.prototype = prototype;
		this.panel = panel;
		this.ch = ch;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		GraphicObject go = prototype.clone();
		ch.handle(new NewObjectCmd(panel, go));

	}

}
