import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProductOfDigits {

    BufferedReader br;
    
    public ProductOfDigits(){
        
    }
    
    private String resolver() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        int numero = Integer.parseInt(br.readLine());
        if(numero == 0)
            return "10";
        if(numero < 10)
            return Integer.toString(numero);
        String q = "";
        for(int i = 9; i > 1; i--){
            if(numero % i == 0){
                q = Integer.toString(i) + q;
                numero = numero/i;
                i++;
            }
        }
        if(numero > 1)
            return "-1";
        else
            return q;
    }
    
    private void correr() throws IOException{
        System.out.println(resolver());
    }
    
    public static void main(String args[]) throws IOException{
        new ProductOfDigits().correr();
    }
    
}