class Solution {

    fun rotate(nums: IntArray, k: Int): Unit {
        val rotateCount = k % nums.size
        reverse(nums, 0, nums.lastIndex)
        reverse(nums, 0, rotateCount-1)
        reverse(nums, rotateCount, nums.lastIndex)
    }

    private fun reverse(nums: IntArray, left: Int, right: Int) {
        var leftIndex = left
        var rightIndex = right
        while (leftIndex <= rightIndex) {
            swap(nums, leftIndex, rightIndex)
            leftIndex++
            rightIndex--
        }
    }

    private fun swap(nums: IntArray, left: Int, right: Int) {
        nums[left] = nums[right].also { nums[right] = nums[left] }
    }
}
