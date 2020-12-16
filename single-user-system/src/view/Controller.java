//package view;
//
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.ListView;
//import javafx.scene.control.ProgressIndicator;
//import javafx.scene.control.SelectionMode;
//import javafx.scene.control.TextArea;
//import javafx.scene.text.Text;
//import sample.DataManager.Hum;
//import sample.DataManager.Project;
//import sample.DataManager.Req;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Controller {
//
//    private List<Project> projects;
//
//    private List<Req> reqs;
//    private List<Req> reqs2;
//
//    private  List<Hum> hums;
//    private  List<Hum> hums2;
//
//    @FXML
//    private Text projectText;
//
//    @FXML
//    private Text idText;
//
//    @FXML
//    private ListView projectView;
//
//    @FXML
//    private ListView reqView;
//
//    @FXML
//    private ListView humanListView;
//
//    @FXML
//    private TextArea projectDescription;
//
//    @FXML
//    private ProgressIndicator progressCircle;
//
//    public void initialize()
//    {
//        //this part is only temporary to tes the functionality of the app
//
//
//
//        Req req = new Req("req 1", 76314);
//        Req req2 = new Req("req 2", 12643);
//        Req req3 = new Req("req 3", 56314);
//        Req req4= new Req("req 4", 98614);
//        Req req5= new Req("req 5", 16514);
//        Req req6 = new Req("req 6", 12314);
//        Req req7 = new Req("req 7", 15414);
//        Req req8 = new Req("req 8", 12314);
//        Req req9 = new Req("req 9", 31244);
//        Req req10 = new Req("req 10", 1324);
//
//        Hum hum = new Hum("iasdha asdad", "ggfdhdaf@gmail.com");
//        Hum hum2 = new Hum("ghwadfa affd", "afdasadaaf@gmail.com");
//        Hum hum3 = new Hum("ifgda fgad", "afaadaf@gmail.com");
//        Hum hum4 = new Hum("gfaaad fggdhd", "asdf@gmail.com");
//
//        projects = new ArrayList<Project>();
//        reqs = new ArrayList<Req>();
//        hums = new ArrayList<Hum>();
//        reqs2 = new ArrayList<Req>();
//        hums2 = new ArrayList<Hum>();
//
//        reqs.add(req);
//        reqs.add(req2);
//        reqs.add(req3);
//        reqs.add(req4);
//        reqs.add(req5);
//
//        reqs2.add(req6);
//        reqs2.add(req7);
//        reqs2.add(req8);
//        reqs2.add(req9);
//        reqs2.add(req10);
//
//        hums.add(hum);
//        hums.add(hum2);
//
//        hums2.add(hum3);
//        hums2.add(hum4);
//
//        Project project = new Project("Proj1", "just don't ask", "13132", 0.88, reqs, hums);
//        Project project2 = new Project("Proj2", "dsada ada fdadj", "313234", 1, reqs2, hums2);
//
//        projects.add(project);
//        projects.add(project2);
//
//        //End of temporary part
//
//        projectDescription.setEditable(false);
//
//        //Updates the content of the app every time the a new item of the list is clicked
//
//        projectView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
//            @Override
//            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
//                if(newValue!=null)
//                {
//                    Project project1 = (Project) projectView.getSelectionModel().getSelectedItem();
//                    projectText.setText(project1.getTitle());
//                    idText.setText(project1.getId());
//                    projectDescription.setText(project1.getDescription());
//                    progressCircle.setProgress(project1.getStatus());
//
//                    humanListView.getItems().setAll(project1.getHums());
//
//                    reqView.getItems().setAll(project1.getReqs());
//
//                }
//
//            }
//        });
//
//
//        //populates the list with the values given
//        projectView.getItems().setAll(projects);
//        //sets the selection mode soo that only one item can be sleected at a time
//        projectView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        //selects the first item by default
//        projectView.getSelectionModel().selectFirst();
//
//        reqView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        reqView.getSelectionModel().selectFirst();
//
//        humanListView.getSelectionModel().selectFirst();
//        humanListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//
//
//
//
//    }
//}
