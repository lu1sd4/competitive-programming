
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Hyperjump1296 {
    BufferedReader br;
    StringTokenizer st;
    
    public Hyperjump1296(){
        
    }
    
    private void resolver() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new InputStreamReader(new FileInputStream("src/input.txt")));
        int n = Integer.parseInt(br.readLine());
        int max = 0;
        int p;
        int sum = 0;
        for(int i = 0; i < n; i++){
            p = Integer.parseInt(br.readLine());
            sum += p;
            if(sum < 0)
                sum = 0;
            if(max < sum)
                max = sum;
        }
        System.out.println(max);
    }
    
    private void correr() throws IOException{
        resolver();
    }
    
    public static void main(String args[]) throws IOException{
        new Hyperjump1296().correr();
    }
}
