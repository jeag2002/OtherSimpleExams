package es.abequo.beans;

import java.util.ArrayList;

public class VM {
	
	private String uuid;
	private int gb;
	private int ram;
	
	private ArrayList<Disk> disks;

	public VM() {
		uuid = "";
		gb = 0;
		ram = 0;
		
		disks = new ArrayList<Disk>();
		
	}
	
	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public int getGb() {
		return gb;
	}


	public void setGb(int gb) {
		this.gb = gb;
	}


	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}
	
	public ArrayList<Disk> getDisks() {
		return disks;
	}

	public void setDisks(ArrayList<Disk> disks) {
		this.disks = disks;
	}


	

}
