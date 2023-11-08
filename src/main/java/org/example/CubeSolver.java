package org.example;

public class CubeSolver {

  public String solve(Cube cube) {


    // Create our data structure to track visited/expanded states
    //
    //    Variable to tell when we are done
    //
    //    Main search loop.  Keep going as long as we are not done and the FRINGE isn't empty
    //    while not done and not fringe.empty():
    //
    //        Get the next SearchNode from the FRINGE
    //
    //        Add it to our set of visited/expanded states (join creates a string from the state)
    //
    //        Don't continue if the cost is too much
    //
    //        Check to see if this node's puzzle state is a goal state
    //            if cur_node.puzzle.is_solved():
    //            It is! We are done, return solution
    //
    //            Generate this SearchNode's successors and add them to the FRINGE
    //
    //            Get the possible moves (actions) for this state
    //
    //            For each move, do the move, create SearchNode from successor, then add to FRINGE
    //                Create new puzzle that new node will point to
    //
    //                Execute the move/action
    //
    //                Add to the FRINGE, as long as we haven't visited that puzzle
    //                    Create the new SearchNode
    //
    //                    Add it to the FRINGE, along with its f-value (stored inside the node)
    //
    //    We didn't find a solution
    //    if not done:
    return null;
  }

}
