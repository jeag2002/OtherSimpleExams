package es.abequo.parser;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.abequo.beans.Disk;
import es.abequo.beans.VM;

public class ParserJSON {
	
	public static VM syncDisk(VM virtualMachine, String JSONStr) throws Exception{
		
		ObjectMapper mapper = new ObjectMapper();
		List<Disk> JSONDisks = mapper.readValue(JSONStr, new TypeReference<List<Disk>>(){});
		List<Disk> JSONDisks_clone = new ArrayList<Disk>();
		JSONDisks_clone.addAll(JSONDisks);
	
		//virtualMachine.getDisks().clear();
		//Collections.sort(myDisks);
		
		for(Disk disk: JSONDisks) {
			if (virtualMachine.getDisks().contains(disk)) {
				int index = virtualMachine.getDisks().indexOf(disk);
				virtualMachine.getDisks().set(index, disk);
				JSONDisks_clone.remove(disk);
			}
		}
		
		
		virtualMachine.getDisks().addAll(JSONDisks_clone);
		Collections.sort(virtualMachine.getDisks());
		
		return virtualMachine;
	}
	

}
