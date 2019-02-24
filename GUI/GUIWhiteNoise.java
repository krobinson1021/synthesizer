package GUI;

import Driver.MyApp;
import Sources.Source;
import Sources.SquareWave;
import Sources.WhiteNoise;
import Widgets.AbstractWidget;
import Widgets.TargetWidget;

public class GUIWhiteNoise extends AbstractWidget {

	Source input;
	
	private WhiteNoise gen;

	int scale;
	
	public GUIWhiteNoise(MyApp parent) {
		super("White Noise", parent);
		
		this.gen = new WhiteNoise();
	}
	
	@Override
	public Source getSource() {
		return gen;
	}


}
