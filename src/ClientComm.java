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
			
			String str = scanner.nextLine();
			
//			// Caso tenha tamanho 5, o frame deve ser enviado para a Camada Fisica
//			if((str.length()%5) == 0) {
//				
//			}
//			// Caso nao tenha, o frame esta chegando da Camada Fisica e deve ser impressa no client
//			else {
//				PhysicalLayer p = new PhysicalLayer();
//				EnlaceLayer e = new EnlaceLayer();
//				
//				p.receiveFrame(str.getBytes());
//				
//				e.receiveFrame(p.sendFrame());
//				
//				System.out.println(new String(e.getFrame()));
//			}
			
			System.out.println(str);
		}
		scanner.close();
	}
}
