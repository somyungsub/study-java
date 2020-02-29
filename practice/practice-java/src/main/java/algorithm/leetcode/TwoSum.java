package algorithm.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        Arrays.stream(twoSum(nums, target)).forEach(System.out::println);
        Arrays.stream(twoSum(new int[]{3,2,4}, 6)).forEach(System.out::println);
    }

    public static int[] twoSum(int[] nums, int target) {


//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                if (nums[i] + nums[j] == target) {
//                    return new int[]{i, j};
//                }
//            }
//        }


        List<Integer> list = IntStream.of(nums).boxed().collect(Collectors.toList());
        for (int i = 0; i < nums.length; i++) {
            int num = target - nums[i];
            int index = list.indexOf(num);
            if (index != i && list.contains(num)) {
                return new int[]{i, index};
            }
        }

        return new int[0];
    }
}
