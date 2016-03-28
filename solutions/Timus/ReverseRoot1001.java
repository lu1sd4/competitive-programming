import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Stack;

public class ReverseRoot1001 {

    StreamTokenizer st;
    
    public ReverseRoot1001(){
        
    }
    
    private void resolver() throws IOException{
        st = new StreamTokenizer(new InputStreamReader(System.in));
        //st = new StreamTokenizer(new InputStreamReader(new FileInputStream("src/input.txt")));
        int token;
        Stack<Double> s = new Stack<>();
        while((token = st.nextToken()) != StreamTokenizer.TT_EOF)
            s.push(Math.sqrt((double)st.nval));
        while(!s.isEmpty())
            System.out.printf("%.4f\n",s.pop());
    }
    
    private void correr() throws IOException{
        resolver();
    }
    
    public static void main(String args[]) throws IOException{
        new ReverseRoot1001().correr();
    }
}
    