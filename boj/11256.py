t = int(input())

output = []
for _ in range(t):
    j, n = map(int, input().split())
    boxes = [list(map(int, input().split())) for _ in range(n)]
    
    availables = [r*c for r, c in boxes]
    availables.sort(reverse=True)
    
    count = 0
    while j > 0:
        j = j - availables[count]
        count = count + 1
    
    output.append(count)

print("\n".join(map(str, output)))
