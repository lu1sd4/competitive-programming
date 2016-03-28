import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SinusDances1149 {

    BufferedReader br;
    public SinusDances1149(){
        
    }
    
    private void resolver() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        s(n);
    }
    
    private void s(int n){
        for(int i = 1; i < n; i++)
            System.out.print("(");
        for(int i = 1; i < n; i++){
            a(i);
            System.out.print("+"+(n-i+1)+")");
        }
        a(n);
        System.out.print("+1");
    }
    
    private void a(int n){
        for(int i = 1; i <= n; i++){
            if(i==n)
                System.out.print("sin("+Integer.toString(i));
            else
                if(i%2 == 0)
                    System.out.print("sin("+Integer.toString(i)+"+");
                else
                    System.out.print("sin("+Integer.toString(i)+"-");
        }
        for(int i = 0; i < n; i++)
            System.out.print(")");
    }
    
    private void correr() throws IOException{
        resolver();
    }
    
    public static void main(String args[]) throws IOException{
        new SinusDances1149().correr();
    }
    
}