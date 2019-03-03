package proj13AhnSlager.bantam.semant;

import proj13AhnSlager.bantam.ast.ASTNode;
import proj13AhnSlager.bantam.ast.ClassList;
import proj13AhnSlager.bantam.ast.Class_;
import proj13AhnSlager.bantam.ast.Program;
import proj13AhnSlager.bantam.visitor.Visitor;

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
