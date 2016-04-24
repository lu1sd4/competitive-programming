import java.io.*;
import java.util.*;

//UVa 454 - Anagrams
class Main {

    static BufferedReader br;
    static StringTokenizer st = new StringTokenizer("");
    
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input.txt"));
        int n = readInt();
        br.readLine();
        ArrayList<String> answers;
        ArrayList<String> words;
        ArrayList<String> sortedWords;
        char[] word;
        String line;
        while(n-- > 0){
            answers = new ArrayList<>();
            words = new ArrayList<>();
            sortedWords = new ArrayList<>();
            line = br.readLine();
            while(!line.equals("")){
                words.add(line);
                word = line.replaceAll(" ", "").toCharArray();
                Arrays.sort(word);
                sortedWords.add(new String(word));
                if((line = br.readLine()) == null) break;
            }
            for(int i = 0; i < sortedWords.size()-1; i++){
                for(int j = i+1; j < sortedWords.size(); j++){
                    if(sortedWords.get(i).equals(sortedWords.get(j))){
                        String[] pair = {words.get(i), words.get(j)};
                        Arrays.sort(pair);
                        answers.add(pair[0]+ " = "+pair[1]);
                    }
                }
            }
            answers.sort(null);
            for(String ans : answers)
                System.out.println(ans);
            if(n != 0) System.out.println("");
        }
    }
    
    static int readInt() throws IOException{
        if(!st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return Integer.parseInt(st.nextToken());
    }
    
}
