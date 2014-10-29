package sohail;

// <? extends Space>
//as a tempolate ofd Space, or has the space?
//public class MyPoint<S> {

//Why only "extends?", and not implements? 
public class MyPoint<S extends Space>  {
	public double x[];
	//Space space_type;
	
	//static 
	public <S extends Space> MyPoint(S sss){
		//S.one_instance = new S();
		//Space ss;
		//int n=S.getDims();
		//Space ss=new S();
		//S ss=new S();
		//S ss=new S();
		
		//S s = S.instantiate(); //no contructor?
		//s=new X();
		//int n=s.getDims(); //not static.
		//this.space_type=s;
		int n=sss.getDims();
		
		x=new double[n];
	}
}


// OrderedPair<>("Even", 8);
