package lck11_J_NCEIL;

import java.util.Calendar;

final class Book {
	// Could change its locking policy in the future 
	// to use private final locks
	private final String title;
	private Calendar dateIssued;
	private Calendar dateDue;
	
	Book(String title) { 
		this.title = title;
	}
	public synchronized void issue(int days) { 
		dateIssued = Calendar.getInstance(); 
		dateDue = Calendar.getInstance(); 
		dateDue.add(dateIssued.DATE, days);
	}
	public synchronized Calendar getDueDate() { 
		return dateDue;
	}
}