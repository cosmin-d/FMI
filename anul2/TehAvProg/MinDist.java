class MinDist{
	Punct A;
	Punct B;
    double dist;
    
    MinDist(){}
    
    public String toString()
	{
        return "[" + A +", "+ B + "] = " +String.format("%.6f",dist) ;
    }
}