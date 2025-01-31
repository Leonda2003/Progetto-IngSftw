package test;

import is.TestGraphics2;
import is.cmd.HistoryCmdHandler;
import is.prompt.GraphicObjectPromptPanel;
import is.prompt.grammarCommand.Command;
import is.prompt.parser.ConcreteFactoryParser;
import is.prompt.parser.FactoryParser;
import is.prompt.parser.analyzer.LexicalAnalyzer;
import is.prompt.visitor.CommandVisitor;
import is.shapes.controller.GraphicObjectController;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.CircleObject;
import is.shapes.model.ImageObject;
import is.shapes.model.RectangleObject;
import is.shapes.view.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;

public class Test {

    @ParameterizedTest
    @ValueSource(strings={"new circle (5.0) (3.1,4.5)", "new img (\"./pippo.png\") (6.1,4.6)", "del id1", "mv id1 (5.9,8.2)", "mvoff id1 (5.9,8.2)",
            "scale id1 2.0", "ls id1", "ls circle", "ls all", "ls groups", "grp id1, id2, id3", "ungrp id3", "area id1", "perimeter rectangle",
            "area all"})
    void testParser(String command) {
        Reader reader = new StringReader(command);
        // Passa il Reader al costruttore della classe ConcreteFactoryParser
        ConcreteFactoryParser parser = new ConcreteFactoryParser(reader);
        // Esegui i tuoi test
        System.out.println(parser.getCommandToInterpret());
    }

    @ParameterizedTest
    @ValueSource(strings={"new circle (5.0) (3.1,4.5)", "new img (\"./pippo.png\") (6.1,4.6)", "del id1", "mv id1 (5.9,8.2)", "mvoff id1 (5.9,8.2)",
            "scale id1 2.0", "ls id1", "ls circle", "ls all", "ls groups", "grp id1, id2, id3", "ungrp id3", "area id1", "perimeter rectangle",
            "area all"})
    void testGraphic(String command) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        JFrame f = new JFrame();

        JToolBar toolbar = new JToolBar();

        JButton undoButt = new JButton("Undo");
        JButton redoButt = new JButton("Redo");

        final HistoryCmdHandler handler = new HistoryCmdHandler();


        final GraphicObjectPanel gpanel = new GraphicObjectPanel();


        gpanel.setPreferredSize(new Dimension(400, 400));

        GraphicObjectViewFactory.FACTORY.installView(RectangleObject.class, new RectangleObjectView());
        GraphicObjectViewFactory.FACTORY.installView(CircleObject.class, new CircleObjectView());
        GraphicObjectViewFactory.FACTORY.installView(ImageObject.class, new ImageObjectView());

        final GraphicObjectPromptPanel gocp = new GraphicObjectPromptPanel(handler);
        gocp.setVisible(true);

        f.add(toolbar, BorderLayout.NORTH);
        f.add(new JScrollPane(gpanel), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout());

        f.setTitle("Shapes");
        f.getContentPane().add(controlPanel, BorderLayout.SOUTH);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);

        CommandVisitor visitor = new CommandVisitor(handler,gocp);
        Command realCommand = new ConcreteFactoryParser(new StringReader(command)).getCommandToInterpret();
        realCommand.accept(visitor);
    }

    @ParameterizedTest
    @ValueSource(strings={"new circle (5.0) (3.1,4.5)", "new img (\"./pippo.png\") (6.1,4.6)", "del id1", "mv id1 (5.9,8.2)", "mvoff id1 (5.9,8.2)",
            "scale id1 2.0", "ls id1", "ls circle", "ls all", "ls groups", "grp id1, id2, id3", "ungrp id3", "area id1", "perimeter rectangle",
            "area all"})
    void testGraphicPanel(String command) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {


        final HistoryCmdHandler handler = new HistoryCmdHandler();
        final GraphicObjectPanel gpanel = new GraphicObjectPanel();

        GraphicObjectViewFactory.FACTORY.installView(RectangleObject.class, new RectangleObjectView());
        GraphicObjectViewFactory.FACTORY.installView(CircleObject.class, new CircleObjectView());
        GraphicObjectViewFactory.FACTORY.installView(ImageObject.class, new ImageObjectView());

        final GraphicObjectPromptPanel gocp = new GraphicObjectPromptPanel(handler);



        CommandVisitor visitor = new CommandVisitor(handler,gocp);
        Command realCommand = new ConcreteFactoryParser(new StringReader(command)).getCommandToInterpret();
        realCommand.accept(visitor);
    }






}
