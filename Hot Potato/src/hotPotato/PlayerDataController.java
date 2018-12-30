package hotPotato;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PlayerDataController {

	@FXML
	TextField enterPlayers;

	@FXML
	TextArea displayPlayers;

	@FXML
	SplitMenuButton remove;

	Queue<String> players = new Queue<String>();
	String newPlayer;
	String currentPlayers;

	public void buttonClickHandler(ActionEvent evt) {
		// Store the clickedbutton and the clickedButton's text into variables
		Button clickedButton = (Button) evt.getTarget();
		String buttonLabel = clickedButton.getText();

		if ("Quit".equals(buttonLabel)) {
			Platform.exit();
		}

		if ("Continue".equals(buttonLabel)) {
			if (!players.isEmpty() && players.length()>2) {
				playGame();
			}
		}

	}

	public void displayFieldHandler(KeyEvent evt) {
		newPlayer = enterPlayers.getText();
		if (evt.getCode().equals(KeyCode.ENTER) && !"".equals(enterPlayers.getText())
				&& !(players.contains(newPlayer))) {
			addPlayer();
			MenuItem player1 = new MenuItem(newPlayer);
			remove.getItems().add(player1);
			player1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					MenuItem menuItem = (MenuItem) event.getTarget();
					String menuItemLabel = menuItem.getText();
					// GOES into this method on click of the menu item 'My Item'
					remove.getItems().remove((players.getIndex(menuItemLabel)));
					players.dequeue(menuItemLabel);
					displayPlayers.setText("");
					for (int i = 0; i < players.length(); i++) {
						currentPlayers = displayPlayers.getText();
						displayPlayers.setText(currentPlayers + players.peek(i) + "\n");
					}
				}
			});
		}
	}

	public void splitMenuHandler(ActionEvent evt) {
		// Store the menuitem that the user clicked and the text of that menuitem into
		// variables
		SplitMenuButton splitMenuButton = (SplitMenuButton) evt.getTarget();
		String splitMenuButtonLabel = splitMenuButton.getText();

		if ("Remove".equals(splitMenuButtonLabel)) {
			remove.getItems().remove(players.length() - 1);
			players.dequeue(players.length() - 1);
			displayPlayers.setText("");
			for (int i = 0; i < players.length(); i++) {
				currentPlayers = displayPlayers.getText();
				displayPlayers.setText(currentPlayers + players.peek(i) + "\n");
			}
		}
	}

	public void addPlayer() {
		players.enqueue(newPlayer);
		currentPlayers = displayPlayers.getText();
		enterPlayers.setText("");
		displayPlayers.setText(currentPlayers + newPlayer + "\n");
	}

	public void playGame() {
		try {
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ActualGame.fxml"));
			BorderPane hotPotatoRoot1 = (BorderPane) loader1.load();
			Scene hotPotatoScene1 = new Scene(hotPotatoRoot1, 300, 316);
			hotPotatoScene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			ActualGameController actualGamePage = loader1.getController();// this gets the controller to use
			actualGamePage.getQueue(players); // this calls the method to get the playerQueue

			Stage hotPotatoStage1 = new Stage();
			hotPotatoStage1.setScene(hotPotatoScene1);
			hotPotatoStage1.show();

			actualGamePage.initialize(null, null);
			actualGamePage.update();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
