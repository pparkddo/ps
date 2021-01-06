from datetime import date
month, day = [int(each) for each in input().split(" ")]
print(date(2007, month, day).strftime("%a").upper())
