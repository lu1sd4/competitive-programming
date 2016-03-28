
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SuperLongSums1048 {

    BufferedReader br;
    
    public SuperLongSums1048(){
        
    }
    
    private void resolver() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new InputStreamReader(new FileInputStream("src/input.txt")));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n1, n2, sum, j;
        int cifraAnterior;
        char[] resultado = new char[n];
        n1 = Integer.parseInt(st.nextToken());
        n2 = Integer.parseInt(st.nextToken());
        sum = n1 + n2;
        resultado[0] =  Character.forDigit(sum, 10);
        for(int i = 1; i < n; i++){
            j = i;
            st = new StringTokenizer(br.readLine());
            n1 = Integer.parseInt(st.nextToken());
            n2 = Integer.parseInt(st.nextToken());
            sum = n1 + n2;
            resultado[i] = Character.forDigit(sum%10, 10);
            while(sum >= 10){
                j--;
                cifraAnterior = resultado[j];
                sum = Character.digit(cifraAnterior, 10) + 1;
                resultado[j] = Character.forDigit(sum%10, 10);
            }
        }
        String s = new String(resultado);
        System.out.println(s);
    }
    
    private void correr() throws IOException{
        resolver();
    }
    
    public static void main(String args[]) throws IOException{
        new SuperLongSums1048().correr();
    }
    
}

