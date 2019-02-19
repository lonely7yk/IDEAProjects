/**
 * Created by shengliyi on 2017/3/4.
 */

public class Main {
//-2147483648
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        int x = 4;
        ListNode head = new ListNode(nums[0]);
        ListNode a = head;

        for (int i = 1; i < nums.length; i++) {
            a.next = new ListNode(nums[i]);
            a = a.next;
        }

        a = head;
        while (a != null) {
            System.out.print(a.val + "\t");
            a = a.next;
        }
        System.out.println();

        ListNode result = new LeetCode86PartitionList().partition(head, x);

        a = result;
        while (a != null) {
            System.out.print(a.val + "\t");
            a = a.next;
        }
    }

}
