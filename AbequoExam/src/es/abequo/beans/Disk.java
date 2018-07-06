package es.abequo.beans;

public class Disk implements Comparable{
	
	private String uuid;
	private int order;
	private int size;
	private Volume volume;
	private Type type;
	
	
	public Disk() {
		uuid = "";
		order = 0;
		size = 0;
	}
	
	
	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public int getOrder() {
		return order;
	}


	public void setOrder(int order) {
		this.order = order;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public Volume getVolume() {
		return volume;
	}


	public void setVolume(Volume volume) {
		this.volume = volume;
	}


	public Type getType() {
		return type;
	}


	public void setType(Type type) {
		this.type = type;
	}
	
	
	@Override
    public boolean equals(Object o) {
 
        if (o == this) {
            return true;
        }
 
        if (!(o instanceof Disk)) {
            return false;
        }
        Disk d = (Disk) o;
        return this.uuid.equals(d.getUuid());
    }
	
	
	
	
	@Override
	public int compareTo(Object arg0) {
		
		if (arg0 == null) return 0;
		if ((arg0 instanceof Disk) == false) return 0;
		else {
			Disk other = (Disk) arg0;
			if (this.uuid.equals(other.getUuid())) return 0;
			else {return this.order - other.getOrder();}
		}
	}


	

}
