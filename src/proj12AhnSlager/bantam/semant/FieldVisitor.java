package proj12AhnSlager.bantam.semant;

import proj12AhnSlager.bantam.ast.*;
import proj12AhnSlager.bantam.visitor.Visitor;

import java.util.ArrayList;

public class FieldVisitor extends Visitor {

    public ArrayList fieldList;

    public ArrayList<String> getFields(Program parseRoot){
        fieldList = new ArrayList();
        parseRoot.accept(this);
        return fieldList;
    }

    public Object visit(Field node){
        System.out.println(node.getName());
        fieldList.add(node.getName());
        if (node.getInit() != null) {
            node.getInit().accept(this);
        }
        return null;
    }
}
