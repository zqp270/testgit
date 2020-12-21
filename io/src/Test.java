/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/13 下午 1:59
 */
public class Test {

    int bj(int[] nums, int left, int right) {

        return nums[left] > nums[right] ? right:left;
    }

    //调整堆
    void heapAdjust(int[] nums,int ks,int length) {
        while(ks < length -1) {
            int right  = 2 * ks + 1;
            int left = 2 * ks;
            int temp = 0;
            if(left > length -1) {
                break;
            }
            if(left < length && right > length -1) {
                temp = bj(nums,left,left);
            }
            if(right < length) {
                temp = bj(nums,left,right);
            }
            if(nums[ks] > nums[temp]) {
                int temp1 = nums[ks];
                nums[ks] = nums[temp];
                nums[temp] = temp1;
            }
            ks = temp;
        }
    }

    void createHeap(int[] nums, int length) {
        for(int  i = (length - 1) / 2; i > 0; i--) {
            heapAdjust(nums,i,length);
        }
    }



    @org.junit.Test
    public void test() {
        int nums[] = {0,49,38,65,97,76,13,27,49};
        createHeap(nums,9);
        for(int i = 1; i < 9; i++) {
            System.out.printf("%d\n", nums[i]);
        }
        for(int i = 1; i < 9; i++) {
            System.out.printf("%d\n", nums[1]);
            nums[1] = nums[ 9 - i];
            heapAdjust(nums,1, 9 - i);
        }
    }
}
