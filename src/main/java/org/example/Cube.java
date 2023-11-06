package org.example;

public class Cube {

  private String[] cubeState;

  private static final int UP = 0;
  private static final int DOWN = 1;
  private static final int LEFT = 2;
  private static final int RIGHT = 3;
  private static final int FRONT = 4;
  private static final int BACK = 5;

  public Cube() {
    cubeState = new String[6];
    cubeState[UP] = "yyyy";
    cubeState[DOWN] = "wwww";
    cubeState[LEFT] = "bbbb";
    cubeState[RIGHT] = "gggg";
    cubeState[FRONT] = "rrrr";
    cubeState[BACK] = "oooo";
  }

  public String getState() {
    return "U: " + cubeState[UP] + " || " +
        "D: " + cubeState[DOWN] + "\n" +
        "L: " + cubeState[LEFT] + " || " +
        "R: " + cubeState[RIGHT] + "\n" +
        "F: " + cubeState[FRONT] + " || " +
        "B: " + cubeState[BACK];
  }

  public void up() {
    // [U2U0U3U1 | D0D1D2D3 | F0F1L2L3 | B0B1R2R3 | R0R1F2F3 | L0L1B2B3]
    upType(false);
  }

  public void upPrime() {
//     [U1U3U0U2 | D0D1D2D3 | B0B1L2L3 | F0F1R2R3 | L0L1F2F3 | R0R1B2B3]
    upType(true);
  }

  public void down() {
    // [U0U1U2U3 | D2D0D3D1 | L0L1B2B3 | R0R1F2F3 | F0F1L2L3 | B0B1R2R3]
    downType(false);
  }

  public void downPrime() {
    downType(true);
  }

  public void downType(boolean prime) {
    String[] newState = new String[6];
    newState[UP] = cubeState[UP];
    int a = RIGHT;
    int b = LEFT;
    if (prime) {
      newState[DOWN] = counterclockwise(cubeState[DOWN]);
      a = LEFT;
      b = RIGHT;
    } else {
      newState[DOWN] = clockwise(cubeState[DOWN]);
    }
    horizontal(false, newState, a, b);
    cubeState = newState;
  }

  public void upType(boolean prime) {
    String[] newState = new String[6];
    newState[DOWN] = cubeState[DOWN];
    int a;
    int b;
    if (prime) {
      newState[UP] = counterclockwise(cubeState[UP]);
      a = RIGHT;
      b = LEFT;
    } else {
      newState[UP] = clockwise(cubeState[UP]);
      a = LEFT;
      b = RIGHT;
    }
    horizontal(true, newState, a, b);
    cubeState = newState;
  }

  private void horizontal(boolean up, String[] newState, int a, int b) {
    if (up) {
      newState[FRONT] = topAndBottom(cubeState[b], cubeState[FRONT]);
      newState[BACK] = topAndBottom(cubeState[a], cubeState[BACK]);
      newState[a] = topAndBottom(cubeState[FRONT], cubeState[a]);
      newState[b] = topAndBottom(cubeState[BACK], cubeState[b]);
    } else {
      newState[a] = topAndBottom(cubeState[a], cubeState[FRONT]);
      newState[b] = topAndBottom(cubeState[b], cubeState[BACK]);
      newState[FRONT] = topAndBottom(cubeState[FRONT], cubeState[b]);
      newState[BACK] = topAndBottom(cubeState[BACK], cubeState[a]);
    }
  }

  private String topAndBottom(String t, String b) {
    return t.substring(0,2) + b.substring(2,4);
  }

  public String clockwise(String s) {
    return s.substring(2,3) + s.charAt(0) + s.charAt(3) + s.charAt(1);
  }

  private String counterclockwise(String s) {
    return s.substring(1,2) + s.charAt(3) + s.charAt(0) + s.charAt(2);
  }

  public void left() {
    // TODO: Build this
  }

  public void leftPrime() {
    // TODO: Build this
  }

  public void right() {
    // TODO: Build this
  }

  public void rightPrime() {
    // TODO: Build this
  }

  public void front() {
    // TODO: Build this
  }

  public void frontPrime() {
    // TODO: Build this
  }

  public void back() {
    // TODO: Build this
  }

  public void backPrime() {
    // TODO: Build this
  }
}
