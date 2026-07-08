import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AMasB1000 {

    BufferedReader br;
    StringTokenizer st;
    
    public AMasB1000(){
        
    }
    
    private void resolver() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        System.out.println((Integer.parseInt(st.nextToken())+Integer.parseInt(st.nextToken())));
    }
    
    private void correr() throws IOException{
        resolver();
    }
    
    public static void main(String args[]) throws IOException{
        new AMasB1000().correr();
    }
}
    