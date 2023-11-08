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

  public Cube(String[] state) {
    cubeState = state;
  }

  public String getState() {
    return "U: " + cubeState[UP] + " || " +
        "D: " + cubeState[DOWN] + "\n" +
        "L: " + cubeState[LEFT] + " || " +
        "R: " + cubeState[RIGHT] + "\n" +
        "F: " + cubeState[FRONT] + " || " +
        "B: " + cubeState[BACK];
  }

  public boolean isSolved() {
    for (String face : cubeState) {
      if (!isSolved(face)) {
        return false;
      }
    }
    return true;
  }

  private boolean isSolved(String face) {
    char color = face.charAt(0);
    for (int i = 0; i < face.length(); i++) {
      if (color != face.charAt(i)) {
        return false;
      }
    }
    return true;
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
    // [B3U1B1U3 | F0D1F2D3 | L2L0L3L1 | R0R1R2R3 | U0F1U2F3 | B0D2B2D0]
    String[] newState = new String[6];
    newState[UP] = threeOneOnLeft(cubeState[UP], cubeState[BACK]);
    newState[DOWN] = zeroTwoOnLeft(cubeState[DOWN], cubeState[FRONT]);
    newState[LEFT] = clockwise(cubeState[LEFT]);
    newState[RIGHT] = cubeState[RIGHT];
    newState[FRONT] = zeroTwoOnLeft(cubeState[FRONT], cubeState[UP]);
    newState[BACK] = twoZeroOnRight(cubeState[BACK], cubeState[DOWN]);
    cubeState = newState;
  }

  public void leftPrime() {
    // [F0U1F2U3 | B3D1B1D3 | L1L3L0L2 | R0R1R2R3 | D0F1D2F3 | B0U2B2U0]
    String[] newState = new String[6];
    newState[UP] = zeroTwoOnLeft(cubeState[UP], cubeState[FRONT]);
    newState[DOWN] = threeOneOnLeft(cubeState[DOWN], cubeState[BACK]);
    newState[LEFT] = counterclockwise(cubeState[LEFT]);
    newState[RIGHT] = cubeState[RIGHT];
    newState[FRONT] = zeroTwoOnLeft(cubeState[FRONT], cubeState[DOWN]);
    newState[BACK] = twoZeroOnRight(cubeState[BACK], cubeState[UP]);
    cubeState = newState;
  }

  public void right() {
    // [U0F1U2F3 | D0B2D2B0 | L0L1L2L3 | R2R0R3R1 | F0D1F2D3 | U3B1U1B3]
    String[] newState = new String[6];

    newState[UP] = oneThreeOnRight(cubeState[UP], cubeState[FRONT]);
    newState[DOWN] = twoZeroOnRight(cubeState[DOWN], cubeState[BACK]);
    newState[LEFT] = cubeState[LEFT];
    newState[RIGHT] = clockwise(cubeState[RIGHT]);
    newState[FRONT] = oneThreeOnRight(cubeState[FRONT], cubeState[DOWN]);
    newState[BACK] = threeOneOnLeft(cubeState[BACK], cubeState[UP]);
    cubeState = newState;
  }

  public void rightPrime() {
    // [U0B2U2B0 | D0F1D2F3 | L0L1L2L3 | R1R3R0R2 | F0U1F2U3 | D3B1D1B3]
    String[] newState = new String[6];
    newState[UP] = twoZeroOnRight(cubeState[UP], cubeState[BACK]);
    newState[DOWN] = oneThreeOnRight(cubeState[DOWN], cubeState[FRONT]);
    newState[LEFT] = cubeState[LEFT];
    newState[RIGHT] = counterclockwise(cubeState[RIGHT]);
    newState[FRONT] = oneThreeOnRight(cubeState[FRONT], cubeState[UP]);
    newState[BACK] = threeOneOnLeft(cubeState[BACK], cubeState[DOWN]);
    cubeState = newState;
  }

  public void front() {
    // [U0U1L3L1 | R2R0D2D3 | L0D0L2D1 | U2R1U3R3 | F2F0F3F1 | B0B1B2B3]
    String[] newState = new String[6];
    newState[UP] = threeOneOnBottom(cubeState[UP], cubeState[LEFT]);
    newState[DOWN] = twoZeroOnTop(cubeState[DOWN], cubeState[RIGHT]);
    newState[LEFT] = zeroOneOnRight(cubeState[LEFT], cubeState[DOWN]);
    newState[RIGHT] = twoThreeOnLeft(cubeState[RIGHT], cubeState[UP]);
    newState[FRONT] = clockwise(cubeState[FRONT]);
    newState[BACK] = cubeState[BACK];
    cubeState = newState;
  }

  public void frontPrime() {
    // [U0U1R0R2 | L1L3D2D3 | L0U3L2U2 | D1R1D0R3 | F1F3F0F2 | B0B1B2B3]
    String[] newState = new String[6];
    newState[UP] = zeroTwoOnBottom(cubeState[UP], cubeState[RIGHT]);
    newState[DOWN] = oneThreeOnTop(cubeState[DOWN], cubeState[LEFT]);
    newState[LEFT] = threeTwoOnRight(cubeState[LEFT], cubeState[UP]);
    newState[RIGHT] = oneZeroOnLeft(cubeState[RIGHT], cubeState[DOWN]);
    newState[FRONT] = counterclockwise(cubeState[FRONT]);
    newState[BACK] = cubeState[BACK];
    cubeState = newState;
  }

  public void back() {
    // [R1R3U2U3 | D0D1L0L2 | U1L1U0L3 | R0D3R2D2 | F0F1F2F3 | B2B0B3B1]
    String[] newState = new String[6];
    newState[UP] = oneThreeOnTop(cubeState[UP], cubeState[RIGHT]);
    newState[DOWN] = zeroTwoOnBottom(cubeState[DOWN], cubeState[LEFT]);
    newState[LEFT] = oneZeroOnLeft(cubeState[LEFT], cubeState[UP]);
    newState[RIGHT] = threeTwoOnRight(cubeState[RIGHT], cubeState[DOWN]);
    newState[FRONT] = cubeState[FRONT];
    newState[BACK] = clockwise(cubeState[BACK]);
    cubeState = newState;
  }

  public void backPrime() {
    // [L2L0U2U3 | D0D1R3R1 | D2L1D3L3 | R0U0R2U1 | F0F1F2F3 | B1B3B0B2]
    String[] newState = new String[6];
    newState[UP] = twoZeroOnTop(cubeState[UP], cubeState[LEFT]);
    newState[DOWN] = threeOneOnBottom(cubeState[DOWN], cubeState[RIGHT]);
    newState[LEFT] = twoThreeOnLeft(cubeState[LEFT], cubeState[DOWN]);
    newState[RIGHT] = zeroOneOnRight(cubeState[RIGHT], cubeState[UP]);
    newState[FRONT] = cubeState[FRONT];
    newState[BACK] = counterclockwise(cubeState[BACK]);
    cubeState = newState;
  }

  private String twoZeroOnRight(String a, String b) {
    // a0b2a2b0
    return a.substring(0,1) + b.charAt(2) + a.charAt(2) + b.charAt(0);
  }

  private String zeroTwoOnLeft(String a, String b) {
    // b0a1b2a3
    return b.substring(0,1) + a.charAt(1) + b.charAt(2) + a.charAt(3);
  }

  private String threeOneOnLeft(String a, String b) {
    // b3a1b1a3
    return b.substring(3,4) + a.charAt(1) + b.charAt(1) + a.charAt(3);
  }

  private String oneThreeOnRight(String a, String b) {
    return a.substring(0,1) + b.charAt(1) + a.charAt(2) + b.charAt(3);
  }

  private String twoThreeOnLeft(String a, String b) {
    // b2a1b3a3
    return b.substring(2,3) + a.charAt(1) + b.charAt(3) + a.charAt(3);
  }

  private String zeroOneOnRight(String a, String b) {
    // a0b0a2b1
    return a.substring(0,1) + b.charAt(0) + a.charAt(2) + b.charAt(1);
  }

  private String twoZeroOnTop(String a, String b) {
    // b2b0a2a3
    return b.substring(2,3) + b.charAt(0) + a.charAt(2) + a.charAt(3);
  }

  private String threeOneOnBottom(String a, String b) {
    // a0a1b3b1
    return a.substring(0,1) + a.charAt(1) + b.charAt(3) + b.charAt(1);
  }

  private String oneZeroOnLeft(String a, String b) {
    // b1a1b0a3
    return b.substring(1,2) + a.charAt(1) + b.charAt(0) + a.charAt(3);
  }

  private String threeTwoOnRight(String a, String b) {
    // a0b3a2b2
    return a.substring(0,1) + b.charAt(3) + a.charAt(2) + b.charAt(2);
  }

  private String oneThreeOnTop(String a, String b) {
    // b1b3a2a3
    return b.substring(1,2) + b.charAt(3) + a.charAt(2) + a.charAt(3);
  }

  private String zeroTwoOnBottom(String a, String b) {
    // a0a1b0b2
    return a.substring(0,1) + a.charAt(1) + b.charAt(0) + b.charAt(2);
  }

}
