import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sum1068 {

    BufferedReader br;
    
    public Sum1068(){
        
    }
    
    private String resolver() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if(n==0)
            return "1";
        if(n>0)
            return Integer.toString(progresion(1, n));
        else
            return Integer.toString(1-progresion(1, -n));
    }
    
    private int progresion(int i, int n){
        return (n*(i+n))/2;
    } 
    
    private void correr() throws IOException{
        System.out.println(resolver());
    }
    
    public static void main(String args[]) throws IOException{
        new Sum1068().correr();
    }
    
}