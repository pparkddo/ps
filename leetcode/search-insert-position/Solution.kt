class Solution {

    fun searchInsert(nums: IntArray, target: Int): Int {
        return binarySearch(nums, target)
    }

    private fun binarySearch(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size - 1

        while (left <= right) {
            val mid = (left + right) / 2

            val value = nums[mid]

            if (value == target) {
                return mid
            }

            if (value < target) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }

        return left
    }
}
