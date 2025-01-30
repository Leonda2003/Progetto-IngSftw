package is.prompt.parser;

import is.prompt.parser.analyzer.LexicalAnalyzer;
import is.prompt.parser.analyzer.Token;
import is.exception.SyntaxException;
import is.prompt.interpreterCommand.*;
import is.prompt.interpreterCommand.area.AreaAllCommand;
import is.prompt.interpreterCommand.area.AreaIDCommand;
import is.prompt.interpreterCommand.area.AreaTypeCommand;
import is.prompt.interpreterCommand.list.*;
import is.prompt.interpreterCommand.move.MoveCommand;
import is.prompt.interpreterCommand.move.MoveOffCommand;
import is.prompt.interpreterCommand.perimeter.PerimeterAllCommand;
import is.prompt.interpreterCommand.perimeter.PerimeterIDCommand;
import is.prompt.interpreterCommand.perimeter.PerimeterTypeCommand;
import is.prompt.interpreterCommand.terminal.*;
import is.prompt.interpreterCommand.type.Circle;
import is.prompt.interpreterCommand.type.Image;
import is.prompt.interpreterCommand.type.Rectangle;
import is.prompt.interpreterCommand.type.TypeCommand;
import is.prompt.interpreterCommand.typeconstr.CircleCommand;
import is.prompt.interpreterCommand.typeconstr.ImageCommand;
import is.prompt.interpreterCommand.typeconstr.RectangleCommand;
import is.prompt.interpreterCommand.typeconstr.TypeconstrCommand;

import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;

public class ConcreteFactoryParser extends FactoryParser{

    private Command command;
    private LexicalAnalyzer analyzer;
    private Token currentToken;
    public ConcreteFactoryParser(Reader in) {
        this.analyzer = new LexicalAnalyzer(in);
        command =createCommandToInterpret();
        expected(Token.EOF);
    }

    private Command createCommandToInterpret() {

        currentToken = analyzer.nexToken();
        if(currentToken.equals(Token.NEW)){

            TerminalCommand terminalCommand = createTerminal();
            TypeconstrCommand typeconstrCommand = createTypeconstr();
            PosCommand posCommand = createPos();
            return new CreateCommand(terminalCommand,typeconstrCommand,posCommand);

        }if(currentToken.equals(Token.DEL)){

            TerminalCommand terminalCommand = createTerminal();
            ObjID objID = createObjID();
            return new RemoveCommand(terminalCommand,objID);

        }if(currentToken.equals(Token.MV)){

            TerminalCommand terminalCommand = createTerminal();
            ObjID objID = createObjID();
            PosCommand posCommand = createPos();
            return new MoveCommand(terminalCommand,objID,posCommand);

        }if(currentToken.equals(Token.MVOFF)){

            TerminalCommand terminalCommand = createTerminal();
            ObjID objID = createObjID();
            PosCommand posCommand = createPos();
            return new MoveOffCommand(terminalCommand,objID,posCommand);

        }if(currentToken.equals(Token.SCALE)){

            TerminalCommand terminalCommand = createTerminal();
            ObjID objID = createObjID();
            Posfloat posfloat = createPosfloat();
            return new ScaleCommand(terminalCommand,objID,posfloat);

        }if(currentToken.equals(Token.LS)){

            TerminalCommand terminalCommand = createTerminal();
            return createList(terminalCommand);

        }if(currentToken.equals(Token.GRP)){

            TerminalCommand terminalCommand = createTerminal();
            ListIDCommand listIDCommand = createListID();
            return new GroupCommand(terminalCommand,listIDCommand);

        }if(currentToken.equals(Token.UNGRP)){

            TerminalCommand terminalCommand = createTerminal();
            ObjID objID = createObjID();
            return new UngroupCommand(terminalCommand,objID);

        }if(currentToken.equals(Token.AREA)){

            TerminalCommand terminalCommand = createTerminal();
            return createArea(terminalCommand);

        }if(currentToken.equals(Token.PERIMETER)){

            TerminalCommand terminalCommand = createTerminal();
            return createPerimeter(terminalCommand);
        }
        String word = analyzer.getWord();
        throw new SyntaxException("No commands found that start like this \n"+
                                   "try one of new-del-mv-mvoff-scale-ls-grp-ungrp-area-perimeter");

    }

    private TypeconstrCommand createTypeconstr() {

        if(currentToken == Token.CIRCLE){

            TypeCommand typeCommand = createType();
            expected(Token.LEFT_PAR);
            Posfloat posfloat = createPosfloat();
            expected(Token.RIGHT_PAR);
            return new CircleCommand(typeCommand,posfloat);
        }
        if(currentToken.equals(Token.RECTANGLE)){

            TypeCommand typeCommand = createType();
            PosCommand posCommand= createPos();
            return new RectangleCommand(typeCommand,posCommand);
        }
        if(currentToken.equals(Token.IMG)){

            TypeCommand typeCommand = createType();
            expected(Token.LEFT_PAR);
            Path path = createPath();
            expected(Token.RIGHT_PAR);
            return new ImageCommand(typeCommand,path);
        }
        String word = analyzer.getWord();
        throw new SyntaxException("Command Syntax Error "+analyzer.getWord());

    }

    private TypeCommand createType() {

        switch (currentToken) {
            case Token.CIRCLE:
                Circle circle = new Circle(currentToken);
                currentToken = analyzer.nexToken();
                return circle;
            case Token.RECTANGLE:
                Rectangle rectangle = new Rectangle(currentToken);
                currentToken = analyzer.nexToken();
                return rectangle;
            case Token.IMG:
                Image image = new Image(currentToken);
                currentToken = analyzer.nexToken();
                return image;
            default:
                throw new SyntaxException("Expected one of circle-rectangle-img-all-groups");
        }
    }

    private Command createPerimeter(TerminalCommand terminalCommand) {

        if(currentToken.equals(Token.OBJ_ID)){

            ObjID objID = createObjID();
            return new PerimeterIDCommand(terminalCommand,objID);

        }else if(currentToken.equals(Token.ALL)){

            TerminalCommand terminalCommand2 = createTerminal();
            return new PerimeterAllCommand(terminalCommand,terminalCommand2);

        }else{

            TypeCommand typeCommand = createType();
            return new PerimeterTypeCommand(terminalCommand,typeCommand);
        }
    }

    private Command createArea(TerminalCommand terminalCommand) {

        if(currentToken.equals(Token.OBJ_ID)){
            ObjID objID = createObjID();
            return new AreaIDCommand(terminalCommand,objID);

        }else if(currentToken.equals(Token.ALL)){

            TerminalCommand terminalCommand2 = createTerminal();
            return new AreaAllCommand(terminalCommand,terminalCommand2);

        }else{
            TypeCommand typeCommand = createType();
            return new AreaTypeCommand(terminalCommand,typeCommand);
        }
    }

    private ListCommand createList(TerminalCommand terminalCommand) {

        if(currentToken.equals(Token.OBJ_ID)){

            ObjID objID = createObjID();
            return new ListObjIDCommand(terminalCommand,objID);

        }else if(currentToken.equals(Token.ALL)){

            All_Groups allGroups = createAll_Groups();
            return new ListAllCommand(terminalCommand,allGroups);

        }else if(currentToken.equals(Token.GROUPS)){

            All_Groups allGroups = createAll_Groups();
            return new ListGroupsCommand(terminalCommand,allGroups);

        }else{

            TypeCommand typeCommand = createType();
            return new ListTypeCommand(terminalCommand,typeCommand);
        }
    }


    private ListIDCommand createListID() {

        ObjID objID = createObjID();
        ListIDCommand listIDCommand= new ListIDCommand(objID);
        while(currentToken == Token.COMA){
            expected(Token.COMA);
            listIDCommand.addObjectID(createObjID());
        }
        return listIDCommand;
    }

    private PosCommand createPos() {

        expected(Token.LEFT_PAR);
        Posfloat posfloat1 =  createPosfloat();
        expected(Token.COMA);
        Posfloat posfloat2 = createPosfloat();
        expected(Token.RIGHT_PAR);
        return new PosCommand(posfloat1,posfloat2);

    }

    private TerminalCommand createTerminal() {

        TerminalCommand terminalCommand = new TerminalCommand(currentToken);
        currentToken = analyzer.nexToken();
        return terminalCommand;
    }

    private ObjID createObjID() {
        if(currentToken == Token.OBJ_ID){
            ObjID objID = new ObjID(currentToken,analyzer.getWord());
            currentToken = analyzer.nexToken();
            return objID;
        }
        throw new SyntaxException("Insert a valid ID");
    }

    private Posfloat createPosfloat() {

        if(analyzer.getNumber() < 0) throw new SyntaxException("only positive numbers accepted");
        Posfloat posfloat = new Posfloat(currentToken, analyzer.getNumber());
        currentToken = analyzer.nexToken();
        return posfloat;
    }

    private Path createPath() {

        if(currentToken == Token.PATH){
            Path path = new Path(currentToken, analyzer.getWord());
            currentToken = analyzer.nexToken();
            return path;
        }
        throw new SyntaxException("Insert a valid Path in quotes");
    }

    private All_Groups createAll_Groups() {
        switch (currentToken){
            case Token.ALL:
            case Token.GROUPS:
                currentToken = analyzer.nexToken();
                return new All_Groups(currentToken);
        }
        throw new SyntaxException("Insert All or Groups");
    }

    private void expected(Token t) {
        if (currentToken != t) {
            String msg = "found "+currentToken+" while expepected "+t;
            throw new SyntaxException(msg);
        }
        currentToken = analyzer.nexToken();
    }


    public static void main(String[] args) {


        while(true){
            Scanner sc = new Scanner(System.in);
            System.out.print("Fornisci la combinazione: ");
            String combinazione = sc.nextLine();
            //sc.close();

            StringReader sr = new StringReader(combinazione);
            FactoryParser parser = new ConcreteFactoryParser(sr);
            System.out.println(parser.getCommandToInterpret());
        }
    }


    @Override
    public Command getCommandToInterpret() {
        return command;
    }
}
