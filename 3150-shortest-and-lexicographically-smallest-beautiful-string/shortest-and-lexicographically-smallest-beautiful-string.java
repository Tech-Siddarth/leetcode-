class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int bestLeft = -1;
        int minLength = s.length() + 1;
        int ones = 0;
        
        for (int l = 0, r = 0; r < s.length(); ++r) {
            if (s.charAt(r) == '1') {
                ones++;
            }
            
            while (ones == k) {
                int currentLen = r - l + 1;
                
                if (currentLen < minLength) {
                    minLength = currentLen;
                    bestLeft = l;
                } else if (currentLen == minLength) {
                    String candidate = s.substring(l, l + minLength);
                    String currentBest = s.substring(bestLeft, bestLeft + minLength);
                    if (candidate.compareTo(currentBest) < 0) {
                        bestLeft = l;
                    }
                }
                
                if (s.charAt(l) == '1') {
                    ones--;
                }
                l++;
            }
        }
        
        return bestLeft == -1 ? "" : s.substring(bestLeft, bestLeft + minLength);
    }
}