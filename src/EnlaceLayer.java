
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
