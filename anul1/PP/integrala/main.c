#include <stdio.h>
#include <stdlib.h>
#define N 1000

int main()
{
 float i, a, b, y, sum = 0;
 printf("Limita inferioara: ");
 scanf("%f", &a);
 printf("Limita superioara: ");
 scanf("%f", &b);
 if (a > b) {
  i = a;
  a = b;
  b = i;
 }
 for (i = a; i < b; i += (b - a) / N) {
  y = i* i + 2 * i- 4;  //f(x) = X^2+2X-4
  sum += y * (b - a) / N;
 }

 printf("\nValoarea integralei este: %.3f", sum);
}
