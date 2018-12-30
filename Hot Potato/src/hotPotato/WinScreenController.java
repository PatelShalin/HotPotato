package hotPotato;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WinScreenController implements Initializable {

	@FXML
	Text first;

	@FXML
	Text second;

	@FXML
	Text third;

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		first.setText(playersOut.pop());
		second.setText(playersOut.pop());
		third.setText(playersOut.pop());
	}

	Queue<String> playerQueue = new Queue<String>();

	// now we will create a method that we can call from the previous window to
	// “get” the existing queue
	public void getQueue(Queue<String> queue) {
		playerQueue = queue;
	}

	Stack<String> playersOut = new Stack<String>();

	public void whoWon(Stack playersOut1) {
		playersOut = playersOut1;
	}

	public void buttonClickHandler(ActionEvent evt) {
		// Store the clickedbutton and the clickedButton's text into variables
		Button clickedButton = (Button) evt.getTarget();
		String buttonLabel = clickedButton.getText();

		if ("Quit".equals(buttonLabel)) {
			Platform.exit();
		}

		if ("Start Over".equals(buttonLabel)) {
			startOver();
		}
	}

	
	
	public void startOver() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerData.fxml"));
			BorderPane hotPotatoRoot = (BorderPane) loader.load();
			Scene hotPotatoScene = new Scene(hotPotatoRoot, 400, 400);
			hotPotatoScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage hotPotatoStage = new Stage();
			hotPotatoStage.setScene(hotPotatoScene);
			hotPotatoStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
