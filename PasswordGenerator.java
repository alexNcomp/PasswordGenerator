// --------------------------------------------------------------------
// Assignment 1
// Written by: Alex Nguyen #2073215
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------
package passwordgenerator;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Alex
 */
public class PasswordGenerator {
    private static int newLine = 0;
    private static int single = 0;
    private static int equal = 0;
    private static int length = 0;
    private static int upper = 0;
    private static int lower = 0;
    private static int special = 0;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // http://www.gutenberg.org/cache/epub/19033/pg19033.txt
        final String[][][] book = {
            {
                {"ALICE was beginning to get very tired of sitting by her sister on the\n",
                    "bank, and of having nothing to do. Once or twice she had peeped into the\n",
                    "book her sister was reading, but it had no pictures or conversations in\n",
                    "it, \"and what is the use of a book,\" thought Alice, \"without pictures or\n",
                    "conversations?\"\n"},
                {"So she was considering in her OWN mind (as well as she could, for the\n",
                    "day made her feel very sleepy and stupid), whether the pleasure of\n",
                    "making a daisy-chain would be worth the trouble of getting up and\n",
                    "picking the daisies, when suddenly a White Rabbit with pink eyes ran\n",
                    "close by her.\n"},
                {"There was nothing so very remarkable in that, nor did Alice think it so\n",
                    "very much out of the way to hear the Rabbit say to itself, \"Oh dear! Oh\n",
                    "dear! I shall be too late!\" But when the Rabbit actually took a watch\n",
                    "out of its waistcoat-pocket and looked at it and then hurried on, Alice\n",
                    "started to her feet, for it flashed across her mind that she had never\n",
                    "before seen a rabbit with either a waistcoat-pocket, or a watch to take\n",
                    "out of it, and, burning with curiosity, she ran across the field after\n",
                    "it and was just in time to see it pop down a large rabbit-hole, under\n",
                    "the hedge. In another moment, down went Alice after it!"}
            },
            {
                {"\"WHAT a curious feeling!\" said Alice. \"I must be shutting up like a\n",
                    "telescope!\"\n"},
                {"And so it was indeed! She was now only ten inches high, and her face\n",
                    "brightened up at the thought that she was now the right size for going\n",
                    "through the little door into that lovely garden.\n"},
                {"After awhile, finding that nothing more happened, she decided on going\n",
                    "into the garden at once; but, alas for poor Alice! When she got to the\n",
                    "door, she found she had forgotten the little golden key, and when she\n",
                    "went back to the table for it, she found she could not possibly reach\n",
                    "it: she could see it quite plainly through the glass and she tried her\n",
                    "best to climb up one of the legs of the table, but it was too slippery,\n",
                    "and when she had tired herself out with trying, the poor little thing\n",
                    "sat down and cried.\n"},
                {"\"Come, there's no use in crying like that!\" said Alice to herself rather\n",
                    "sharply. \"I advise you to leave off this minute!\" She generally gave\n",
                    "herself very good advice (though she very seldom followed it), and\n",
                    "sometimes she scolded herself so severely as to bring tears into her\n",
                    "eyes.\n"},
                {"Soon her eye fell on a little glass box that was lying under the table:\n",
                    "she opened it and found in it a very small cake, on which the words \"EAT\n",
                    "ME\" were beautifully marked in currants. \"Well, I'll eat it,\" said\n",
                    "Alice, \"and if it makes me grow larger, I CAN reach the key; and if it\n",
                    "makes me grow smaller, I can creep under the door: so either way I'll\n",
                    "get into the garden, and I don't care which happens!\"\n"},
                {"She ate a little bit and said anxiously to herself, \"Which way? Which\n",
                    "way?\" holding her hand on the top of her head to feel which way she was\n",
                    "growing; and she was quite surprised to find that she remained the same\n",
                    "size. So she set to work and very soon finished off the cake."}
            },
            {
                {"The King and Queen of Hearts were seated on their throne when they\n",
                    "arrived, with a great crowd assembled about them--all sorts of little\n",
                    "birds and beasts, as well as the whole pack of cards: the Knave was\n",
                    "standing before them, in chains, with a soldier on each side to guard\n",
                    "him; and near the King was the White Rabbit, with a trumpet in one hand\n",
                    "and a scroll of parchment in the other. In the very middle of the court\n",
                    "was a table, with a large DISH of tarts upon it. \"I wish they'd get the\n",
                    "trial done,\" Alice thought, \"and hand 'round the refreshments!\"\n"},
                {"The judge, by the way, was the King and he wore his crown over his great\n",
                    "wig. \"That's the jury-box,\" thought Alice; \"and those twelve creatures\n",
                    "(some were animals and some were birds) I suppose they are the jurors.\"\n"},
                {"Just then the White Rabbit cried out \"Silence in the court!\"\n"},
                {"\"HERALD! read the accusation!\" said the King."}
            }
        };
        System.out.println("Welcome to the password generator game.");
        System.out.println();
        System.out.println("Generating passwords now...");
        TimeUnit.SECONDS.sleep(2);
        int totalPasswords = 0;
        for (int i = 0; i < 10000; i++) {
            String word1;  String word2; String word3;
            do {
                String[] line = getLine(book);
                word1 = getWord(line); word2 = getWord(line); word3 = getWord(line);
            } while (conditions(word1, word2, word3));

            
            String password = word1 + word2 + word3;
            System.out.printf("Password: %-18sNewline: %-6dSingle: %-6dEqual: %-6d"
                    + "Length: %-6dUpper: %-6dLower: %-6dSpecial: %-6d\n", password, 
                    newLine, single, equal, length, upper, lower, special);
            
            newLine = 0; single = 0; equal = 0; length = 0; upper = 0; lower = 0; special = 0;
            totalPasswords++;
            if (lower >= 1) break;
        }
        System.out.println();
        System.out.println("Total passwords generated: " + totalPasswords);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Game Over.");
    }
    
    /**
     * Generates an integer within a range.
     * @param min The minimum range.
     * @param max The maximum range.
     * @return A random integer between the minimum and the maximum.
     */
    public static int generateInt(int min, int max) {
        Random rng = new Random();
        return rng.nextInt((max - min) + 1) + min;
    }
    
    /**
     * Splits the words of a book array to an array of Strings.
     * @param book The book with a page number (first []), a paragraph (second [])
     * and the line number (third []).
     * @return An array of words from a line in the book.
     */
    public static String[] getLine(String[][][] book) {
        int pageNum = generateInt(0, book.length - 1); 
        int paraNum = generateInt(0, book[pageNum].length - 1);
        int lineNum = generateInt(0, book[pageNum][paraNum].length - 1);
       
        String[] line = book[pageNum][paraNum][lineNum].split(" ");
        return line;
    }
    
    public static String getWord(String[] line) {
        return line[generateInt(0, line.length - 1)];
    }
    
    public static boolean containsEscape(String word1, String word2, String word3) {
        if (word1.contains("\n") || word2.contains("\n") || word3.contains("\n")) {
            newLine++;
            return true;
        }
        return false;
    }
    
    public static boolean isSingle(String word1, String word2, String word3) {
        if (word1.length() == 1 || word1.length() == 1 || word1.length() == 1) {
            single++;
            return true;
        }
        return false;
    }
    
     public static boolean isSame(String word1, String word2, String word3) {
        if (word1.equals(word2) || word2.equals(word3) || word1.equals(word3)) {
            equal++;
            return true;
        }
        return false;
    }
     
    public static boolean isCorrectLength(String word1, String word2, String word3) {
        String s = word1 + word2 + word3;
        if (s.length() < 8 || s.length() >= 16) {
            length++;
            return true;
        }
        return false;
    }
    
    public static boolean containsUpper(String word1, String word2, String word3) {
        String s = word1 + word2 + word3;
        if (!s.equals(s.toLowerCase())) {
            return true;
        }
        upper++;
        return false;
    }
    
    public static boolean containsLower(String word1, String word2, String word3) {
        String s = word1 + word2 + word3;
        if (!s.equals(s.toUpperCase())) {
            return true;
        }
        lower++;
        return false;
    }
    
    // I don't know how to do this
    public static boolean containsOneSpecial(String word1, String word2, String word3) {
        String s = word1 + word2 + word3;
        return true;
    }
    
    public static boolean conditions(String word1, String word2, String word3) {
        if (containsEscape(word1, word2, word3)) return true;
        else if (isSingle(word1, word2, word3)) return true;
        else if (isSame(word1, word2, word3)) return true;
        else if (isCorrectLength(word1, word2, word3)) return true;
        else if (!containsUpper(word1, word2, word3)) return true;
        else if (!containsLower(word1, word2, word3)) return true;
        else return false;
    }
}
