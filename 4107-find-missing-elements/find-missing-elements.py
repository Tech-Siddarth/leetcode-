class Solution:
    def findMissingElements(self, nums: List[int]) -> List[int]:
        st = set(nums)
        low = min(nums)
        high = max(nums)
        ans = []
        for i in range (low , high):
            if i not in st:
                ans.append(i)
        return ans