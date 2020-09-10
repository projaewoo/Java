import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class IOEx3 {
	
	public static void main(String[] args) {
		byte [] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte [] outSrc = null;
		byte [] temp = new byte[4];
		
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);		//inScr를 통해 ByteArrayInputStream선언 및 생성.
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		System.out.println("Input Source : " + Arrays.toString(inSrc));
		
		try {
			while(input.available() >0) {					//ByteArrayInputStream으로부터 읽을 수 있는 바이트의 숫자를 정수형으로 반환.
				input.read(temp);			//InputStream으로부터 읽어와서 temp에 저장.
				output.write(temp); 		//읽어온 데이터를 temp에 씀.
				System.out.println("temp : " + Arrays.toString(temp));
				
				outSrc = output.toByteArray();
				printArrays(temp, outSrc);
				System.out.println();
			}
		}catch(IOException e) {}	
		
	}
	
	static void printArrays(byte [] temp, byte [] outSrc) {
		System.out.println("temp              : " + Arrays.toString(temp));
		System.out.println("Output Source : " + Arrays.toString(outSrc));
	}
}
