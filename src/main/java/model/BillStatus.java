package model;

public enum BillStatus {
	uncompleted, completed, rejected;
	
	public String getStringBillStatus() {
		if(this == uncompleted) 
			return "uncompleted";
		else if(this == completed)
			return "completed";
		else if(this == rejected)
			return "rejected";
		return null;
	}
	
	public BillStatus getBillStatusByString(String billStatus) {
		if(billStatus.equalsIgnoreCase("completed"))
			return completed;
		else if(billStatus.equalsIgnoreCase("uncompleted"))
			return BillStatus.uncompleted;
		else if(billStatus.equalsIgnoreCase("rejected"))
			return BillStatus.rejected;
		return null;
	}
}
