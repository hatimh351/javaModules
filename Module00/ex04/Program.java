import java.util.Scanner;

public class Program{


    static void parseInput(String inp) throws Exception {
        
        int len = inp.length();

        if (len <= 2 && inp.equals("42"))
            throw new Exception("Invalid Input");

        
        for (int i = 0; i < len - 2; i++){
            char c = inp.charAt(i);

            if (!(c >= 'A' && c <= 'Z'))
                throw new Exception("Invalid Input");
            }
        
            if (inp.charAt(len - 1) != '2' || inp.charAt(len - 2) != '4')
                throw new Exception("Invalid Input");
        }


    static  int [] countCharacters(String s){
        int len = s.length() - 2;


        int [] arr = new int [26];


        for (int i = 0; i < len;i++){
            char c = s.charAt(i);
            arr[c -  'A']++;
        }
    
        return arr;
    }
    public static int getMax(int [] arr){
        int len = arr.length;
        int max = arr[0];
        for (int i = 0;i < len;i++){
            if (max < arr[i])
                max = arr[i];
        }
        return max;
    }

    public static int [] getResults(int [] count, int max){
        int [] res = new int [10 * 30];
        int [] arr = new int [26];
        for (int i = 0; i < 26; i++)
            arr[i] = ('A' + i);


        for (int i = 1; i < 26; i++){

            if (count[i - 1] < count[i])
            {
                int tmp = count[i];
                count[i] = count[i - 1];
                count[i - 1] = tmp;


                tmp = arr[i];
                arr[i] = arr[i - 1];
                arr[i - 1] = tmp;

                i = 0;
            }
        }

        for (int i = 0; i < 10; i++){
            res[i] = arr[i];
            res[i + 10] = count[i];
            res[i + 20] = (count[i] * 10) / max;
        }
        return res;
    }


    public static void printRes(int [] res){

        int current_index = 0;

        for (int i = 10; i >= 0; i--){

            while(current_index < 10 &&res[current_index + 20] == i)
            {
                if (res[current_index + 10] <= 10)
                    System.out.print(" ");
                System.out.printf(" %d", res[current_index + 10]);
                current_index++;
            }
            System.out.print("\n");
            if (i != 0)
            {
                for (int j = 0; j < current_index; j++){
                    System.out.print("  #");
                }
            }
        }

        for (int i = 0; i < 10; i++){
            System.out.printf("  %c", res[i]);
        }
        System.out.print("\n");
    }


    public static void main(String[] args) {
        String inp;

        try (Scanner scanner = new Scanner(System.in))
        {
            System.out.printf("-> ");
            inp = scanner.nextLine();
            parseInput(inp);
            int [] count = countCharacters(inp);
            int [] res = getResults(count, getMax(count));
            printRes(res);
        }
        catch ( Exception error ) {

            System.err.println(error.getMessage());
            System.exit(-1);
        }

        
    }




}