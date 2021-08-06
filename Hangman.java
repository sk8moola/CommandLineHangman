import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;

class Hangman {

    String sampleWords[] = new String[] {
        "barks", "indubitably", "examination", "nintendo", "xylophone", "onigiri", "vacation"};

    //Hangman obj = new Hangman();
    //Random random = new Random();
    //Scanner scan = new Scanner(System.in);
    HashMap<String, Integer> hm = new HashMap<String, Integer>();
    int remaining = 6;
    
    public void gameMechanics() {
        Random random = new Random();
        Scanner scan = new Scanner(System.in); //We're constantly having to take user input so might need to have a while loop instead
        String word = sampleWords[random.nextInt(7)];
        
        this.addToHashMap(word);

        //System.out.println(hm.get("i"));
        
        System.out.println("Welcome to Hangman! Your current word is " + word.length() + " letters long.");
        System.out.println("Please guess either a character or a word!");
        String guess = scan.nextLine();

        if(guess.length() == 1 && hm.containsKey(guess)) { //it's a letter and it's in the word
            System.out.println(guess + " is in the word! Here's how it looks: ");
            //print out word with only the correct letter(s) e.g. XXXXeXXX
        }
        else if (guess.length() == 1 && (!(hm.containsKey(guess)))) {
            System.out.println(guess + " is not in the word :(");
            System.out.println("You have " + (remaining-1) + " guesses remaining");
            System.out.println("Here are the incorrect letters so far");
        }

        scan.close();
    }

    public void addToHashMap(String s) {
        for(int i = 0; i < s.length(); i++) {
            String curchar = Character.toString(s.charAt(i));
            if (!(hm.containsKey(curchar))) {
                hm.put(curchar, 1);
            }
            else {
                int value = hm.get(curchar);
                value += 1;
                hm.put(curchar, value);
            }
        }
    }

    public static void main(String[] args) {
        
        
        Hangman obj = new Hangman();
        obj.gameMechanics();
        //System.out.println(obj.stringGenerator());
        
    }


    /**public String stringGenerator() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();

        return generatedString;
    }*/
    

}