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
//		String str;

		while (scanner.hasNextLine()) {
			String str = new String(scanner.nextLine());
			
			PhysicalLayer physical = new PhysicalLayer();
			EnlaceLayer enlace = new EnlaceLayer();
			
			// Camada Fisica envia framde de bytes para a Camada de Enlace
			// physical.sendFrame();
			// Camada de Enlace recebe frade de bytes da Camada Fisica
			physical.setFrame(str);
			enlace.receiveFrame(physical.sendFrame());
			
			System.out.println(new String(enlace.getFrame()));

			server.sendMessage(str);
		}
		scanner.close();
	}
}
