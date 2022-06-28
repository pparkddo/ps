class Solution {

    fun singleNumber(nums: IntArray): Int {
        var answer = 0

        for (element in nums) {
            answer = answer.xor(element)
        }

        return answer
    }

    fun singleNumberWithReduce(nums: IntArray): Int {
        return nums.reduce { acc, number -> acc xor number }
    }
}
