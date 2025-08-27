import java.util.*;

public class Main {
    public static void main(String[] args) {
      
      Scanner sc = new Scanner(System.in);
      
      int n = sc.nextInt();
      
      int[] arr = new int[n];
      
      for(int i=0; i<n; i++){
        arr[i] = sc.nextInt();
      }
      
      int k = sc.nextInt();
      
      int ans = findMinCost(arr, n, k);
      
      System.out.println(ans);
  }
  
  public static int findMinCost(int[] arr, int n, int k){
    
    int minCost = Integer.MAX_VALUE;
    
    int totalOnes = 0;
    
    int ans = 0;
    
    int i=0, j=0;
    
    int cost = 0;
    
    while(j < n){
      
      cost += arr[j];
      
      if(arr[j] == 1){
        totalOnes++;
      }
      
      if(j-i+1 == k){
        minCost = Math.min(minCost, cost);
        cost -= arr[i];
        i++;
      }
      j++;
    }
    ans += (minCost * (minCost+1))/2;
  
    ans += (totalOnes - minCost);
    
    return ans;
  }
}