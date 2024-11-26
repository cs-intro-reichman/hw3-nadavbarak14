/** Functions for checking if a given string is an anagram. */
public class Anagram {
    public static void main(String args[]) {
        // Tests the isAnagram function.
        System.out.println(isAnagram("silent", "listen")); // true
        System.out.println(isAnagram("William Shakespeare", "I am a weakish speller")); // true
        System.out.println(isAnagram("Madam Curie", "Radium came")); // true
        System.out.println(isAnagram("Tom Marvolo Riddle", "I am Lord Voldemort")); // true

        // Tests the preProcess function.
        System.out.println(preProcess("What? No way!!!"));

        // Tests the randomAnagram function.
        System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");

        // Performs a stress test of randomAnagram 
        String testString = "1234567";
        Boolean pass = true;
        //// 10 can be changed to much larger values, like 1000
        for (int i = 0; i < 10; i++) {
            String randomAnagram = randomAnagram(testString);
            System.out.println(randomAnagram);
            pass = pass && isAnagram(testString, randomAnagram);
            if (!pass) break;
        }
        System.out.println(pass ? "test passed" : "test Failed");
    }

    public static boolean isPunctuation(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        String punctuation = " .,:;!?\"'()-{}[]<>";
        char character = str.charAt(0);
        return punctuation.indexOf(character) != -1;
    }

    public static boolean isSame(String first, String second) {
        return first.equals(second.toLowerCase()) || first.equals(second.toUpperCase());
    }

    // Returns true if the two given strings are anagrams, false otherwise.
    public static boolean isAnagram(String first, String second) {
        if (first == null || second == null) {
            return false;
        }
        if (first.isEmpty() && second.isEmpty()) {
            return true;
        }
        String[] firstElements = first.split("");
        int firstLength = first.length();
        int secondLength = second.length();
        String[] secondElements = second.split("");
        boolean found;
        for (int i = 0; i < firstLength; i++) {
            if (!isPunctuation(firstElements[i])) {
                found = false;
                for (int j = 0; j < secondLength && !found; j++) {
                    if (!isPunctuation(secondElements[j]) && isSame(firstElements[i], secondElements[j])) {
                        found = true;
                        secondElements[j] = "";
                    }
                }
                if (!found) {
                    return false;
                }
            }
        }
        for (int j = 0; j < secondLength; j++) {
            if (!secondElements[j].equals("") && !isPunctuation(secondElements[j])) {
                return false;
            }
        }
        return true;
    }

    // Returns a preprocessed version of the given string: all the letter characters are converted
    // to lower-case, and all the other characters are deleted, except for spaces, which are left
    // as is. For example, the string "What? No way!" becomes "whatnoway"
    public static String preProcess(String str) {
        if (str == null || str.isEmpty()) return str;
        StringBuilder processedString = new StringBuilder();
        for (char character : str.toCharArray()) {
            if (!isPunctuation(String.valueOf(character))) {
                processedString.append(Character.toLowerCase(character));
            } else if (character == ' ') {
                processedString.append(character);
            }
        }
        return processedString.toString();
    }

    // Returns a random anagram of the given string. The random anagram consists of the same
    // characters as the given string, re-arranged in a random order.
    public static String randomAnagram(String str) {
        String[] characters = str.split("");
        for (int i = characters.length - 1; i > 0; i--) {
            int randomIndex = (int) (Math.random() * (i + 1));
            String temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return String.join("", characters);
    }
}