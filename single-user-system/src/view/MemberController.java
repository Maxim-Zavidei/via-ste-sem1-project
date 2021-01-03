package view;

import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MemberController {

    @FXML private TextField name;
    @FXML private TextArea mail;

    public List<Object> processMemberResults() {
        String memberName = name.getText().trim();
        String memberDescription = mail.getText().trim();

        return Arrays.asList(memberName, memberDescription);
    }
}