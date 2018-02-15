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

			// envia frame de bytes para o Server
			System.out.println(new String(scanner.nextLine()));
		}
		scanner.close();
	}
}
