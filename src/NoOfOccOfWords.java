import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class NoOfOccOfWords {

    public static void main(String[] args)  {

        FileWriter fw = null;
        Scanner file=null;
        try {
             file = new Scanner(new File(System.getProperty("user.dir") + "/src/files/TestFile"));
            Map<String, Integer> wordsCount = new HashMap<String, Integer>();
            while (file.hasNext()) {
                String word = file.next();
                Integer count = wordsCount.get(word);
                if (count != null) {
                    count++;
                } else
                    count = 1;
                wordsCount.put(word, count);
            }
            file.close();
            ArrayList<Integer> list = new ArrayList<>();
            LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
            for (Map.Entry<String, Integer> entry : wordsCount.entrySet()) {
                list.add(entry.getValue());
            }
            Collections.sort(list, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2.compareTo(o1);
                }
            });

            for (Integer i : list) {
                for (Map.Entry<String, Integer> entry : wordsCount.entrySet()) {
                    if (entry.getValue().equals(i)) {
                        sortedMap.put(entry.getKey(), entry.getValue());
                    }
                }
            }

            fw = new FileWriter(System.getProperty("user.dir") + "/src/files/ResultFile");
            for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
                fw.write(entry.getKey() + "\t" + entry.getValue() + "\n");
            }


        } catch (IOException e) {
            System.out.println("exception occured" + e.getStackTrace());
        } finally {
            file.close();
            try{
                fw.close();
            }
            catch (IOException e)
            {
                System.out.println( "Exception occured while closing the write: "+e.getStackTrace());
            }
        }
    }
}
