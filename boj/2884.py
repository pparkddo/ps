h, m = map(int, input().split())

h = (24*(m<45 and h==0)+h) - 1*(m<45)
m = (60*(m<45)+m) - 45

print(h, m)
