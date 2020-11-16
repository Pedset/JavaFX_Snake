package Controller;

public class VolumeCtrl {

	private static byte volume = 50;

	public static byte getVolume() {
		return volume;
	}

	public static void setVolume(byte v) {
		volume = v;
	}
}
