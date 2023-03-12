import java.io.*;
import java.util.*;

public class WebServer {
    public static void main(String[] args) {
        try {
            // create a FileReader object to read input from the file TWSP_large.txt
            FileReader fileReader = new FileReader("src/TWSP_large.txt");//Uses the large dataset

            // create a BufferedReader object to read the input
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // read T(Testcase) from the first line of the input file
            int T = Integer.parseInt(bufferedReader.readLine());
            int[][] websites = new int[T][3];//Making an array of websites containing T rows and 3 columns for text,image and form content respectively

            // read the remaining lines of the input file loop through the num of testcase T
            for (int i = 0; i < T; i++) {
                String line = bufferedReader.readLine();
                if (line == null || line.trim().isEmpty()) {
                    continue; // skip empty lines
                }
                String[] tokens = line.trim().split("\\s+");
                for (int j = 0; j < 3; j++) {
                    websites[i][j] = Integer.parseInt(tokens[j]);
                }
            }

            // sort the websites array based on the criteria defined in the CompareWebsite class
            Arrays.sort(websites, new CompareWebsite());

            // create a FileWriter object to write output to the file
            FileWriter fileWriter = new FileWriter("output.txt");

            // create a BufferedWriter object to write the output in chunks
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // write the sorted websites array to the output file
            for (int i = 0; i < T; i++) {
                bufferedWriter.write(websites[i][0] + "," + websites[i][1] + "," + websites[i][2]);
                bufferedWriter.newLine();
            }

            // close the input and output streams
            bufferedReader.close();
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class CompareWebsite implements Comparator<int[]> {
        @Override
        public int compare(int[] website1, int[] website2) {
            if (website1[0] != website2[0]) {
                return Integer.compare(website1[0], website2[0]); // sort by text content size
            } else if (website1[1] != website2[1]) {
                return Integer.compare(website2[1], website1[1]); // sort by image size (in reverse order) as mentioned that images need to be sorted reverse order
            } else {
                return Integer.compare(website1[2], website2[2]); // sort by form size
            }
        }
    }
}
