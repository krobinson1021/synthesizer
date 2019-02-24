package Driver;

import java.util.ArrayList;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import GUI.GUIAdjustVolume;
import GUI.GUISawtoothWave;
import GUI.GUISinewaveGenerator;
import GUI.GUISquareWave;
import GUI.GUITriangleWave;
import GUI.GUIWhiteNoise;
import Widgets.Cable;
import Widgets.SourceWidget;
import Widgets.Speaker;
import Widgets.TargetWidget;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class MyApp extends Application {

	Line line;
	ArrayList<SourceWidget> allSources = new ArrayList<SourceWidget>();
	ArrayList<TargetWidget> allTargets = new ArrayList<TargetWidget>();
	ArrayList<Cable> allCables = new ArrayList<Cable>();
	SourceWidget sourceWidgetHolder;
	TargetWidget targetWidgetHolder;
	int sinewaveWidgetCount = 0;
	int volumeAdjustCount = 0;
	int whiteNoiseCount = 0;
	int waveCount = 0;
	Boolean hit = false;

	@Override
	public void start(Stage window) throws Exception {

		line = null;
		sourceWidgetHolder = null;
		targetWidgetHolder = null;

	// Creating all widgets

		GUISinewaveGenerator sinewaveGUI1 = new GUISinewaveGenerator(this);
		GUISinewaveGenerator sinewaveGUI2 = new GUISinewaveGenerator(this);
		GUISinewaveGenerator sinewaveGUI3 = new GUISinewaveGenerator(this);

		GUIAdjustVolume volumeAdjustGUI1 = new GUIAdjustVolume(this);
		GUIAdjustVolume volumeAdjustGUI2 = new GUIAdjustVolume(this);
		GUIAdjustVolume volumeAdjustGUI3 = new GUIAdjustVolume(this);

		GUIWhiteNoise whiteNoiseGUI1 = new GUIWhiteNoise(this);
		GUIWhiteNoise whiteNoiseGUI2 = new GUIWhiteNoise(this);
		GUIWhiteNoise whiteNoiseGUI3 = new GUIWhiteNoise(this);

		GUISquareWave squareWaveGUI1 = new GUISquareWave(this);
		GUISquareWave squareWaveGUI2 = new GUISquareWave(this);
		GUISquareWave squareWaveGUI3 = new GUISquareWave(this);
		
		GUISawtoothWave sawtoothWaveGUI1 = new GUISawtoothWave(this);
		GUISawtoothWave sawtoothWaveGUI2 = new GUISawtoothWave(this);
		GUISawtoothWave sawtoothWaveGUI3 = new GUISawtoothWave(this);
		
		GUITriangleWave triangleWaveGUI1 = new GUITriangleWave(this);
		GUITriangleWave triangleWaveGUI2 = new GUITriangleWave(this);
		GUITriangleWave triangleWaveGUI3 = new GUITriangleWave(this);

		Speaker speaker = new Speaker(this);

	// Adding sources to array list
		
		allSources.add(sinewaveGUI1); // sine wave is only a source
		allSources.add(sinewaveGUI2);
		allSources.add(sinewaveGUI3);
		allSources.add(squareWaveGUI1); // square wave is only a source
		allSources.add(squareWaveGUI2);
		allSources.add(squareWaveGUI3);
		allSources.add(whiteNoiseGUI1); // white noise is only a source
		allSources.add(whiteNoiseGUI2);
		allSources.add(whiteNoiseGUI3);
		allSources.add(volumeAdjustGUI1); // filters are both sources and targets
		allSources.add(volumeAdjustGUI2);
		allSources.add(volumeAdjustGUI3);
		allSources.add(sawtoothWaveGUI1);
		allSources.add(sawtoothWaveGUI2);
		allSources.add(sawtoothWaveGUI3);
		allSources.add(triangleWaveGUI1);
		allSources.add(triangleWaveGUI2);
		allSources.add(triangleWaveGUI3);


	// Adding targets to array list
		
		allTargets.add(volumeAdjustGUI1);
		allTargets.add(volumeAdjustGUI2);
		allTargets.add(volumeAdjustGUI3);
		allTargets.add(speaker); // speaker is only a target

	// Creating library buttons	
		
		Button startButton = new Button("PLAY");
		Button sinewaveButton = new Button("Sine Wave");
		Button squarewaveButton = new Button("Square Wave");
		Button whitenoiseButton = new Button("White Noise");
		Button volumeAdjustButton = new Button("Volume Adjust");
		Button sawtoothwaveButton = new Button("Sawtooth Wave");
		Button trianglewaveButton = new Button("Triangle Wave");


	// Creating start button and hbox for bottom of window
		
		HBox hbox = new HBox(startButton);
		hbox.setPrefSize(700, 40);
		hbox.setStyle("-fx-background-color: #606060;");

	// Creating main border pane
		
		BorderPane rootPane = new BorderPane();
		rootPane.setBottom(hbox);
		
	// Creating widget library and adding buttons

		VBox widgetLibrary = new VBox();
		rootPane.setRight(widgetLibrary);
		widgetLibrary.setStyle("-fx-border-color: black;" + "-fx-border-insets: 5;" + "-fx-border-width: 1;");
		widgetLibrary.setSpacing(15);
		widgetLibrary.getChildren().add(sinewaveButton);
		widgetLibrary.getChildren().add(squarewaveButton);
		widgetLibrary.getChildren().add(whitenoiseButton);
		widgetLibrary.getChildren().add(volumeAdjustButton);
		widgetLibrary.getChildren().add(sawtoothwaveButton);
		widgetLibrary.getChildren().add(trianglewaveButton);

	// Creating pane to where all widgets can move around	
		
		Pane widgetArea = new Pane();
		widgetArea.setPrefSize(500, 400);
		rootPane.setCenter(widgetArea);
		widgetArea.getChildren().add(speaker.getTargetCircle());
		
	// Creating main window

		Scene scene = new Scene(rootPane, 700, 700);
		window.setTitle("Katie's Synthesizer");
		window.setScene(scene);
		window.show();
		
	// Action events

		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					Clip c = AudioSystem.getClip();
					AudioFormat format16 = new AudioFormat(c.getFormat().getSampleRate(), 16, 1, true, false);
					AudioClip clip = new AudioClip(speaker.getSource().generate());
					c.open(format16, clip.getByteArray(), 0, clip.getByteArray().length);
					System.out.println("about to play");
					c.start();
					System.out.println("done");

				} catch (LineUnavailableException lue) {
					lue.printStackTrace();
				}
			}
		});

		sinewaveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				sinewaveWidgetCount++;
				try {
					if (sinewaveWidgetCount == 1) {
						widgetArea.getChildren().add(sinewaveGUI1.getBorderPane());
					} else if (sinewaveWidgetCount == 2) {
						widgetArea.getChildren().add(sinewaveGUI2.getBorderPane());
					} else if (sinewaveWidgetCount == 3) {
						widgetArea.getChildren().add(sinewaveGUI3.getBorderPane());
					}
				} catch (Exception ex) {
					System.out.println("You have reached the sine wave widget limit.");
				}
			}
		});

		volumeAdjustButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				volumeAdjustCount++;
				try {
					if (volumeAdjustCount == 1) {
						widgetArea.getChildren().add(volumeAdjustGUI1.getBorderPane());
					} else if (volumeAdjustCount == 2) {
						widgetArea.getChildren().add(volumeAdjustGUI2.getBorderPane());
					} else if (volumeAdjustCount == 3) {
						widgetArea.getChildren().add(volumeAdjustGUI3.getBorderPane());
					}
				} catch (Exception ex) {
					System.out.println("You have reached the volume adjust widget limit.");
				}
			}
		});

		whitenoiseButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				whiteNoiseCount++;
				try {
					if (whiteNoiseCount == 1) {
						widgetArea.getChildren().add(whiteNoiseGUI1.getBorderPane());
					} else if (whiteNoiseCount == 2) {
						widgetArea.getChildren().add(whiteNoiseGUI2.getBorderPane());
					} else if (whiteNoiseCount == 3) {
						widgetArea.getChildren().add(whiteNoiseGUI3.getBorderPane());
					}
				} catch (Exception ex) {
					System.out.println("You have reached the white noise widget limit.");
				}
			}
		});

		squarewaveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				waveCount++;
				try {
					if (waveCount == 1) {
						widgetArea.getChildren().add(squareWaveGUI1.getBorderPane());
					} else if (waveCount == 2) {
						widgetArea.getChildren().add(squareWaveGUI2.getBorderPane());
					} else if (waveCount == 3) {
						widgetArea.getChildren().add(squareWaveGUI3.getBorderPane());
					}
				} catch (Exception ex) {
					System.out.println("You have reached the square wave widget limit.");
				}
			}
		});
		
		sawtoothwaveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				waveCount++;
				try {
					if (waveCount == 1) {
						widgetArea.getChildren().add(sawtoothWaveGUI1.getBorderPane());
					} else if (waveCount == 2) {
						widgetArea.getChildren().add(sawtoothWaveGUI2.getBorderPane());
					} else if (waveCount == 3) {
						widgetArea.getChildren().add(sawtoothWaveGUI3.getBorderPane());
					}
				} catch (Exception ex) {
					System.out.println("You have reached the square wave widget limit.");
				}
			}
		});
		
		trianglewaveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				waveCount++;
				try {
					if (waveCount == 1) {
						widgetArea.getChildren().add(triangleWaveGUI1.getBorderPane());
					} else if (waveCount == 2) {
						widgetArea.getChildren().add(triangleWaveGUI2.getBorderPane());
					} else if (waveCount == 3) {
						widgetArea.getChildren().add(triangleWaveGUI3.getBorderPane());
					}
				} catch (Exception ex) {
					System.out.println("You have reached the square wave widget limit.");
				}
			}
		});

		widgetArea.setOnMousePressed(e -> {
			Point2D mousePosition = new Point2D(e.getSceneX(), e.getSceneY());

			for (SourceWidget sourceWidget : allSources) {

				Circle sourceCircle = sourceWidget.getSourceCircle();
				Point2D sourceCircleLocal = new Point2D(sourceCircle.getCenterX(), sourceCircle.getCenterY());
				Point2D sourceCircleCenterScene = sourceCircle.localToScene(sourceCircleLocal);

				if (sourceCircle.contains(sourceCircle.sceneToLocal(mousePosition))) { // in circle
					line = new Line();
					sourceWidgetHolder = sourceWidget;
					System.out.println("Source widget is " + sourceWidgetHolder);
					
					line.setStartX(sourceCircleCenterScene.getX());
					line.setStartY(sourceCircleCenterScene.getY());
					line.setEndX(mousePosition.getX());
					line.setEndY(mousePosition.getY());
					widgetArea.getChildren().add(line);
					sourceWidget.setIsDragging(true);
				}
			}
		});
		
		widgetArea.setOnMouseDragged(e -> {
			try {
				line.setEndX(e.getSceneX());
				line.setEndY(e.getSceneY());
			} catch (NullPointerException ex) {
//				System.out.print("You can't click there. ");
			}
		});

		widgetArea.setOnMouseReleased(e -> {
			for (TargetWidget targetWidget : allTargets) {
				Point2D mousePosition = new Point2D(e.getSceneX(), e.getSceneY());
				Circle targetCircle = targetWidget.getTargetCircle();

				if (targetCircle.contains(targetCircle.sceneToLocal(mousePosition))) {
					hit = true;
					targetWidgetHolder = targetWidget;
					System.out.println("Target widget: " + targetWidget);
				}
			}
			if (hit.equals(true)) {
				Cable cable1 = new Cable(sourceWidgetHolder, targetWidgetHolder);
				allCables.add(cable1);
				widgetArea.getChildren().add(cable1.getCable());
				
				for (SourceWidget sourceWidget : allSources) {
						sourceWidget.setIsDragging(false);
				}
			}
			widgetArea.getChildren().remove(line);
			hit = false;
			sourceWidgetHolder = null;
		});

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	public void updateCables() {
		for (Cable c : allCables) {
			c.updateCable();
		}
	}
}