public class LeetCode58LengthofLastWord {
    public int lengthOfLastWord(String s) {
        String[] words = s.split(" ");
        if (words.length == 0) return 0;

        String lastWord = words[words.length - 1];

        return lastWord.length();
    }
}