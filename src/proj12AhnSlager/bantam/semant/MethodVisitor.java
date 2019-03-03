package proj12AhnSlager.bantam.semant;

import proj12AhnSlager.bantam.ast.*;
import proj12AhnSlager.bantam.visitor.Visitor;

import java.util.ArrayList;

public class MethodVisitor extends Visitor {

    public ArrayList methodList;

    public ArrayList<String> getMethods(Program parseRoot){
        methodList = new ArrayList();
        parseRoot.accept(this);
        return methodList;
    }

    public Object visit(Method node){
        System.out.println(node.getName());
        methodList.add(node.getName());
        node.getFormalList().accept(this);
        node.getStmtList().accept(this);
        return null;
    }
}
