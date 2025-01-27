package is.parser;

import is.analyzer.Context;
import is.analyzer.LexicalAnalyzer;
import is.analyzer.Token;
import is.exception.SyntaxException;
import is.interpreterCommand.*;
import is.interpreterCommand.area.AreaAllCommand;
import is.interpreterCommand.area.AreaIDCommand;
import is.interpreterCommand.area.AreaTypeCommand;
import is.interpreterCommand.list.*;
import is.interpreterCommand.move.MoveCommand;
import is.interpreterCommand.move.MoveOffCommand;
import is.interpreterCommand.perimeter.PerimeterAllCommand;
import is.interpreterCommand.perimeter.PerimeterIDCommand;
import is.interpreterCommand.perimeter.PerimeterTypeCommand;
import is.interpreterCommand.terminal.ObjID;
import is.interpreterCommand.terminal.Path;
import is.interpreterCommand.terminal.Posfloat;
import is.interpreterCommand.terminal.TerminalCommand;
import is.interpreterCommand.type.Circle;
import is.interpreterCommand.type.Image;
import is.interpreterCommand.type.Rectangle;
import is.interpreterCommand.type.TypeCommand;
import is.interpreterCommand.typeconstr.CircleCommand;
import is.interpreterCommand.typeconstr.ImageCommand;
import is.interpreterCommand.typeconstr.RectangleCommand;
import is.interpreterCommand.typeconstr.TypeconstrCommand;

import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;

public class ConcreteFactoryParser extends FactoryParser{

    private Command command;
    private LexicalAnalyzer analyzer;
    private Token currentToken;
    private
    ConcreteFactoryParser(Reader in) {
        this.analyzer = new LexicalAnalyzer(in);
        command =createCommandToInterpret();
        expected(Token.EOF);
    }
    @Override
    public Command createCommandToInterpret() {

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
            PosCommand posCommand = createPos();
            return new MoveOffCommand(terminalCommand,objID,posCommand);

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
        throw new SyntaxException("No command found starting with '"+word+"'\n"+
                                   "try one of new-del-mv-mvoff-scale-ls-grp-ungrp-area-perimeter");

    }

    private TypeconstrCommand createTypeconstr() {

        if(currentToken.equals(Token.CIRCLE)){

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
        throw new SyntaxException("Command Syntax Error");

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

            TerminalCommand terminalCommand2 = createTerminal();
            return new ListAllCommand(terminalCommand,terminalCommand2);

        }else if(currentToken.equals(Token.GROUPS)){

            TerminalCommand terminalCommand2 = createTerminal();
            return new ListGroupsCommand(terminalCommand,terminalCommand2);

        }else{

            TypeCommand typeCommand = createType();
            return new ListTypeCommand(terminalCommand,typeCommand);
        }
    }

    private ListIDCommand createListID() {

        ObjID objID = createObjID();
        ListIDCommand listIDCommand= new ListIDCommand(objID);
        while(currentToken == Token.COMA) listIDCommand.addObjectID(createObjID());
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

    private TypeCommand createType() {

        if(currentToken.equals(Token.CIRCLE)){
            currentToken = analyzer.nexToken();
            return new Circle();
        }
        if(currentToken.equals(Token.RECTANGLE)){
            currentToken = analyzer.nexToken();
            return new Rectangle();
        }
        if(currentToken.equals(Token.IMG)){
            currentToken = analyzer.nexToken();
            return new Image();
        }
        throw new SyntaxException("No command found with '"+analyzer.getWord()+"'\n"+
                "try one of circle-rectangle-img");
    }

    private TerminalCommand createTerminal() {

        TerminalCommand terminalCommand = new TerminalCommand(currentToken);
        analyzer.nexToken();
        return terminalCommand;
    }

    private ObjID createObjID() {

        ObjID objID = new ObjID(currentToken,analyzer.getWord());
        currentToken = analyzer.nexToken();
        return objID;
    }

    private Posfloat createPosfloat() {

        Posfloat posfloat = new Posfloat(currentToken, analyzer.getNumber());
        currentToken = analyzer.nexToken();
        return posfloat;
    }

    private Path createPath() {

        Path path = new Path(currentToken, analyzer.getWord());
        currentToken = analyzer.nexToken();
        return path;

    }

    private void expected(Token t) {
        if (currentToken != t) {
            String msg = "found "+currentToken+" while expepected "+t;
            throw new SyntaxException(msg);
        }
        currentToken = analyzer.nexToken();
    }


    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        System.out.print("Fornisci la combinazione: ");
        String combinazione = sc.nextLine();
        //sc.close();

        StringReader sr = new StringReader(combinazione);
        FactoryParser parser = new ConcreteFactoryParser(sr);






    }


}
