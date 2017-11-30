
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MainClient {
	public static void main(String[] args) 
	throws UnknownHostException, IOException{
		System.out.print("Insert your IP address: ");
		Scanner scanner = new Scanner(System.in);
		
		// Dispara o cliente
		new Client(scanner.nextLine(), 12345).execute();
		
		scanner.close();
	}
}
