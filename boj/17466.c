#include<stdio.h>
#pragma warning(disable:4996)
int main() {
    long long int n;
    long long int p;
    long long int answer = 1;

    scanf("%lld %lld", &n, &p);

    for (long long int number=1; number<=n; number++)
        answer = answer * number % p;

    printf("%lld", answer);
}