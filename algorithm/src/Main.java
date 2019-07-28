/**
 * Created by shengliyi on 2017/3/4.
 */

public class Main {
    public static void main(String[] args) {
        int[] nums = {3,30,34,5,9};
        String result = new LeetCode179LargestNumber().largestNumber(nums);
        System.out.println(result);
    }

    public static void printLinkedList(LeetCode148SortList.ListNode node) {
        while (node != null) {
            System.out.print(node.val);
            if (node.next != null) System.out.print("-->");
            node = node.next;
        }

        System.out.println();
    }
}
