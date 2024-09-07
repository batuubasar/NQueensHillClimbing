# NQueensHillClimbing

## Project Objectives

This project utilizes two different algorithms to solve two classic problems:

1. **Eight Queens Problem**: Solved using the Hill Climbing algorithm. The goal is to place eight queens on a chessboard in such a way that no two queens threaten each other.

2. **Bridge and Torch Problem**: Solved using the A* algorithm. This problem involves finding the shortest path for individuals to cross a bridge at night with limited resources.

## Hill Climbing and Stochastic Hill Climbing

**Hill Climbing**: A local search algorithm that moves to the best neighbor at each step. However, it risks getting stuck in local maxima.

**Stochastic Hill Climbing**: A variant of the classic Hill Climbing algorithm. Instead of always moving to the best neighbor, it randomly selects a neighbor, which helps to avoid local maxima.

### Stochastic Hill Climbing Implementation

In classic Hill Climbing, the `getNeighbour` function selects the best neighbor. In Stochastic Hill Climbing, this function is modified to randomly select a neighbor:

```java
public State getNeighbour(State currentState) {
    int N = currentState.getSize();
    int[] neighbourState = currentState.cloneState();
    
    int randomIndex = (int) (Math.random() * N);
    neighbourState[randomIndex] = (int) (Math.random() * N);
    
    return new State(neighbourState);
}
