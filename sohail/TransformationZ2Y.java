package sohail;

import org.apache.commons.math3.special.Erf;

public class TransformationZ2Y implements Transformation<ZSpace, YSpace> {

	YSpace yspace;
	Transformation<YSpace, ZSpace> inverseProvided;


	TransformationZ2Y(YSpace ys, Transformation<YSpace, ZSpace> inverseProvided ){
		this.yspace=ys;
		this.inverseProvided=inverseProvided;
	}

	@Override
	public MyPoint<YSpace> transform(MyPoint<ZSpace> s) {

		MyPoint<YSpace> p = new MyPoint<YSpace>(yspace);
		for(int i=0;i<yspace.getDims();i++)
			p.x[i]=-Math.sqrt(2.0)*Erf.erfcInv(2*s.x[i]);
		return p;


	}

	@Override
	public Transformation<YSpace, ZSpace> getInverse(ZSpace s1space){
			//throws Exception {
		if(inverseProvided!= null)
			return inverseProvided;
		else{
			return null;
			//throw new Exception("reverse not provided here");
			//ZSpace a_zpace = null;
			//return new TransformationY2Z(a_zpace);
		}
	}

}




//
//
//new Transformation<ZSpace, YSpace>() {
//
//	YSpace yspace=ys; //no access to parameters
//	
//	Transformation<ZSpace, YSpace>(YSpace ys){
//		
//	}
//	//how to assign in a constructur? 
//	
//	@Override
//	public MyPoint<YSpace> transform(MyPoint<ZSpace> zs) {
//		
//		MyPoint<YSpace> p = new MyPoint<YSpace>(ys);
//	for(int i=0;i<zspace.getDims();i++)
//			p.x[i]=-Math.sqrt(2.0)*Erf.erfcInv(2*s.x[i]);
//		return p;
//	
//	}
//	
//	@Override
//	public Transformation<YSpace, ZSpace> getInverse(YSpace ys) {
//		return TransformationY2Z.this; // cool!
//	}
//};
