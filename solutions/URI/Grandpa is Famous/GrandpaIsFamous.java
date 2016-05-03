import java.io.*;
import java.util.*;

public class GrandpaIsFamous {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st;
        int N, M, d;
        int[] appearances;
        String line;
        ArrayList<Integer> secondbests;
        String printline;
        while(!(line = br.readLine()).equals("0 0")){
            st = new StringTokenizer(line);
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            appearances = new int[10000];
            while(N-- > 0){
                st = new StringTokenizer(br.readLine());
                d = M;
                while(d-- > 0)
                    appearances[Integer.parseInt(st.nextToken())]++;
            }
            int b = 0;
            int sb = 0;
            for(int i = 0; i < appearances.length;i++){
                if(appearances[i] > b){
                    sb = b;
                    b = appearances[i];
                } else if(appearances[i] > sb){
                    sb = appearances[i];
                }
            }
            secondbests = new ArrayList<>();
            for(int i = 0; i < appearances.length; i++)
                if(appearances[i] == sb)
                    secondbests.add(i);
            for(int i = 0; i < secondbests.size(); i++)
                if(i < secondbests.size()-1)
                    System.out.print(secondbests.get(i)+" ");
            System.out.println("");
        }
    }
    
}
