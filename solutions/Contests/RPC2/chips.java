import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class chips {

    public static void main(String Args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringTokenizer st;
        int N, B, T;
        while((line=br.readLine()) != null){
            int eatenB = 0, eatenT = 0;
            int bowlB = 0, bowlT = 0;
            st = new StringTokenizer(line);
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());
            while(N > 0){
                if(bowlB == 0){
                    if(B > N){
                        bowlB += N;
                        N = 0;
                    }
                    else{
                        bowlB += B;
                        N -= B;
                    }
                } else {
                    bowlB--;
                    eatenB++;
                }
                if(bowlT == 0){
                    if(T > N){
                        bowlT += N;
                        N = 0;
                    } else {
                        bowlT += T;
                        N -= T;
                    }
                } else {
                    bowlT--;
                    eatenT++;
                }
            }
            if(bowlB > 0)
                eatenB += bowlB;
            if(bowlT > 0)
                eatenT += bowlT;
            System.out.println(Integer.toString(eatenB)+" "+Integer.toString(eatenT));
        }
    }
    
}
