table, questions = [], []

count, question_count = map(int, input().split())

for sheet in range(1, count+1):
    table.extend([sheet] * int(input()))

for _ in range(question_count):
    questions.append(int(input()))

for question in questions:
    print(table[question])
