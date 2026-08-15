class Solution:
    def multiply(self, num1: str, num2: str) -> str:
        if num1 == "0" or num2 == "0":
            return "0"

        n, m = len(num1), len(num2)
        result = [0] * (n + m)

        for i in range(n - 1, -1, -1):
            for j in range(m - 1, -1, -1):
                product = int(num1[i]) * int(num2[j])

                p1 = i + j
                p2 = i + j + 1

                total = product + result[p2]

                result[p2] = total % 10
                result[p1] += total // 10

        while result[0] == 0:
            result.pop(0)

        return ''.join(map(str, result))