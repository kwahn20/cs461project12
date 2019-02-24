package proj12AhnSlager.bantam.semant;

import proj12AhnSlager.bantam.ast.*;
import proj12AhnSlager.bantam.util.ClassTreeNode;
import proj12AhnSlager.bantam.util.Error;
import proj12AhnSlager.bantam.util.ErrorHandler;
import proj12AhnSlager.bantam.visitor.Visitor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;

public class EnvironmentBuilder extends Visitor {
    /**
     * Maps class names to ClassTreeNode objects representing the class
     */
    private Hashtable<String, ClassTreeNode> classMap;
    private HashSet<String> illegalNames;
    private ClassTreeNode currentClass;
    private ErrorHandler errorHandler;
    private Program program;


    public EnvironmentBuilder(Hashtable classMap, ClassTreeNode currClass, ErrorHandler errorHandler, Program program){
        this.classMap = classMap;
        this.currentClass = currClass;
        this.program = program;
        this.errorHandler = errorHandler;
        this.illegalNames = new HashSet<String>(Arrays.asList("null", "this", "super", "void", "int", "boolean"));
    }
    public void build(){
        this.currentClass = null;
        this.program.accept(this);
    }

    public Object visit(Class_ node){
        currentClass = this.classMap.get(node.getName());
        currentClass.getVarSymbolTable().enterScope();
        currentClass.getMethodSymbolTable().enterScope();
        node.getMemberList().accept(this);
        currentClass.getMethodSymbolTable().exitScope();
        currentClass.getVarSymbolTable().exitScope();

        return null;
    }

    public Object visit(Field node) {

        String declaredName = node.getName();
        
        if(this.illegalNames.contains(declaredName)){
            errorHandler.register(Error.Kind.SEMANT_ERROR,
                    currentClass.getASTNode().getFilename(), node.getLineNum(),
                    "The name " + declaredName + " is reserved");
        }

        if(this.currentClass.getVarSymbolTable().peek(declaredName) != null){
            errorHandler.register(Error.Kind.SEMANT_ERROR,
                    currentClass.getASTNode().getFilename(), node.getLineNum(),
                    "The name " + declaredName + " has been declared prior");
        }
        this.currentClass.getVarSymbolTable().add(declaredName, node.getType());

        return null;
    }

    public Object visit(Method node){

        String declaredName = node.getName();

        if(this.illegalNames.contains(declaredName)){
            errorHandler.register(Error.Kind.SEMANT_ERROR,
                    currentClass.getASTNode().getFilename(), node.getLineNum(),
                    "The name " + declaredName + " is reserved");
        }

        if(this.currentClass.getMethodSymbolTable().peek(declaredName) != null){
            errorHandler.register(Error.Kind.SEMANT_ERROR,
                    currentClass.getASTNode().getFilename(), node.getLineNum(),
                    "The name " + declaredName + " has been declared prior");
        }

        currentClass.getMethodSymbolTable().add(node.getName(), node);


        currentClass.getMethodSymbolTable().enterScope();
        node.getFormalList().accept(this);
        node.getStmtList().accept(this);
        currentClass.getMethodSymbolTable().exitScope();

        return null;
    }



}
