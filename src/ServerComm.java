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
		String str;

		while (scanner.hasNextLine()) {
			str = new String(scanner.nextLine());

			PhysicalLayer physical = new PhysicalLayer();
			EnlaceLayer enlace = new EnlaceLayer();

			// Camada Fisica envia framde de bytes para a Camada de Enlace
			// physical.sendFrame();
			// Camada de Enlace recebe frade de bytes da Camada Fisica
			physical.setFrame(str);
			physical.convert5Bto4B();

			System.out.println("Frame received:\t" + physical.getFrame());

			// Gera error na sequencia de bits do frame recebido pela Camada Física
			physical.setFrame(physical.generateError(physical.getFrame(), 20));

			System.out.println("Frame sended:\t" + physical.getFrame());

			physical.convert4Bto5B();

			enlace.receiveFrame(physical.sendFrame());
			server.sendMessage(enlace.getFrame());
		}
		scanner.close();
	}
}
