import java.io.*;
import java.util.*;

// UVa 10226 - Hardwood Species
public class Main {
    
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
    
    static class Species implements Comparable<Species>{
        String name;
        double value;
        public Species(String name, double value){
            this.name = name;
            this.value = value;
        }
        @Override
        public int compareTo(Species sp2){
            return this.name.compareTo(sp2.name);
        }
    }
    
    public static void main(String[] args) throws IOException{
        
        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input.txt"));
        pw = new PrintWriter(System.out);
        
        TreeMap<String, Double> species;
        ArrayList<Species> speciesArr;
        double totalTrees;
        String line;
        
        int t = Integer.parseInt(br.readLine());
        br.readLine();
        while(t-- > 0){
            species = new TreeMap<>();
            speciesArr = new ArrayList<>();
            totalTrees = 0;
            while((line = br.readLine()) != null && !line.isEmpty()){
                totalTrees++;
                if(species.containsKey(line)){
                    species.put(line, species.get(line) + 1.0);
                }
                else
                    species.put(line, 1.0);
            }
            for(String key : species.keySet())
                speciesArr.add(new Species(key, species.get(key)/totalTrees*100));
            Collections.sort(speciesArr);
            for(Species sp : speciesArr)
                pw.printf("%s %.4f\n", sp.name, sp.value);
            if(t >= 1) pw.println();
        }
        
        br.close();
        pw.close();
        
    }
        
}