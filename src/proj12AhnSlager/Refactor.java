package proj12AhnSlager;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import proj12AhnSlager.bantam.ast.Program;
import proj12AhnSlager.bantam.semant.*;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.control.ListView;

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

    public Refactor(EditController editController, Program parseRoot){
        this.editController = editController;
        this.parseRoot = parseRoot;
    }

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

    public void getClasses(Stage window){
        window.close();
        ClassVisitor classVisitor = new ClassVisitor();
        ArrayList<String> names = classVisitor.getClasses(this.parseRoot);
        CodeArea currentCodeArea = this.editController.getCurJavaCodeArea();
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

    public void getMethods(Stage window){
        window.close();
        MethodVisitor methodVisitor = new MethodVisitor();
        ArrayList<String> names = methodVisitor.getMethods(this.parseRoot);
        CodeArea currentCodeArea = this.editController.getCurJavaCodeArea();
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

    public void getFields(Stage window){
        window.close();
        FieldVisitor fieldVisitor = new FieldVisitor();
        ArrayList<String> names = fieldVisitor.getFields(this.parseRoot);
        CodeArea currentCodeArea = this.editController.getCurJavaCodeArea();
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
