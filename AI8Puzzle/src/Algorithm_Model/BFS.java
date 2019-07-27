package Algorithm_Model;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/**
 *
 * @author Aghapy
 */
public class BFS implements Algorithm{

    @Override
    public ArrayList<Node> search(int [] board, Node parent ) {
        Queue<Node> queue = new LinkedList<Node>();
        Node root = new Node(board, null);
        queue.add(root);

        performSearch(queue);

        return null;
    }
        /*
         * Helper method to check to see if a SearchNode has already been evaluated.
         * Returns true if it has, false if it hasn't.
         */
        private static boolean checkRepeats(Node n) {
            boolean retValue = false;
            Node checkNode = n;

            // While n's parent isn't null, check to see if it's equal to the node
            // we're looking for.
            while (n.getParent() != null && !retValue) {
                if (n.getParent().getState().equals(checkNode.getState())) {
                    retValue = true;
                }
                n = n.getParent();
            }

            return retValue;
        }

        /**
         * Performs a BFSearch using q as the search space
         *
         * @param q - A SearchNode queue to be populated and searched
         */
        public static void performSearch(Queue<Node> q) {
            int searchCount = 1; // counter for number of iterations

            while (!q.isEmpty()) // while the queue is not empty
            {
                //System.out.println("Ssize of queue"+q.size());
                Node tempNode = q.poll();

                if (!tempNode.isGoal()) // if tempNode is not the goal
                // state
                {
                    System.out.println(tempNode.getState()[0]+" "+tempNode.getState()[1]+" "+tempNode.getState()[2]+"\n"+
                            tempNode.getState()[3]+" "+tempNode.getState()[4]+" "+tempNode.getState()[5]+"\n"+
                            tempNode.getState()[6]+" "+tempNode.getState()[7]+" "+tempNode.getState()[8]+"\n"
                    );
                    tempNode.generateChildren();
                    ArrayList<Node> Children = tempNode.getChildren();
                    for (int i = 0; i < Children.size(); i++) {
                        int [] qq = Children.get(i).getState();

                        // second parameter here adds the cost of the new node to
                        // the current cost total in the SearchNode
                        Node newNode = new Node(qq ,null);

                        if (!checkRepeats(newNode)) {
                            q.add(newNode);
                        }
                    }
                    searchCount++;
                } else
                // The goal state has been found. Print the path it took to get to
                // it.
                {
                    // Use a stack to track the path from the starting state to the
                    // goal state
                    Stack<Node> solutionPath = new Stack<Node>();
                    solutionPath.push(tempNode);
                    tempNode = tempNode.getParent();

                    while (tempNode.getParent() != null) {
                        solutionPath.push(tempNode);
                        tempNode = tempNode.getParent();
                    }
                    solutionPath.push(tempNode);

                    // The size of the stack before looping through and emptying it.
                    int loopSize = solutionPath.size();

                    for (int i = 0; i < loopSize; i++) {
                        tempNode = solutionPath.pop();
                        tempNode.getState();
                        System.out.println();
                        System.out.println();
                    }

                    System.exit(0);
                }
            }

            // This should never happen with our current puzzles.
            System.out.println("Error! No solution found!");
        }


}
