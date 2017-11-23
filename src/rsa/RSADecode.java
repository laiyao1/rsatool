package rsa;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Base64;

public class RSADecode {

	public static String RSAdecode(String encodeResBase64,RSAKey Key,int encodeGroupLen) throws UnsupportedEncodingException {
		
		byte[] encodeRes = Base64.getDecoder().decode(encodeResBase64);  
		int nBit = Key.n.bitLength();
		int nByte = nBit/8;
		System.out.print("nByte:");System.out.println(nByte);
		System.out.print("encodeRes.length:");System.out.println(encodeRes.length);
		byte[] strByte = new byte[encodeRes.length/nByte*encodeGroupLen*2];
		for(int i=0,j=0;i<encodeRes.length;i+=nByte,j++) {
			byte partByte[] = new byte[nByte];
			System.arraycopy(encodeRes,i,partByte,0,nByte);
			BigInteger nmodRes = new BigInteger(1,partByte);
			System.out.print("nmodRes:");System.out.println(nmodRes);
			BigInteger cuttedNumber = nmodRes.modPow(Key.d, Key.n);
			System.out.print("cuttedNumber:");System.out.println(cuttedNumber);
			byte cuttedstrByte[] = cuttedNumber.toByteArray();
			System.out.print("cuttedstrByteLen:");System.out.println(cuttedstrByte.length);
			if(cuttedstrByte[0] == 0)
			{
				System.out.println("已删除最高位字节");
				System.arraycopy(cuttedstrByte,1,strByte,j*encodeGroupLen+encodeGroupLen-(cuttedstrByte.length-1),cuttedstrByte.length-1);
			}
			else
			{
				System.arraycopy(cuttedstrByte,0,strByte,j*encodeGroupLen+encodeGroupLen-cuttedstrByte.length,cuttedstrByte.length);
			}
			
		}
		String decodeRes = new String(strByte);//,"utf-8");
		decodeRes = decodeRes.trim();
		return decodeRes;
		
	}
}
