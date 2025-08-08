package GitFolder.hrutwikCode.comparator;
import java.util.ArrayList;
public class Practice {

        public static void main(String[] args) {
            int[] nums = {1, 2, 3, 7, 5, 8, 6, 4};
            int target = 10;

            ArrayList<String> printed = new ArrayList(); // To track printed pairs

            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] + nums[j] == target) {
//                        int a = Math.min(nums[i], nums[j]);
//                        int b = Math.max(nums[i], nums[j]);
                        int a, b;
                        if (nums[i] < nums[j]) {
                            a = nums[i];
                            b = nums[j];
                        } else {
                            a = nums[j];
                            b = nums[i];
                        }
                        String pair = a + "," + b;

                        // check if this pair is already printed
                        boolean alreadyPrinted = false;
//                        for (int k = 0; k < printed.size(); k++) {
//                            if (printed.get(k).equals(pair)) {
//                                alreadyPrinted = true;
//                                break;
//                            }
//                        }

//                        if (!alreadyPrinted) {
                            System.out.println("(" + a + ", " + b + ")");
                            printed.add(pair);
//                        }

                    }
                }
            }
//            System.out.println(printed);
        }


}
