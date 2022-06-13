class Solution {

    fun removeDuplicates(nums: IntArray): Int {
        var answer = 0

        var lastFilledIndex = 0
        var previousNumber: Int? = null

        for (index in nums.indices) {
            val value = nums[index]

            if (previousNumber == null) {
                previousNumber = value
                answer++
                continue
            }

            if (previousNumber != value) {
                lastFilledIndex++
                nums[lastFilledIndex] = value
                previousNumber = value
                answer++
            }
        }

        return answer
    }
}
