package Widgets;

import Sources.Source;
import javafx.scene.shape.Circle;

public interface TargetWidget {

	Circle getTargetCircle();
	
	void connectInput(Source s);
	
}
