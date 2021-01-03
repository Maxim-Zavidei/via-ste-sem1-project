package view;

import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ReqDialog {

    @FXML private TextField title;
    @FXML private TextArea description;
    @FXML private DatePicker deadPick;

    public List<Object> processReqResults() {
        String requirementTitle = title.getText().trim();
        String requirementDescription = description.getText().trim();

        return Arrays.asList(requirementTitle, requirementDescription);
    }
}