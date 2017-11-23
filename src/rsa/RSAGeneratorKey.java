package rsa;
import java.math.*;
import java.util.*;
import rsa.RSAKey;

public class RSAGeneratorKey {
	
	static BigInteger p,q;
	static BigInteger n,e,d;
	
	public static void main(String[] args) {
		generatePQN2(128);
		System.out.print("p=");
		System.out.println(p);
		System.out.print("q=");
		System.out.println(q);
		
	}
	
	public static RSAKey generateKey(int nBit) {
		
		generatePQN2(nBit);
		System.out.print("p=");
		System.out.println(p);
		System.out.print("q=");
		System.out.println(q);
		
		
		BigInteger phi_n;
		phi_n = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		
		e = new BigInteger("65537");
		d = e.modInverse(phi_n);
		
		System.out.print("n=");
		System.out.println(n);
		System.out.print("n.Len=");
		System.out.println(n.bitLength());
		System.out.print("e=");
		System.out.println(e);
		System.out.print("d=");
		System.out.println(d);
		
		RSAKey res = new RSAKey(p,q,n,e,d);
		return res;
	}
	
	public static void generatePQN(int nBit) {
		Random rnd =new Random();
		while(true) {
			p = new BigInteger(nBit/2,10,rnd);
			q = new BigInteger(nBit/2,10,rnd);
			n = p.multiply(q);
			if(n.bitLength() != nBit) continue;
			//if( p.subtract(q).abs().bitCount() <= Math.max(nBit/2-100 ,nBit/3) ) continue;
			break;
		}
	}
	
	static int[] prime= { 2, 3, 5, 7, 11, 13, 17, 19, 
						  23, 29, 31, 37, 41, 43, 47,
						  53, 59, 61, 67, 71, 73, 79,
						  83, 89, 97};
	
	public static void generatePQN2(int nBit) {
		Random rnd =new Random();
		while(true) {
			while(true) {
				p = new BigInteger(nBit/2,0,rnd);
				System.out.print("p=");
				System.out.println(p);
				if(isPrimeProbable(p,20)) break;
			}
			
			while(true) {
				q = new BigInteger(nBit/2,0,rnd);
				System.out.print("q=");
				System.out.println(q);
				if(isPrimeProbable(q,20)) break;
			}
			
			n = p.multiply(q);
			if(n.bitLength() != nBit) continue;
			//if( p.subtract(q).abs().bitCount() <= Math.max(nBit/2-100 ,nBit/3) ) continue;
			break;
		}
	}
	//使用Miller-Rabin进行概率素数测试
	
	public static boolean isPrimeProbable(BigInteger p,int Times) {
		
		for(int i=0;i<prime.length;i++)
		{
			String primeStr=String.valueOf(prime[i]);
			if(p.mod(new BigInteger(primeStr)).equals(BigInteger.ZERO))
				return false;
		}
		
		BigInteger m = p.subtract(BigInteger.ONE);  
        BigInteger y = BigInteger.ZERO;  
        int k = 0;  //指数
        while((m.mod(BigInteger.valueOf(2))).equals(BigInteger.ZERO)){  
        	k++;  
            m = m.divide(BigInteger.valueOf(2));  
        }  
        
        Random d = new Random();  
        for(int i = 0 ; i < Times ; i++){  
        	int t = 0;  
            if(p.compareTo(BigInteger.valueOf(10000)) == 1){  
            	t = 10000;  
            }
            else{  
            	t = n.intValue() - 1;  
            }  
            int a = d.nextInt(t) + 1;//生成1~t的随机数  
            BigInteger x = (BigInteger.valueOf(a)).modPow(m, p);  
            for(int j=0;j<k;j++){  
            	y = (x.multiply(x)).mod(p);  
            	if(y.equals(BigInteger.ONE) && !(x.equals(BigInteger.ONE)) && !(x.equals(p.subtract(BigInteger.ONE)))) return false;  
            	x = y;
            }  
            if(!(y.equals(BigInteger.ONE))) return false;  
        }  
        return true;
	}

}
