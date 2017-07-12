#include <stdio.h>
#include <stdlib.h>

    int main()
{
    int n, m;
    struct nod { int nr; struct nod *next; };
    struct nod *p, *q;
    int i, count;

    printf("n="); scanf("%d", &n);
    printf("m="); scanf("%d", &m);


    p = q = malloc(sizeof(struct nod));

    p->nr= 1;
    for (i = 2; i <= n;i++) {
        p->next = malloc(sizeof(struct nod));
        p = p->next;
        p->nr = i;
    }

    p->next = q;


    for (count = n; count > 1;count--) {
       for (i = 0; i < m - 1; i++)
          p = p->next;

       p->next = p->next->next;
    }

    printf("Last number is %d .", p->nr);

    return 0;
}

