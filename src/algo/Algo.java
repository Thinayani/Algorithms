// w1789984_2019714

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

/**
 *
 * @author thina
 */
import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.LinkedList;

public class Algo {
    // Java program for implementation of Ford Fulkerson
// algorithm

 
    static int V; // Number of nodes in graph
 
    /* Returns true if there is a path from source 's' to
      sink 't' in residual graph. Also fills parent[] to
      store the path */
    boolean bfs(int rGraph[][], int s, int t, int parent[]) //breath first search
    {
        // Create a visited array 
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;
 
        // source vertex as visited
        LinkedList<Integer> queue
            = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;
 
        // BFS Loop
        while (queue.size() != 0) {
            int u = queue.poll();
 
            for (int v = 0; v < V; v++) {
                if (visited[v] == false
                    && rGraph[u][v] > 0) {
                    if (v == t) {
                        parent[v] = u;
                        return true;
                    }
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
 
        return false;
    }
 
    // Returns tne maximum flow from s to t 
    int fordFulkerson(int graph[][], int s, int t)
    {
        
        int u, v;
 
        // Create a residual graph
        int rGraph[][] = new int[V][V];
 
        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];
  
 
        // This array is filled by BFS 
        int parent[] = new int[V];
 
        int max_flow = 0; // There is no flow initially
 
        while (bfs(rGraph, s, t, parent)) {
            // Find minimum residual capacity 
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow
                    = Math.min(path_flow, rGraph[u][v]);
            }
 
            // update residual capacities of the edges 
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }
 
            // Add path flow to overall flow
            max_flow += path_flow;
        }
 
        return max_flow;
    }
 
    // Driver program to test above functions
    public static void main(String[] args) 
        throws java.lang.Exception
    {
        Scanner inputFile = null;
        
	try {
            inputFile = new Scanner(new File(args[0]));
        } 
        catch (FileNotFoundException e) {
            System.out.println("File not Found !");
            
        }
        
        ArrayList<Integer> myArray = new ArrayList();
        ArrayList<Integer> array1 = new ArrayList();
        ArrayList<Integer> array2 = new ArrayList();
        ArrayList<Integer> array3 = new ArrayList();
        
        while (inputFile.hasNextInt()) {
            int data = inputFile.nextInt();
            myArray.add(data);
        }
        
        System.out.println("-------------------------------------");
        System.out.println("Number of Nodes is " + myArray.get(0));
        System.out.println("-------------------------------------");
        System.out.println("");
        System.out.println(myArray);
        
        V = myArray.get(0);
        myArray.remove(0);
        
        System.out.println(myArray);
        System.out.println("");
        
        //Seperating arrayList in to three arrayList
        for(int i=0; i<myArray.size(); i= i+3) {
            array1.add(myArray.get(i));
            array2.add(myArray.get(i+1));
            array3.add(myArray.get(i+2));
        }
        System.out.println(array1);
        System.out.println(array2);
        System.out.println(array3);
        System.out.println("");
        
        int tGraph[][] = new int[V][V];
        
        for (int i = 0; i < array1.size(); i++) {
            tGraph[array1.get(i)][array2.get(i)] = array3.get(i);
        }
        
        for (int[] row : tGraph){
                   System.out.println(Arrays.toString(row)); 
                 }
        
        
        Algo m = new Algo();
            
        long start = System.currentTimeMillis();
        
        System.out.println("-------------------------------------");
        System.out.println("The maximum possible flow is " + m.fordFulkerson(tGraph, 0, V-1));    
        System.out.println("-------------------------------------");
        System.out.println("");
        
        long now = System.currentTimeMillis();
        double elapsed = (now - start) / 1000.0;
        
        System.out.println("Time taken to run the FordFulkerson Algorithm : " + elapsed);
       
    }
}
    