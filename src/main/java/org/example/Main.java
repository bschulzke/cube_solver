package org.example;

public class Main {
  public static void main(String[] args) {

    Cube cube = new Cube();

    System.out.println(cube.getState());
    System.out.println("Cube is solved? " + cube.isSolved());
  }
}