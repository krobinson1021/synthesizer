package Widgets;

import Driver.MyApp;
import Sources.Source;
import javafx.scene.shape.Circle;

public class Speaker implements TargetWidget {
	
	Source s;
	
	Circle circle;
	
	public Speaker(MyApp parent) {
		Circle speakerCircle = new Circle();
		speakerCircle.setRadius(30.0f);
		speakerCircle.setFill(javafx.scene.paint.Color.BLUE);
		speakerCircle.setCenterX(540);
		speakerCircle.setCenterY(610);
		this.circle = speakerCircle;
	}
	
	@Override
	public void connectInput(Source s) {
		System.out.println("Source: " + s);
		this.s = s;		
	}
	
	public Source getSource() {
		return s;
	}

	@Override
	public Circle getTargetCircle() {
		return this.circle;
	}
	
}