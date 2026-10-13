import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
public class Main{
    static Scanner in = new Scanner(System.in);
    static ArrayList<String>list = new ArrayList<>();
    static boolean needsToBeSaved = false;
    static boolean loop = true;
    public static void main(String[]args){
        menu();
        do{
            String userChoice = SafeInput.getRegExString(in,"Your choice: ","^[AaDdIiMmVvOoSsCcQq]$");
            switch(userChoice){
                case "A":
                case "a":
                    add();
                    break;
                case "S":
                case "s":
                    save();
                    break;
                case "Q":
                case "q":
                    saveFirst();
                    quit();
                    break;
                case "O":
                case "o":
                    saveFirst();
                    open();
                    break;
                case "D":
                case "d":
                    delete();
                    break;
                case "I":
                case "i":
                    insert();
                    break;
                case "V":
                case "v":
                    view();
                    break;
                case "M":
                case "m":
                    move();
                    break;
                case "C":
                case "c":
                    clear();
            }
        }
        while(loop);
    }
    private static void save(){
        if (!list.isEmpty()){
            String fileName = SafeInput.getNonZeroLenString(in, "What should be the name of the file in which to store your list?");
            Path path = Path.of("/home/rudra-prajapati/IdeaProjects/Lab_13_FileListMaker/src/dir_for_holding_user_txt_files/" + fileName + ".txt");
            try{
                Files.write(path,list);
            }
            catch (IOException e){
                System.out.println("ERROR: " + e.getMessage());
            }
            needsToBeSaved = false;
        }
    }
    private static void open(){
        Path dir = Path.of("/home/rudra-prajapati/IdeaProjects/Lab_13_FileListMaker/src/dir_for_holding_user_txt_files");
        ArrayList<Path>dirFiles=new ArrayList<>();
        int count = 0;
        try{
            for(Path path : Files.newDirectoryStream(dir)) {
                count++;
                System.out.println(count+". "+path.getFileName());
                dirFiles.add(path);
            }
            int userChoice = SafeInput.getRangedInt(in,"Enter the number corresponding to the file you want to open: ",1,count);
            System.out.println(Files.readString(dirFiles.get(userChoice-1)));
        }
        catch(IOException e){
            System.out.println("ERROR: "+e.getMessage());
        }
    }
    private static void menu(){
        System.out.println("Menu Options:\nEnter 'A' to append an item to the current list.");
        System.out.println("Enter 'D' to delete an item from the current list.");
        System.out.println("Enter 'I' to insert an item into the current list.");
        System.out.println("Enter 'M' to move an item within the current list.");
        System.out.println("Enter 'V' to view the current list.");
        System.out.println("Enter 'O' to open a list file.");
        System.out.println("Enter 'S' to save the current list.");
        System.out.println("Enter 'C' to remove all items from the current list.");
        System.out.println("Enter 'Q' to quit.");
    }
    private static void quit(){
        boolean userChoice = SafeInput.getYNConfirm(in,"Are you sure you want to quit?(Y/n): ");
        if(userChoice){
            loop = false;
        }
    }
    private static void add(){
        String item = SafeInput.getNonZeroLenString(in,"Enter the item to append");
        list.add(item);
        needsToBeSaved = true;
    }
    private static void delete(){
        if(!list.isEmpty()){
            int count = 0;
            for(String item : list){
                count++;
                System.out.println(count+". "+item);
            }
            int userChoice = SafeInput.getRangedInt(in,"Enter the number corresponding to the item you want to delete: ",1,count);
            list.remove(userChoice-1);
            needsToBeSaved = true;
        }
    }
    private static void insert(){
        needsToBeSaved = true;
        String itemInsert = SafeInput.getNonZeroLenString(in,"Enter the item to insert");
        if(list.isEmpty()){
            list.add(itemInsert);
        }
        else{
            int count = 0;
            for(String item : list){
                count++;
                System.out.println(count+". "+item);
            }
            System.out.println((count+1)+".");
            int userChoice = SafeInput.getRangedInt(in,"Enter the number where your item would be inserted: ",1,count+1);
            list.add(userChoice-1,itemInsert);
        }
    }
    private static void view(){
        System.out.println(list);
    }
    private static void move(){
        if(list.size()>1){
            int count = 0;
            for(String item : list){
                count++;
                System.out.println(count+". "+item);
            }
            int itemMove = SafeInput.getRangedInt(in,"Enter the number corresponding to the item you want to move: ",1,count);
            int newItemPosition = SafeInput.getRangedInt(in,"Enter the number where this item should be moved: ",1,count);
            String theItem = list.remove(itemMove-1);
            list.add(newItemPosition-1,theItem);
            needsToBeSaved = true;
        }
    }
    private static void clear(){
        boolean userChoice = SafeInput.getYNConfirm(in,"Are you sure?(Y/n): ");
        if(userChoice){
            int index = 0;
            while(!list.isEmpty()){
                list.remove(index);
                index++;
            }
            needsToBeSaved = false;
        }
    }
    private static void saveFirst(){
        if(needsToBeSaved){
            boolean userInput = SafeInput.getYNConfirm(in,"Do you want to save your current list first - otherwise, it'll be discarded?(Y/n): ");
            if(userInput){
                save();
                needsToBeSaved = false;
            }
        }
    }
}