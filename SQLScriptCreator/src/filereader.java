import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
 
public class filereader {
 
	public static boolean isInt( String input )  
	{  
	   try  
	   {  
	      Integer.parseInt( input );  
	      return true;  
	   }  
	   catch( Exception e)  
	   {  
	      return false;  
	   }  
	}  
	
	public static void main(String[] args) {
 
		BufferedReader br = null;
		String[] questions = new String[200];
		String[] answers = new String[200];
		String[] headers = new String[200];
		int lecture = 0;
		
		//int qi = 1;
		int i = 0;
		
		try {
			
			String sCurrentLine;
			//String filename = System.getProperty("user.home") + "/Desktop/script.txt";
			
			File folder[] = files();
			for(int f = 0; f < folder.length; f++)
			{
				for (int x =1; x < questions.length; x++)
				{
					questions[x] = "";
					answers[x]= "";
				}
				int qi = 0 ; 
				
				br = new BufferedReader(new FileReader(folder[f].getAbsolutePath()));//filename));
				
				//Skip first line
				for(int skip = 0; skip < 1; skip++){
					headers[f] = br.readLine();
					System.out.println(headers[f]);
				}
				String temp = "";
				for(int y = 8; y < headers[f].length(); y++)
				{
					temp = headers[f].substring(8,y);
					if(isInt(temp))
					{
						lecture = Integer.parseInt(headers[f].substring(8,y));
						if(isInt(headers[f].substring(8,y+1)))
						{
							lecture = Integer.parseInt(headers[f].substring(8,y+1));
							System.out.println("THIS IS " + lecture);
						}
						break;
					}
				}
				
				while ((sCurrentLine = br.readLine()) != null) {
					sCurrentLine = sCurrentLine.trim();
					sCurrentLine = sCurrentLine.replaceAll("\t", "");
					if(sCurrentLine.length() > 0)
					{
						//System.out.println(sCurrentLine);
						if(isInt(sCurrentLine.substring(0, 1)))
						{
							qi++;
							i = 1;
							while(isInt(sCurrentLine.substring(0,i)))
							{
								i++;
							}
							questions[qi] = sCurrentLine.substring(i);
							//System.out.println(questions[qi]);
							//System.out.println("Question: " + questions[qi]);
							
						}else{
							if(sCurrentLine.contains("A."))
							{
								sCurrentLine = sCurrentLine.substring(2);
							}
							answers[qi]+= "" + sCurrentLine;
							//System.out.println("Answer: " + answers[qi]);
						}
					}
				}
				createSQLFile(questions, answers, lecture);
				//System.out.println("End of lecture " + headers[f]);
				br.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void createSQLFile(String[] questions, String[] answers, int lecture)
	{
	    int subject = 11;
	    
		String filename = System.getProperty("user.home") + "/Desktop/testSQLFile.txt";
		try{
			OutputStreamWriter writer = new OutputStreamWriter(
					new FileOutputStream(filename, true), "UTF-8");
	            BufferedWriter fbw = new BufferedWriter(writer);
	          //  int i = 1; 
	            //while(questions[i] != null)
	            //{
	            for(int i = 1; i < questions.length; i++){
					if(questions[i].length() > 0)
					{
						fbw.write("INSERT INTO Flashcards(SubjectID, LectureNum, QuestionNum, Question, Answer) VALUES("+subject + ", " + lecture + ", "+  i + ", '" + questions[i] +"', '" + answers[i] + "');");
	            		fbw.newLine();
					}
				}
	            fbw.close();
	            writer.close();
	        }catch (Exception e) {
	            System.out.println("Error: " + e.getMessage());
	        }
		}

	public static File[] files()
	{
		File folder = new File(System.getProperty("user.home") + "/Documents/E-Health Flashcards/RESPIRATORY SYSTEM");
		File[] listOfFiles = folder.listFiles();
		File[] txtFiles = new File[listOfFiles.length];
		int j = 0;
		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".txt")) {
		        System.out.println("File " + listOfFiles[i].getName());
		        txtFiles[j] = listOfFiles[i];
		        	j++;
		      } else if (listOfFiles[i].isDirectory()) {
		        System.out.println("Directory " + listOfFiles[i].getName());
		      }
		    }
		  return txtFiles;
	}
}
