patterns = [
    "c=",
    "c-",
    "dz=",
    "d-",
    "lj",
    "nj",
    "s=",
    "z=",
]

s = input()
for each in patterns:
    s = s.replace(each, "_")
print(len(s))
