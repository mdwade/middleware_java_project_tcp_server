package controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.Log;
import service.Server;

public class ServerController implements Initializable {

    public static ArrayList<Log> logs = new ArrayList<>();
    public static int currentUser = 0;

    @FXML
    private Pane graphRegion;

    @FXML
    private LineChart<String, Integer> graph;

    @FXML
    private AnchorPane view;

    @FXML
    private Button runCloseBtn;

    @FXML
    private TableView<Log> logTable;

    @FXML
    private TableColumn<Log, String> userAction;

    @FXML
    private TableColumn<Log, String> useradressIP;

    @FXML
    private TableColumn<Log, String> nameMachine;

    @FXML
    private TableColumn<Log, String> dateEtHeure;

    @FXML
    private Text statusText;

    private boolean status = false;

    ServerSocket serverSocket;

    static ObservableList<Log> data = FXCollections.observableArrayList();

    @FXML
    void closeServer(ActionEvent event) {
	System.exit(0);
    }

    @FXML
    void showGraph(ActionEvent event) {
	logTable.setVisible(false);
	graphRegion.setVisible(true);
    }

    @FXML
    void showLog(ActionEvent event) {
	graphRegion.setVisible(false);
	logTable.setVisible(true);
    }

    @FXML
    void runServer(ActionEvent event) {

	if (status == false) {
	    new Thread(() -> {
		try {
		    serverSocket = new ServerSocket(8888);
		    Log log = new Log("Démarrage du serveur", "...", "...", "" + new Date());
		    data.add(log);

		    while (true) {
			Socket socket = serverSocket.accept();

			Platform.runLater(() -> {

			    InetAddress inetAddress = socket.getInetAddress();
			    String adressMachine = inetAddress.getHostAddress();
			    String nomMachine = inetAddress.getHostName();
			    Log log1 = new Log("Connexion du client", adressMachine, nomMachine, "" + new Date());
			    data.add(log1);
			});
			new Thread(new Server(socket)).start();
			logTable.setItems(data);
		    }
		} catch (Exception e) {
		    System.out.println("Erreur de connexion :" + e.getMessage());
		}
	    }).start();

	    status = true;

	    statusText.setText("Le serveur a démarré");
	    statusText.setStyle("-fx-fill:green");

	    runCloseBtn.setText("Arréter");
	    runCloseBtn.setStyle("-fx-background-color:#ff401f");
	} else {
	    try {
		serverSocket.close();

		Log log = new Log("Arrét du serveur", "...", "...", "" + new Date());
		data.add(log);

		statusText.setText("Le serveur a arrété");
		statusText.setStyle("-fx-fill:#ff401f");

		runCloseBtn.setText("Démarrer");
		runCloseBtn.setStyle("-fx-background-color:green");

		status = false;

	    } catch (IOException e) {
		System.out.println("Probléme d'arrêt du serveur :" + e.getMessage());
	    }
	}
    }

    public static void setTable() {
	logs.forEach(log -> data.add(log));
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
	userAction.setCellValueFactory(new PropertyValueFactory<>("Action"));
	useradressIP.setCellValueFactory(new PropertyValueFactory<>("adressMachine"));
	nameMachine.setCellValueFactory(new PropertyValueFactory<>("nomMachine"));
	dateEtHeure.setCellValueFactory(new PropertyValueFactory<>("dateEtHeure"));

	XYChart.Series<String, Integer> series = new XYChart.Series<>();
	graph.setTitle("Charge du Serveur");

	ScheduledExecutorService scheduledExecutorService;
	scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

	// put dummy data onto graph per second
	scheduledExecutorService.scheduleAtFixedRate(() -> {
	    // Update the chart
	    /// Platform.runLater(() -> {

	    series.getData().add(new XYChart.Data<>(new SimpleDateFormat("HH:mm:ss").format(new Date()),
		    ServerController.currentUser));
	    // });
	}, 0, 5, TimeUnit.SECONDS);
	graph.getData().add(series);
	logTable.setItems(data);
    }
}
