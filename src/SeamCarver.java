import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by clementvenard on 12/01/2016.
 */
public class SeamCarver {

    public static void main(String args[]) {

        //method to read the txt file and create an array with that data
        Scanner inputStream = null;

        Scanner userInput = new Scanner(System.in);
        System.out.print("Chose matrix to test\n Enter an int from 1 to 5: ");

        String input = userInput.nextLine();


        try {
            inputStream = new Scanner(new File("TestData/" + input + "-in.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the file, wrong input: " + input);
            System.exit(0);
        }

        // determine the height, width and number of seams to use.
        int height = 0;
        int width = 0;
        int seamNo = 0;

        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                String line = inputStream.next();
                height = Integer.parseInt(line);
            }

            if (i == 1) {
                String line = inputStream.next();
                width = Integer.parseInt(line);
            }

            if (i == 2) {
                String line = inputStream.next();
                seamNo = Integer.parseInt(line);
            }

        }

        // create input array to store values from the .txt file
        int inputArray[][] = new int[width][height];


        // add all the values from the .txt file to an array
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                String line = inputStream.next();
                inputArray[j][i] = Integer.parseInt(line);
            }
        }

        for (int z = 0; z < seamNo; z++) {

            //create an empty area that will act as a memoization
            int memoArray[][] = new int[width][height + 1];

            // method to calculate minimum path array memoArray
            for (int i = height; i > -1; i--) {
                for (int j = 0; j < width; j++) {
                    try {
                        memoArray[j][i] = inputArray[j][i] + Math.min(Math.min(memoArray[j - 1][i + 1],
                                memoArray[j][i + 1]), memoArray[j + 1][i + 1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        try {
                            memoArray[j][i] = inputArray[j][i] + Math.min(memoArray[j - 1][i + 1], memoArray[j][i + 1]);
                        } catch (ArrayIndexOutOfBoundsException e1) {
                            try {
                                memoArray[j][i] = inputArray[j][i] + Math.min(memoArray[j][i + 1], memoArray[j + 1][i + 1]);
                            } catch (ArrayIndexOutOfBoundsException e2) {
                                continue;
                            }
                        }
                    }
                }
            }


                // find the lowest value in the top row
                int x = 0;

                for (int j = 0; j < width - 1; j++) {
                    try {
                        // determine which is the lowest value in the row
                        if (memoArray[x][0] <= memoArray[j + 1][0]) {
                        }

                        else {
                            x = j + 1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        continue;
                    }
                }
                inputArray[x][0] = 0;


            //iterate through all the rows
            for (int i=1; i<height; i++){

                //check centre and right
                if(x==0){
                    if(memoArray[x][i] <= memoArray[x+1][i]){
                        x=0;
                    }
                    else {
                        x=1;
                    }
                }

                if(x==width-1){
                    //check centre and left
                    if(memoArray[x][i] > memoArray[x-1][i]){
                        x=x-1;
                    }
                }

                else {
                    //check left centre and right
                    try {
                        // determine which is the lowest value in the row
                        int v = Math.min(Math.min(memoArray[x-1][i],memoArray[x][i]),memoArray[x+1][i]);
                        for(int r=-1; r<2; r++){
                            if (memoArray[x+r][i]==v){
                                x=x+r;
                            }
                        }

                        }
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.print(e);
                    }
                }


                // set each value to zero to make it easy to remove later
                // this is assuming in the power matrix there is no value of zero
                inputArray[x][i] = 0;

            }


            // create the final outputArray
            for (int i = 0; i < height; i++) {
                int v = 0;
                for (int j = 0; j < width; j++) {
                    if (inputArray[j][i] == 0) {
                        continue;
                    } else {
                        inputArray[v][i] = inputArray[j][i];
                        v++;
                    }
                }
            }

            width = width - 1;

        }

        // print outputArray

        System.out.print("Output Array: \n");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(inputArray[j][i]);
                System.out.print(" \t");
            }
            System.out.println();
        }

        System.out.println();

    }

}

