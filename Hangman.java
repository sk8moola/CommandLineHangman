import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;

class Hangman {

    String sampleWords[] = new String[] { "barks", "indubitably", "examination", "nintendo", "xylophone", "onigiri",
            "vacation", "poggers", "ramen", "love", "makima" };

    HashMap<String, Integer> hm = new HashMap<String, Integer>();
    HashMap<String, Integer> inWord = new HashMap<String, Integer>();

    int remaining = 6;

    public void gameMechanics() {
        Random random = new Random();
        StringBuilder incorrect = new StringBuilder("Here are the incorrect letters so far: "); //build upon the incorrect prompt
        int ic = 0; //ic = incorrect counter
        Scanner scan = new Scanner(System.in); //We're constantly having to take user input so might need to have a while loop instead
        String word = sampleWords[random.nextInt(7)]; // pick a random word, from the word bank
        
        this.addToHashMap(word);
        
        System.out.println("Welcome to Hangman! Your current word is " + word.length() + " letters long.");
        
        while (hm.size() != inWord.size()) {
            System.out.println("Please guess either a character or a word!");
            String guess = scan.nextLine();

            if (remaining == 1 && (!(hm.containsKey(guess))) && !(guess.equals(word))) { //if this is their last try
                System.out.println("SSSS OOOOOo yiiiikkesssss that's embarrassing :/");
                System.out.println("That's it, GAME OVER. Go outside....touch some grass. Go talk to your friends, that's it. You're done");
                break;
            }

            if (inWord.containsKey(guess)) { //if they guess the same letter
                System.out.println("What are you doing?? You've already guessed that letter. Try againnnnn");
                continue;
            }

            if(guess.length() == 1 && hm.containsKey(guess)) { //it's a letter and it's in the word
                inWord.put(guess, 1);
                System.out.println(guess + " is in the word! Here's how it looks: ");
                this.printSubstring(word); //print out word with only the correct letter(s) e.g. XXXXeXXX ALSO OPTIMIZE THISSSSSS!!!!!!!!!!!!!!!
            }
            else if (guess.length() == 1 && (!(hm.containsKey(guess)))) { //if it's an incorrect letter
                --remaining;
                ++ic;
                System.out.println(guess + " is not in the word :(");
                System.out.println("You have " + remaining + " guesses remaining");
                if(guess.length() == 1 && ic == 1) { //if it's the first incorrect letter
                    incorrect.append(guess);
                }
                else {
                    incorrect.append(", " + guess);
                }
                System.out.println(incorrect);
            
            }
            else if (guess.length() > 1) { //if they guess a word
                if (guess.equals(word)) { //they completely guess the word
                   System.out.println("Wooooowwwwww look at the brain on this guy. Thinks he's soooo smarrrttt. Well you win....NOTHING. The prize is the friends we made along the way :^)");
                   break;
                }
                else { //they guess the whole word incorrectly
                    --remaining;
                    System.out.println("Tried to guess it early, eh? Stop being trying to be a little genius and keeping guessing, buckaroo");
                    System.out.println("Better be careful, you've only got " + remaining + " tries left");
                }
            }
        }

        scan.close();
    }

    public void printSubstring(String s) { ///print out word with only the correct letter(s)
        StringBuilder replace = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            if (!(inWord.containsKey(Character.toString(s.charAt(i))))) {
                replace.setCharAt(i, 'X');
            }
        }

        if ((replace.toString()).equals(s)) { 

            System.out.println("You Win!!! Congratulations!! You get....absolutely nothing. Goodbye :)");
        } else {
            System.out.println(replace);
        }

    }

    public void addToHashMap(String s) {
        for (int i = 0; i < s.length(); i++) {
            String curchar = Character.toString(s.charAt(i));
            if (!(hm.containsKey(curchar))) {
                hm.put(curchar, 1);
            } else {
                int value = hm.get(curchar);
                value += 1;
                hm.put(curchar, value);
            }
        }
    }

    public static void main(String[] args) {

        Hangman obj = new Hangman();
        obj.gameMechanics();
    }
}