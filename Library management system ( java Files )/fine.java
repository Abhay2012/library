/*fine calculation*/
public class fine {
	public int getFine(String s,String s1)
	{
		int d,m,y,sd,sm,sy,td=0,tf;
		d=Integer.parseInt(s.substring(0,2));
		m=Integer.parseInt(s.substring(3,5));
		y=Integer.parseInt(s.substring(6,8));
		sd=Integer.parseInt(s1.substring(0,2));
		sm=Integer.parseInt(s1.substring(3,5));
		sy=Integer.parseInt(s1.substring(6,8));
		if(sy>y)
		{
			td+=365*(sy-y);
		}
		if(sm>m)
		{
			td+=30*(sm-m);
			if(m<2&&sm>2)
			{
				if(y%4==0)
				{
					td-=1;
				}
				else
				{
					td-=2;
				}
			}
			else
			{
				if((m%2)!=0)
				{
					td+=1;
				}
			}
		}
		if(sd>d)
		{
			td+=(sd-d);
		}
		td-=15;
		tf=td+(td/7);
		if(tf<0)
		{
			tf=0;
		}
		return tf;
	}
}
