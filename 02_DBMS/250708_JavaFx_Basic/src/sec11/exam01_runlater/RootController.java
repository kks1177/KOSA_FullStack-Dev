package sec11.exam01_runlater;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RootController implements Initializable {
	// UI components injected from FXML
	@FXML private Label lblTime;
	@FXML private Button btnStart;
	@FXML private Button btnStop;
	
	// Flag to control the loop
	private boolean stop;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Register event handlers for buttons
		btnStart.setOnAction(event -> handleBtnStart(event));
		btnStop.setOnAction(event -> handleBtnStop(event));
	}
	
	// Start button handler
	public void handleBtnStart(ActionEvent e) {
		stop = false;

		// Create and start a new background thread
		Thread thread = new Thread() {
			@Override
			public void run() {
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

				// Loop until stop flag is set
				while (!stop) {
					String strTime = sdf.format(new Date());

					// Update UI on the JavaFX Application Thread
					Platform.runLater(() -> {
						lblTime.setText(strTime);
					});

					try {
						Thread.sleep(100); // Sleep for 100 milliseconds
					} catch (InterruptedException e) {
						// Ignore interruption
					}
				}
			}
		};

		thread.setDaemon(true); // Mark thread as daemon so it does not prevent application exit
		thread.start();         // Start the thread
	}
	
	// Stop button handler
	public void handleBtnStop(ActionEvent e) {
		stop = true; // Set stop flag to true to end the loop
	}
}
