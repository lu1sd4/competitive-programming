import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class eggs {

    public static void main(String[] args) throws IOException{
   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int N;
        boolean[] primes = new boolean[1000001];
        int[] largestPrimeF = new int[primes.length];
        Arrays.fill(primes, true);
        primes[0] = false;
        largestPrimeF[1] = 1;
        for (int i = 2; i <= 1000000; i++) {
            if (primes[i]) {
                int k = 1;
                for (int j = i; j*k <= 1000000; k++) {
                    primes[j*k] = false;
                    largestPrimeF[j*k] = j;
                }
            }
        }
        System.out.println("end");
        while((line = br.readLine()) != null){
            N = Integer.parseInt(line);
            int batches = 0;
            while(N > 0){
                N -= largestPrimeF[N];
                batches++;
            }
            System.out.println(batches);
        }//*/
    }
    
}
