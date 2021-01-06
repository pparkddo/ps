def solution(phone_book):
    numbers = {each: id_ for id_, each in enumerate(phone_book)}
    for number in numbers:
        origin_id = numbers[number]
        for index in range(len(number)):
            prefix = number[:index+1]
            matched_id = numbers.get(prefix) 
            if matched_id is not None and matched_id != origin_id:
                return False
    return True


if __name__ == "__main__":
    phone_books = [
        ["119", "97674223", "1195524421"],
        ["123", "456", "789"],
        ["12", "123", "1235", "567", "88"],
    ]
    answers = [False, True, False]

    for p, a in zip(phone_books, answers):
        if a == solution(p):
            print("::: good!")
        else:
            print("::: bad")
