/*
Given a string s, partition s such that every substring of the partition
 is a palindrome.

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]
 */

import java.util.ArrayList;
import java.util.List;

public class LeetCode131PalindromePartitioning {
//    // 法一：recursive-DFS 25.01% waiting improve
//    public List<List<String>> partition(String s) {
//        List<List<String>> result = new ArrayList<>();
//        if (s.length() == 0) return result;
//        if (s.length() == 1) {  // 如果只有 1 个元素，单作为一个 List 加入到 result 中
//            result.add(new ArrayList<>(Arrays.asList(s)));
//            return result;
//        }
//
//        for (int i = 0; i < s.length(); i++) {
//            if (isPalindrome(s, 0, i)) {
//                String curr = s.substring(0, i + 1);            // 当前字符串
//                String left = s.substring(i + 1, s.length());   // 剩下的字符串
//                List<List<String>> palindromeLists = partition(left);
//
//                // 如果无结果，则当前字符串独自作为一个List；否则，在逐个列表前加入该字符串
//                if (palindromeLists.size() == 0) {
//                    result.add(new ArrayList<>(Arrays.asList(curr)));
//                } else {
//                    for (List<String> palindromeList : palindromeLists) {
//                        palindromeList.add(0, curr);
//                        result.add(palindromeList);
//                    }
//                }
//            }
//        }
//
//        return result;
//    }

    // // 法二：backtrack-DFS 95%+
    // public List<List<String>> partition(String s) {
    //     List<List<String>> result = new ArrayList<>();
    //     List<String> currList = new ArrayList<>();
    //     partition(s, 0, currList, result);
    //     return result;
    // }

    // private void partition(String s, int index, List<String> currList, List<List<String>> result) {
    //     if (index == s.length()) {
    //         result.add(new ArrayList<>(currList));
    //         return;
    //     }

    //     for (int i = index; i < s.length(); i++) {
    //         if (isPalindrome(s, index, i)) {
    //             String currStr = s.substring(index, i + 1);
    //             currList.add(currStr);
    //             partition(s, i + 1, currList, result);
    //             currList.remove(currList.size() - 1);
    //         }
    //     }
    // }

    // // 判断是否为回文
    // private boolean isPalindrome(String s, int begin, int end) {
    //     for (int i = begin; i <= (begin + end) / 2; i++) {
    //         if (s.charAt(i) != s.charAt(end - (i - begin))) return false;
    //     }

    //     return true;
    // }


    // DP+DFS: 2ms 95%
    public List<List<String>> partition(String s) {
        if (s.length() == 0) return new ArrayList<>();
        
        boolean[][] dp = new boolean[s.length()][s.length()];
        List<List<String>> res = new ArrayList<>();
        List<String> curr = new ArrayList<>();
        
        // 这步 DP 是关键
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                }
            }
        }
        
        dfs(curr, res, 0, s, dp);
        
        return res;
    }
    
    private void dfs(List<String> curr, List<List<String>> res, int idx, String s, boolean[][] dp) {
        if (idx == s.length()) {
            res.add(new ArrayList<>(curr));
            return;
        }
        
        for (int i = idx + 1; i < s.length() + 1; i++) {
            // [idx, i)
            if (dp[idx][i - 1]) {
                String tmp = s.substring(idx, i);
                curr.add(tmp);
                dfs(curr, res, i, s, dp);
                curr.remove(curr.size() - 1);
            }
        }
    }
}
