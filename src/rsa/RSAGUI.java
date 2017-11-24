package rsa;
import javax.swing.*;
//import java.awt.*;
//import java.awt.Color;
//import java.awt.FlowLayout;
//import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
//import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class RSAGUI {

	static RSAKey Key;
	static int nBit = 128;
	public static void main(String[] args) {
		
		

		JFrame f = new JFrame("RSATool");
        f.setSize(800, 600);
        f.setLocation(200, 200);
  
        f.setLayout(null);
        
        Dimension DeTextArea = new Dimension(350,150);
        JPanel p1 = new JPanel();
        p1.setBounds(50, 50, 300, 60);
  
        //p1.setBackground(Color.RED);
        BorderLayout p1Layout = new BorderLayout();
        p1Layout.setHgap(10);
        p1.setLayout(p1Layout);
        
        JPanel p1left = new JPanel();
        p1left.setLayout(new BoxLayout(p1left, BoxLayout.Y_AXIS)); 
        
        JButton bGenerateKey = new JButton("GenerateKey");
        bGenerateKey.setSize(new Dimension(200, 30));
        p1left.add(bGenerateKey);
        
        
        String[] encrptionTypes = new String[] { "RSA-128", "RSA-256","RSA-512","RSA-768","RSA-1024","RSA-2048" };
        JComboBox<Object> cEncrptionType = new JComboBox<Object>(encrptionTypes);
        cEncrptionType.setSize(new Dimension(350, 1000));
        p1left.add(cEncrptionType);
        
        JLabel lValueP = new JLabel("ValueP");
        p1left.add(lValueP);
        
        JTextArea tavalueP = new JTextArea();
        tavalueP.setSize(new Dimension(350, 1000));
        tavalueP.setText("这里显示RSA密码的p\n");
        tavalueP.setLineWrap(true);
        JScrollPane svalueP = new JScrollPane(tavalueP);
        svalueP.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p1left.add(svalueP);
        
        JLabel lValueQ = new JLabel("ValueQ");
        p1left.add(lValueQ);
        
        JTextArea tavalueQ = new JTextArea();
        tavalueQ.setSize(new Dimension(350, 1000));
        tavalueQ.setText("这里显示RSA密码的q\n");
        tavalueQ.setLineWrap(true);
        JScrollPane svalueQ = new JScrollPane(tavalueQ);
        svalueQ.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p1left.add(svalueQ);
        
        JLabel lValueN = new JLabel("ValueN");
        p1left.add(lValueN);
        
        JTextArea tavalueN = new JTextArea();
        tavalueN.setSize(new Dimension(350, 1000));
        tavalueN.setText("这里显示RSA密码的n\n");
        tavalueN.setLineWrap(true);
        JScrollPane svalueN = new JScrollPane(tavalueN);
        svalueN.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p1left.add(svalueN);
        
        JLabel lValueE = new JLabel("ValueE(Public Key)");
        p1left.add(lValueE);
        
        JTextArea tavalueE = new JTextArea();
        tavalueE.setSize(new Dimension(350, 1000));
        tavalueE.setText("这里显示RSA密码的e\n");
        tavalueE.setLineWrap(true);
        JScrollPane svalueE = new JScrollPane(tavalueE);
        svalueE.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p1left.add(svalueE);
        
        JLabel lValueD = new JLabel("ValueD(Private Key)");
        p1left.add(lValueD);
        
        JTextArea tavalueD = new JTextArea();
        tavalueD.setSize(new Dimension(350, 1000));
        tavalueD.setText("这里显示RSA密码的d\n");
        tavalueD.setLineWrap(true);
        JScrollPane svalueD = new JScrollPane(tavalueD);
        svalueD.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p1left.add(svalueD);
        
        JLabel lGenerateKeyTime = new JLabel("生成密钥计时");
        p1left.add(lGenerateKeyTime);
        
        p1.add(p1left, BorderLayout.WEST);
        
        JPanel p1right = new JPanel();
        p1right.setLayout(new BoxLayout(p1right, BoxLayout.Y_AXIS)); 
        
        JLabel lEncryptionKeyE = new JLabel("EncryptionKeyE");
        p1right.add(lEncryptionKeyE);
        
        JTextArea tagetE = new JTextArea();
        tagetE.setSize(new Dimension(350, 1000));
        tagetE.setText("这里输入RSA公钥的e\n");
        tagetE.setLineWrap(true);
        JScrollPane sgetE = new JScrollPane(tagetE);
        sgetE.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p1right.add(sgetE);
        
        JLabel lEncryptionKeyN = new JLabel("EncryptionKeyN");
        p1right.add(lEncryptionKeyN);
        
        JTextArea tagetN = new JTextArea();
        tagetN.setSize(new Dimension(200, 150));
        tagetN.setText("这里输入RSA公钥的n\n");
        tagetN.setLineWrap(true);
        JScrollPane sgetN = new JScrollPane(tagetN);
        sgetN.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p1right.add(sgetN);
        
        JLabel lEncryptionText = new JLabel("EncryptionText");
        p1right.add(lEncryptionText);
        
        JTextArea tagetStr = new JTextArea();
        tagetStr.setSize(new Dimension(200, 150));
        tagetStr.setText("这里输入需要加密的文本\n");
        tagetStr.setLineWrap(true);
        JScrollPane sgetStr = new JScrollPane(tagetStr);
        sgetStr.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p1right.add(sgetStr);
        
        JButton bEncryption = new JButton("Encryption");
        bEncryption.setPreferredSize(new Dimension(200, 30));
        p1right.add(bEncryption);
        
        JLabel lEncryptionRes = new JLabel("EncryptionRes");
        p1right.add(lEncryptionRes);
        
        JTextArea taEncode = new JTextArea();
        taEncode.setSize(new Dimension(200, 150));
        taEncode.setText("这里显示加密结果(base64)\n");
        taEncode.setLineWrap(true);
        JScrollPane sEncode = new JScrollPane(taEncode);
        sEncode.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p1right.add(sEncode);
        
        JLabel lEncodeTime = new JLabel("加密计时");
        p1right.add(lEncodeTime);
        
        p1.add(p1right, BorderLayout.CENTER);
        
        
  
        JPanel p2 = new JPanel();
        p2.setBounds(10, 150, 300, 60);
        BorderLayout p2Layout = new BorderLayout();
        p2Layout.setHgap(10);
        p2.setLayout(p2Layout);
        
        JPanel p2left = new JPanel();
        p2left.setLayout(new BoxLayout(p2left, BoxLayout.Y_AXIS)); 
        
        JLabel lDecryptionKeyD = new JLabel("DecryptionKeyD");
        p2left.add(lDecryptionKeyD);
        
        JTextArea tagetPriD = new JTextArea();
        tagetPriD.setSize(DeTextArea);
        tagetPriD.setText("这里输入RSA私钥的d\n");
        tagetPriD.setLineWrap(true);
        JScrollPane sgetPriD = new JScrollPane(tagetPriD);
        sgetPriD.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p2left.add(sgetPriD);
        
        JLabel lDecryptionKeyN = new JLabel("DecryptionKeyN");
        p2left.add(lDecryptionKeyN);
        
        JTextArea tagetPriN = new JTextArea();
        tagetPriN.setSize(DeTextArea);
        tagetPriN.setText("这里输入RSA私钥的n\n");
        tagetPriN.setLineWrap(true);
        JScrollPane sgetPriN = new JScrollPane(tagetPriN);
        sgetPriN.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p2left.add(sgetPriN);
        
        JLabel lDecryptionKeyP = new JLabel("DecryptionKeyP");
        p2left.add(lDecryptionKeyP);
        
        JTextArea tagetPriP = new JTextArea();
        tagetPriP.setSize(DeTextArea);
        tagetPriP.setText("这里输入用于RSA解密加速的n的因子p(或q)\n");
        tagetPriP.setLineWrap(true);
        JScrollPane sgetPriP = new JScrollPane(tagetPriP);
        sgetPriP.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p2left.add(sgetPriP);
        
        p2.add(p2left, BorderLayout.WEST);
        
        JPanel p2right = new JPanel();
        p2right.setLayout(new BoxLayout(p2right, BoxLayout.Y_AXIS)); 
        
        JLabel lDecryptionText = new JLabel("DecryptionText");
        p2right.add(lDecryptionText);
        
        JTextArea tagetDeStr = new JTextArea();
        tagetDeStr.setSize(new Dimension(200, 150));
        tagetDeStr.setText("这里输入需要解密的内容(base64)\n");
        tagetDeStr.setLineWrap(true);
        JScrollPane sgetDeStr = new JScrollPane(tagetDeStr);
        sgetDeStr.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p2right.add(sgetDeStr);
        
        JRadioButton b0 = new JRadioButton("常规");
        b0.setSelected(true);
        b0.setBounds(50, 50, 130, 30);
        p2right.add(b0);
        
        JRadioButton b1 = new JRadioButton("中国剩余定理(CRT)加速");
        b1.setBounds(50, 50, 130, 30);
        p2right.add(b1);
        
        JRadioButton b2 = new JRadioButton("中国剩余定理(CRT)&费马定理(Fermat Theory)加速");
        b2.setBounds(50, 100, 130, 30);
        p2right.add(b2);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(b0);
        bg.add(b1);
        bg.add(b2);
        
        JButton bDecryption = new JButton("Decryption");
        bDecryption.setSize(new Dimension(200, 30));
        p2right.add(bDecryption);
        
        JLabel lDecryptionRes = new JLabel("DecryptionRes");
        p2right.add(lDecryptionRes);
        
        JTextArea taDecode = new JTextArea();
        taDecode.setSize(new Dimension(200, 150));
        taDecode.setText("这里显示解密结果\n");
        taDecode.setLineWrap(true);
        JScrollPane sDecode = new JScrollPane(taDecode);
        sDecode.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p2right.add(sDecode);
        
        JLabel lDecryptionTime = new JLabel("解密计时");
        p2right.add(lDecryptionTime);
        
        p2.add(p2right, BorderLayout.CENTER);
        
        bGenerateKey.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int selectedEncrptionIndex = cEncrptionType.getSelectedIndex();
				if(selectedEncrptionIndex == 0) nBit=128;
				else if (selectedEncrptionIndex == 1) nBit=256;
				else if (selectedEncrptionIndex == 2) nBit=512;
				else if (selectedEncrptionIndex == 3) nBit=768;
				else if (selectedEncrptionIndex == 3)nBit = 1024;
				else nBit = 2048;
				long sysDate = System.currentTimeMillis();
				Key = RSAGeneratorKey.generateKey(nBit);
				sysDate = System.currentTimeMillis() - sysDate;
				lGenerateKeyTime.setText("生成密钥时间:" + sysDate + "ms");
				BigInteger resP = Key.p, resQ = Key.q, resN = Key.n, resE = Key.e, resD = Key.d ;
				String resPStr = resP.toString(), resQStr = resQ.toString(), resNStr = resN.toString();
				String resEStr = resE.toString(), resDStr = resD.toString();
				tavalueP.setText("");tavalueP.append(resPStr);
				tavalueQ.setText("");tavalueQ.append(resQStr);
				tavalueN.setText("");tavalueN.append(resNStr);
				tavalueE.setText("");tavalueE.append(resEStr);
				tavalueD.setText("");tavalueD.append(resDStr);
				tagetE.setText("");tagetE.append(resEStr);
				tagetN.setText("");tagetN.append(resNStr);
				tagetPriD.setText("");tagetPriD.append(resDStr);
				tagetPriN.setText("");tagetPriN.append(resNStr);
				tagetPriP.setText("");tagetPriP.append(resPStr);
				
				
			}
        });
        
        bEncryption.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				BigInteger inputE = new BigInteger(tagetE.getText().trim());
				BigInteger inputN = new BigInteger(tagetN.getText().trim());
				RSAKey inputKey = new RSAKey(BigInteger.ZERO,BigInteger.ZERO,inputN,inputE,BigInteger.ZERO);
				String inputStr = tagetStr.getText();
				long sysDate = System.currentTimeMillis();
				String encodeStr = RSAEncode.RSAencode(inputStr, inputKey, inputN.bitLength()/8-1);
				sysDate = System.currentTimeMillis() - sysDate;
				lEncodeTime.setText("加密时间:" + sysDate + "ms");
				
				taEncode.setText("");taEncode.append(encodeStr);
			}
        });
  
        bDecryption.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				BigInteger inputD = new BigInteger(tagetPriD.getText().trim());
				BigInteger inputN = new BigInteger(tagetPriN.getText().trim());
				String tmpP = tagetPriP.getText().trim();
				BigInteger inputP;
				if (tmpP.length()==0 || b0.isSelected()) {
					inputP = BigInteger.ZERO;
				}
				else {
					inputP = new BigInteger(tagetPriP.getText().trim());
				}
				
				RSAKey inputKey = new RSAKey(inputP,BigInteger.ZERO,inputN,BigInteger.ZERO,inputD);
				String inputStr = tagetDeStr.getText().trim();
				String decodeStr;
				try {
					long sysDate = System.currentTimeMillis();
					decodeStr = RSADecode.RSAdecode(inputStr, inputKey, inputN.bitLength()/8-1,b2.isSelected());
					sysDate = System.currentTimeMillis() - sysDate;
					taDecode.setText("");taDecode.append(decodeStr);
					lDecryptionTime.setText("解密时间:"+sysDate+"ms");
					
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					taDecode.setText("");taDecode.append("Error:解密异常");
					
				}
				
			}
        });
        
        
        
        JTabbedPane tp = new JTabbedPane();
        tp.add(p1);
        tp.add(p2);
  
        // 设置tab的标题
        tp.setTitleAt(0, "encryption");
        tp.setTitleAt(1, "decryption");
         
  
        f.setContentPane(tp);
 
        // 关闭窗体的时候，退出程序
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 让窗体变得可见
        f.setVisible(true);
        
	}
}
