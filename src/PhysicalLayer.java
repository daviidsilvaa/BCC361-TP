

public class PhysicalLayer{
	private String frame;

	public PhysicalLayer() { }

	public void receiveFrame(String f) {
		this.frame = new String(f);
	}

	public String sendFrame() {
		return this.frame;
	}

	public String getFrame() {
		return this.frame;
	}
	
	public void setFrame(String f) {
		this.frame = f;
	}
	
	public void convert5Bto4B(){
		String B5_temp = new String(); // Terá tamanho 5
		String B4 = new String("");

		for(int i = 0; i < (this.frame.length()/5); i++) {
			B5_temp = "";
			
			for(int j = 0; j < 5; j++) {
				B5_temp += String.valueOf(this.frame.charAt((i*5)+j));
			}
			B4 += returnB4(B5_temp);
		}
		
		this.setFrame(B4);
	}

	public void convert4Bto5B(){

		String B4_temp = new String(""); // Terá tamanho 4
		String B5 = new String("");

		for(int i = 0; i < (this.frame.length()/4); i++) {
			B4_temp = "";
			for(int j = 0; j < 4; j++) {
				B4_temp += String.valueOf(this.frame.charAt(i+j));
			}

			B5 = B5 + returnB5(B4_temp);
		}

		this.setFrame(B5);
	}

	public String returnB4(String B5) {
		String B4 = new String("");

		switch (B5){
			case "11110":
				B4 = "0000";
				break;
			case "01001":
				B4 = "0001";
				break;
			case "10100":
				B4 = "0010";
				break;
			case "10101":
				B4 = "0011";
				break;
			case "01010":
				B4 = "0100";
				break;
			case "01011":
				B4 = "0101";
				break;
			case "01110":
				B4 = "0110";
				break;
			case "01111":
				B4 = "0111";
				break;
			case "10010":
				B4 = "1000";
				break;
			case "10011":
				B4 = "1001";
				break;
			case "10110":
				B4 = "1010";
				break;
			case "10111":
				B4 = "1011";
				break;
			case "11010":
				B4 = "1100";
				break;
			case "11011":
				B4 = "1101";
				break;
			case "11100":
				B4 = "1110";
				break;
			case "11101":
				B4 = "1111";
				break;
		}

		return B4;
	}
	
	public String returnB5(String B4) {
		String B5 = new String("");

		switch (B4){
			case "0000":
				B5 = "11110";
				break;
			case "0001":
				B5 = "01001";
				break;
			case "0010":
				B5 = "10100";
				break;
			case "0011":
				B5 = "10101";
				break;
			case "0100":
				B5 = "01010";
				break;
			case "0101":
				B5 = "01011";
				break;
			case "0110":
				B5 = "01110";
				break;
			case "0111":
				B5 = "01111";
				break;
			case "1000":
				B5 = "10010";
				break;
			case "1001":
				B5 = "10011";
				break;
			case "1010":
				B5 = "10110";
				break;
			case "1011":
				B5 = "10111";
				break;
			case "1100":
				B5 = "11010";
				break;
			case "1101":
				B5 = "11011";
				break;
			case "1110":
				B5 = "11100";
				break;
			case "1111":
				B5 = "11101";
				break;
		}

		return B5;
	}
}
