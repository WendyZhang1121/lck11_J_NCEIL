package lck11_J_NCEIL;

import java.io.IOException;
import java.util.Calendar;

//Client
public class BookWrapper {
	
	private final Book book;
	
	BookWrapper(Book book) { 
		this.book = book;
	}
	
	public void issue(int days) { 
		book.issue(days);
	}

	public Calendar getDueDate() { 
		return book.getDueDate();
	}
	
	public void renew() { 
		synchronized (book) {
			if (book.getDueDate().before(Calendar.getInstance())) { 
				throw new IllegalStateException("Book overdue");
			} else {
				book.issue(14); // Issue book for 14 days
			} 
		}
	}
	
	public void testCase(final String title){
		Thread test = new Thread(new Runnable() {
			public void run() {
				Book testB = new Book(title);
				BookWrapper testBW = new BookWrapper(testB);
				testBW.renew();
				}
			});
			   test.start();
	}
	
	public void main(String[] args) throws IOException { 
		
		testCase("book1"); // starts thread 1 
		testCase("Book2"); // starts thread 2
	}
}

