import java.io.IOException;

public class MainServer {
	public static void main(String[] args) throws IOException {
		// Inicializa o servidor
		new Server(12345).execute();
	}
}
