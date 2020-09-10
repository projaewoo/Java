import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class IOEx2 {
	public static void main(String[] args) {
		byte [] inScr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte [] outScr = null;
		byte [] temp = new byte[10];
		
		ByteArrayInputStream input = null;				//ByteArrayIntputStrem형 input 선언.
		ByteArrayOutputStream output = null;    		//ByteArrayOutputStream형 output 선언.
		
		input = new ByteArrayInputStream(inScr);		//inScr를 이용해서 ByteArrayInputStream생성.
		output = new ByteArrayOutputStream();			//ByteArrayOutputStream 생성.
		
		input.read(temp, 0, temp.length);			//temp배열에서 temp[0] ~ temp길이만큼 읽어들임.
		output.write(temp, 6, 4);						//temp[7], temp[8], temp[9], temp[10]의 데이터를 읽어옴.
		
		outScr = output.toByteArray();	//temp로부터 읽어들인 데이터를 ByteArray로 변환. ->outScr에 대입.
		
		System.out.println("Input Source : " + Arrays.toString(inScr));
		System.out.println("temp             : " + Arrays.toString(temp));
		System.out.println("Output Source : " + Arrays.toString(outScr));
	}
}
