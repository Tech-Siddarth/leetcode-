class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;
        
        int leftSum = 0;
        int rightSum = 0;
        int leftCount = 0;
        int rightCount = 0;
        
        for (int i = 0; i < half; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                leftCount++;
            } else {
                leftSum += (c - '0');
            }
        }
        
        for (int i = half; i < n; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                rightCount++;
            } else {
                rightSum += (c - '0');
            }
        }
        
     
        if ((leftCount + rightCount) % 2 != 0) {
            return true;
        }
        
        return 2 * (leftSum - rightSum) != 9 * (rightCount - leftCount);
    }
}