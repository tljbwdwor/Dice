import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TomLloydJonesLabb1Version2 {
//create class 'sort' to use as bubble sort algorithm for updating score board later.
    //NOTE not used as of latest version which includes player names in score board. Need to figure out how to utilise output.
        /*public static void sort(int[] input) {

                        int inputLength = input.length;
                        int temp;
                        boolean is_sorted;

                        for (int i = 0; i < inputLength; i++) {
                                is_sorted = true;
                                for (int j = 1; j < (inputLength - i); j++) {
                                        if (input[j - 1] > input[j]) {
                                                temp = input[j - 1];
                                                input[j - 1] = input[j];
                                                input[j] = temp;
                                                is_sorted = false;
                                        }
                                }
                                if (is_sorted) break;
                                System.out.println("\n");
                        }
                }*/

    public static void main(String[] args) throws InterruptedException {

        //IDEA: us a char array of PYRAMID and have it print vertically down in the middle of the Mario pyramid. The have CASINOS written underneath. 'Where your money is laid to rest!
        String[] pyramid = {"P","Y","R","A","M","I","D"};
        String[] casino = {"C","A","S","I","N","O"};
        String[] secondLine = {"$","$","$"};
        int height = 7;

        System.out.println("        $");
        System.out.print("       ");
        TimeUnit.MILLISECONDS.sleep(400);
        for (int s = 1; s <= secondLine.length; s++) {
            System.out.print(secondLine[s-1]);
            TimeUnit.MILLISECONDS.sleep(400);
        }
        System.out.print("\n");

        for(int i = 0; i < height; ++i) {
            int l;
            for(l = 0; l < height - i - 1; ++l) {
                System.out.print(" ");
            }

            for(l = 0; l < i + 1; ++l) {
                System.out.print("$");
            }


            System.out.print(" " + pyramid[i] + " ");

            for(l = 0; l < i + 1; ++l) {
                System.out.print("$");
            }
            TimeUnit.MILLISECONDS.sleep((300));
            System.out.print("\n");

        }
        System.out.print("      ");
        for (int i = 0; i <= 5; i++) {
            TimeUnit.MILLISECONDS.sleep(250);
            System.out.print(casino[i]);
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.println("\nWhere your money comes to rest!");
        TimeUnit.SECONDS.sleep(1);

        //set up variables for number of sides, points and dice
        int numberSides = 6;
        int numberDice = 5;
        int totalPoints = 0;
        int computerPoints = 0;
        String numberThrows;

        //set up int arrays of size 5 to store each throw, and another to bank numbers. Same for the computers results
        int[] results = new int[10];
        int[] computer = new int[10];


        //set up an array to hold six high scores- note that only five will be displayed, but we will add the current score to the 6th place each round.
        int[] highScores = {0, 10, 25, 50, 100, 150};
        String[] position = {"5th. ", "4th. ", "3rd. ", "2nd. ", "1st. "};
        String[] hsNames = {" ", "Mikey", "Donnie", "Raph", "Leo", "Splinter"};


        String firstRoll;
        //String numberOfPlayers;
        System.out.println("Welcome to Pyramid Casino, Java's premiere gambling paradise! Throw from 3 to 10 dice up to a maximum of 3 times. Highest combined total wins!");


        //get username
        String username1;
        Scanner name = new Scanner(System.in);
        System.out.println("What is your name?");
        username1 = name.next();
        System.out.println("Hi, " + username1 + "! I hope you're feeling lucky... Firstly, I need to ask");


        //set up a boolean to use in a while loop, so that we can keep the game running until exited.
        boolean playing = true;
        while (playing) {

            try {
                do {
                    Scanner nrDice = new Scanner(System.in);
                    System.out.println("How many dice shall we use? Pick from 3 to 10.");
                    numberDice = nrDice.nextInt();
                }
                while ((numberDice < 3) || (numberDice > 10));

            }
            catch (Exception wrongType){
                System.out.println("You'll need to input a number between 3 and 10. For now, we'll play with 5.");
                numberDice = 5;
            }

            System.out.println("So, we're playing with " + numberDice + " dice.");
            //set up do while to prompt for number of throws
            do {
                System.out.println("How many throws? Choose 1, 2 or 3.");
                Scanner startGame = new Scanner(System.in);
                numberThrows = startGame.next();
            }
            while (!numberThrows.contains("1") && !numberThrows.contains("2") && !numberThrows.contains("3"));
            //create an int based on the input. DO NOT KNOW THE BEST WAY FOR THIS, using ifs for now
            int numberOfRolls = 0;
            if (numberThrows.equals("1")) numberOfRolls = 1;
            else if (numberThrows.equals("2")) numberOfRolls = 2;
            else if (numberThrows.equals("3")) numberOfRolls = 3;
            System.out.println("ok, you've selected to throw " + numberOfRolls + " time(s). Let's roll!");


            //For loop to keep the throws running so long as the input is long.
            for (int r = 1; r <= numberOfRolls; r++) {

                //giving chance to have input for rolling, or to quit
                do {
                    Scanner rollAll = new Scanner(System.in);
                    System.out.println("type roll to begin or Q to quit!");
                    firstRoll = rollAll.next();
                }while (!firstRoll.contains("q") && (!firstRoll.contains("Q") && (!firstRoll.contains("r") && (!firstRoll.contains("R")))));

                if (firstRoll.equalsIgnoreCase("q")) {
                    System.out.println("Ok then, maybe another time!");
                    return;
                } else if (firstRoll.contains("r") || (firstRoll.contains("R"))) {

                    //FIRST ROLL. Results stored as an array, "results."
                    for (int i = 0; i < numberDice; i++) {
                        results[i] = (int) (1 + numberSides * Math.random());
                        System.out.println("Dice " + (i + 1) + " is " + results[i]);
                        totalPoints += results[i];
                    }
                    //for loop to calculate sum of each dice for a throw
                    int throwTotal = 0;
                    for (int i = 0; i < results.length; i++) {
                        throwTotal += results[i];
                    }


                    System.out.println("Nice throw. You got " + throwTotal);

                    if (numberOfRolls != 1) {
                        System.out.println(username1 + ", your running total is " + totalPoints);
                    } else if (numberOfRolls > 1) {

                        String second = "";
                        do {
                            Scanner secondThrow = new Scanner(System.in);
                            System.out.println("type roll to throw again!");
                            second = secondThrow.next();
                        }
                        while (!second.contains("r") || !second.contains("R"));
                    }
                }
            }

            System.out.println(username1 + ", your grand total is " + totalPoints);


            //COMPUTERS TURN
            TimeUnit.SECONDS.sleep(1);

            System.out.println("\nMy turn! Here I go...");

            for (int i = 0; i < numberOfRolls; i++) {
                System.out.println("Rolling...");
                TimeUnit.SECONDS.sleep(1);

                for (int j = 0; j < numberDice; j++) {
                    computer[j] = (int) (1 + numberSides * Math.random());
                    System.out.println("Dice " + (j + 1) + " is " + computer[j]);
                    computerPoints += computer[j];
                }
                //for loop to calculate sum of each dice for a throw
                int pcThrowTotal = 0;
                for (int x = 0; x < computer.length; x++) {
                    pcThrowTotal += computer[x];
                }


                System.out.println("Looks like I got " + pcThrowTotal);

                System.out.println("My running total is " + computerPoints);
                TimeUnit.SECONDS.sleep(1);
            }
            System.out.println("My grand total is " + computerPoints);

            TimeUnit.SECONDS.sleep(1);

            System.out.print("\nSeeing as your grand total was " + totalPoints + ", ");

            //set parameters for win/lose messages
            if (totalPoints < computerPoints) {
                System.out.println("bad luck, you lose! Well I guess the house does always win...");
            } else if ((totalPoints == computerPoints) && totalPoints != 0) {
                System.out.println("it's a draw! What are the odds?");
            } else if (totalPoints != 0)
                System.out.println("you win... but how about giving me a chance to win some of that back?!");


            TimeUnit.SECONDS.sleep(1);

            System.out.println("\n\nALL TIME GREAT SCORES!\n");

            if (totalPoints > highScores[1]){
                TimeUnit.MILLISECONDS.sleep(300);
                System.out.println("YOU MADE THE BOARD!!\nWell done\nSincerely, Pyramid Casino management.\n");
            }

            if (totalPoints > highScores[0]){
                highScores[0] = totalPoints;
                hsNames[0] = username1;

                boolean madeASwap = false;
                int tempScore;
                String tempName;

                //sort parallel arrays by score. If int score is sorted, String names is sorted in the exact same sequence.
                //NOTE- could this not be done as a method? In which case, how do we add both objects into the algorithm?
                do {
                    madeASwap = false;

                    for (int i = 0; i < 5; i++) {
                        if (highScores[i] > highScores[i+1]){
                            tempScore = highScores[i];
                            highScores[i] = highScores[i+1];
                            highScores[i+1] = tempScore;
                            tempName = hsNames[i];
                            hsNames[i] = hsNames[i+1];
                            hsNames[i+1] = tempName;

                            madeASwap = true;
                        }
                    }
                }while (madeASwap == true);

                for (int i = 0; i < 5; i++) {
                    TimeUnit.MILLISECONDS.sleep(300);
                    System.out.print(position[i] + " ");
                    System.out.print(highScores[i+1] + "   ");
                    System.out.print(hsNames[i+1] + "\n");
                }
            }


            TimeUnit.SECONDS.sleep(1);
            //reset total points for any future rounds
            totalPoints = 0;
            computerPoints = 0;

            for (int i = 0; i < results.length; i++) {
                results[i] = 0;
                computer[i] = 0;
            }


            //ask whether to keep playing. Set bool 'playing' to false if any answer other than 'y' or 'Y' is given.
            Scanner keep = new Scanner(System.in);
            System.out.println("Keep playing? Y/N");
            String answer = keep.next();
            //playing = (answer.contains("y") || (answer.contains("Y")));
            if (answer.contains("y") || (answer.contains("Y"))){
                System.out.println("Ok, let's keep going!");
                playing = true;
            }
            else if ((answer.contains("n") || (answer.contains("N")))){
                System.out.println("Goodbye, come back soon to lose more money!");
                playing = false;
            }
            else {
                System.out.println("It seems like you may have had one too many of our complimentary beverages... I'm going to have to ask you to leave.");
                playing = false;
            }

        }
    }
}
