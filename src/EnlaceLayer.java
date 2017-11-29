
//import java.util.Random;

public class EnlaceLayer{

	private byte frame[];
//	private static Random r = new Random();
	
	EnlaceLayer(){ }
	
	public void receiveFrame(byte recv[]) {
		this.frame = recv.clone();
	}
	
	public byte[] sendFrame(String size) {
		this.frame = size.getBytes();
		
    	return this.frame;
    }
	
	public byte[] getFrame() {
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