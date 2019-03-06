/*
 * File: Refactor.java
 * Names: Kevin Ahn, Kyle Slager
 * Class: CS461
 * Project 13
 */

package proj13AhnSlager;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import proj13AhnSlager.bantam.ast.Program;
import proj13AhnSlager.bantam.semant.*;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.control.ListView;

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
    private TextField userEntryTextField;


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
    public void initialize(){
        CodeArea currentCodeArea = this.editController.getCurJavaCodeArea();
        Stage popupWindow  = new Stage();
        GridPane layout    = new GridPane();
        Scene scene        = new Scene(layout);

        textToSearch       = currentCodeArea.getText();
        classButton        = new Button("Class");
        methodButton         = new Button("Method");
        fieldButton   = new Button("Field");
        userEntryTextField = new TextField();

        layout.add(classButton,0,1);
        layout.add(methodButton, 0, 2);
        layout.add(fieldButton, 0, 3);

        classButton.setOnAction(event -> getClasses(popupWindow));
        methodButton.setOnAction(event -> getMethods(popupWindow));
        fieldButton.setOnAction(event -> getFields(popupWindow));

        popupWindow.setScene(scene);
        popupWindow.showAndWait();
    }

    /**
     * method to get the list of classes in the current file, creates a new ClassVisitor
     * to get the list of name and adds them to an arrayList.
     * @param window the current window
     */
    public void getClasses(Stage window){
        window.close();
        ClassVisitor classVisitor = new ClassVisitor(); // creates the visitor
        ArrayList<String> names = classVisitor.getClasses(this.parseRoot);
        CodeArea currentCodeArea = this.editController.getCurJavaCodeArea();
        this.getHelper(names);
    }

    /**
     * method to get the list of methods in the current file, creates a new MethodVisitor
     * to get the list of names and adds them to an arrayList.
     * @param window the current window
     */
    public void getMethods(Stage window){
        window.close();
        MethodVisitor methodVisitor = new MethodVisitor(); // creates the method visitor
        ArrayList<String> names = methodVisitor.getMethods(this.parseRoot);
        CodeArea currentCodeArea = this.editController.getCurJavaCodeArea();
        this.getHelper(names);
    }

    /**
     * method to get the list of fields in the current file, creates a new FieldVisitor
     * to get the list of names and adds them to an arrayList.
     * @param window the current window
     */
    public void getFields(Stage window){
        window.close();
        FieldVisitor fieldVisitor = new FieldVisitor();
        ArrayList<String> names = fieldVisitor.getFields(this.parseRoot);
        CodeArea currentCodeArea = this.editController.getCurJavaCodeArea();
        this.getHelper(names);
    }

    public void getHelper(ArrayList names){
        Stage popupWindow  = new Stage();
        GridPane layout    = new GridPane();
        Scene scene        = new Scene(layout);

        ListView listView = new ListView();
        System.out.println(names);
        for(int i = 0; i<names.size(); i++){
            listView.getItems().add(names.get(i));
        }
        layout.add(listView,0,1);
        popupWindow.setScene(scene);
        popupWindow.showAndWait();
    }

    public void refactorAll(){

    }
}
