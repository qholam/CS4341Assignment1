import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;  
public class BuildWorld {

	/**
	 * Constructor
	 * @param int row
	 * @param int column
	 * @param
	 */
  int row;
  int column;
	public BuildWorld(int row, int column)
	{
		this.row = row;
    this.column = column;
    int hashLimit = rand.nextInt(maxHash) + 1;
	}
  
  /**
   * Create a txt file if does not exit
   * @param  filename File
   * @return boolean
   */
  private static boolean createFile(File fn) throws IOException
  {
    boolean flag = false;
    try{  
       if(!fn.exists())
       {  
        fn.createNewFile();  
        flag = true;
       }  
      }catch(IOException e){  
       e.printStackTrace();  
      }  
    return true;
  }
  /**
   * Write into a txt file
   * @param String [][]
   * @param filename
   */
  private static boolean writeFile(String[][] content, File fn) throws IOException
  {
    boolean flag = false;
    int row, column;
    row = content.length;
    column = content[0].length;
    FileWriter fp = NULL;
    try{
      fp = new FileWriter(fn);  
      for(int i = 0;i < row; i++){
        for(int j = 0;j < column; j++){
                fp.write(content[i][j] + "\t");
        }
        fp.write("\r\n");
      }
      fp.close();
      flag = true;
    }catch (IOException e){
       e.printStackTrace();  
    }
    return flag;
  }
  
  /**
  * Build a 2D array which represents a world to navigate
  * @param String [][] worldArray
  */
  private String[][] BuildArray()
  {    
    Random rand = new Random();
    String[][] worldArray = new int[this.row][this.column];
    for (int i = 0; i < this.column; i ++){
      for (int j = 0; j < this.row; j ++){
         worldArray[j][i] = Integer.toString(rand.nextInt(9) + 1);
   	 }
  	}
    //add hash marks to represent obstacles
    for (int i = 0; i < this.hashLimit; i ++){
    	worldArray[rand.nextInt(this.row)][rand.nextInt(this.column)] = "#";
  	}
    worldArray[rand.nextInt(this.row)][rand.nextInt(this.column)] = "G";
    worldArray[rand.nextInt(this.row)][rand.nextInt(this.column)] = "S";
    return worldArray;
	}