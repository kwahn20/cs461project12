package proj13AhnSlager.bantam.semant;

import proj13AhnSlager.bantam.ast.*;
import proj13AhnSlager.bantam.visitor.Visitor;

import java.util.ArrayList;

public class DependencyVisitor extends Visitor {

    public ArrayList dependencyList;
    public String name;

    public ArrayList initialize(Program parseRoot, String name){
        this.name = name;
        dependencyList = new ArrayList();
        parseRoot.accept(this);
        return dependencyList;
    }

    public Object visit(DeclStmt node){
        Expr expr = node.getInit();
        System.out.println(expr.getExprType());
            if(expr instanceof VarExpr) {
                System.out.println("Shmello");
                if(((VarExpr) expr).getName().equals(this.name)) {
                    dependencyList.add(((VarExpr) expr).getName());
                }
            }
            expr.accept(this);
        return null;
    }

     public Object visit(Field node){
        Expr expr = node.getInit();
        if(expr instanceof  VarExpr) {
            System.out.println("Hello");
            if(((VarExpr) expr).getName().equals(this.name)) {
                dependencyList.add(((VarExpr) expr).getName());
            }
        }
         if (expr != null) {
             System.out.println(expr.getExprType());
             expr.accept(this);
         }
        return null;
     }
}
