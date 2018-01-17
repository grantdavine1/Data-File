import java.io.*;
import javax.swing.JOptionPane;
import java.io.EOFException;

public class lab2 {
							// Number of records in the file.
	final private static int MAX_RECORD_NUMBER = 20;
							// Size of record.
	final private static int RECORD_LENGTH = 71;
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {		
		
		String fileName = "";
		fileName = JOptionPane.showInputDialog(null, "Please input the file location with file name: ");
		File loc = new File(fileName);

//		File loc = new File("D:\\Users\\workspace\\116 Lab 2\\demoDataFile.txt");
		RandomAccessFile store = new RandomAccessFile(loc, "rw");
		
		
		String Dummy = "No record exsit here yet";
		while(Dummy.length() < 71){
			Dummy += " ";
		}
		store.seek(0);
		
		try{
			System.out.println(store.readUTF());
			
		}
		catch(EOFException ex){
			for (int i = 0; i < MAX_RECORD_NUMBER; ++i){
				store.writeUTF(Dummy);	// Fill file with dummy records.
			}
		}
		
		
		
			
		String Description = "";
		int recLocation = 0;
		String where = "0";
		String cmd = "start";
		
		String l71 = "";

		
		while (cmd.compareToIgnoreCase("end") != 0 ){
			
			cmd = JOptionPane.showInputDialog(null, "Please input a command:");
			l71 = "";
			
			
		    if (cmd.compareToIgnoreCase("new") == 0){
		    	// Command is "new"  -  user wants to make a new record
		    	
		    	//ID 5 charas
		    	int temp = 0;
		    	try{
		    		Description = JOptionPane.showInputDialog(null, "Please input an ID between 1 and 20: ");
			    	temp = Integer.parseInt(Description);
			    	while(temp > 20 || temp <= 0){
			    		Description = JOptionPane.showInputDialog(null, "Please reinput an ID between 1 and 20: ");
			    		temp = Integer.parseInt(Description);
			    	}
			    	while(Description.length() < 5){
			    		Description += " ";
			    	}
			    	assert Description.length() == 5;
			    	l71 += Description;
		    	}
		    	catch(NumberFormatException ex){
		    		System.out.println("Try again. (Incorrect input: an integer is required)");
		    		while(1 == 1){
		    			try{
		    				Description = JOptionPane.showInputDialog(null, "Please input an ID between 1 and 20: ");

					    	temp = Integer.parseInt(Description);
					    	while(temp > 20 || temp <= 0){
					    		Description = JOptionPane.showInputDialog(null, "Please reinput an ID between 1 and 20: ");
					    		temp = Integer.parseInt(Description);
					    	}
					    	while(Description.length() < 5){
					    		Description += " ";
					    	}
					    	l71 += Description;
						    break;
					    	
					    	
		    			}
		    			catch(NumberFormatException ex2){
		    				System.out.println("Try again. (Incorrect input: an integer is required)");
		    			}
		    		}
		    	}

					
		    	
		    	//name 26 charas
		    	Description = JOptionPane.showInputDialog(null, "Please input a name: ");
		    	String tempStr = "";
		    	if(Description.length() > 26){
		    		tempStr = Description.substring(0, 26);
		    		Description = tempStr;
		    	}
		    	else{
		    		while(Description.length() != 26){
			    		Description += " ";
			    	}
		    	}
		    	assert Description.length() == 26;
		    	l71 += Description;
		    	
		    	//team name 26 charas
		    	Description = JOptionPane.showInputDialog(null, "Please input a team name: ");
		    	if(Description.length() > 26){
		    		tempStr = Description.substring(0, 26);
		    		Description = tempStr;
		    	}
		    	else{
		    		while(Description.length() != 26){
			    		Description += " ";
			    	}
		    	}
		    	assert Description.length() == 26;
		    	l71 += Description;
		    	
		    	
		    	//skill lv 5 charas
		    	try{
		    		Description = JOptionPane.showInputDialog(null, "Please input a skill level: ");
		    		int temp1 = Integer.parseInt(Description);
		    		while(temp1 > 99 || temp1 < 0){
			    		Description = JOptionPane.showInputDialog(null, "Please reinput an skill level between 0 and 99: ");
			    		temp1 = Integer.parseInt(Description);
			    	}
			    	while(Description.length() < 5){
			    		Description += " ";
			    	}
			    	assert Description.length() == 5;
			    	l71 += Description;
		    	}
		    	catch(NumberFormatException ex){
		    		System.out.println("Try again. (Incorrect input: an integer is required)");
		    		while(1 == 1){
		    			try{
			    			Description = JOptionPane.showInputDialog(null, "Please reinput a skill level: ");
			    			int temp1 = Integer.parseInt(Description);
				    		while(temp1 > 99 || temp1 < 0){
					    		Description = JOptionPane.showInputDialog(null, "Please reinput an skill level between 0 and 99: ");
					    		temp1 = Integer.parseInt(Description);
					    	}
					    	while(Description.length() < 5){
					    		Description += " ";
					    	}
					    	assert Description.length() == 5;
					    	l71 += Description;
					    	break;
			    		}
			    		catch(NumberFormatException ex2){
			    			System.out.println("Try again. (Incorrect input: an integer is required)");
			    		}
		    		}
		    		
		    		
		    	}
		    	
		    	
		    	//draft date 9 charas
		    	Description = JOptionPane.showInputDialog(null, "Please input today's date: ");
		    	if(Description.length() > 9){
		    		tempStr = Description.substring(0, 9);
		    		Description = tempStr;
		    	}
		    	l71 += Description;

		    	recLocation = temp;
		    	
				if (recLocation == 0){
					recLocation = 1;
				}
								// find the correct location in the file	
				store.seek((RECORD_LENGTH + 2) * (recLocation - 1));	
				
				
				
				System.out.println(l71);
				store.writeUTF(l71);
				System.out.println();
		    }
		    
		    
		    
	
		    if (cmd.compareToIgnoreCase("old") == 0 ){
				// Command is "old"  -  user wants to see an existing record
				
						
						
				try{
		    		where = JOptionPane.showInputDialog(null, "Input rec number:");
					recLocation = Integer.parseInt(where);
			    	while(recLocation > 20 || recLocation <= 0){
			    		where = JOptionPane.showInputDialog(null, "Please reinput a rec number between 1 and 20: ");
			    		recLocation = Integer.parseInt(where);
			    		
			    	}
			    	
		    	}
		    	catch(NumberFormatException ex){
		    		System.out.println("Try again. (Incorrect input: an integer is required)");
		    		while(1 == 1){
		    			try{
		    				where = JOptionPane.showInputDialog(null, "Please input a rec number between 1 and 20: ");
		    				recLocation = Integer.parseInt(where);
					    	while(recLocation > 20 || recLocation < 0){
					    		where = JOptionPane.showInputDialog(null, "Please reinput a rec number between 1 and 20: ");
					    		recLocation = Integer.parseInt(where);
					    	}
					    	
						    break;
					    	
					    	
		    			}
		    			catch(NumberFormatException ex2){
		    				System.out.println("Try again. (Incorrect input: an integer is required)");
		    			}
		    		}
		    	}
						
						
						
						
				store.seek((RECORD_LENGTH + 2) * (recLocation - 1));
				Description = store.readUTF();
				JOptionPane.showMessageDialog(null, Description);
		    }
		}
	}
}
