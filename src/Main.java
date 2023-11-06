import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        String green = "\u001B[32m";
        Function f1 = new Function(); 
        //f1.displayPlaylist(f1.getMusicList());
        //f1.search();
        int choice = 0; 
        while(choice != 3){
            f1.menuDisplay();
            Scanner scan = new Scanner(System.in); 
            choice = scan.nextInt();
            switch (choice) {
                case 1:{
                    f1.displayPlaylist(f1.getMusicList());
                    f1.search(); 
                    break; 
                }               
                default:
                {
                    System.out.println("Bye bye have a great time !!!");
                }
                    break;
            }
          
        }
        
        return; 
        
    }
}