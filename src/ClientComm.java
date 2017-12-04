import java.io.InputStream;
import java.util.Scanner;

public class ClientComm implements Runnable{
	private InputStream server;

	public ClientComm(InputStream server) {
		this.server = server;
	}

	public void run() {
		Scanner scanner = new Scanner(this.server);

		while (scanner.hasNextLine()) {
			PhysicalLayer physical = new PhysicalLayer();
			EnlaceLayer enlace = new EnlaceLayer();

			// Camada de Enlace envia frame de bytes para a Camada Fisica
			// enlace.sendFrame(str);
			// Camada Fisica recebe frame de bytes da Camada de Enlace

			enlace.receiveFrame(scanner.nextLine());
			physical.receiveFrame(enlace.sendFrame());

			// Camada Fisica envia frame de bytes para o Server
			System.out.println(new String(physical.getFrame()));
		}
		scanner.close();
	}
}
