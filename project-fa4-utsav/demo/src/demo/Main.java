package demo;

public class Main {
	
	public static void display(int num) {
		
		String str = Integer.toString(num);
		
	    String arr[] = str.split("");
	    
	 
	    
	  for(int i=0;i<arr.length;i++) {
		  
		  switch(arr[i]) {
		  
		  case "1":  System.out.print("one ");
		  		break;
		  case "2":  System.out.print("two ");
	  		break;
		  case "3":  System.out.print("three ");
	  		break;
	  		
		  case "4":  System.out.print("four ");
	  		break;
	  
	  
	  		
		  
		  
		  }
	  }
		


			
	}
	
	public static void display(String str) {
		
		String arr[]= str.split(" ");
		
		 for(int i=0;i<arr.length;i++) {
			  
			  switch(arr[i]) {
			  
			  case "one":  System.out.print("1");
			  		break;
			  case "two":  System.out.print("2");
		  		break;
			  case "three":  System.out.print("3");
		  		break;
		  		
			  case "four":  System.out.print("4");
		  		break;
		  
		  
		  		
			  
			  
			  }
		 }
		
		
	}
  
	public static void main(String args[]) {
		
		
		display(123);
		
		}
	
}
