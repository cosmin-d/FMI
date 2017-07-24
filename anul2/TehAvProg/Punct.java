class Punct{
    int x;
    int y;
    
    Punct(){}
    
    Punct(int a, int b)
	{
        x = a; 
        y = b;
    }
    
    public String toString()
	{
        return "(" + x + "," + y + ") ";
    }
}