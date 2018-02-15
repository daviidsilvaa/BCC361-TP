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
		// Quando chega uma mensagem, distribui para todos
		Scanner scanner = new Scanner(this.client);
		String str, str_bit, str_aux, str_client;
		int index;

		while (scanner.hasNextLine()) {
			PhysicalLayer physical = new PhysicalLayer();
			EnlaceLayer enlace = new EnlaceLayer();
			ApplicationLayer application = new ApplicationLayer();

			application.setFrame(scanner.nextLine()); // Camada de Aplicacao recebe mensagem do Cliente
			application.down(); // Camada de Aplicacao aplica operaceos ate enviar mensagem para a Camada de Enlace
			enlace.setFrame(application.getFrame()); // Camada de Enlace recebe mensagem da Camada de Aplicacao
			physical.setFrame(enlace.sendFrame()); // Camada Fisica recebe mensagem da Camada de Enlace
			physical.convert4Bto5B();	// camada fisica aplica 4B/5B

			System.out.println("Frame received:\t" + physical.getFrame());

			physical.setFrame(physical.generateError(physical.getFrame(), 0)); // gera error na sequencia de bits do frame recebido pela Camada Física

			System.out.println("Frame sended:\t" + physical.getFrame());

			physical.convert5Bto4B(); // Camada Fisica aplica 5B/4B
			enlace.setFrame(physical.getFrame()); // Camada de Enlace recebe mensagem da Camada Fisica
			application.setFrame(enlace.getFrame()); // Camada de Aplicacao recebe mensagem da Camada de Enlace
			application.up(); // Camada de Aplicacao aplica operaceos ate enviar mensagem para o Cliente

			server.sendMessage(application.getFrame());	// Server envia mensagem para Clientes
		}
		scanner.close();
	}
}
