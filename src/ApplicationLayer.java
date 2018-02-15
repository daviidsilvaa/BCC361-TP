
import java.util.Random;

public class ApplicationLayer{
	private String frame;
    private String client_name;

	public ApplicationLayer() { }

	public void down() {
        String str_bit, str_aux;
    	int index;

        str_aux = str_bit = new String("");

        this.client_name = new String("");
        index = 0;

        // salva o nome do Client que está enviando
        while(this.frame.charAt(index) != ' '){
            this.client_name += this.frame.charAt(index);
            index++;
        }

        // converte cada Char para seu respectivo valor em Bits
        for(int i = index+1; i < this.frame.length(); i++){
            str_aux += Integer.toBinaryString(this.frame.charAt(i));
        }
        // System.out.println("str: " + str + "\t" + str.length());

        // corrige a ordem dos bits dentro da string de bits
        for(int i = 0; i < str_aux.length(); i++){
            str_bit += str_aux.charAt(str_aux.length() - 1 - i);
        }

        this.frame = str_bit;
	}

	public void up() {
        String str_aux;

        // converte cada sequencia de Bit para seu valor em Char
        str_aux = new String("");
        for(int i = 0; i < this.frame.length(); i += 7){
            int potencia = 0, asc_val = 0;

            for(int j = 0; j < 7; j++){
                if(this.frame.charAt(i + j) == '1'){
                    asc_val += (int)Math.pow(2, potencia);
                }
                potencia += 1;
            }
            str_aux += (char)asc_val;
        }

        // corrige a ordem da cadeia de Char que será impressa
        this.frame = new String(this.client_name + " ");
        for(int i = 0; i < str_aux.length(); i++)
            this.frame += str_aux.charAt(str_aux.length() - 1 - i);

	}

	public String getFrame() {
		return this.frame;
	}
	public void setFrame(String frame) {
		this.frame = new String(frame);
	}

    public String getClientName(){
        return this.client_name;
    }
    public void setClientName(String client_name) {
        this.client_name = client_name;
    }
}
