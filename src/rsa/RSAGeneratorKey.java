package rsa;
import java.math.*;
import java.util.*;
import rsa.RSAKey;

public class RSAGeneratorKey {
	
	//static int nBit = 64;
	//生成p&q两个大质数
	int nLength = (int)(128*Math.log(2)/Math.log(10))+1;
	static BigInteger p,q;
	static BigInteger n,e,d;
	
	public static RSAKey generateKey(int nBit) {
		
		generatePQN(nBit);
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
		/*
		BigInteger tp;
		tp =  new BigInteger(nBit-1, rnd);
		tp = tp.multiply(new BigInteger("2"));
		tp = tp.add(BigInteger.ONE);
		while(!isPrimeProbable(tp)) {
			tp =  new BigInteger(nBit, rnd);
		}
		System.out.println();
		System.out.println(tp);
		*/
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
	
	
	//使用Miller-Rabin进行概率素数测试
	/*
	public static boolean isPrimeProbable(BigInteger p) {
		BigInteger tp;
		int k;
		k=0;
		tp = p.subtract(BigInteger.ONE);
		while (tp.mod(new BigInteger("2"))==new BigInteger("0")) {
			tp = tp.divide(new BigInteger("2"));
			k++;
		}
		BigInteger q;
		q = tp;
		Random rnd =new Random();
		int t = 20;//进行素数测试的次数
		while(t>0) {
			BigInteger a;
			do {
				a =  new BigInteger(nBit-1, rnd);
			}
			while(a.compareTo(p)!=-1);
			if(!isPrimeTest(p,a,q,k)) {
				return false;
			}
		}
		
		return true;
		
	}
	
	//Miller-Rabin 测试；	
	public static boolean isPrimeTest(BigInteger n, BigInteger a,BigInteger q,int k) {
		BigInteger tmp;
		tmp = a.modPow(q,n);
		if(tmp.compareTo(BigInteger.ONE)==0){
			return true;
		}
		//BigInteger tmp = new BigInteger("1");
		BigInteger n_1 =  n.subtract(BigInteger.ONE);
		for(int j=0;j<k;j++) {
			tmp = tmp.modPow(new BigInteger("2"), n);
			if(tmp.compareTo(n_1)==0){
				return true;
			}
		}
		return false;
	}
	*/
	

}
