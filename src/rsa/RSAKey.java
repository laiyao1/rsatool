package rsa;

import java.math.BigInteger;

public class RSAKey {
		
	BigInteger p,q,n,e,d;
	RSAKey(BigInteger p,BigInteger q,BigInteger n,BigInteger e,BigInteger d)
	{
		this.p = p; this.q = q;
		this.n = n; this.e = e; this.d = d;
	}

}
