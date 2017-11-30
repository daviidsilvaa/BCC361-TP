
//import java.util.Random;

public class EnlaceLayer{

	private String frame;

	EnlaceLayer(){ }

	public void receiveFrame(String f) {
		this.frame = new String(f);
	}
	
	public String sendFrame() {
    	return this.frame;
    }
	
	public String getFrame() {
		return this.frame;
	}
}

//public static byte[] sendFrame(String size) {
//	frame = size.getBytes();
//
////	data = new byte[Integer.parseInt(size)*4];
//	System.out.println("frame.size: " + frame.length);
//
//	for(int i = 0; i < frame.length; i++) {
//		frame[i] = (byte)r.nextInt(2);
//	}
//
//	return frame;
//}
