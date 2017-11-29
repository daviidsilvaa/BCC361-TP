
import java.io.IOException;
import java.net.UnknownHostException;

public class MainClient {
	public static void main(String[] args) 
	throws UnknownHostException, IOException{
		// Dispara o cliente
		new Client("127.0.0.1", 12345).execute();
	}
}
