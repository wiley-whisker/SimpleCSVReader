import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Very basic representation of a csv File. Loads data into memory as a 2D array of strings.
 * @author Wiley Matthews
 */
public class csvFile {
  String[] headers;
  String[][] data;

  /**
   * Initializes as a 2D array (matrix) of the data.
   * @param f File to be interpreted as a csv File.
   * @throws FileNotFoundException If unable to find file.
   */
  public csvFile(File f) throws FileNotFoundException{
    Scanner s;
    try {
      s = new Scanner(f); // Create scanner (reason for the try).
      headers = s.nextLine().split(","); // Get column headers.
      int size = (getSize(f) - 1); // Determine number of lines (rows) in the file.
      data = new String[size][]; // Appropriately size the data array based on number of rows.
      for ( int i = 0; i < size; i++) { // Load rows from file.
        data[i] = s.nextLine().split(",");
      }
      s.close();
    } catch(Exception e) {
      throw new FileNotFoundException();
    }
  }

  /**
   * Print file contents to the command line as a matrix.
   */
  public void print() {
    for (int i = 0; i < headers.length; i++) {
      System.out.printf(headers[i] + ", "); // Print column headers.
    }
    System.out.printf("\n");
    for (int i = 0; i < (data.length - 1); i++) {
      for (int j = 0; j < data[i].length; j++) {
        System.out.printf(data[i][j] + " "); // Print value at i,j.
      }
      System.out.printf("\n");
    }
  }

  /**
   * Helper method to determine size of initial data file.
   * @param f File to determine size of.
   * @return lines Total number of lines in file.
   */
  private int getSize(File f) {
    int lines = 0;
    try {
      Scanner s = new Scanner(f);
      while (s.hasNextLine()) {
        s.nextLine();
        lines++;
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return lines;
  }
}
