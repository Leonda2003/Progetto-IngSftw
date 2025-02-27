package is.system.shapes.view;

import is.system.cmd.CmdHandler;
import is.system.shapes.model.AbstractGraphicObject;
import is.system.shapes.model.GraphicObject;
import is.system.shapes.specificCmd.NewObjectCmd;

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
