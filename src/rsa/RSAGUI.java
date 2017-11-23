package rsa;
import javax.swing.*;
import java.awt.*;
//import java.awt.Color;
//import java.awt.FlowLayout;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class RSAGUI {
	/*
	private static void createAndShowGUI() {
        // ȷ��һ��Ư������۷��
        JFrame.setDefaultLookAndFeelDecorated(true);

        // ���������ô���
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ��� "Hello World" ��ǩ
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

        // ��ʾ����
        frame.pack();
        frame.setVisible(true);
    }*/
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
        bGenerateKey.setPreferredSize(new Dimension(200, 30));
        p1left.add(bGenerateKey);
        
        
        String[] encrptionTypes = new String[] { "RSA-128", "RSA-256","RSA-512","RSA-768","RSA-1024" };
        JComboBox cEncrptionType = new JComboBox(encrptionTypes);
        p1left.add(cEncrptionType);
        
        JLabel lValueP = new JLabel("ValueP");
        p1left.add(lValueP);
        
        JTextArea tavalueP = new JTextArea();
        tavalueP.setPreferredSize(new Dimension(350, 150));
        tavalueP.setText("������ʾRSA�����p\n");
        tavalueP.setLineWrap(true);
        JScrollPane svalueP = new JScrollPane(tavalueP);
        svalueP.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p1left.add(svalueP);
        
        JLabel lValueQ = new JLabel("ValueQ");
        p1left.add(lValueQ);
        
        JTextArea tavalueQ = new JTextArea();
        tavalueQ.setPreferredSize(new Dimension(350, 150));
        tavalueQ.setText("������ʾRSA�����q\n");
        tavalueQ.setLineWrap(true);
        JScrollPane svalueQ = new JScrollPane(tavalueQ);
        svalueQ.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p1left.add(svalueQ);
        
        JLabel lValueN = new JLabel("ValueN");
        p1left.add(lValueN);
        
        JTextArea tavalueN = new JTextArea();
        tavalueN.setPreferredSize(new Dimension(350, 150));
        tavalueN.setText("������ʾRSA�����n\n");
        tavalueN.setLineWrap(true);
        JScrollPane svalueN = new JScrollPane(tavalueN);
        svalueN.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p1left.add(svalueN);
        
        JLabel lValueE = new JLabel("ValueE(Public Key)");
        p1left.add(lValueE);
        
        JTextArea tavalueE = new JTextArea();
        tavalueE.setPreferredSize(new Dimension(350, 150));
        tavalueE.setText("������ʾRSA�����e\n");
        tavalueE.setLineWrap(true);
        JScrollPane svalueE = new JScrollPane(tavalueE);
        svalueE.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p1left.add(svalueE);
        
        JLabel lValueD = new JLabel("ValueD(Private Key)");
        p1left.add(lValueD);
        
        JTextArea tavalueD = new JTextArea();
        tavalueD.setPreferredSize(new Dimension(350, 150));
        tavalueD.setText("������ʾRSA�����d\n");
        tavalueD.setLineWrap(true);
        JScrollPane svalueD = new JScrollPane(tavalueD);
        svalueD.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p1left.add(svalueD);
        
        
        
        p1.add(p1left, BorderLayout.WEST);
        
        JPanel p1right = new JPanel();
        p1right.setLayout(new BoxLayout(p1right, BoxLayout.Y_AXIS)); 
        
        JLabel lEncryptionKeyE = new JLabel("EncryptionKeyE");
        p1right.add(lEncryptionKeyE);
        
        JTextArea tagetE = new JTextArea();
        tagetE.setPreferredSize(new Dimension(200, 150));
        tagetE.setText("��������RSA��Կ��e\n");
        tagetE.setLineWrap(true);
        JScrollPane sgetE = new JScrollPane(tagetE);
        sgetE.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p1right.add(tagetE);
        
        JLabel lEncryptionKeyN = new JLabel("EncryptionKeyN");
        p1right.add(lEncryptionKeyN);
        
        JTextArea tagetN = new JTextArea();
        tagetN.setPreferredSize(new Dimension(200, 150));
        tagetN.setText("��������RSA��Կ��n\n");
        tagetN.setLineWrap(true);
        JScrollPane sgetN = new JScrollPane(tagetN);
        sgetN.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p1right.add(tagetN);
        
        JLabel lEncryptionText = new JLabel("EncryptionText");
        p1right.add(lEncryptionText);
        
        JTextArea tagetStr = new JTextArea();
        tagetStr.setPreferredSize(new Dimension(200, 150));
        tagetStr.setText("����������Ҫ���ܵ��ı�\n");
        tagetStr.setLineWrap(true);
        JScrollPane sgetStr = new JScrollPane(tagetStr);
        sgetStr.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p1right.add(tagetStr);
        
        JButton bEncryption = new JButton("Encryption");
        bEncryption.setPreferredSize(new Dimension(200, 30));
        p1right.add(bEncryption);
        
        JLabel lEncryptionRes = new JLabel("EncryptionRes");
        p1right.add(lEncryptionRes);
        
        JTextArea taEncode = new JTextArea();
        taEncode.setPreferredSize(new Dimension(200, 150));
        taEncode.setText("������ʾ���ܽ��(base64)\n");
        taEncode.setLineWrap(true);
        JScrollPane sEncode = new JScrollPane(taEncode);
        sEncode.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p1right.add(taEncode);
        
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
        tagetPriD.setPreferredSize(DeTextArea);
        tagetPriD.setText("��������RSA˽Կ��d\n");
        tagetPriD.setLineWrap(true);
        JScrollPane sgetPriD = new JScrollPane(tagetPriD);
        sgetPriD.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p2left.add(tagetPriD);
        
        JLabel lDecryptionKeyN = new JLabel("DecryptionKeyN");
        p2left.add(lDecryptionKeyN);
        
        JTextArea tagetPriN = new JTextArea();
        tagetPriN.setPreferredSize(DeTextArea);
        tagetPriN.setText("��������RSA˽Կ��n\n");
        tagetPriN.setLineWrap(true);
        JScrollPane sgetPriN = new JScrollPane(tagetPriN);
        sgetPriN.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p2left.add(tagetPriN);
        
        JLabel lDecryptionKeyP = new JLabel("DecryptionKeyP");
        p2left.add(lDecryptionKeyP);
        
        JTextArea tagetPriP = new JTextArea();
        tagetPriP.setPreferredSize(DeTextArea);
        tagetPriP.setText("������������RSA���ܼ��ٵ�n������p(��q)\n");
        tagetPriP.setLineWrap(true);
        JScrollPane sgetPriP = new JScrollPane(tagetPriP);
        sgetPriP.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p2left.add(tagetPriP);
        
        p2.add(p2left, BorderLayout.WEST);
        
        JPanel p2right = new JPanel();
        p2right.setLayout(new BoxLayout(p2right, BoxLayout.Y_AXIS)); 
        
        JLabel lDecryptionText = new JLabel("DecryptionText");
        p2right.add(lDecryptionText);
        
        JTextArea tagetDeStr = new JTextArea();
        tagetDeStr.setPreferredSize(new Dimension(200, 150));
        tagetDeStr.setText("����������Ҫ���ܵ�����(base64)\n");
        tagetDeStr.setLineWrap(true);
        JScrollPane sgetDeStr = new JScrollPane(tagetDeStr);
        sgetDeStr.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p2right.add(tagetDeStr);
        
        JButton bDecryption = new JButton("Decryption");
        bDecryption.setPreferredSize(new Dimension(200, 30));
        p2right.add(bDecryption);
        
        JLabel lDecryptionRes = new JLabel("DecryptionRes");
        p2right.add(lDecryptionRes);
        
        JTextArea taDecode = new JTextArea();
        taDecode.setPreferredSize(new Dimension(200, 150));
        taDecode.setText("������ʾ���ܽ��\n");
        taDecode.setLineWrap(true);
        JScrollPane sDecode = new JScrollPane(taDecode);
        sDecode.setVerticalScrollBarPolicy( 
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        p2right.add(taDecode);
        
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
				else nBit = 1024;
				Key = RSAGeneratorKey.generateKey(nBit);
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
				String encodeStr = RSAEncode.RSAencode(inputStr, inputKey, inputN.bitLength()/8-1);
				
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
				if (tmpP.length()==0) {
					inputP = BigInteger.ZERO;
				}
				else {
					inputP = new BigInteger(tagetPriP.getText().trim());
				}
				
				RSAKey inputKey = new RSAKey(inputP,BigInteger.ZERO,inputN,BigInteger.ZERO,inputD);
				String inputStr = tagetDeStr.getText().trim();
				String decodeStr;
				try {
					decodeStr = RSADecode.RSAdecode(inputStr, inputKey, inputN.bitLength()/8-1);
					taDecode.setText("");taDecode.append(decodeStr);
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					taDecode.setText("");taDecode.append("Error:�����쳣");
				}
				
				
			}
        });
        
        JTabbedPane tp = new JTabbedPane();
        tp.add(p1);
        tp.add(p2);
  
        // ����tab�ı���
        tp.setTitleAt(0, "encryption");
        tp.setTitleAt(1, "decryption");
         
        ImageIcon i = new ImageIcon("e:/project/j2se/j.png");
        tp.setIconAt(0,i );
        tp.setIconAt(1,i );
  
        f.setContentPane(tp);
 
        // �رմ����ʱ���˳�����
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // �ô����ÿɼ�
        f.setVisible(true);
        
	}
}
