import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class IOEx1 {
	public static void main(String[] args) {
		byte [] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte [] outSrc = null;
		
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = null;
		
		input = new ByteArrayInputStream(inSrc);	//ByteArrayInputStream 생성, inSrc를 사용하는, 
		output = new ByteArrayOutputStream();
		
		int data = 0;
		
		while( (data = input.read() ) != -1) {		//int형 data에  ByteArrayInputStream으로부터 데이터를 byte로 읽어들임.
			output.write(data);				//void write(int b)
		}
		
		outSrc = output.toByteArray();		//스트림의 내용을 byte배열로 반환.
		
		System.out.println("Input Source : " + Arrays.toString(inSrc));
		System.out.println("Output Source : " + Arrays.toString(outSrc));
	}
}
