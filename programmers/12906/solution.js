function solution(arr) {
  return arr.filter((each, index, array) => each !== array[index-1]);
}

console.log(solution([1,1,3,3,0,1,1]))
console.log(solution([4,4,4,3,3]))
