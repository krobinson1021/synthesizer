package Sources;

import Driver.AudioClip;

public class SawtoothWave implements Source {

	AudioClip ac;
	
	int frequency;

	public SawtoothWave(int pFrequency) {
		frequency = pFrequency;
		ac = new AudioClip();
		ac.setFrequency(pFrequency);
	}

	@Override
	public AudioClip generate() {
		
		for (int i = 0; i < ac.getSampleCount(); i++) {
			int saw = (int) (((-2 * 32767) / Math.PI) * Math.atan(1/Math.tan((2 * Math.PI * frequency * i / ac.getSampleRate()))));
			ac.setSample(i, saw);
		}
		return ac;
		
	}

	public void setFrequency(int pFrequency) {
		frequency = pFrequency;
		ac.setFrequency(pFrequency);
	}
}
