package rsa;
import java.math.BigInteger;
import java.util.Base64;

public class RSAEncode {
	
	
	public static String RSAencode(String str,RSAKey Key,int encodeGroupLen) {
		
		byte[] strByte = str.getBytes();
		int encodeResNum = (strByte.length-1)/encodeGroupLen+1;
		int nBit = Key.n.bitLength();
		int nByte = nBit/8;
		byte[] encodeRes = new byte [encodeResNum*nByte];
		System.out.println("encodeResLength=" + encodeRes.length);
		for(int i=0,j=0;i<strByte.length;i+=encodeGroupLen,j++)
		{
			
			byte cuttedstrByte[] = new byte[encodeGroupLen];//new byte[Math.min(encodeGroupLen,strByte.length-i)];
			System.arraycopy(strByte, i, cuttedstrByte, 0, Math.min(encodeGroupLen,strByte.length-i));
			BigInteger cuttedNumber = new BigInteger(1, cuttedstrByte);
			System.out.println("第"+ (j+1) + "块数字为:" + cuttedNumber);
			//BigInteger pmodRes = cuttedNumber.modPow(Key.e , Key.p);
			//BigInteger qmodRes = cuttedNumber.modPow(Key.e , Key.q);
			//BigInteger pInversemodq = Key.p.modInverse(Key.q);
			//BigInteger qInversemodp = Key.q.modInverse(Key.p);
			//System.out.println(pInversemodq);
			//System.out.println(qInversemodp);
			//BigInteger nmodRes = pmodRes.multiply(qInversemodp).multiply(Key.q).add(qmodRes.multiply(pInversemodq).multiply(Key.p)).mod(Key.n);
			//System.out.print("pmodRes:");System.out.println(pmodRes);
			//System.out.print("qmodRes:");System.out.println(qmodRes);
			//System.out.print("nmodRes:");System.out.println(nmodRes);
			BigInteger nmodRes = cuttedNumber.modPow(Key.e , Key.n);
			System.out.print("nmodRes2:");System.out.println(nmodRes);
			byte partByte[] = nmodRes.toByteArray();
			System.out.println("分块字节长度为:" + partByte.length);
			if(partByte[0] == 0)
			{
				System.out.println("已删除最高位字节");
				System.arraycopy(partByte, 1, encodeRes , j*nByte+nByte-(partByte.length-1), partByte.length-1);
			}
			else
			{
				System.arraycopy(partByte, 0, encodeRes , j*nByte+nByte-partByte.length, partByte.length);
			}
			
		}
		String encodeResBase64 = Base64.getEncoder().encodeToString(encodeRes);
		System.out.println("加密结果为:(base64)");
		System.out.println(encodeResBase64);
		return encodeResBase64;
		
	}
	
}
