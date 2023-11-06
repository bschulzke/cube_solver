package org.example;

public class Cube {

  private String[] cubeState;

  private final int UP = 0;
  private final int DOWN = 1;
  private final int LEFT = 2;
  private final int RIGHT = 3;
  private final int FRONT = 4;
  private final int BACK = 5;

  public Cube() {
    cubeState = new String[6];
    cubeState[UP] = "yyyy";
    cubeState[DOWN] = "wwww";
    cubeState[LEFT] = "bbbbb";
    cubeState[RIGHT] = "gggg";
    cubeState[FRONT] = "rrrr";
    cubeState[BACK] = "oooo";
  }

  public String getState() {
    return "Up-side: " + cubeState[UP] + "\n" +
        "Down-side: " + cubeState[DOWN] + "\n" +
        "Left-side: " + cubeState[LEFT] + "\n" +
        "Right-side: " + cubeState[RIGHT] + "\n" +
        "Front-side: " + cubeState[FRONT] + "\n" +
        "Back-side: " + cubeState[BACK];
  }

  public void up() {
    // [U2U0U3U1 | D0D1D2D3 | F0F1L2L3 | B0B1R2R3 | R0R1F2F3 | L0L1B2B3]
    upType(false, LEFT, RIGHT);
  }

  public void upPrime() {
//     [U1U3U0U2 | D0D1D2D3 | B0B1L2L3 | F0F1R2R3 | L0L1F2F3 | R0R1B2B3]
    upType(true, RIGHT, LEFT);
  }

  public void down() {
    // [U0U1U2U3 | D2D0D3D1 | L0L1B2B3 | R0R1F2F3 | F0F1L2L3 | B0B1R2R3]
    downType(false, RIGHT, LEFT);
  }

  public void downPrime() {
    downType(true, LEFT, RIGHT);
  }

  public void downType(boolean prime, int a, int b) {
    String[] newState = new String[6];
    newState[UP] = cubeState[UP];
    if (prime) {
      newState[DOWN] = counterclockwise(cubeState[DOWN]);
    } else {
      newState[DOWN] = clockwise(cubeState[DOWN]);
    }
    newState[a] = topAndBottom(cubeState[a], cubeState[FRONT]);
    newState[b] = topAndBottom(cubeState[b], cubeState[BACK]);
    newState[FRONT] = topAndBottom(cubeState[FRONT], cubeState[b]);
    newState[BACK] = topAndBottom(cubeState[BACK], cubeState[a]);
    cubeState = newState;
  }

  public void upType(boolean prime, int a, int b) {
    String[] newState = new String[6];
    newState[DOWN] = cubeState[DOWN];
    if (prime) {
      newState[UP] = counterclockwise(cubeState[UP]);
    } else {
      newState[UP] = clockwise(cubeState[UP]);
    }
    newState[a] = topAndBottom(cubeState[FRONT], cubeState[a]);
    newState[b] = topAndBottom(cubeState[BACK], cubeState[b]);
    newState[FRONT] = topAndBottom(cubeState[b], cubeState[FRONT]);
    newState[BACK] = topAndBottom(cubeState[a], cubeState[BACK]);
    cubeState = newState;
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

}
