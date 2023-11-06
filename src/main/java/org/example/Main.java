package org.example;

public class Main {
  public static void main(String[] args) {
    Cube cube = new Cube();
    System.out.println(cube.getState());

    cube.up();
    System.out.println("----UP----");
    System.out.println(cube.getState());

    cube.upPrime();
    System.out.println("----UP PRIME----");
    System.out.println(cube.getState());

    cube.down();
    System.out.println("----DOWN----");
    System.out.println(cube.getState());

    cube.downPrime();
    System.out.println("----DOWN PRIME----");
    System.out.println(cube.getState());

    cube.left();
    System.out.println("----LEFT----");
    System.out.println(cube.getState());

    cube.leftPrime();
    System.out.println("----LEFT PRIME----");
    System.out.println(cube.getState());

    cube.right();
    System.out.println("----RIGHT----");
    System.out.println(cube.getState());

    cube.rightPrime();
    System.out.println("----RIGHT PRIME----");
    System.out.println(cube.getState());

    cube.front();
    System.out.println("----FRONT----");
    System.out.println(cube.getState());

    cube.frontPrime();
    System.out.println("----FRONT PRIME-----");
    System.out.println(cube.getState());

    cube.back();
    System.out.println("----BACK----");
    System.out.println(cube.getState());

    cube.backPrime();
    System.out.println("----BACK PRIME----");
    System.out.println(cube.getState());
  }
}