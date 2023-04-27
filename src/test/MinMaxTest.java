package test;

import process.minmax.Tree;
import process.minmax.TreeFactory;

import java.util.Scanner;

public class MinMaxTest {
    public static void main(String[] args){
        int coinNumber=10;
        try (Scanner scanner = new Scanner(System.in)) {
            while (coinNumber>0){
                Tree tree= new Tree(TreeFactory.buildPlayerNode(coinNumber,3));
                coinNumber=tree.findMove();
                System.out.println(coinNumber);
                coinNumber-=scanner.nextInt();
                System.out.println(coinNumber);
            }
        }
    }
}
