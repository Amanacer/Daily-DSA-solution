import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        HashMap<String, Integer> map = new HashMap<>();
        String[] words = str.split(" ");
        boolean found = false;

        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.print(entry.getKey() + " ");
                found = true;
            }
        }

        if (!found) {
            System.out.println("NA");
        }

        sc.close();
    }
}
