import java.io.*;
import java.util.*;

public class royale {
    
    static BufferedReader br;
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException{
        
        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input.txt"));
        
        int[] nums;
        String line;
        StringBuilder sb;
        int[] inds = {1,5,3,4,2,9,7,8,6,10,11,15,13,14,12,19,17,18,16,50,51,55,53,54,52,59};
        while((line = br.readLine()) != null){
            int ind = 0;
            nums = new int[26];
            sb = new StringBuilder();
            for(int i = 0; i < line.length(); i++){
                char c = line.charAt(i);
                if(nums[c-97] == 0){
                    sb.append(inds[ind]);
                    nums[c-97] = inds[ind];
                    ind++;
                } else {
                    sb.append(nums[c-97]);
                }
            }
            System.out.println(sb.toString());
        }
        
    }
    
}
