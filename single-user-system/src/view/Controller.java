package view;

import model.*;
import mediator.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Controller {

    ProjectManagementModelManager projectManagementModelManager = ProjectManagementPersistence.load();
    public ObservableList<Project> projects;
    private ObservableList<Requirement> reqs;
    private ObservableList<Member> hums;

    @FXML private Text projectText;
    @FXML private Text idText;
    @FXML private ListView projectView;
    @FXML private ListView reqView;
    @FXML private ListView humanListView;
    @FXML private TextArea projectDescription;
    @FXML private ProgressIndicator progressCircle;
    @FXML private ContextMenu deleteProjectContextMenu;
    @FXML private ContextMenu deleteRequirementContextMenu;
    @FXML private GridPane mainGrid;
    @FXML private TextArea reqDescription;
    @FXML private Text reqTitle;
    @FXML private Text reqId;
    @FXML private ProgressBar progressBar;
    @FXML private Text completedReq;
    @FXML private ContextMenu unassignMenu;

    public void initialize() {

        projects = FXCollections.observableArrayList();
        reqs = FXCollections.observableArrayList();
        hums = FXCollections.observableArrayList();


        projects.addAll(projectManagementModelManager.getAllProjects());


        projectDescription.setEditable(false);
        completedReq.setVisible(false);



        deleteProjectContextMenu = new ContextMenu();
        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction(actionEvent -> {
            Project project = (Project) projectView.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete project");
            alert.setContentText("Are you sure you want to delete the project: " + project.getTitle() + " id: " + project.getId());
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    projectManagementModelManager.removeProject(project);
                    projects.remove(project);
                } catch (Exception e) {
                    Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    errorAlert.setTitle("Warning");
                    errorAlert.setContentText(e.getMessage());
                    Optional<ButtonType> errorResponse = errorAlert.showAndWait();
                }
            }
        });
        deleteProjectContextMenu.getItems().addAll(deleteItem);




        unassignMenu = new ContextMenu();
        MenuItem unassignMemberMenu = new MenuItem("Unassign Member");
        unassignMemberMenu.setOnAction(actionEvent -> {
            Member member = (Member) humanListView.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Unassign member");
            alert.setContentText("Are you sure you want to unassign the Member: " + member.getFullName() + " mail: " + member.getEmail());
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Project project = (Project) projectView.getSelectionModel().getSelectedItem();
                try {
                    for (Task task : project.getAllTasks()) task.unassignMember(member);
                } catch (Exception e) {
                    Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    errorAlert.setTitle("Warning");
                    errorAlert.setContentText(e.getMessage());
                    Optional<ButtonType> errorResponse = errorAlert.showAndWait();
                }
            }
        });
        unassignMenu.getItems().addAll(unassignMemberMenu);




        deleteRequirementContextMenu = new ContextMenu();
        MenuItem deleteItemReq = new MenuItem("Delete");
        deleteItemReq.setOnAction(actionEvent -> {
            Requirement requirement = (Requirement) reqView.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete requirement");
            alert.setContentText("Are you sure you want to delete the requirement: " + requirement.getTitle() + " id: " + requirement.getId());
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Project project = (Project) projectView.getSelectionModel().getSelectedItem();
                try {
                    project.removeRequirement(requirement);
                } catch (Exception e) {
                    Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    errorAlert.setTitle("Warning");
                    errorAlert.setContentText(e.getMessage());
                    Optional<ButtonType> errorResponse = errorAlert.showAndWait();
                }
            }
        });
        deleteRequirementContextMenu.getItems().addAll(deleteItemReq);


        // Updates the content of the app every time the a new item of the list is clicked.
        projectView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {

                Project project = (Project) projectView.getSelectionModel().getSelectedItem();
                projectText.setText(project.getTitle());
                idText.setText(project.getId());
                projectDescription.setText(project.getDescription());
                progressCircle.setProgress(0.5);

                humanListView.setItems(FXCollections.observableArrayList(project.getAllMembers()));
                reqView.setItems(FXCollections.observableArrayList(project.getAllRequirements()));
                reqView.getSelectionModel().selectFirst();
            }
        });

        reqView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                completedReq.setVisible(false);
                Requirement requirement = (Requirement) reqView.getSelectionModel().getSelectedItem();

                reqTitle.setText(requirement.getTitle());
                reqId.setText(requirement.getId());
                reqDescription.setText(requirement.getDescription());
                progressBar.setProgress(requirement.getStatus());
                if (progressBar.getProgress() == 1) completedReq.setVisible(true);
            }
        });


        // Populates the list with the values given.
        projectView.setItems(projects);
        //sets the selection mode soo that only one item can be sleected at a time
        projectView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        projectView.getSelectionModel().selectFirst();

        reqView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        reqView.getSelectionModel().selectFirst();

        humanListView.getSelectionModel().selectFirst();
        humanListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Sets the context menu to the listview.

        projectView.setCellFactory(new Callback<ListView, ListCell>() {
            @Override public ListCell call(ListView listView) {
                ListCell<Project> cell = new ListCell<>() {
                    @Override
                    protected void updateItem(Project project, boolean b) {
                        super.updateItem(project, b);
                        setText(b ? null : project.getTitle());
                    }
                };
                cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> cell.setContextMenu(isNowEmpty ? null : deleteProjectContextMenu));
                return cell;
            }
        });

        reqView.setCellFactory(new Callback<ListView, ListCell>() {
            @Override public ListCell call(ListView listView) {
                ListCell<Requirement> cell = new ListCell<>() {
                    @Override protected void updateItem(Requirement req, boolean b) {
                        super.updateItem(req, b);
                        if (b) {
                            setText(null);
                        } else {
                            setText(req.toString());
                        }
                    }
                };
                cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> cell.setContextMenu(isNowEmpty ? null : deleteRequirementContextMenu));
                return cell;
            }
        });

        humanListView.setCellFactory(new Callback<ListView, ListCell>() {
            @Override public ListCell call(ListView listView) {
                ListCell<Member> cell = new ListCell<>() {
                    @Override protected void updateItem(Member hum, boolean b) {
                        super.updateItem(hum, b);
                        if (b) {
                            setText(null);
                        } else {
                            setText(hum.toString());
                        }
                    }
                };
                cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> cell.setContextMenu(isNowEmpty ? null : unassignMenu));
                return cell;
            }
        });
    }

    @FXML public void showItemDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainGrid.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("projectDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Can not add new projects at the moment.");
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            DialogController controller = fxmlLoader.getController();
            List<Object> projectData = controller.processResults();
            projects.add(projectManagementModelManager.addProject((String) projectData.get(0), (String) projectData.get(1), 1, 1, 2030));
        }
    }

    public void showRequirementDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainGrid.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("reqDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Can not add new requirements at the moment.");
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            ReqDialog controller = fxmlLoader.getController();
            List<Object> requirementData = controller.processReqResults();
            Project project = (Project) projectView.getSelectionModel().getSelectedItem();
            reqView.getSelectionModel().select(project.addRequirement((String) requirementData.get(0), (String) requirementData.get(1), 1, 1, 2025, "Critical"));
        }
    }

    public void showMemberDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainGrid.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("memberDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Dialog could not be loaded");
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            MemberController controller = fxmlLoader.getController();
            List<Object> memberData = controller.processMemberResults();
            Project project = (Project) projectView.getSelectionModel().getSelectedItem();
            humanListView.getSelectionModel().select(projectManagementModelManager.addMember((String) memberData.get(0), "Jaime", new MyDate(1, 1, 2000), "jaimeelena@gmail.com"));
        }
    }
}