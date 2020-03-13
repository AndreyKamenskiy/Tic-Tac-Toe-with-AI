package tictactoe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       System.out.print("Enter cells: ");
       String stringFormat = scanner.nextLine();
       TTGame ttGame = new TTGame();
       ttGame.setField(stringFormat);
       ttGame.showfield();
       int x = 5;
       int y = 5;

       boolean validAnswer = false;
       do {
           System.out.print("Enter the coordinates: ");
           String coord = scanner.nextLine();
           try {
              String[] arr = coord.split("\\s+");
              x = Integer.parseInt(arr[0]);
              y = Integer.parseInt(arr[1]);

           } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
           } catch (ArrayIndexOutOfBoundsException e) {
               System.out.println("You should enter 2 numbers!");
               continue;
           }

           if ( x < 1 || x > 3 || y < 1 || y > 3) {
               System.out.print("Coordinates should be from 1 to 3!\n");
                continue;
           }

           if (!ttGame.getField().isCellXYEmpty(x, y)) {
               System.out.print("This cell is occupied! Choose another one!\n");
               continue;
           }

           validAnswer = true;

       } while (!validAnswer);

       if (!ttGame.makeMoveXY(x, y)) {
           System.out.print("Error occupied when try to make move!");
           System.exit(1);
       }
       ttGame.showfield();
       System.out.println(ttGame.getStatus());

    }
}
