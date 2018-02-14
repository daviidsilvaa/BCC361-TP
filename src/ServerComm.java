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
		String str, str_bit, str_aux;

		while (scanner.hasNextLine()) {
			str = new String(scanner.nextLine());
			str_aux = str_bit = new String("");

			// converte cada Char para seu respectivo valor em Bits
			for(int i = 0; i < str.length(); i++){
				str_aux += Integer.toBinaryString(str.charAt(i));
			}
			// corrige a ordem dos bits dentro da string de bits
			for(int i = 0; i < str_aux.length(); i++){
				str_bit += str_aux.charAt(str_aux.length() - 1 - i);
			}

			PhysicalLayer physical = new PhysicalLayer();
			EnlaceLayer enlace = new EnlaceLayer();


			// Camada Fisica envia frame de bytes para a Camada de Enlace
			// physical.sendFrame();
			// Camada de Enlace recebe frade de bytes da Camada Fisica
			physical.setFrame(str_bit);
			physical.convert4Bto5B();
			//128.0.0.1
			System.out.println("Frame received:\t" + physical.getFrame());

			// gera error na sequencia de bits do frame recebido pela Camada Física
			physical.setFrame(physical.generateError(physical.getFrame(), 0));

			System.out.println("Frame sended:\t" + physical.getFrame());

			physical.convert5Bto4B();

			enlace.receiveFrame(physical.sendFrame());
			
			// converte cada sequencia de Bit para seu valor em Char
			str = new String("");
			for(int i = 0; i < enlace.getFrame().length(); i += 7){
				int potencia = 0, asc_val = 0;
				
				for(int j = 0; j < 7; j++){
					if(str_bit.charAt(i + j) == '1'){
						asc_val += (int)Math.pow(2, potencia);
					}
					potencia += 1;
				}
				str += (char)asc_val;
			}
			
			// corrige a ordem da cadeia de Char que será impressa
			str_aux = str;
			str = new String("");
			for(int i = 0; i < str_aux.length(); i++)
				str += str_aux.charAt(str_aux.length() - 1 - i);
			
			//server.sendMessage(enlace.getFrame());
			server.sendMessage(str);
		}
		scanner.close();
	}
}
