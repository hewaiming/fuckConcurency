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
	public static final String IP_ADDR = "172.16.0.5";// 服务器地址
	public static final int PORT = 9099;// 服务器端口号
    private static RealPotV realPotV;
	
	public static void main(String[] args) {
		System.out.println("客户端启动...");
	
		while (true) {
			Socket socket = null;
			try {
				// 创建一个流套接字并将其连接到指定主机上的指定端口号
				socket = new Socket(IP_ADDR, PORT);
                socket.setSoTimeout(0);
				
				// 向服务器端发送数据
				OutputStream out = socket.getOutputStream();
//				System.out.print("请输入: \t");
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
             // 读取服务器端数据
				InputStream in = socket.getInputStream();              
                byte[] temp = new byte[20];// 默认最带为256256
                in.read(temp);
                String str=new String(temp);
                System.out.println("得到数据："+str);     
//				String ret = in.readUTF();
//				System.out.println("服务器端返回过来的是: " + ret);
				// 如接收到 "OK" 则断开连接
				// if ("OK".equals(ret)) {
				// System.out.println("客户端将关闭连接");
				// Thread.sleep(500);
				// break;
				// }

				out.close();
				in.close();
			} catch (Exception e) {
				System.out.println("客户端异常:" + e.getMessage());
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						socket = null;
						System.out.println("客户端 finally 异常:" + e.getMessage());
					}
				}
			}
		}
	}

}
