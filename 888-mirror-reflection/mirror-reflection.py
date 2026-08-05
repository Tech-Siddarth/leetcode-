class Solution:
    def mirrorReflection(self, p: int, q: int) -> int:
        g = gcd(p, q)

        p //= g
        q //= g

        if p % 2 == 1 and q % 2 == 1:
            return 1
        elif p % 2 == 1:
            return 0
        else:
            return 2