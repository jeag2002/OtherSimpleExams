package es.abequo;

import es.abequo.beans.Disk;
import es.abequo.beans.Type;
import es.abequo.beans.VM;
import es.abequo.beans.Volume;
import es.abequo.parser.ParserJSON;

public class Main {

	public static void main(String[] args) throws Exception {
		
		VM virtualMachine = new VM();
		
		virtualMachine.setUuid("VM-1");
		virtualMachine.setGb(200);
		virtualMachine.setRam(200);
		
		
		Disk disk = new Disk();
		disk.setUuid("xxxx");
		disk.setOrder(0);
		disk.setSize(0);
		disk.setVolume(Volume.TB);
		disk.setType(Type.VMDK_FLASH);
		
		virtualMachine.getDisks().add(disk);
		
		System.out.println("SIZE_DISKS: " + virtualMachine.getDisks().size());
		System.out.println("UUID - 0: " + virtualMachine.getDisks().get(0).getUuid());
		System.out.println("TYPE - 0: " + virtualMachine.getDisks().get(0).getType());
		
		
		String JSONStr = "[{\"uuid\":\"xxxx\", \"order\":1, \"size\":1, \"volume\":\"GB\", \"type\":\"FLASH\"},"
				+ "{\"uuid\":\"yyyy\", \"order\":0, \"size\":0, \"volume\":\"GB\", \"type\":\"DISK\"} ]";
		virtualMachine = ParserJSON.syncDisk(virtualMachine, JSONStr);
		
		System.out.println("SIZE_DISKS: " + virtualMachine.getDisks().size());
		System.out.println("UUID - 0: " + virtualMachine.getDisks().get(0).getUuid());
		System.out.println("TYPE - 0: " + virtualMachine.getDisks().get(0).getType());
		System.out.println("UUID - 1: " + virtualMachine.getDisks().get(1).getUuid());
		System.out.println("TYPE - 1: " + virtualMachine.getDisks().get(1).getType());

	}

}
