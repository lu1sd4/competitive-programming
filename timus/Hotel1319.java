import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Hotel1319 {

    BufferedReader br;
    StringTokenizer st;
    
    public Hotel1319(){
        
    }
    
    private void resolver() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int ans[][] = new int[n][n];
        int num = 1;
        for(int i = 0; i < (2*n-1); i++){
            if(i <= (n-1))
                for(int j = i; j >= 0; j--){
                    ans[i-j][n-j-1] = num;
                    num++;
                }
            else
                for(int j = 2*n-2-i; j >= 0; j--){
                    ans[n-j-1][2*n-2-i-j] = num;
                    num++;
                }
        }
        for(int i = 0; i < ans.length; i++){
            for(int j = 0; j < ans[i].length; j++)
                System.out.print(ans[i][j]+" ");
            System.out.print("\n");
        }
    }
    
    private void correr() throws IOException{
        resolver();
    }
    
    public static void main(String args[]) throws IOException{
        new Hotel1319().correr();
    }
}
    