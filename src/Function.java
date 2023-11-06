import java.util.Scanner;
import java.io.IOException;
import javax.sound.sampled.*; 
import java.io.File;

// create funtion for the program 
public class Function {
    private String folderName = "musicDatabase"; //declare the mussicDatabase name
    private String folderPath;              // decalare the folder path, difference from folder name
    private File database;                  // the folder containing all songs
    private String songName;                // this is the song name
    private File songFile;                  // the file containing the song
    public File[] musicList;               // create an array containing all musics in the database 
    private String green = "\u001B[32m";    // this is for green color
    private String cyan = "\033[0;36m";     // thís is for cyan color
    // Constructor of function 
Function(){
        //check if the musicdatabase foler exit in the user memory and get the folder path
        File folderCheckExit = new File(folderName);
        folderPath = folderCheckExit.getAbsolutePath();
        System.out.println(folderCheckExit.getAbsolutePath());
        //open and store the database
        try {
            database = new File(folderPath);
            musicList = database.listFiles(); 
        } catch (Exception e) {
            System.out.println("Smth wrong with opening the music database");
        }
    }
//getter for musicList
    public File[] getMusicList() {
        return musicList;
    }
//Method: display the music playlist 
    public void displayPlaylist (File [] musicList){
        int i = 1; 
        System.err.println( cyan + "💿: Your playlist: ");
        System.err.println(green + "========================================");
        for(File fileCheck : musicList){
            String songName = fileCheck.getName();
            System.err.println(green + i + ": " + songName.substring(0, songName.length() - 4));// display the song name without the extension
            i++; 
        }
           System.err.println(green + "========================================");
    }
//print the layout
    public String layout(){
        String choice; 
        Scanner scan = new Scanner(System.in); 
        System.err.println();
        System.err.println(cyan +  "  ♬: " + songName.substring(0, songName.length() - 4));
        System.out.println(green + "||============================================================================||");
        System.err.println(green + "|| 0:0 -----------------------∘---------------------------------------- 3:12  ||");
        System.out.println(green + "||                      ↻      ◁     ||     ▷       ↺                         ||");
        System.out.println(green + "||============================================================================||");
        System.out.println(green + "|| P: Play    ||     S: Pause     ||       R: Replay     ||      Q: Quit      || ");
        System.out.println(green + "||============================================================================||");
        System.err.print  ("Enter your choice: ");
        choice = scan.nextLine();
        choice = choice.toUpperCase(); 
        return choice;
    }
//Method: searching music name
    public void search(){
        do{
            try {
                // scan the song name that user put in
                Scanner scan = new Scanner(System.in); 
                System.err.println("Enter the song(by name): ");
                this.songName = scan.nextLine();
                songName = songName +".wav";  // put the extension .wav to the name; 
                for(File fileFind : musicList){  // make a loop (for-each) to search through all the audio
                    if (fileFind.isFile() && fileFind.getName().equals(songName)){
                        this.songFile = new File (fileFind.getAbsolutePath()); // put data 
                    } 
                }
                if(this.songFile == null){
                    System.out.println("Cannot find your song.");
                }
               
            } catch (Exception e) {
                System.err.println("There is an error in finding the song you want to listen.");
            }
            
        }while (this.songFile == null);  
        // put the data into the songFile
        String choice = ""; 
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(songFile);  
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            choice = layout();
            while (! choice.equals("Q")){
                switch (choice) {
                    case ("P"): {
                        if(clip.isActive()){
                            System.err.println();
                            System.err.println();
                            System.err.println();
                            System.err.println("|---------------------|");
                            System.err.println("| The song is playing |");
                            System.err.println("|_____________________|");
                            choice = layout(); 
                            break; 
                        }
                        else{
                            clip.start();
                            choice =layout();
                            break; 
                        }
                        
                        }
                    case ("S"): {
                        if (clip.isActive()){
                            clip.stop();
                            choice = layout();
                            break;
                        }
                        else{
                            System.err.println();
                            System.err.println();
                            System.err.println("|---------------------|");
                            System.err.println("| Play the song first |");
                            System.err.println("|_____________________|");
                            choice = layout(); 
                            break; 
                        }
                
                    }
                    case ("R"):{
                        clip.start();
                        clip.setMicrosecondPosition(0);
                        choice = layout(); 
                        break;
                    }
                    default: { 
                            System.err.println();
                            System.err.println();
                            System.err.println("|---------------------|");
                            System.err.println("|    Invalid choice   |");
                            System.err.println("|_____________________|");
                            choice = layout(); 
                            break;
                    }
                }
            }  
            clip.stop();
            clip.close();
        } catch (Exception e) {
            System.err.println("There is an error in converting audio");
            System.out.println("The file must be .wav");
        }
    
    }
// menu display 
    public void menuDisplay(){
        System.out.println(green + "-------------------------SÌPÓTTIPHI-------------------------");
        System.out.println(green + "||Designed by: Clayzzz                                    ||");
        System.out.println(green + "||                                                        ||");
        System.out.println(green + "||                 █▀ █▀█ █▀█ ▀█▀ █ █▀▀ █▄█               ||");
        System.out.println(green + "||                 ▄█ █▀▀ █▄█ ░█░ █ █▀░ ░█░               ||");
        System.out.println(green + "||                                                        ||");
        System.out.println(green + "||♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫||");
        System.out.println(green + "||                                                        ||");
        System.out.println(green + "||                                                        ||");
        System.out.println(green + "||                    1: See the playlist                 ||");
        System.out.println(green + "||                                                        ||");
        System.out.println(green + "||                                                        ||");
        System.out.println(green + "||                         3:EXIT                         ||");
        System.out.println(green + "||                                                        ||");
        System.out.println(green + "||--------------------------------------------------------||");
    }
}
