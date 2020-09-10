import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream(args[0]);
			FileOutputStream fos = new FileOutputStream(args[1]);
			
			int data = 0;
			while((data = fis.read()) != -1) {		//FileIntputStream으로부터 데이터를 바이트로 읽어옴.	/1Byte = 8bit = 숫자 표현 범위 : (-128 ~ 127) = (0 ~ 255) //데이터 범위 : -1~255 (-1 : 입력값이 더 이상 없을 때) // 따라서 int형 값(-2,147,483,648 ~ 2,147,483,647)으로 반환.
				fos.write(data);
			}
		}catch (IOException ex) {
			System.out.println(ex);
		}
	}
}