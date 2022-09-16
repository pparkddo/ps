normal_counts = [1, 1, 2, 2, 2, 8]

counts = list(map(int, input().split()))

print(" ".join([str(n - c) for n, c in zip(normal_counts, counts)]))
