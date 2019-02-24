package Driver;

public class AudioClip {

	private int duration; // unit is seconds

	private int sampleRate;

	private byte[] byteArray; // byte array is given to the sound card

	private int frequency;

	public AudioClip() {
		duration = 1; // 1 second
		sampleRate = 44100;

		byte[] data = new byte[sampleRate * 2];
		byteArray = data;
	}

	public AudioClip(AudioClip pAc) { // copy constructor (helpful for AdjustVolume)
		duration = pAc.duration;
		sampleRate = pAc.sampleRate;

		byte[] new_data = new byte[sampleRate * 2];
		for (int i = 0; i < (sampleRate * 2); i++) {
			new_data[i] = pAc.byteArray[i];
		}
		byteArray = new_data;
	}

	public int getSample(int index) {
		int lsb = byteArray[2 * index];
		int msb = byteArray[(2 * index) + 1];

		lsb &= 0xFF;
		msb &= 0xFF;

		int result = 0;

		result |= lsb;
		msb <<= 8;
		result |= msb;

		if ((result >> 15) == 1) { // if negative, 16th bit is 1
			result |= (0xFFFF0000);
		}
		return result;
	}

	public void setSample(int index, int newValue) {
		byte lsb = byteArray[2 * index];
		byte msb = byteArray[(2 * index) + 1];

		lsb = (byte) (newValue & 0xFF);
		msb = (byte) ((newValue >> 8) & 0xFF);

		byteArray[2 * index] = lsb;
		byteArray[(2 * index) + 1] = msb;
	}

	public int getSampleCount() {
		return sampleRate * duration;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int pDuration) {
		duration = pDuration;
	}

	public int getSampleRate() {
		return sampleRate;
	}

	public void setSampleRate(int pSampleRate) {
		sampleRate = pSampleRate;
	}

	public byte[] getByteArray() {
		return byteArray;
	}

	public void setByteArray(byte[] pByteArray) {
		byteArray = pByteArray;
	}

	public int getFrequency() {
		return frequency;
	}
	
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
}
