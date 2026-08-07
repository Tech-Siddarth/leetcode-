class Solution:
    def smallestNumber(self, num: str, t: int) -> str:
        factors = {
            0: {},
            1: {},
            2: {2: 1},
            3: {3: 1},
            4: {2: 2},
            5: {5: 1},
            6: {2: 1, 3: 1},
            7: {7: 1},
            8: {2: 3},
            9: {3: 2}
        }

        need = {2: 0, 3: 0, 5: 0, 7: 0}

        for p in [2, 3, 5, 7]:
            while t % p == 0:
                need[p] += 1
                t //= p

        if t != 1:
            return "-1"

        def make_digits(cnt):
            a = cnt[2]
            b = cnt[3]

            d8 = a // 3
            a %= 3

            d9 = b // 2
            b %= 2

            d4 = a // 2
            a %= 2

            d6 = 0

            if a == 1 and b == 1:
                d6 = 1
                a = 0
                b = 0
            elif b == 1 and d4 == 1:
                d6 = 1
                d4 = 0
                a = 1
                b = 0

            return {
                2: a,
                3: b,
                4: d4,
                5: cnt[5],
                6: d6,
                7: cnt[7],
                8: d8,
                9: d9
            }

        def build(d):
            ans = []
            for digit in range(2, 10):
                ans.append(str(digit) * d[digit])
            return ''.join(ans)

        required = make_digits(need)
        required_len = sum(required.values())

        if required_len > len(num):
            return build(required)

        current = {2: 0, 3: 0, 5: 0, 7: 0}

        for ch in num:
            digit = int(ch)
            for p, count in factors[digit].items():
                current[p] += count

        first_zero = num.find('0')

        if first_zero == -1:
            if all(current[p] >= need[p] for p in need):
                return num
            first_zero = len(num)

        for i in range(len(num) - 1, -1, -1):
            digit = int(num[i])

            for p, count in factors[digit].items():
                current[p] -= count

            if i > first_zero:
                continue

            for bigger in range(digit + 1, 10):
                remaining = {}

                for p in [2, 3, 5, 7]:
                    used = current[p] + factors[bigger].get(p, 0)
                    remaining[p] = max(0, need[p] - used)

                suffix_digits = make_digits(remaining)
                suffix_len = sum(suffix_digits.values())
                available = len(num) - i - 1

                if suffix_len <= available:
                    ones = available - suffix_len

                    return (
                        num[:i]
                        + str(bigger)
                        + '1' * ones
                        + build(suffix_digits)
                    )

        required = make_digits(need)
        required_len = sum(required.values())

        return (
            '1' * (len(num) + 1 - required_len)
            + build(required)
        )


        