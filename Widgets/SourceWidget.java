package Widgets;

import Sources.Source;
import javafx.scene.shape.Circle;

public interface SourceWidget {

	Circle getSourceCircle();

	Source getSource();

	public void setIsDragging(Boolean isDragging);

	public Boolean getIsDragging();

}
