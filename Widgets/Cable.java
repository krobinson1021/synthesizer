package Widgets;

import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Cable {

	private Line line;

	private SourceWidget sourceWidget;

	private TargetWidget targetWidget;
	
	public Cable(SourceWidget sw, TargetWidget tw) {
		this.sourceWidget = sw;
		this.targetWidget = tw;
		
		Circle sourceCircle = sw.getSourceCircle();
		Point2D sourceCircleLocal = new Point2D(sourceCircle.getCenterX(), sourceCircle.getCenterY());
		Point2D sourceCircleCenterScene = sourceCircle.localToScene(sourceCircleLocal);
		
		Circle targetCircle = tw.getTargetCircle();
		Point2D targetCircleCenterLocal = new Point2D(targetCircle.getCenterX(), targetCircle.getCenterY());
		Point2D targetCircleCenterScene = targetCircle.localToScene(targetCircleCenterLocal);
		
		line = new Line(sourceCircleCenterScene.getX(), sourceCircleCenterScene.getY(), 
				targetCircleCenterScene.getX(), targetCircleCenterScene.getY());
		
		targetWidget.connectInput(sourceWidget.getSource()); // connect audio
		updateCable();
	}
	
	public void	updateCable() {

		Circle sourceCircle = sourceWidget.getSourceCircle();
		Point2D sourceCircleLocal = new Point2D(sourceCircle.getCenterX(), sourceCircle.getCenterY());
		Point2D sourceCircleCenterScene = sourceCircle.localToScene(sourceCircleLocal);
		
		Circle targetCircle = targetWidget.getTargetCircle();
		Point2D targetCircleCenterLocal = new Point2D(targetCircle.getCenterX(), targetCircle.getCenterY());
		Point2D targetCircleCenterScene = targetCircle.localToScene(targetCircleCenterLocal);
		
		line.setStartX(sourceCircleCenterScene.getX());
		line.setStartY(sourceCircleCenterScene.getY());
		line.setEndX(targetCircleCenterScene.getX());
		line.setEndY(targetCircleCenterScene.getY());
		
	}

	public Line getCable() {
		return line;
	}

	public void setCable(Line cable) {
		this.line = cable;
	}

	public SourceWidget getSourceWidget() {
		return sourceWidget;
	}

	public void setSourceWidget(SourceWidget sourceWidget) {
		this.sourceWidget = sourceWidget;
	}

	public TargetWidget getTargetWidget() {
		return targetWidget;
	}

	public void setTargetWidget(TargetWidget targetWidget) {
		this.targetWidget = targetWidget;
	}

}
