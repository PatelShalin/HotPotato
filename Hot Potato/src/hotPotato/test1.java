package hotPotato;

import java.awt.Desktop.Action;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class test1 implements Initializable {

	@FXML
	TextArea current;

	@FXML
	TextArea holder;
	private Service<Void> backgroundThread;

	@FXML
	TextArea Out;

	String store = "";

	String playerOut = "";

	int w = 0;
	int p = 0;
	
	int random = (int) ((Math.random()*4)+2);
	int random1 = (int) ((Math.random()*2)+2);

	Stack<String> playersOut = new Stack<String>();

	public boolean automatic = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub		
		for (int i = 0; i < (playerQueue.length()); i++) {
			current.setText(store + playerQueue.peek(i));
			store = current.getText() + "\n";
		}
		store = "";
	}

	public void buttonClickHandler(ActionEvent evt) {
		Button button = (Button) evt.getTarget();
		String buttonLabel = button.getText();

		if ("Quit".equals(buttonLabel)) {
			Platform.exit();
		}

		if ("Automatic Mode".equals(buttonLabel)) {
			backgroundThread.restart();
			automatic = true;
			holder.textProperty().bind(backgroundThread.messageProperty());
		}

		if ("Continue".equals(buttonLabel)) {
			if (w < random1) {
				playerOut = playerQueue.peek(w);
				holder.setText(playerOut);
				w += 1;
			} else {
				w = 0;
				p += 1;
				random1 = (int) ((Math.random()*2)+2);
			}
			if (p==random) {
				playersOut.push(playerOut);
				playerQueue.dequeue(playerOut);
				store = Out.getText() + "\n";
				Out.setText(store + playerOut);

				store = "";
				initialize(null,null);
				p = 0;
				random = (int) ((Math.random()*4)+2);
			}
			if (playerQueue.isEmpty()) {
				current.setText("");
				winScreen();
			}
			
		}

	}

	// create a new instance of the Queue class (in this case type String)
	Queue<String> playerQueue = new Queue<String>();
	Queue<String> AgainPlayers = new Queue<String>();
		
	// now we will create a method that we can call from the previous window to
	// “get” the existing queue
	public void getQueue(Queue<String> queue) {
		playerQueue = queue;
		AgainPlayers = queue;
	}

	public void update() {

		backgroundThread = new Service<Void>() {

			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {

					@Override
					protected Void call() throws Exception {

						for (int i = 0; i < random; i++) {
							for (int w = 0; w < random1; w++) {
								updateMessage(playerQueue.peek(w));
								Thread.sleep(500);
								playerOut = playerQueue.peek(w);
								
							}
						}
						return null;
					}

				};
			}
		};

		backgroundThread.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

			@Override
			public void handle(WorkerStateEvent event) {
				holder.textProperty().unbind();
				playersOut.push(playerOut);
				playerQueue.dequeue(playerOut);
				store = Out.getText() + "\n";
				Out.setText(store + playerOut);
				random = (int) ((Math.random()*4)+2);
				random1 = (int) ((Math.random()*2)+2);

				store = "";

				if (playerQueue.length() < 1) {
					current.setText("");
					winScreen();
					backgroundThread.cancel();
				} else {
					initialize(null, null);
					update();
				}

			}

		});

		if (automatic) {
			holder.textProperty().bind(backgroundThread.messageProperty());
			backgroundThread.restart();
		}

	}

	public void winScreen() {
		store = "";

		playerOut = "";

		w = 0;
		p = 0;
		
		automatic = false;
		
		
		
		try {
			FXMLLoader loader2 = new FXMLLoader(getClass().getResource("WinScreen.fxml"));
			BorderPane winScreenRoot = (BorderPane) loader2.load();
			Scene winScreenScene = new Scene(winScreenRoot, 228, 321.6);
			winScreenScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			WinScreenController winScreen = loader2.getController();// this gets the controller to use
			winScreen.whoWon(playersOut); // this calls the method to get the playerQueue
			winScreen.initialize(null, null);
			
			WinScreenController winScreenPage = loader2.getController();// this gets the controller to use
			

			Stage winScreenStage = new Stage();
			winScreenStage.setScene(winScreenScene);
			winScreenStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
