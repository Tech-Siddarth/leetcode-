class Solution {
public:
    string lexPalindromicPermutation(string s, string target) {
        int n = s.size();
        vector<int> cnt(26);

        for (char c : s) cnt[c - 'a']++;

        char mid = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2) {
                if (mid) return "";
                mid = 'a' + i;
            }
            cnt[i] /= 2;
        }

        string left;

        auto build = [&]() {
            string t = left;
            for (int i = 25; i >= 0; i--)
                t += string(cnt[i], 'a' + i);

            string r = t;
            reverse(r.begin(), r.end());

            return t + (mid ? string(1, mid) : "") + r;
        };

        for (int pos = 0; pos < n / 2; pos++) {
            for (int c = 0; c < 26; c++) {
                if (!cnt[c]) continue;

                cnt[c]--;
                left.push_back('a' + c);

                string candidate = build();

                if (candidate > target) {
                    break;
                }

                left.pop_back();
                cnt[c]++;
            }

            if ((int)left.size() != pos + 1)
                return "";
        }

        string ans = build();
        return ans > target ? ans : "";
    }
};