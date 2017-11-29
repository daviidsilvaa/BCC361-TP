import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	private int port;
	private List<PrintStream> clients;
	private ServerSocket servidor;

	public Server(int port) {
		this.port = port;
		this.clients = new ArrayList<PrintStream>();
	}

	public void execute () throws IOException {
		servidor = new ServerSocket(this.port);
		System.out.println("Porta " + this.port + " aberta!");

		while(true){
			// Aceita um cliente
			Socket client = servidor.accept();
			System.out.println("Nova conexão com o cliente " + client.getInetAddress().getHostAddress());

			// Adiciona saida do cliente a lista
//			PrintStream print_stream = new PrintStream(client.getOutputStream());
			this.clients.add(new PrintStream(client.getOutputStream()));

			// Cria comunicador de cliente numa nova thread
			new Thread(new ServerComm(client.getInputStream(), this)).start();
		}
	}
	
	public void sendMessage(String frame) {
		System.out.println(frame);
		
		// Envia mensagem para todo mundo
		for (PrintStream client : this.clients) {
			client.println(frame);
		}
	}
}
