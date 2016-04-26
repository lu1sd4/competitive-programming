import java.io.*;
import java.util.*;

//UVa 12247 - Jollo
class Main {

    static BufferedReader br;
    static StringTokenizer st = new StringTokenizer("");
    
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input.txt"));
        String line;
        int princess[][], prince[][], cards[], princesswins, tobeat;
        while(!(line = br.readLine()).equals("0 0 0 0 0")){
            st = new StringTokenizer(line);
            princess = new int[2][3];
            prince = new int[2][2];
            cards = new int[53];
            for(int i = 0; i < princess[0].length; i++){
                princess[0][i] = readInt();
                cards[princess[0][i]] = 1;
            }
            for(int i = 0; i < prince[0].length; i++){
                prince[0][i] = readInt();
                cards[prince[0][i]] = 1;
            }
            Arrays.sort(princess[0]);
            Arrays.sort(prince[0]);
            
            princesswins = 0;
            
            for(int i = 0; i < princess[0].length; i++)
                for(int j = 1; j >= 0; j--)
                    if(prince[1][j] == 0)
                        if(princess[0][i] > prince[0][j]){
                            princesswins++;
                            princess[1][i] = 1;
                            prince[1][j] = 1;
                            break;
                        }
            if(princesswins == 2)
                System.out.println("-1");
            else{
                if(princesswins == 0){
                    for(int i = 1; i < cards.length; i++)
                        if(cards[i] != 1){
                            System.out.println(i);
                            break;
                        }
                } else {
                    tobeat = 0;
                    for(int i = 1; i >= 0; i--)
                        if(prince[1][i] == 0)
                            for(int j = 0; j < princess[1].length; j++)
                                if(prince[0][i] > princess[0][j]){
                                    princess[1][j] = 1;
                                    for(int k = 0; k < princess[1].length; k++)
                                        if(princess[1][k] == 0)
                                            tobeat = princess[0][k];
                                }
                    while(tobeat < cards.length && cards[tobeat] ==1)
                        tobeat++;
                    System.out.println(tobeat != -1 && tobeat <= 52 ? tobeat : -1);
                }
            }
        }
    }
    
    static int readInt() throws IOException{
        if(!st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return Integer.parseInt(st.nextToken());
    }
    
}
