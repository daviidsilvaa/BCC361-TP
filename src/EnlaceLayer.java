
public class EnlaceLayer{

	private String frame;

	EnlaceLayer(){ }

	public void receiveFrame(String f) {
		this.frame = new String(f);
	}

	public String sendFrame() {
    	return this.frame;
    }

	public String getFrame() {
		return this.frame;
	}
	public void setFrame(String frame) {
		this.frame = frame;
	}
	
	// Detecção De Erro
	
    public void detectParityBit(String frame) {	//	bits de paridade
        int count = 0;
        
        for(int i = 0; i < frame.length(); i++){
           if (frame.charAt(i) == '1'){
            	count++;
            }
        }
        if(count % 2 == 0) {
        	this.setFrame(frame + "0");
        } else {
        	this.setFrame(frame + "1");
        }
    }
    
    static void detectErro(String frame){
        int count = 0;
        
        for(int i = 0; i < frame.length(); i++){
            if(frame.charAt(i) == '1') {
                count++;
            }
        }
        if(count % 2 == 0){
        	System.out.println("Paridade: SEM ERRO");
        } else {
            System.out.println("Paridade: ERRO");
        }
    }
    
    // Checksum
    public static String[] frameBreaker(String frame) {
    	int flag = 0;
    	int seg_size;
        

        if (frame.length() % 5 == 0) {
            flag = 1;
            seg_size = frame.length()/5;
        } else {
            flag = 0;
            seg_size = frame.length()/4;
        }
        String[] segments = new String[seg_size];

        for (int i = 0, j = 0; i < frame.length(); i += (4 + flag), j++) {
            frame = new String("");
            for (int k = i; k < i + (4 + flag) && k < frame.length(); k++) {
                frame = frame + frame.charAt(k);

            }

            segments[j] = frame;
        }

        return segments;
    }
    
    public String solveChecksum(String frame) {
        int seg_size;
        int flag = 0;

        if (frame.length() % 5 == 0) {
            flag = 1;
            seg_size = frame.length() / 5;
        } else {
            flag = 0;
            seg_size = frame.length() / 4;
        }
        String[] segments = new String[seg_size];

        for (int i = 0, j = 0; i < frame.length(); i += (4 + flag), j++) {
            String frame1 = "";
            for (int k = i; k < i + (4 + flag) && k < frame.length(); k++) {
                frame1 = frame1 + frame.charAt(k);

            }

            segments[j] = frame1;
        }

        int sum = 0;
        for (int i = 0; i < seg_size; i++) {
            sum += (Integer.parseInt(segments[i], 2));
        }

        String checksum = Integer.toBinaryString(sum);
        String inverted = "";
        
        for (int i = 0; i < checksum.length(); i++) {
            if (checksum.charAt(i) == '0') {
                inverted += '1';
            } else {
                inverted += '0';
            }
        }
        for (int i = 0; i < inverted.length(); i++) {
            if (inverted.charAt(i) != '0') {
                return null;
            }
        }
        String ret = "";
        for (int i = 0; i < seg_size - 1; i++) {
            ret += segments[i];
        }
        
        return ret;
    }
    
    public String createChecksum(String[] segments) {

        int sum = 0;
        for (int i = 0; i < segments.length; i++) {
            sum += (Integer.parseInt(segments[i], 2));
        }

        String checksum = Integer.toBinaryString(sum);
        String f = "";
        String invertedChecksum = "";
        
        for (int k = 0; k < checksum.length(); k++) {
            if (checksum.charAt(k) == '0') {
                invertedChecksum += '1';
            } else {
                invertedChecksum += '0';
            }
        }
        if (invertedChecksum.length() > segments[0].length()) {
            invertedChecksum = invertedChecksum.substring(1, invertedChecksum.length());
        }
        for (int i = 0; i < segments.length; i++) {
            f += segments[i];
        }

        for (int k = 0; k < invertedChecksum.length(); k++) {
            f += invertedChecksum.charAt(k);
        }

        return f;

    }
}


