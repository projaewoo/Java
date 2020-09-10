import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamEx1 {
	public static void main(String[] args) {
		try {
			FileOutputStream fos = new FileOutputStream("123.txt");
			BufferedOutputStream bos = new BufferedOutputStream(fos, 5);		//BufferedOutputStream의 버퍼 크기 : 5
			for(int i = '1'; i <= '9'; i++) {			//파일 123.txt에 1부터 9까지 출력.
				bos.write(i);
//				bos.flush();       //flush() 없으면 결과값 : 12345.		//flush() 있으면 결과값 : 123456789.
			}
			fos.close();
		}catch(IOException e) {
			System.out.println(e);
		}
	}
}
