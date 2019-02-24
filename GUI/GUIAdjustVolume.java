package GUI;
import Driver.MyApp;
import Filters.AdjustVolume;
import Sources.Source;
import Widgets.AbstractWidget;
import Widgets.TargetWidget;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;

public class GUIAdjustVolume extends AbstractWidget implements TargetWidget {
		
	Source input;
	
	AdjustVolume adjustment;
	
	public GUIAdjustVolume(MyApp parent) {
		super("Volume Adjust", parent);
		
		adjustment = new AdjustVolume(0);
		
		Slider widgetSlider = new Slider();
		widgetSlider.setMin(0);
		widgetSlider.setMax(100);
		widgetSlider.setShowTickMarks(true);
		widgetSlider.setShowTickLabels(true);
        widgetSlider.setBlockIncrement(0.1f);
	    
	    widgetSlider.valueProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				try {
					adjustment.setScale(((Number) newValue).intValue());

				} catch (Exception e) {
					e.getMessage();
				}
			}
		});
		getGridPane().add(widgetSlider, 10, 5, 20, 1);
	}

	@Override
	public void connectInput(Source s) {
		adjustment.connectInput(s);
	}
	
	@Override
	public Source getSource() {
		return adjustment;
	}

}
