package socketDemo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.sun.org.apache.bcel.internal.util.ByteSequence;
import com.sun.org.apache.xerces.internal.impl.io.MalformedByteSequenceException;
import com.sun.org.apache.xerces.internal.xs.datatypes.ByteList;
import com.sun.swing.internal.plaf.basic.resources.basic;

public class GetDataFromJKJ {
	public static final String IP_ADDR = "172.16.0.5";// ��������ַ
	public static final int PORT = 9099;// �������˿ں�
    private static RealPotV realPotV;
	
	public static void main(String[] args) {
		System.out.println("�ͻ�������...");
	
		while (true) {
			Socket socket = null;
			try {
				// ����һ�����׽��ֲ��������ӵ�ָ�������ϵ�ָ���˿ں�
				socket = new Socket(IP_ADDR, PORT);
                socket.setSoTimeout(0);
				
				// ��������˷�������
				OutputStream out = socket.getOutputStream();
//				System.out.print("������: \t");
				byte[] mBytes=new byte[4];
//				realPotV=new RealPotV();
//				realPotV.setCmd(0x11);
//				realPotV.setWorkRoom(1);				
//				realPotV.setFArea(1);
//				realPotV.setFPot(2);
				mBytes[0]=0x11;
				mBytes[1]=1;
				mBytes[2]=1;
				mBytes[3]=4;
				out.write(mBytes);			
                out.flush();
             // ��ȡ������������
				InputStream in = socket.getInputStream();              
                byte[] temp = new byte[20];// Ĭ�����Ϊ256256
                in.read(temp);
                String str=new String(temp);
                System.out.println("�õ����ݣ�"+str);     
//				String ret = in.readUTF();
//				System.out.println("�������˷��ع�������: " + ret);
				// ����յ� "OK" ��Ͽ�����
				// if ("OK".equals(ret)) {
				// System.out.println("�ͻ��˽��ر�����");
				// Thread.sleep(500);
				// break;
				// }

				out.close();
				in.close();
			} catch (Exception e) {
				System.out.println("�ͻ����쳣:" + e.getMessage());
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						socket = null;
						System.out.println("�ͻ��� finally �쳣:" + e.getMessage());
					}
				}
			}
		}
	}

}
