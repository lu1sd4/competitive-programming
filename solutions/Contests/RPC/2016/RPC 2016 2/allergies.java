import java.io.*;
import java.util.*;

public class allergies {
    
    public static Set<Character> stringtocharset(String s){
        Set<Character> set = new HashSet<>();
        for(char a : s.toCharArray())
            set.add(a);
        return set;
    }

    public static void main(String[] args) throws IOException{
        
        char[] all = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-- >0){
            Set<Character> allergicto = stringtocharset(br.readLine());
            int F = Integer.parseInt(br.readLine());
            Set<Set<Character>> foodSet = new HashSet<>();
            Set<Set<Character>> universe = new HashSet<>();
            while(F-- > 0){
                Set<Character> food = stringtocharset(br.readLine());
                universe.add(food);
                if(!food.containsAll(allergicto))
                    foodSet.add(food);
            }
            /*int c;
            int x = 0;
            for(char a : allergens){
                c = 0;
                for(Set<Character> f : foodSet){
                    if(f.contains(a)){
                        break;
                    } else {
                        c++;
                    }
                }
                if(c == foodSet.size()){
                    System.out.println(a);
                    break;
                }
                x++;
            }
            if(x == allergens.size())
                System.out.println("No Solution");//*/
            boolean sol = false;
            Set<Set<Character>> gen;
            for(char a : all){
                gen = new HashSet<>();
                for(Set<Character> f : universe){
                    if(!f.contains(a))
                        gen.add(f);
                }
                if(gen.size() == foodSet.size())
                    if(gen.containsAll(foodSet)){
                        System.out.println(a);
                        sol = true;
                        break;
                    }
            }
            if(!sol)
                System.out.println("No Solution");//*/
            
        }
    }
    
}
