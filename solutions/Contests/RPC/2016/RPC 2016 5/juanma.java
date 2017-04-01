import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class juanma {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            double n = Double.parseDouble(br.readLine());
            double ans = (Math.pow(n,4) - 6 * Math.pow(n, 3) + 23 * Math.pow(n, 2) - 18 * n + 24)/24;
            System.out.println((int)ans);
        }
    }

}
