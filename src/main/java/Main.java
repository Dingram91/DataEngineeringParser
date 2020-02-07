import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    try {
      CSVReader reader = new CSVReader(new FileReader("src/data/SEOExample.csv"));


      List<String[]> records = reader.readAll();

      Iterator<String[]> iterator = records.iterator();
      while (iterator.hasNext()) {
        System.out.println(Arrays.toString(iterator.next()));
      }

      // close the file
      reader.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (CsvValidationException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (CsvException e) {
      e.printStackTrace();
    }
  }
}
