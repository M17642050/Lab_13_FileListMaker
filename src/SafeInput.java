import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class SafeInput{
    public static String getNonZeroLenString(Scanner pipe, String prompt){
        String retString = "";
        do {
            System.out.print(prompt+": ");
            retString = pipe.nextLine();
        }
        while(retString.length()==0);
        return retString;
    }
    public static int getInt(Scanner pipe, String prompt){
        int userInput = 0;
        boolean loop = true;
        do {
            System.out.print(prompt);
            if(pipe.hasNextInt()){
                userInput = pipe.nextInt();
                pipe.nextLine();
                loop = false;
            }
            else {
                pipe.nextLine();
            }
        }
        while(loop);
        return userInput;
    }
    public static double getDouble(Scanner pipe, String prompt){
        double userInput = 0;
        boolean loop = true;
        do {
            System.out.print(prompt);
            if(pipe.hasNextDouble()){
                userInput = pipe.nextDouble();
                pipe.nextLine();
                loop = false;
            }
            else {
                pipe.nextLine();
            }
        }
        while(loop);
        return userInput;
    }
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high){
        boolean loop = true;
        int validUserInput = 0;
        do {
            System.out.print(prompt);
            if(pipe.hasNextInt()){
                int initialUserInput = pipe.nextInt();
                pipe.nextLine();
                if(initialUserInput >= low && initialUserInput <= high){
                    validUserInput = initialUserInput;
                    loop = false;
                }
            }
            else {
                pipe.nextLine();
            }
        }
        while(loop);
        return validUserInput;
    }
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        boolean loop = true;
        double validUserInput = 0;
        do {
            System.out.print(prompt);
            if (pipe.hasNextDouble()) {
                double initialUserInput = pipe.nextDouble();
                pipe.nextLine();
                if (initialUserInput >= low && initialUserInput <= high) {
                    validUserInput = initialUserInput;
                    loop = false;
                }
            } else {
                pipe.nextLine();
            }
        }
        while (loop);
        return validUserInput;
    }
    public static boolean getYNConfirm(Scanner pipe, String prompt){
        boolean loop = true;
        boolean validUserInput = false;
        do {
            System.out.print(prompt);
            String initialUserInput = pipe.nextLine();
            if(initialUserInput.equalsIgnoreCase("Y")){
                validUserInput = true;
                loop = false;
            }
            else if(initialUserInput.equalsIgnoreCase("N")){
                loop = false;
            }
        }
        while(loop);
        return validUserInput;
    }
    public static String getRegExString(Scanner pipe, String prompt, String regEx){
        Pattern pattern = Pattern.compile(regEx);
        String userInput = "";
        boolean loop = true;
        do {
            System.out.print(prompt);
            userInput = pipe.nextLine();
            Matcher matcher = pattern.matcher(userInput);
            if(matcher.matches()){
                loop = false;
            }
        }
        while(loop);
        return userInput;
    }
    public static void prettyHeader(String msg){
        String spaceBefore = "";
        String spaceAfter = "";
        if(msg.length()<=54){
            int spaceToFill = 60 - msg.length() - 6;
            int spaceToFillBefore = spaceToFill/2;
            int spaceToFillAfter = spaceToFill - spaceToFillBefore;
            for(int i=0;i<spaceToFillBefore;i++){
                spaceBefore = spaceBefore+" ";
            }
            for(int i=0;i<spaceToFillAfter;i++){
                spaceAfter = spaceAfter+" ";
            }
            for(int i=1;i<61;i++) {
                System.out.print("*");
            }
            System.out.print("\n***"+spaceBefore+msg+spaceAfter+"***\n");
            for(int j=1;j<61;j++){
                System.out.print("*");
            }
        }
        else{
            System.out.print("Sorry, message too long.");
        }
    }
}