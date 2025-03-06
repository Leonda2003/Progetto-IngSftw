package test;


import is.system.cmd.HistoryCmdHandler;
import is.system.prompt.GraphicObjectPromptPanel;
import is.system.prompt.grammarCommand.GrammarCommand;
import is.system.prompt.parser.ConcreteBuilderParser;
import is.system.prompt.visitor.CommandVisitor;
import is.system.shapes.controller.GraphicObjectController;
import is.system.shapes.model.CircleObject;
import is.system.shapes.model.ImageObject;
import is.system.shapes.model.RectangleObject;
import is.system.shapes.view.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import static is.system.support.Utility.instalFactory;

public class Test {

    @ParameterizedTest
    @ValueSource(strings={"new circle (5.0) (3.1,4.5)", "new img (\"./pippo.png\") (6.1,4.6)", "del id1", "mv id1 (5.9,8.2)", "mvoff id1 (5.9,8.2)",
            "scale id1 2.0", "ls id1", "ls circle", "ls all", "ls groups", "grp id1, id2, id3", "ungrp id3", "area id1", "perimeter rectangle",
            "area all","grp id1,id2,id3"})
    void testParser(String command) {
        System.out.println(new ConcreteBuilderParser().getCommandToInterpret(command));
    }

    @ParameterizedTest
    @ValueSource(strings={"new circle (5.0) (3.1,4.5)", "new img (\"C:/Users/mikit/Desktop/minecraft-1-logo.png\") (6.1,4.6)", "del id4", "mv id1 (5.9,8.2)", "mvoff id1 (5.9,8.2)",
            "scale id1 2.0", "ls id1", "ls circle", "ls all", "ls groups", "grp id1, id2, id3", "ungrp id36", "area id1", "perimeter rectangle",
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
        
        for(int i = 0; i < 3; i++){
            GrammarCommand realGrammarCommand = new ConcreteBuilderParser().getCommandToInterpret("new circle (5.0) (3.1,4.5)");
            realGrammarCommand.accept(visitor);
        }
        
        
        GrammarCommand realGrammarCommand = new ConcreteBuilderParser().getCommandToInterpret(command);
        realGrammarCommand.accept(visitor);
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


        instalFactory();

        gpanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                goc.setControlledObject(gpanel.getGraphicObjectAt(e.getPoint()).getValue());
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
                GrammarCommand realGrammarCommand = new ConcreteBuilderParser().getCommandToInterpret(string);
                System.out.println(realGrammarCommand);
                realGrammarCommand.accept(visitor);
            }
            i+=2;
            handler.undo();
            System.out.println("UNDO");

            String[] s2 = {"mv id"+i+" (5.9,8.2)", "mvoff id"+i+" (5.9,8.2)",
                    "scale id"+i+" 2.0", "ls id"+i, "ls circle", "ls all", "ls groups", "grp id"+i+",id"+(i-1)+",id"+(i-2), "ungrp id"+(i+1)};
            for(String string : s2 ){
                GrammarCommand realGrammarCommand = new ConcreteBuilderParser().getCommandToInterpret(string);
                System.out.println(realGrammarCommand);
                realGrammarCommand.accept(visitor);
            }
            i+=1;
            handler.undo();
            System.out.println("UNDO");



            GrammarCommand realGrammarCommand = new ConcreteBuilderParser().getCommandToInterpret("del id"+i);
            System.out.println(realGrammarCommand);
            realGrammarCommand.accept(visitor);


            handler.undo();
            System.out.println("UNDO");

            String[] s3 = { "area id"+i, "perimeter rectangle", "area all"};
            for(String string : s3 ){
                GrammarCommand realGrammarCommand1 = new ConcreteBuilderParser().getCommandToInterpret(string);
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
        GrammarCommand realGrammarCommand1 = new ConcreteBuilderParser().getCommandToInterpret(sb.toString());
        System.out.println(realGrammarCommand1);
        realGrammarCommand1.accept(visitor);

        int z = 0;
        while(z < 500){
            int p1 = new Random().nextInt(0,1000);
            int p2 = new Random().nextInt(0,1000);
            GrammarCommand realGrammarCommand2 = new ConcreteBuilderParser().getCommandToInterpret("mv id"+i+"("+p1+","+p2+")");
            System.out.println(realGrammarCommand2);
            realGrammarCommand2.accept(visitor);
            z+=1;
        }

        handler.printStory();

    }

    @org.junit.jupiter.api.Test
    void testFileStory(){

        int lineNumber = 0;
        BufferedReader reader = null;

        try {
           reader = new BufferedReader(new FileReader("GraphicObjects/src/test/fileStory.txt"));
            String line;
            while ((line= reader.readLine())  != null) {
                lineNumber += 1;
                if(!line.isEmpty() && !line.endsWith(" OK")) {System.out.println("no ok in line " + lineNumber);}
            }
            System.out.println("all ok util last line checked : "+lineNumber);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    @org.junit.jupiter.api.Test
    void testGroup() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        JFrame f = new JFrame();



        final HistoryCmdHandler handler = new HistoryCmdHandler();
        CommandVisitor visitor = new CommandVisitor(handler);

        GraphicObjectViewFactory.FACTORY.installView(RectangleObject.class, new RectangleObjectView());
        GraphicObjectViewFactory.FACTORY.installView(CircleObject.class, new CircleObjectView());
        GraphicObjectViewFactory.FACTORY.installView(ImageObject.class, new ImageObjectView());


        final GraphicObjectPanel gpanel = new GraphicObjectPanel();
        gpanel.setPreferredSize(new Dimension(500, 500));



        f.add(gpanel);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);


        int i = 1;

        while(i<=50) {

            String s1 = "new circle (50.0) (100,100)";

            GrammarCommand realGrammarCommand = new ConcreteBuilderParser().getCommandToInterpret(s1);
            System.out.println(realGrammarCommand);
            realGrammarCommand.accept(visitor);
            i++;
        }

        i = 1;
        int j = 1;

        while(i<= 24) {

            String s1 = "grp id"+j+", id"+(j+1)+ ", id"+(j+2);
            j += 3;

            GrammarCommand realGrammarCommand = new ConcreteBuilderParser().getCommandToInterpret(s1);
            System.out.println(realGrammarCommand);
            realGrammarCommand.accept(visitor);
            i++;
        }

        i = 76;
        j = 1;

        while(i<25) {

            j = new Random().nextInt(0,73);
            String s1 = "grp id"+j+" id"+(j+1)+ " id"+(j+2);


            GrammarCommand realGrammarCommand = new ConcreteBuilderParser().getCommandToInterpret(s1);
            System.out.println(realGrammarCommand);
            realGrammarCommand.accept(visitor);
        }

        while(i<25) {

            j = new Random().nextInt(0,73);
            String s1 = "del id"+j;


            GrammarCommand realGrammarCommand = new ConcreteBuilderParser().getCommandToInterpret(s1);
            System.out.println(realGrammarCommand);
            realGrammarCommand.accept(visitor);
        }


        for(int  e = 0; e <= 100; e++){
            handler.undo();
        }

        for(int  e = 0; e <= 100; e++){
            handler.redo();
        }
    }










}
