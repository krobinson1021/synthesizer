package Widgets;

import Driver.MyApp;
import Sources.Source;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

public abstract class AbstractWidget implements SourceWidget {

	private Source gen;

	private BorderPane borderPane;

	private Circle sourceCircle;

	private Circle targetCircle;

	private GridPane gridPane;

	private Point2D position;

	private Boolean isDragging;

	protected AbstractWidget(String title, MyApp parent) {

		Label widgetTitle = new Label(title);
		isDragging = false;

		GridPane gridPane = new GridPane(); // creating grid pane (center of border pane)
//		gridPane.add(widgetTitle, 0, 0, 10, 10);
		this.gridPane = gridPane;

	// Creating jacks
		
		Circle widgetJackRight = new Circle();
		widgetJackRight.setRadius(10.0f);
		widgetJackRight.setFill(javafx.scene.paint.Color.RED);
		this.sourceCircle = widgetJackRight;

		Circle widgetJackLeft = new Circle();
		widgetJackLeft.setRadius(10.0f);
		widgetJackLeft.setFill(javafx.scene.paint.Color.BLACK);
		this.targetCircle = widgetJackLeft;
		
	// Creating border pane (overall widget layout)

		BorderPane borderPane = new BorderPane();
		borderPane.setStyle("-fx-border-color: black");
		borderPane.setCenter(this.gridPane);
		borderPane.setRight(this.sourceCircle);
		borderPane.setLeft(widgetJackLeft);
		borderPane.setTop(widgetTitle);
		borderPane.setStyle("-fx-background-color: #DCDCDC; -fx-border-color: black; -fx-border-width: 1;");
		this.borderPane = borderPane;
		
	// widget dragging events

		borderPane.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (!isDragging) {
					double x = borderPane.getTranslateX() - mouseEvent.getScreenX();
					double y = borderPane.getTranslateY() - mouseEvent.getScreenY();
					position = new Point2D(x, y);
					borderPane.getScene().setCursor(Cursor.MOVE);
				}
			}
		});

		borderPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (!isDragging) {
					borderPane.setTranslateX(mouseEvent.getScreenX() + position.getX());
					borderPane.setTranslateY(mouseEvent.getScreenY() + position.getY());
				}
				parent.updateCables();
			}
		});

	}

	public Source getSource() {
		return gen;
	}

	public void setSource(Source gen) {
		this.gen = gen;
	}

	public BorderPane getBorderPane() {
		return borderPane;
	}

	public GridPane getGridPane() {
		return gridPane;
	}

	public Source getGen() {
		return gen;
	}

	@Override
	public Circle getSourceCircle() {
		return sourceCircle;
	}

	public Circle getTargetCircle() {
		return targetCircle;
	}

	public Point2D getPosition() {
		return position;
	}

	public void setIsDragging(Boolean isDragging) {
		this.isDragging = isDragging;
	}

	public Boolean getIsDragging() {
		return isDragging;
	}

}
