package org.example;

public class Main {
  public static void main(String[] args) {
    Cube cube = new Cube();
    System.out.println(cube.getState());

    cube.up();
    System.out.println("----Up move----");
    System.out.println(cube.getState());

    cube.upPrime();
    System.out.println("----Up prime----");
    System.out.println(cube.getState());

    cube.down();
    System.out.println("----Down move----");
    System.out.println(cube.getState());

    cube.downPrime();
    System.out.println("----Down prime----");
    System.out.println(cube.getState());
  }
}