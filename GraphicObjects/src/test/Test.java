package test;

import is.TestGraphics2;
import is.cmd.HistoryCmdHandler;
import is.prompt.GraphicObjectPromptPanel;
import is.prompt.grammarCommand.GrammarCommand;
import is.prompt.parser.ConcreteBuilderParser;
import is.prompt.visitor.CommandVisitor;
import is.shapes.controller.GraphicObjectController;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.CircleObject;
import is.shapes.model.ImageObject;
import is.shapes.model.RectangleObject;
import is.shapes.view.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Test {

    @ParameterizedTest
    @ValueSource(strings={"new circle (5.0) (3.1,4.5)", "new img (\"./pippo.png\") (6.1,4.6)", "del id1", "mv id1 (5.9,8.2)", "mvoff id1 (5.9,8.2)",
            "scale id1 2.0", "ls id1", "ls circle", "ls all", "ls groups", "grp id1, id2, id3", "ungrp id3", "area id1", "perimeter rectangle",
            "area all","grp id1,id2,id3"})
    void testParser(String command) {
        Reader reader = new StringReader(command);
        ConcreteBuilderParser parser = new ConcreteBuilderParser(reader);
        System.out.println(parser.getCommandToInterpret());
    }

    @ParameterizedTest
    @ValueSource(strings={"new circle (5.0) (3.1,4.5)", "new img (\"./pippo.png\") (6.1,4.6)", "del id1", "mv id1 (5.9,8.2)", "mvoff id1 (5.9,8.2)",
            "scale id1 2.0", "ls id1", "ls circle", "ls all", "ls groups", "grp id1, id2, id3", "ungrp id3", "area id1", "perimeter rectangle",
            "area all","grp id1,id2,id3"})
    void testGraphic(String command) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        JFrame f = new JFrame();
        JToolBar toolbar = new JToolBar();


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

        CommandVisitor visitor = new CommandVisitor(handler);
        GrammarCommand realGrammarCommand = new ConcreteBuilderParser(new StringReader(command)).getCommandToInterpret();
        realGrammarCommand.accept(visitor);
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



        CommandVisitor visitor = new CommandVisitor(handler);
        GrammarCommand realGrammarCommand = new ConcreteBuilderParser(new StringReader(command)).getCommandToInterpret();
        realGrammarCommand.accept(visitor);
    }

    @org.junit.jupiter.api.Test
    void testGraphicPanel2() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {



        String[] s = {"new circle (5.0) (3.1,4.5)", "new img (\"./pippo.png\") (6.1,4.6)", "del id2", "mv id1 (5.9,8.2)", "mvoff id1 (5.9,8.2)",
                "scale id1 2.0", "ls id1", "ls circle", "ls all", "ls groups", "grp id1", "ungrp id3", "area id1", "perimeter rectangle",
                "area all"};
        final HistoryCmdHandler handler = new HistoryCmdHandler();
        final GraphicObjectPanel gpanel = new GraphicObjectPanel();



        final GraphicObjectPromptPanel gocp = new GraphicObjectPromptPanel(handler);



        CommandVisitor visitor = new CommandVisitor(handler);
        for(String string : s ){
            GrammarCommand realGrammarCommand = new ConcreteBuilderParser(new StringReader(string)).getCommandToInterpret();
            realGrammarCommand.accept(visitor);
        }
        
    }

    @org.junit.jupiter.api.Test
    void testAllPerformance() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        JFrame f = new JFrame();

        JToolBar toolbar = new JToolBar();

        JButton undoButt = new JButton("Undo");
        JButton redoButt = new JButton("Redo");

        final HistoryCmdHandler handler = new HistoryCmdHandler();

        undoButt.addActionListener(evt -> handler.undo());

        redoButt.addActionListener(evt -> handler.redo());

        toolbar.add(undoButt);
        toolbar.add(redoButt);

        final GraphicObjectPanel gpanel = new GraphicObjectPanel();
        gpanel.setPreferredSize(new Dimension(500, 500));

        final GraphicObjectPromptPanel prompt = new GraphicObjectPromptPanel(handler);

        final GraphicObjectController goc = new GraphicObjectController(handler);


        JScrollPane panel = new JScrollPane(gpanel);

        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(goc);

        JSplitPane splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel, goc);
        JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPane1, prompt);


        GraphicObjectViewFactory.FACTORY.installView(RectangleObject.class, new RectangleObjectView());
        GraphicObjectViewFactory.FACTORY.installView(CircleObject.class, new CircleObjectView());
        GraphicObjectViewFactory.FACTORY.installView(ImageObject.class, new ImageObjectView());

        AbstractGraphicObject go = new RectangleObject(new Point(10, 10), 20, 50);
        JButton rectButton = new JButton(new CreateObjectAction(go, gpanel, handler));
        rectButton.setText(go.getType());
        toolbar.add(rectButton);

        go = new CircleObject(new Point(10, 10), 10);
        JButton circButton = new JButton(new CreateObjectAction(go, gpanel, handler));
        circButton.setText(go.getType());
        toolbar.add(circButton);

        go = new CircleObject(new Point(10, 10), 100);
        JButton circButton2 = new JButton(new CreateObjectAction(go, gpanel, handler));
        circButton2.setText("big " + go.getType());
        toolbar.add(circButton2);

        go = new ImageObject(new ImageIcon(TestGraphics2.class.getResource("shapes/model/NyaNya.gif")), new Point(240, 187));
        JButton imgButton = new JButton(new CreateObjectAction(go, gpanel, handler));
        imgButton.setText(go.getType());
        toolbar.add(imgButton);

        gpanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                goc.setControlledObject(gpanel.getGraphicObjectAt(e.getPoint()));
            }
        });



        f.add(splitPane2);
        f.add(toolbar, BorderLayout.NORTH);

        f.setTitle("Shapes");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);


        int i = 1;

        final GraphicObjectPromptPanel gocp = new GraphicObjectPromptPanel(handler);



        CommandVisitor visitor = new CommandVisitor(handler);


        while(i<2000){

            String[] s1 = {"new circle (5.0) (3.1,4.5)", "new img (\"C:/Users/mikit/Desktop/minecraft-1-logo.png\") (6.1,4.6)","scale id"+(i+1)+" 0.1","new rectangle (80,80) (80,80)", "del id"+i};
            for(String string : s1 ){
                GrammarCommand realGrammarCommand = new ConcreteBuilderParser(new StringReader(string)).getCommandToInterpret();
                System.out.println(realGrammarCommand);
                realGrammarCommand.accept(visitor);
            }
            i+=2;
            handler.undo();
            System.out.println("UNDO");

            String[] s2 = {"mv id"+i+" (5.9,8.2)", "mvoff id"+i+" (5.9,8.2)",
                    "scale id"+i+" 2.0", "ls id"+i, "ls circle", "ls all", "ls groups", "grp id"+i+",id"+(i-1)+",id"+(i-2), "ungrp id"+(i+1)};
            for(String string : s2 ){
                GrammarCommand realGrammarCommand = new ConcreteBuilderParser(new StringReader(string)).getCommandToInterpret();
                System.out.println(realGrammarCommand);
                realGrammarCommand.accept(visitor);
            }
            i+=1;
            handler.undo();
            System.out.println("UNDO");



            GrammarCommand realGrammarCommand = new ConcreteBuilderParser(new StringReader("del id"+i)).getCommandToInterpret();
            System.out.println(realGrammarCommand);
            realGrammarCommand.accept(visitor);


            handler.undo();
            System.out.println("UNDO");

            String[] s3 = { "area id"+i, "perimeter rectangle", "area all"};
            for(String string : s3 ){
                GrammarCommand realGrammarCommand1 = new ConcreteBuilderParser(new StringReader(string)).getCommandToInterpret();
                System.out.println(realGrammarCommand1);
                realGrammarCommand1.accept(visitor);
            }
            i+=1;
        }

        StringBuilder sb = new StringBuilder("grp ");
        sb.append("id1");
        for(int j = 2; j<i; j++){
            sb.append(",id"+j);
        }
        GrammarCommand realGrammarCommand1 = new ConcreteBuilderParser(new StringReader(sb.toString())).getCommandToInterpret();
        System.out.println(realGrammarCommand1);
        realGrammarCommand1.accept(visitor);

        int z = 0;
        while(z < 50000){
            int p1 = new Random().nextInt(0,1000);
            int p2 = new Random().nextInt(0,1000);
            GrammarCommand realGrammarCommand2 = new ConcreteBuilderParser(new StringReader("mv id"+i+"("+p1+","+p2+")")).getCommandToInterpret();
            System.out.println(realGrammarCommand2);
            realGrammarCommand2.accept(visitor);
            z+=1;
        }


    }







}
