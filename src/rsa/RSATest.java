package rsa;
//import rsa.*;
import java.io.*;

public class RSATest {
	
	static RSAKey Key;
	static byte strByte[];
	static int nBit = 1024;
	static int encodeGroupLen;
	static String encodeResBase64;
	static String decodeRes;
	public static void main(String[] args) throws IOException {
		

		
		Key = RSAGeneratorKey.generateKey(nBit);
		System.out.println("please input the string which you want to encode");
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));	
		String str=br.readLine();
		System.out.println(str);
		System.out.println("str.length:"+ str.length());
		//strByte = str.getBytes();//"utf-8");
		encodeGroupLen = nBit/(2*8);
		System.out.println("·Ö¿é×Ö·û´®³¤¶È:" + encodeGroupLen);
		encodeResBase64 = RSAEncode.RSAencode(str,Key,encodeGroupLen);
		decodeRes = RSADecode.RSAdecode(encodeResBase64,Key,encodeGroupLen);
		
		System.out.println("decodeRes:");System.out.println(decodeRes);
		System.out.println("decodeRes.length:");System.out.println(decodeRes.length());
	}
}
