/*
 * File: Refactor.java
 * Names: Kevin Ahn, Kyle Slager
 * Class: CS461
 * Project 13
 */

package proj13AhnSlager;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import proj13AhnSlager.bantam.ast.Program;
import proj13AhnSlager.bantam.semant.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @author Kevin Ahn, Kyle Slager
 * A refactoring class that sets up a window that sets up the UI to display the
 * classes, methods ands fields of a class to refactor them if the user decides to
 */
public class Refactor {

    private Iterator<int[]> indices = new ArrayList<int[]>().iterator();
    private String target, textToSearch;
    private EditController editController;
    private Program parseRoot;
    private Button findButton;
    private Button replaceAllButton;
    private Button classButton;
    private Button methodButton;
    private Button fieldButton;
    private Button refactorButton;
    private TextField userEntryTextField;
    private Stage popupWindow;
    private Stage refactorWindow;


    /**
     * constructor for the class
     * @param editController editController to be used by the class
     * @param parseRoot the program ast
     */
    public Refactor(EditController editController, Program parseRoot){
        this.editController = editController;
        this.parseRoot = parseRoot;
    }

    /**
     * a method that initializes the window that will hold the information from the AST.
     * Creates a set of buttons that will redirect the user to the list of classes, methods,
     * or fields
     */
    public void initialize(String type){
        if(type.equals("class")){
            getClasses();
        }
        else if(type.equals("method")){
            getMethods();
        }
        else{
            getFields();
        }
    }

    /**
     * method to get the list of classes in the current file, creates a new ClassVisitor
     * to get the list of name and adds them to an arrayList.
     */
    public void getClasses(){
        ClassVisitor classVisitor = new ClassVisitor(); // creates the visitor
        ArrayList<String> names = classVisitor.getClasses(this.parseRoot);
        this.getHelper(names, "Classes");
    }

    /**
     * method to get the list of methods in the current file, creates a new MethodVisitor
     * to get the list of names and adds them to an arrayList.
     */
    public void getMethods(){
        MethodVisitor methodVisitor = new MethodVisitor(); // creates the method visitor
        ArrayList<String> names = methodVisitor.getMethods(this.parseRoot);
        this.getHelper(names, "Methods");
    }

    /**
     * method to get the list of fields in the current file, creates a new FieldVisitor
     * to get the list of names and adds them to an arrayList.
     */
    public void getFields(){
        FieldVisitor fieldVisitor = new FieldVisitor();
        ArrayList<String> names = fieldVisitor.getFields(this.parseRoot);
        this.getHelper(names, "Fields");
    }

    public void getHelper(ArrayList names, String type) {
        ChoiceDialog<String> dialog = new ChoiceDialog<>(type, names);
        dialog.setTitle("Refactor");
        dialog.setHeaderText("Welcome to Refactoring Helper");
        dialog.setContentText("Choose what you would like to refactor:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            getNewName(result.get());
        }
    }

    public void getNewName(String oldName){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Choose New Name");
        dialog.setHeaderText("Welcome to Refactoring Helper");
        dialog.setContentText("Choose a new name");

        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()){
            String newName = result.get();
            refactorAll(oldName, newName);
        }
    }

    public void refactorAll(String oldName, String newName){

        String source = editController.getCurJavaCodeArea().getText();

        String newText     = source.replace(oldName, newName);

        editController.handleSelectAll();
        editController.getCurJavaCodeArea().replaceSelection(newText);

    }
}
