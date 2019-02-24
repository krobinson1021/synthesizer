package Mixers;
import java.util.ArrayList;

import Driver.AudioClip;
import Sources.Source;

public class Synthesizer implements Mixer {

	Source input;

	ArrayList<Source> allInputs;

	int scale;

	@Override
	public AudioClip generate() {
		AudioClip result = new AudioClip();

		for (Source s : allInputs) {
			AudioClip ac = s.generate();
			for (int i = 0; i < result.getSampleRate(); i++) {
				int temp = ac.getSample(i) + result.getSample(i);
				result.setSample(i, temp);
			}
		}
		return result;
	}

	@Override
	public void addInput(Source pInput) {
		allInputs.add(pInput);
	}

}
