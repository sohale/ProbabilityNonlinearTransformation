package sohail;

import org.apache.commons.math3.special.Erf;

//Later, Templatify this.
public class TransformationY2Z implements Transformation<YSpace,ZSpace> {


	ZSpace zspace; //make it static
	TransformationY2Z(ZSpace spc){
		this.zspace=spc;
	}
	
	@Override
	public MyPoint<ZSpace> transform(MyPoint<YSpace> s) {
		MyPoint<ZSpace> p = new MyPoint<ZSpace>(zspace);
		for(int i=0;i<zspace.getDims();i++)
			p.x[i]=0.5*Erf.erfc(-s.x[i] / Math.sqrt(2.0) * 10);
			//noprmp: p = 0.5 * erfc(-z ./ sqrt(2));
		return p;
	}



	@Override
	public Transformation<ZSpace, YSpace> getInverse(YSpace ys) {
		//normq = -sqrt(2).*erfcinv(2*p);
		//return new Transformation<ZSpace, YSpace>(ys) {
		return new TransformationZ2Y(ys, TransformationY2Z.this);
		
		
	}


}
