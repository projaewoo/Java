import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class IOEx4 {
	public static void main(String[] args) {
		byte [] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte [] outSrc = null;
		byte [] temp = new byte[4];
		
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		try {
			while(input.available() > 0) {
				int leng = input.read(temp);		//temp에 읽어옴. 읽어온 바이트 수를 leng에 저장.
				output.write(temp, 0, leng);		//temp에서 index[0]에서 leng만큼 바이트 내보냄.
				
				outSrc = output.toByteArray();
			}
		}catch(IOException e) {}
		
		System.out.println("Input Source : " + Arrays.toString(inSrc));
		System.out.println("temp             : " + Arrays.toString(temp));
		System.out.println("Output Source : " + Arrays.toString(outSrc));
				
	}
}
