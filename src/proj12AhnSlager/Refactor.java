package proj12AhnSlager;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import proj12AhnSlager.bantam.ast.Program;

import java.util.ArrayList;
import java.util.Iterator;

public class Refactor {

    private Iterator<int[]> indices = new ArrayList<int[]>().iterator();
    private String target, textToSearch;
    private EditController editController;
    private Button findButton;
    private Button replaceAllButton;
    private Button classButton;
    private Button methodButton;
    private Button fieldButton;
    private TextField userEntryTextField;

    public Refactor(EditController editController){
        this.editController = editController;
    }

    public void initialize(Program root){
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

    }

    public void getMethods(Stage window){
        window.close();
    }

    public void getFields(Stage window){
        window.close();
    }

    public void refactorAll(){

    }
}
