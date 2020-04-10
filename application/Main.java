package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

// Classe utilisée pour l'étape 9
class FontParams {
	private String name, effect;
	private int size;
	private Color color;
	
	FontParams() {
		name = "Arial";
		effect = "Normal";
		size = 16;
		color = Color.BLACK;
	}
	
	FontParams(String name, String effect, int size, Color color) {
		this.name = name;
		this.effect = effect;
		this.size = size;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public Font getFont() {
		Font font = null;
		
		if ("Normal".equals(effect))
			font = Font.font(name, size);
		
		else if ("Gras".equals(effect))
			font = Font.font(name, FontWeight.BOLD, size);
		
		else if ("Italique".equals(effect))
			font = Font.font(name, FontPosture.ITALIC, size);
		
		else if ("Gras Italique".equals(effect))
			font = Font.font(name, FontWeight.BOLD, FontPosture.ITALIC, size);
		
		return font;
	}
	
	
}

public class Main extends Application {
	private static final double MAIN_SCENE_WIDTH = 600;
	private static final double MAIN_SCENE_HEIGHT = 500;
	
	private static final double LISTVIEW_CELL_HEIGHT = 23.5;
	private static final double LISTVIEW_FONT_SELECTION_WIDTH = 150;
	private static final double LISTVIEW_FONT_STYLE_SELECTION_WIDTH = 125;
	private static final double LISTVIEW_FONT_SIZE_SELECTION_WIDTH = 50;

	private static final double POLICE_CONTAINER_HEIGHT = 225;
	
	private static final String TEXTE_EXEMPLE = "Sa majesté Kontin 1er";
	
	private BorderPane root;
	private VBox mainVBox;
	
	private AnchorPane choixTypePolice;
	private FlowPane titreEffet;
	private TilePane checkboxesEffets;
	private FlowPane titreApercu;
	private GridPane contenuApercu;
	
	private Button definirParDefaut;
	private Button effetsDeTexte;
	private Button ok;
	private Button annuler;
	
	private HBox hboxBouttons;
	
	private Font policeTitres;
	
	private Label effets;	
	private Label apercu;
	
	private CheckBox barre;
	private CheckBox barreDouble;
	private CheckBox exposant;
	private CheckBox indice;
	private CheckBox petitesMajuscules;
	private CheckBox majuscules;
	private CheckBox masque;
	
	private Label police;
	private TextField policeSelectionnee;
	private ListView<String> policeSelection;
	private VBox vboxPolice;
	
	private Label stylePolice;
	private TextField stylePoliceSelectionnee;
	private ListView<String> stylePoliceSelection;
	private VBox vboxStyleDePolice;
	
	private Label taillePolice;
	private TextField taillePoliceSelectionnee;
	private ListView<String> taillePoliceSelection;
	private VBox vboxTaillePolice;
	
	private Label couleurPolice;
	private ColorPicker colorP;
	private VBox vboxCouleur;
	
	Canvas apercuPolice;
	GraphicsContext gc;
	
	
	@Override
	public void start(Stage primaryStage) {
		
		root = new BorderPane();
		root.setPadding(new Insets(10));
	
		/* ----------------------- Etape 1 ----------------------- */
		// Créer une Vertical Box
		mainVBox = new VBox();
		
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
		definirParDefaut = new Button("Définir par défaut");
		effetsDeTexte = new Button("Effets de texte...");
		ok = new Button("OK");
		annuler = new Button("Annuler");
		
		// Créer un conteneur Horizontal Box
		hboxBouttons = new HBox();
		
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
		policeTitres = new Font("Arial", 12);
		
		effets = new Label("Effets");
		effets.setFont(policeTitres);
		Separator separateurEffets = new Separator();
		separateurEffets.setMinWidth(MAIN_SCENE_WIDTH - 55.0);
		separateurEffets.setMaxWidth(MAIN_SCENE_WIDTH - 55.0);
		titreEffet.getChildren().addAll(effets, separateurEffets);
		
		apercu = new Label("Aperçu");
		apercu.setFont(policeTitres);
		Separator separateurApercu = new Separator();
		separateurApercu.setMinWidth(MAIN_SCENE_WIDTH - 60.0);
		separateurApercu.setMaxWidth(MAIN_SCENE_WIDTH - 60.0);
		titreApercu.getChildren().addAll(apercu, separateurApercu);
		
		/* ----------------------- Etape 4 ----------------------- */
		// Créer 7 checkboxes
		barre = new CheckBox("Barré");
		barreDouble = new CheckBox("Barré double");
		exposant = new CheckBox("Exposant");
		indice = new CheckBox("Indice");
		petitesMajuscules = new CheckBox("Petites Majuscules");
		majuscules = new CheckBox("Majuscules");
		masque = new CheckBox("Masqué");
		
		checkboxesEffets.getChildren().addAll(barre, barreDouble, exposant,
				indice, petitesMajuscules, majuscules, masque);
		
		// Disposition des CheckBox
		checkboxesEffets.setTileAlignment(Pos.TOP_LEFT);
		
		checkboxesEffets.setPrefColumns(2);
		checkboxesEffets.setPrefRows(4);
		checkboxesEffets.setHgap(144);
		
		/* ----------------------- Etape 5 ----------------------- */
		// Label, TextField et ListView pour la police
		police = new Label("Police :");
		policeSelectionnee = new TextField();
		policeSelection = new ListView<>();
		
		policeSelectionnee.setText("Arial");
		
		// Mettre la liste des polices utilisables dans la ListView
		ObservableList<String> toutesLesPolices = FXCollections.observableArrayList(javafx.scene.text.Font.getFamilies());
		policeSelection.setItems(toutesLesPolices);
		
		// Définir une taille min et max pour l'affichage de la ListView
		policeSelection.setMinHeight(5 * LISTVIEW_CELL_HEIGHT);
		policeSelection.setMaxHeight(5 * LISTVIEW_CELL_HEIGHT);
		policeSelection.setMinWidth(LISTVIEW_FONT_SELECTION_WIDTH);
		policeSelection.setMaxWidth(LISTVIEW_FONT_SELECTION_WIDTH);
		policeSelectionnee.setMinWidth(LISTVIEW_FONT_SELECTION_WIDTH);
		policeSelectionnee.setMaxWidth(LISTVIEW_FONT_SELECTION_WIDTH);
		
		// Petite astuce pour pas passer 3h à régler les positions à la main
		vboxPolice = new VBox();
		
		vboxPolice.getChildren().addAll(police, policeSelectionnee,
				policeSelection);
		
		// Label, TextField et ListView pour la taille
		stylePolice = new Label("Style de police :");
		stylePoliceSelectionnee = new TextField();
		stylePoliceSelection = new ListView<>();
		
		stylePoliceSelectionnee.setText("Normal");
		
		// Mettre la liste des styles utilisables dans la ListView
		ObservableList<String> tousLesStylesDePolice = FXCollections.observableArrayList("Normal", "Italique", "Gras", "Gras Italique");
		stylePoliceSelection.setItems(tousLesStylesDePolice);
		
		// Définir une taille min et max pour l'affichage de la ListView
		stylePoliceSelection.setMinHeight(5 * LISTVIEW_CELL_HEIGHT);
		stylePoliceSelection.setMaxHeight(5 * LISTVIEW_CELL_HEIGHT);
		stylePoliceSelection.setMinWidth(LISTVIEW_FONT_STYLE_SELECTION_WIDTH);
		stylePoliceSelection.setMaxWidth(LISTVIEW_FONT_STYLE_SELECTION_WIDTH);
		stylePoliceSelectionnee.setMinWidth(LISTVIEW_FONT_STYLE_SELECTION_WIDTH);
		stylePoliceSelectionnee.setMaxWidth(LISTVIEW_FONT_STYLE_SELECTION_WIDTH);
		
		// Petite astuce pour pas passer 3h à régler les positions à la main
		vboxStyleDePolice = new VBox();
		
		vboxStyleDePolice.getChildren().addAll(stylePolice, stylePoliceSelectionnee,
				stylePoliceSelection);
		
		
		// Label, TextField et ListView pour la taille de la police
		taillePolice = new Label("Taille de police :");
		taillePoliceSelectionnee = new TextField();
		taillePoliceSelection = new ListView<>();
		
		taillePoliceSelectionnee.setText("16");
		
		// Mettre la liste des polices utilisables dans la ListView
		ArrayList<String> taillesTmp = new ArrayList<>();
		for (int i = 8; i < 30; i++) {
			if (i < 15)
				taillesTmp.add(Integer.toString(i));
			else if (i < 20)
				taillesTmp.add(Integer.toString(i * 2));
			else
				taillesTmp.add(Integer.toString(i * 5));
		}
		
		ObservableList<String> toutesLesTaillesPolices = FXCollections.observableArrayList(taillesTmp);
		taillePoliceSelection.setItems(toutesLesTaillesPolices);
		
		// Définir une taille min et max pour l'affichage de la ListView
		taillePoliceSelection.setMinHeight(5 * LISTVIEW_CELL_HEIGHT);
		taillePoliceSelection.setMaxHeight(5 * LISTVIEW_CELL_HEIGHT);
		taillePoliceSelection.setMinWidth(LISTVIEW_FONT_SIZE_SELECTION_WIDTH);
		taillePoliceSelection.setMaxWidth(LISTVIEW_FONT_SIZE_SELECTION_WIDTH);
		taillePoliceSelectionnee.setMinWidth(LISTVIEW_FONT_SIZE_SELECTION_WIDTH);
		taillePoliceSelectionnee.setMaxWidth(LISTVIEW_FONT_SIZE_SELECTION_WIDTH);
		
		// Petite astuce pour pas passer 3h à régler les positions à la main
		vboxTaillePolice = new VBox();
		
		vboxTaillePolice.getChildren().addAll(taillePolice, taillePoliceSelectionnee,
				taillePoliceSelection);

		
		/* ----------------------- Etape 6 ----------------------- */
		// ColorPicker
		couleurPolice = new Label("Couleur de police :");
		colorP = new ColorPicker(Color.BLACK);
		
		// Petite astuce pour pas passer 3h à régler les positions à la main
		vboxCouleur = new VBox();
		
		vboxCouleur.getChildren().addAll(couleurPolice, colorP);
		
		choixTypePolice.getChildren().addAll(vboxPolice, vboxStyleDePolice, vboxTaillePolice, vboxCouleur);
		choixTypePolice.setMinHeight(POLICE_CONTAINER_HEIGHT);
		
		// Mise en place des composants dans l'AnchorPane
		AnchorPane.setLeftAnchor(vboxPolice, 10.0);
		AnchorPane.setLeftAnchor(vboxStyleDePolice, (MAIN_SCENE_WIDTH / 2.0) - (LISTVIEW_FONT_SIZE_SELECTION_WIDTH / 2.0) - 20.0);
		AnchorPane.setRightAnchor(vboxTaillePolice, 10.0);
		AnchorPane.setBottomAnchor(vboxCouleur, 10.0);
		AnchorPane.setLeftAnchor(vboxCouleur, 10.0);
		
		/* ----------------------- Etape 7 ----------------------- */
		annuler.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	Stage stage = (Stage) annuler.getScene().getWindow();
		    	stage.close();
		    }
		});
		
		/* ----------------------- Etape 8 ----------------------- */
		// Empêche de rentrer des valeurs à la main dans les champs de texte
		policeSelectionnee.setDisable(true);
		policeSelectionnee.setStyle("-fx-opacity: 1;");
		stylePoliceSelectionnee.setDisable(true);
		stylePoliceSelectionnee.setStyle("-fx-opacity: 1;");
		taillePoliceSelectionnee.setDisable(true);
		taillePoliceSelectionnee.setStyle("-fx-opacity: 1;");

		policeSelection.setOnMouseClicked(event -> {
			policeSelectionnee.setText(policeSelection.getSelectionModel().getSelectedItem());
			
			// Code pour l'étape 9
			redrawTextOnCanvas();
		});		
		
		stylePoliceSelection.setOnMouseClicked(event -> {
			stylePoliceSelectionnee.setText(stylePoliceSelection.getSelectionModel().getSelectedItem());
			
			// Code pour l'étape 9
			redrawTextOnCanvas();
		});	
		
		taillePoliceSelection.setOnMouseClicked(event -> {
			taillePoliceSelectionnee.setText(taillePoliceSelection.getSelectionModel().getSelectedItem());
			
			// Code pour l'étape 9
			redrawTextOnCanvas();
		});
		
		/* ----------------------- Etape 9 ----------------------- */
		apercuPolice = new Canvas();
		apercuPolice.setHeight(50);
		apercuPolice.setWidth(MAIN_SCENE_WIDTH - 20.0);
		gc = apercuPolice.getGraphicsContext2D();
        
        // Draw a Text
		redrawTextOnCanvas();
		
		colorP.setOnAction(event -> {
			redrawTextOnCanvas();
        });
		
		Label policeLabel = new Label("Police TrueType, identique à l'écran et à l'impression.");
		
		GridPane.setRowIndex(apercuPolice, 0);
		GridPane.setRowIndex(policeLabel, 1);
		contenuApercu.getChildren().addAll(apercuPolice, policeLabel);
		
		Scene scene = new Scene(root, MAIN_SCENE_WIDTH, MAIN_SCENE_HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private FontParams getFontsParamsFromViewLists() {
		String name = policeSelectionnee.getText();
		String effect = stylePoliceSelectionnee.getText();
		int size = Integer.valueOf(taillePoliceSelectionnee.getText());
		Color colour = colorP.getValue();
		return new FontParams(name, effect, size, colour);
	}
	
	private void redrawTextOnCanvas() {
		gc = apercuPolice.getGraphicsContext2D();
		FontParams fontparams = getFontsParamsFromViewLists();
		gc.setFill(Color.WHITE); 
		gc.fillRect(0, 0, MAIN_SCENE_WIDTH - 20.0, 100);
		gc.strokeLine(0, 25, 100, 25);
		gc.strokeLine(apercuPolice.getWidth(), 25, apercuPolice.getWidth() - 100, 25);
		gc.strokeRect(0, 0, apercuPolice.getWidth(), apercuPolice.getHeight());
		gc.setTextAlign(TextAlignment.CENTER);
	    gc.setTextBaseline(VPos.CENTER);
		gc.setFont(fontparams.getFont());
		gc.setFill(fontparams.getColor());
		gc.fillText(TEXTE_EXEMPLE, apercuPolice.getWidth() / 2.0, apercuPolice.getHeight() / 2.0);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
