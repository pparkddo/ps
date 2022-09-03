def solution(nums):
    return min(len(set(nums)), int(len(nums)/2))


if __name__ == '__main__':
    print(solution([3, 1, 2, 3]))
    print(solution([3, 3, 3, 2, 2, 4]))
    print(solution([3, 3, 3, 2, 2, 2]))
