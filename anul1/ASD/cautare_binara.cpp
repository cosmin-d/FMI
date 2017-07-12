#include <iostream>

using namespace std;

int main()
{
    int st,dr,mij,i,j,n,x,k;
    k=0;
    cout<<"n= ";
    cin>>n;
    cout<<"introduceti elementele vectorului in ordine crescatoare: ";
    int a[n];
    for (i=0;i<n;i++)
        cin>>a[i];
        cout<<"introduceti elementul cautat: ";
        cin>>x;
    st=0;
    dr=n;
    mij=dr/2;
    if(a[mij]==x)
        cout<<"elementul cautat a fost gasit pe pozitia "<<mij+1;
    else if(x<a[mij])
    {        j=mij-1;
              while(j>=st)
              {if (a[j]==x)

                  {
                      k=1;
                      cout<<"elementul cautat a fost gasit pe pozitia "<<j+1<<endl;
                  }
                  j--;
              }
    }
    else
    {
        j=mij+1;
        while(j<=dr)
        {
            if (a[j]==x)
                  {
                      k=1;
                      cout<<"elementul cautat a fost gasit pe pozitia "<<j+1<<endl;
                  }
                  j++;
        }
if(k==0)
    cout<<"elementul nu a fost gasit";

}
}
