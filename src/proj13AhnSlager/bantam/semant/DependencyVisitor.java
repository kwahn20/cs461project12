package proj13AhnSlager.bantam.semant;

import org.reactfx.value.Var;
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
            if(expr instanceof VarExpr) {
                if(((VarExpr) expr).getName().equals(this.name)) {
                    dependencyList.add(((VarExpr) expr).getName());
                }
            }
            expr.accept(this);
        return null;
    }

    public Boolean checkExpr(Expr expr){
        Expr expr1 = ((BinaryExpr) expr).getLeftExpr();
        Expr expr2 = ((BinaryExpr) expr).getRightExpr();
        if(expr1 instanceof VarExpr){
            return ((VarExpr) expr1).getName().equals(this.name);
        }
        else{
            checkExpr(expr1);
        }
        if(expr1 instanceof VarExpr){
            return ((VarExpr) expr2).getName().equals(this.name);
        }
        else{
            checkExpr(expr2);
        }
        return false;

    }

    public Object visit(AssignExpr node){
        Expr expr = node.getExpr();
        if(expr instanceof BinaryExpr){
            if(checkExpr(expr)){
                dependencyList.add(node.getName());
            }
        }
        if(expr instanceof VarExpr){
            if(((VarExpr) expr).getName().equals(this.name)){
                dependencyList.add(node.getName());
            }
        }
        node.getExpr().accept(this);
        return null;
    }

     public Object visit(Field node){
        Expr expr = node.getInit();
        if(expr instanceof  VarExpr) {
            if(((VarExpr) expr).getName().equals(this.name)) {
                dependencyList.add(node.getName());
            }
        }
         if (expr != null) {
             expr.accept(this);
         }
        return null;
     }
}
