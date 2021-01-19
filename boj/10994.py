BLANK = " "
STAR = "*"


def get_size(n):
    if n == 1:
        return 1
    return (n-1) * 4 + 1

    
def get_offset(size, current_size):
    return (size - current_size) // 2


def assign_star(table, size, n):
    current_size = get_size(n)
    offset = get_offset(size, current_size)
    for index in range(offset, offset+current_size):
        table[index][offset] = STAR
        table[offset][index] = STAR
        table[index][offset+current_size-1] = STAR
        table[offset+current_size-1][index] = STAR
    if n == 1:
        return
    assign_star(table, size, n-1)


def print_table(table):
    for row in table:
        print("".join(row))


def main():
    n = int(input())
    size = get_size(n)
    table = [[BLANK] * size for each in range(size)]
    assign_star(table, size, n)
    print_table(table)


main()
