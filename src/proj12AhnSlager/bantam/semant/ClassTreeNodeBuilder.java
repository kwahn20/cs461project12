package proj12AhnSlager.bantam.semant;

import proj12AhnSlager.bantam.visitor.Visitor;
import proj12AhnSlager.bantam.util.*;
import proj12AhnSlager.bantam.semant.*;
import proj12AhnSlager.bantam.ast.*;

import java.util.Hashtable;

public class ClassTreeNodeBuilder extends Visitor {

    /**
     * Maps class names to ClassTreeNode objects representing the class
     */
    private Hashtable<String, ClassTreeNode> classMap;
    private ErrorHandler errorHandler;

    public ClassTreeNodeBuilder(Hashtable<String, ClassTreeNode> classMap, ErrorHandler errorHandler){
        this.classMap = classMap;
        this.errorHandler = errorHandler;
    }
    public void buildClasses(Program ast){
        super.visit(ast);
    }

    /**
     * visits the class node and adds the proper ClassTreeNode to the classMap
     * @param node the class node
     * @return
     */
    @Override
    public Object visit(Class_ node){
        ClassTreeNode classNode = new ClassTreeNode(node, false, true, this.classMap);
        classMap.put(node.getName(),classNode);
        return null;

    }
}
