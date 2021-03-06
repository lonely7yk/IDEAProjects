/**
 * Created by shengliyi on 2017/3/18.
 */
public class LeetCode005longestPalindrome {

    static int maxLength;
    static String res;

    public static String longestPalindrome(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1) {
                if (s.charAt(i) == s.charAt(i+1)) {
                    Judge(s,i);
                    Judge(s, i ,i+1);
                } else {
                    Judge(s, i);
                }
            }

        }
        return res;
    }

    public static void Judge(String s, int i) {
        int left = i, right = i;
        while (s.charAt(left) == s.charAt(right)) {
            right++;
            left--;
            if (right >= s.length() || left < 0) break;
        }
        if (right - left - 1 > maxLength) {
            maxLength = right - left - 1;
            res = s.substring(left+1, right);
        }
    }

    public static void Judge(String s, int i, int j) {
        int left = i, right = j;
        while (s.charAt(left) == s.charAt(right)) {
            right++;
            left--;
            if (right >= s.length() || left < 0) break;
        }
        if (right - left - 1 > maxLength) {
            maxLength = right - left - 1;
            res = s.substring(left+1, right);
        }
    }
}
