package org.example;

public class CubeNode {

  int cost;
  Cube cube;
  String path;
  Integer h;
  int f;

  public CubeNode(Cube cube, int cost, String path) {
    this.cube = cube;
    this.cost = cost;
    this.path = path;
    this.f = computeFValue();
  }

  private int computeFValue() {
    this.h = computeHeuristic();
    return h + cost;
  }

  private int computeHeuristic() {
    // TODO: Implement
    return 0;
  }

  public int f_value() {
    return f;
  }

}
