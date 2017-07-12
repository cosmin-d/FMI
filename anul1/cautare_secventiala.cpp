#include <iostream>

using namespace std;

int main()
{
    int i,n,x,k;
    k=0;
    cout<<"n= ";
    cin>>n;
    int a[n];
    cout<<"introduceti elementele vectorului in ordine crescatoare: ";
    for (i=0;i<n;i++)
        cin>>a[i];
        cout<<"introduceti elementul cautat: ";
        cin>>x;
        for(i=0;i<n;i++)
        {
            if(a[i]==x)
                {
                    cout<<"elementul cautat a fost gasit pe pozitia "<<i+1;
                    k=1;
                  }
}
     if(k==0)
    cout<<"elementul nu a fost gasit";
}
