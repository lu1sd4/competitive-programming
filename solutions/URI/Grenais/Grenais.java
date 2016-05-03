import java.io.*;
import java.util.*;

public class Grenais {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = 0, I = 0, G = 0, D = 0, gI, gG;
        String ans = "1";
        while(ans.equals("1")){
            st = new StringTokenizer(br.readLine());
            gI = Integer.parseInt(st.nextToken());
            gG = Integer.parseInt(st.nextToken());
            if(gI > gG)
                I++;
            else if(gG > gI)
                G++;
            else
                D++;
            N++;
            System.out.println("Novo grenal (1-sim 2-nao)");
            ans = br.readLine();
        }
        System.out.println(N+" grenais");
        System.out.println("Inter:"+I);
        System.out.println("Gremio:"+G);
        System.out.println("Empates:"+D);
        if(I>G)
            System.out.println("Inter venceu mais");
        else if(G>I)
            System.out.println("Gremio venceu mais");
        else
            System.out.println("Não houve vencedor");
    }
    
}
