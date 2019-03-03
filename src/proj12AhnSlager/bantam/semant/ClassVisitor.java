package proj12AhnSlager.bantam.semant;

import proj12AhnSlager.bantam.ast.ASTNode;
import proj12AhnSlager.bantam.ast.ClassList;
import proj12AhnSlager.bantam.ast.Class_;
import proj12AhnSlager.bantam.ast.Program;
import proj12AhnSlager.bantam.visitor.Visitor;

import java.util.ArrayList;

public class ClassVisitor extends Visitor {

    public ArrayList classList;

    public ArrayList<String> getClasses(Program parseRoot){
        classList = new ArrayList();
        parseRoot.accept(this);
        return classList;
    }

    public Object visit(Class_ node){
        System.out.println(node.getName());
        classList.add(node.getName());
        node.getMemberList().accept(this);
        return null;
    }
}
