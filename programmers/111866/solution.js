function solution(surveys, choices) {
  let scores = {
    "R": 0,
    "T": 0,
    "C": 0,
    "F": 0,
    "J": 0,
    "M": 0,
    "A": 0,
    "N": 0,
  };

  for (let index = 0; index < surveys.length; index++) {
    _applySurvey(surveys[index], choices[index], scores)
  }

  return _generateType(scores);
}

function _applySurvey(survey, choice, scores) {
  if (choice === 4) {
    return;
  }

  const [firstType, secondType] = survey;

  if (choice < 4) {
    scores[firstType] += (4 - choice);
  }

  if (choice > 4) {
    scores[secondType] += (choice - 4);
  }
}

function _generateType(scores) {
  let type = "";

  type += scores["R"] >= scores["T"] ? "R" : "T";
  type += scores["C"] >= scores["F"] ? "C" : "F";
  type += scores["J"] >= scores["M"] ? "J" : "M";
  type += scores["A"] >= scores["N"] ? "A" : "N";

  return type;
}

console.log(solution(["AN", "CF", "MJ", "RT", "NA"], [5, 3, 2, 7, 5]))
console.log(solution(["TR", "RT", "TR"], [7, 1, 3]))
