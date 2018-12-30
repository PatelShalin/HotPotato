package hotPotato;

import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HotPotatoController {
	
	static Stage hotPotatoStage1;
	
	public void buttonClickHandler(ActionEvent evt) {
		// Store the clickedbutton and the clickedButton's text into variables
		Button clickedButton = (Button) evt.getTarget();
		String buttonLabel = clickedButton.getText();

		if ("Quit".equals(buttonLabel)) {
			Platform.exit();
		}

		if ("Play".equals(buttonLabel)) {
			inputNames();
		}
		if ("Instructions".equals(buttonLabel)) {
			instructions();
		}
		
		if ("Close".equals(buttonLabel)) {
			hotPotatoStage1.close();
		}

	}

	public void inputNames() {
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
	
	public void instructions() {
		try {
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Instructions.fxml"));
			BorderPane instrucRoot = (BorderPane) loader1.load();
			Scene hotPotatoScene1 = new Scene(instrucRoot, 225, 180);
			hotPotatoScene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			hotPotatoStage1 = new Stage();
			hotPotatoStage1.setScene(hotPotatoScene1);
			hotPotatoStage1.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}