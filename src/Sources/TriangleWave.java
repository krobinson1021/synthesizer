package Sources;

import Driver.AudioClip;

public class TriangleWave implements Source {

	AudioClip ac;
	
	int frequency;

	public TriangleWave(int pFrequency) {
		frequency = pFrequency;
		ac = new AudioClip();
		ac.setFrequency(pFrequency);
	}

	@Override 
	public AudioClip generate() {
		
		for (int i = 0; i < ac.getSampleCount(); i++) {
			int triangle = (int) ((2 * 32767 / Math.PI) * Math.asin(Math.sin((2 * Math.PI * frequency * i / ac.getSampleRate()))));
			ac.setSample(i, triangle);
		}
		return ac;
		
	}

	public void setFrequency(int pFrequency) {
		frequency = pFrequency;
		ac.setFrequency(pFrequency);
	}
}