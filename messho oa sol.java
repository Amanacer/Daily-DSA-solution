import java.util.*;

public class PerfectSquarePairsCounter {
    // Maximum number we'll need to factorize
    private static final int MAX_NUMBER = 1_000_001;
    
    // Stores the smallest prime factor for each number (precomputed)
    private static int[] smallestPrimeFactor = new int[MAX_NUMBER];
    
    // Counts how many valid pairs we find
    private static long validPairsCount = 0;
    
    // Tracks how many times we've seen each "square-free" value
    private static Map<Long, Long> squareFreeFrequency = new HashMap<>();

    public static void main(String[] args) {
        // Step 1: Prepare our prime number sieve
        preparePrimeSieve();
        
        Scanner scanner = new Scanner(System.in);
        
        // Step 2: Read input and process each node's value
        int totalNodes = scanner.nextInt();
        int[] nodeSquareFreeValues = calculateSquareFreeValues(scanner, totalNodes);
        
        // Step 3: Build the tree structure
        List<Integer>[] tree = buildTreeStructure(scanner, totalNodes);
        
        // Step 4: Traverse the tree and count valid pairs
        countPerfectSquarePairs(tree, nodeSquareFreeValues);
        
        System.out.println(validPairsCount);
        scanner.close();
    }

    // ========================
    // Prime Number Preparation
    // ========================
    
    private static void preparePrimeSieve() {
        // Initially assume each number is prime (its own smallest factor)
        for (int i = 2; i < MAX_NUMBER; i++) {
            smallestPrimeFactor[i] = i;
        }
        
        // Sieve of Eratosthenes algorithm
        for (int i = 2; i * i < MAX_NUMBER; i++) {
            if (smallestPrimeFactor[i] == i) { // If i is prime
                // Mark all multiples of i
                for (int j = i * i; j < MAX_NUMBER; j += i) {
                    if (smallestPrimeFactor[j] == j) { // If not already marked
                        smallestPrimeFactor[j] = i;
                    }
                }
            }
        }
    }

    // =============================
    // Calculate Square-Free Values
    // =============================
    
    private static int[] calculateSquareFreeValues(Scanner scanner, int nodeCount) {
        int[] squareFreeValues = new int[nodeCount];
        
        for (int i = 0; i < nodeCount; i++) {
            int value = scanner.nextInt();
            squareFreeValues[i] = computeSquareFreeValue(value);
        }
        
        return squareFreeValues;
    }
    
    private static int computeSquareFreeValue(int value) {
        Map<Integer, Integer> primeFactors = getPrimeFactorization(value);
        int squareFree = 1;
        
        // A number is a perfect square if all exponents in its prime 
        // factorization are even. The "square-free" part is the product
        // of primes with odd exponents.
        for (Map.Entry<Integer, Integer> entry : primeFactors.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                squareFree *= entry.getKey();
            }
        }
        
        return squareFree;
    }
    
    private static Map<Integer, Integer> getPrimeFactorization(int value) {
        Map<Integer, Integer> factors = new HashMap<>();
        
        while (value != 1) {
            int prime = smallestPrimeFactor[value];
            factors.put(prime, factors.getOrDefault(prime, 0) + 1);
            value /= prime;
        }
        
        return factors;
    }

    // =================
    // Tree Construction
    // =================
    
    private static List<Integer>[] buildTreeStructure(Scanner scanner, int nodeCount) {
        // Create adjacency list for the tree
        List<Integer>[] tree = new ArrayList[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            tree[i] = new ArrayList<>();
        }
        
        // Read and build all edges
        for (int i = 1; i < nodeCount; i++) {
            int node1 = scanner.nextInt();
            int node2 = scanner.nextInt();
            tree[node1].add(node2);
            tree[node2].add(node1);
        }
        
        return tree;
    }

    // ==========================
    // Counting Valid Pairs Logic
    // ==========================
    
    private static void countPerfectSquarePairs(List<Integer>[] tree, int[] squareFreeValues) {
        boolean[] visited = new boolean[tree.length];
        depthFirstSearch(0, visited, tree, squareFreeValues);
    }
    
    private static void depthFirstSearch(int currentNode, boolean[] visited, 
                                       List<Integer>[] tree, int[] squareFreeValues) {
        visited[currentNode] = true;
        long currentSquareFree = squareFreeValues[currentNode];
        
        // The key insight: two nodes (a,b) form a valid pair if:
        // squareFree(a) == squareFree(b)
        // Because then a*b will have all even exponents in its prime factorization
        
        // Count how many nodes we've seen with this square-free value
        validPairsCount += squareFreeFrequency.getOrDefault(currentSquareFree, 0L);
        
        // Record that we've seen this square-free value
        squareFreeFrequency.put(currentSquareFree, 
                              squareFreeFrequency.getOrDefault(currentSquareFree, 0L) + 1);
        
        // Explore all connected nodes
        for (int neighbor : tree[currentNode]) {
            if (!visited[neighbor]) {
                depthFirstSearch(neighbor, visited, tree, squareFreeValues);
            }
        }
        
        // Backtrack - remove this node's square-free value as we leave
        squareFreeFrequency.put(currentSquareFree, 
                              squareFreeFrequency.get(currentSquareFree) - 1);
    }
}