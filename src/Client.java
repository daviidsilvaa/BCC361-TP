import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client{
	private String host;
	private int port;
	   
	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}
	   
	public void execute() throws UnknownHostException, IOException {
		Socket client = new Socket(this.host, this.port);

		// Thread para enviar mensagens do servidor
		new Thread(new ClientComm(client.getInputStream())).start();
		
		// Recebe frame do teclado e envia para o servidor
		Scanner keyboard = new Scanner(System.in);
		PrintStream frame = new PrintStream(client.getOutputStream());

		while (keyboard.hasNextLine()) {
			frame.println(keyboard.nextLine());
		}

		frame.close();
		keyboard.close();
		client.close();    
	}
}
