package view;

import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DialogController {

    @FXML private TextField title;
    @FXML private TextArea description;

    public List<Object> processResults() {
        String projectTitle = title.getText().trim();
        String projectDescription = description.getText().trim();

        return Arrays.asList(projectTitle, projectDescription);
    }
}

