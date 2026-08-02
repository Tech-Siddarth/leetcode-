class Solution {
public:
    int reverse(int x) {
        int temp = x;
        long long reverse = 0;
        while (temp!=0){
            int digit = temp%10;
            reverse = reverse*10+digit;
            temp = temp/10;
            if (reverse > INT_MAX || reverse < INT_MIN)
                return 0;

            x /= 10;
        }
        return (int)reverse;
    }
};