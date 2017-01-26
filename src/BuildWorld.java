import java.io.File;  
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
	public BuildWorld(int row, int column, String fn) throws IOException
	{
		this.row = row;
		this.column = column;
		String worldArray[][] = new String[this.row][this.column];
		worldArray = this.BuildArray();
		File world1 = new File(fn);
	    this.writeFile(worldArray, world1);
	}

	public static void main(String [] args) throws IOException
	{
		new BuildWorld(12, 17, "TRY1");
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
	private boolean writeFile(String[][] content, File fn) throws IOException
	{
		createFile(fn);
		boolean flag = false;
		int row, column;
		row = content.length;
		column = content[0].length;
		FileWriter fp = null;
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
		String[][] worldArray = new String[this.row][this.column];
		for (int i = 0; i < this.column; i ++){
			for (int j = 0; j < this.row; j ++){
				worldArray[j][i] = Integer.toString(rand.nextInt(9) + 1);
			}
		}
		//add hash marks to represent obstacles
		int hashLimit = rand.nextInt(9) + 1;
		for (int i = 0; i < hashLimit; i ++){
			worldArray[rand.nextInt(this.row)][rand.nextInt(this.column)] = "#";
		}
		worldArray[rand.nextInt(this.row)][rand.nextInt(this.column)] = "G";
		worldArray[rand.nextInt(this.row)][rand.nextInt(this.column)] = "S";
		return worldArray;
	}
}