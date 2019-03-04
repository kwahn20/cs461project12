/*
 * File: ClassVisitor.java
 * Names: Kevin Ahn, Kyle Slager
 * Class: CS461
 * Project 13
 */

package proj13AhnSlager.bantam.semant;

import proj13AhnSlager.bantam.ast.ASTNode;
import proj13AhnSlager.bantam.ast.ClassList;
import proj13AhnSlager.bantam.ast.Class_;
import proj13AhnSlager.bantam.ast.Program;
import proj13AhnSlager.bantam.visitor.Visitor;

import java.util.ArrayList;

/**
 * @author Kevin Ahn and Kyle Slager
 * ClassVisitor class that extends the Visitor class to get the list of classes from
 * an AST and add it to an arrayList
 */
public class ClassVisitor extends Visitor {

    public ArrayList classList;

    /**
     * method that creates a new class list and calls the parseRoots accepts method
     * @param parseRoot
     * @return an ArrayList of class names
     */
    public ArrayList<String> getClasses(Program parseRoot){
        classList = new ArrayList();
        parseRoot.accept(this);
        return classList;
    }

    /**
     * overrides the Class_ visit method to add in the names of each class in the AST
     * @param node the class node
     * @return
     */
    @Override
    public Object visit(Class_ node){
        System.out.println(node.getName()); // used for testing
        classList.add(node.getName()); // adds it to the class list
        node.getMemberList().accept(this);
        return null;
    }
}
