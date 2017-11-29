import java.io.InputStream;
import java.util.Scanner;

public class ServerComm implements Runnable{
	private InputStream client;
	private Server server;

	public ServerComm(InputStream client, Server server) {
		this.client = client;
		this.server = server;
	}

	public void run() {
		// Quando chega um frame, distribui para todos
		Scanner scanner = new Scanner(this.client);
		
		while (scanner.hasNextLine()) {
//			PhysicalLayer p = new PhysicalLayer();
//			EnlaceLayer e = new EnlaceLayer();
//			
//			p.receiveFrame(e.sendFrame(scanner.nextLine()));
//			
//			server.sendMessage(new String(p.getFrame()));
//			
			server.sendMessage(scanner.nextLine());
		}
		scanner.close();
	}
}
