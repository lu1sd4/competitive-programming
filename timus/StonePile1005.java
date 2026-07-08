import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StonePile1005 {

    BufferedReader br;
    
    public StonePile1005(){
        
    }
    
    private void resolver() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new InputStreamReader(new FileInputStream("src/input.txt")));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numeros[] = new int[n];
        int minDif = Integer.MAX_VALUE;
        int dif;
        int bits = 1;
        for(int i = 0; i < n; i++) 
            numeros[i] = Integer.parseInt(st.nextToken());
        while(minDif > 0 && bits <= Math.pow(2,n)){
            dif = 0;
            for(int i = 0; i < n; i++){
                if(((bits >> i) & 1) == 1) 
                    dif+=numeros[i];
                else 
                    dif -=numeros[i];
            }
            dif = Math.abs(dif);
            if(dif < minDif)
                minDif = dif;
            bits++;
        }
        
        System.out.println(minDif);
        
    }
    
    private void correr() throws IOException{
        resolver();
    }
    
    public static void main(String args[]) throws IOException{
        new StonePile1005().correr();
    }
    
}