package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class Main extends Application {
	private static final double LISTVIEW_CELL_HEIGHT = 23.5;
	
	@Override
	public void start(Stage primaryStage) {
		
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10));
	
		/* ----------------------- Etape 1 ----------------------- */
		// Créer une Vertical Box
		VBox mainVBox = new VBox();
		
		mainVBox.setSpacing(10);
		
		// Créer 5 conteneurs
		AnchorPane choixTypePolice = new AnchorPane();
		FlowPane titreEffet = new FlowPane();
		TilePane checkboxesEffets = new TilePane();
		FlowPane titreApercu = new FlowPane();
		GridPane contenuApercu = new GridPane();
		
		// Aide à la visualisation des conteneurs
		/*choixTypePolice.setStyle("-fx-background-color: salmon;");
		titreEffet.setStyle("-fx-background-color: sandybrown;");
		checkboxesEffets.setStyle("-fx-background-color: yellow;");
		titreApercu.setStyle("-fx-background-color: lightgreen;");
		contenuApercu.setStyle("-fx-background-color: lightblue;");*/
		
		// Ajouter les conteneurs dans la Vertical Box
		mainVBox.getChildren().addAll(choixTypePolice, titreEffet,
				checkboxesEffets, titreApercu, contenuApercu);
		
		root.setCenter(mainVBox);
		
		/* ----------------------- Etape 2 ----------------------- */
		// Créer 4 bouttons
		Button definirParDefaut = new Button("Définir par défaut");
		Button effetsDeTexte = new Button("Effets de texte...");
		Button ok = new Button("OK");
		Button annuler = new Button("Annuler");
		
		// Créer un conteneur Horizontal Box
		HBox hboxBouttons = new HBox();
		
		// Mettre les bouttons dans le conteneur
		hboxBouttons.getChildren().addAll(definirParDefaut, effetsDeTexte,
				ok, annuler);
		
		// Rendre ça un peu plus joli
		hboxBouttons.setAlignment(Pos.CENTER);
		hboxBouttons.setSpacing(10);
		
		// Aide à la visualisation du conteneur
		// hboxBouttons.setStyle("-fx-background-color: mediumslateblue;");
		
		// Ajouter la Horizontal Box dans la partie « bottom » du BorderPane
		root.setBottom(hboxBouttons);
		
		/* ----------------------- Etape 3 ----------------------- */
		Font policeTitres = new Font("Arial", 12);
		
		Label effets = new Label("Effets");
		effets.setFont(policeTitres);
		titreEffet.getChildren().add(effets);
		
		Label apercu = new Label("Aperçu");
		apercu.setFont(policeTitres);
		titreApercu.getChildren().add(apercu);
		
		/* ----------------------- Etape 4 ----------------------- */
		// Créer 7 checkboxes
		CheckBox barre = new CheckBox("Barré");
		CheckBox barreDouble = new CheckBox("Barré double");
		CheckBox exposant = new CheckBox("Exposant");
		CheckBox indice = new CheckBox("Indice");
		CheckBox petitesMajuscules = new CheckBox("Petites Majuscules");
		CheckBox majuscules = new CheckBox("Majuscules");
		CheckBox masque = new CheckBox("Masqué");
		
		checkboxesEffets.getChildren().addAll(barre, barreDouble, exposant,
				indice, petitesMajuscules, majuscules, masque);
		
		// Disposition des CheckBox
		checkboxesEffets.setTileAlignment(Pos.TOP_LEFT);
		
		checkboxesEffets.setPrefColumns(2);
		checkboxesEffets.setPrefRows(4);
		checkboxesEffets.setHgap(144);
		
		mainVBox.setFillWidth(false);
		
		/* ----------------------- Etape 5 ----------------------- */
		// Label, TextField et ListView pour la police
		Label police = new Label("Police :");
		TextField policeSelectionnee = new TextField();
		ListView<String> policeSelection = new ListView<>();
		
		// Mettre la liste des polices utilisables dans la ListView
		ObservableList<String> toutesLesPolices = FXCollections.observableArrayList(javafx.scene.text.Font.getFamilies());
		policeSelection.setItems(toutesLesPolices);
		
		// Définir une taille min et max pour l'affichage de la ListView
		policeSelection.setMinHeight(5 * LISTVIEW_CELL_HEIGHT);
		policeSelection.setMaxHeight(5 * LISTVIEW_CELL_HEIGHT);
		
		// Petite astuce pour pas passer 3h à régler les positions à la main
		VBox vboxPolice = new VBox();
		
		vboxPolice.getChildren().addAll(police, policeSelectionnee,
				policeSelection);
		
		choixTypePolice.getChildren().add(vboxPolice);
		
		Scene scene = new Scene(root, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
