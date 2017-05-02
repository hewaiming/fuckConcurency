package socketDemo;

import java.io.Serializable;

public class RealPotV implements Serializable {

	private int Cmd,WorkRoom,FArea,FPot;
	private int  CheckSum;
	public RealPotV(int cmd, int workRoom, int fArea, int fPot, int checkSum) {
		super();
		Cmd = cmd;
		WorkRoom = workRoom;
		FArea = fArea;
		FPot = fPot;
		CheckSum = checkSum;
	}
	public RealPotV() {
		super();
	}
	public int getCmd() {
		return Cmd;
	}
	public void setCmd(int cmd) {
		Cmd = cmd;
	}
	public int getWorkRoom() {
		return WorkRoom;
	}
	public void setWorkRoom(int workRoom) {
		WorkRoom = workRoom;
	}
	public int getFArea() {
		return FArea;
	}
	public void setFArea(int fArea) {
		FArea = fArea;
	}
	public int getFPot() {
		return FPot;
	}
	public void setFPot(int fPot) {
		FPot = fPot;
	}
	public int getCheckSum() {
		return CheckSum;
	}
	public void setCheckSum(int checkSum) {
		CheckSum = checkSum;
	}
	
	
}
