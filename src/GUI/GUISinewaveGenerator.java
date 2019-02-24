package GUI;

import Driver.MyApp;
import Sources.SinewaveGenerator;
import Sources.Source;
import Widgets.AbstractWidget;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;

public class GUISinewaveGenerator extends AbstractWidget {

	private SinewaveGenerator gen;

	public GUISinewaveGenerator(MyApp parent) {
		super("Sine Wave", parent);

		this.gen = new SinewaveGenerator(300); // 300 is lowest frequency I'd like slider to provide

		Slider widgetSlider = new Slider();
		widgetSlider.setMin(0);
		widgetSlider.setMax(500); // 800 is max frequency
		widgetSlider.setShowTickMarks(true);
        widgetSlider.setBlockIncrement(0.1f);
		
		widgetSlider.valueProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				try {
					gen.setFrequency(((Number) newValue).intValue() + 300);
				} catch (Exception e) {
					e.getMessage();
				}
			}
		});

		getGridPane().add(widgetSlider, 10, 5, 20, 1);

	}

	public SinewaveGenerator getSinewaveGenerator() {
		return gen;
	}
	
	@Override
	public Source getSource() {
		return gen;
	}

}
