package sohail;

//Later, Templatify this.
public class LinearTransformationX2Y implements Transformation<XSpace,YSpace> {

	double[][] c;
	
	YSpace space2;
	LinearTransformationX2Y(double a, double b, YSpace spc2){
		space2=spc2;
		
		c=new double[2][2];
		c[0][0]=1;
		c[1][1]=1;
		c[1][0]=a;
		c[0][1]=b;
	}
	
	@Override
	public MyPoint<YSpace> transform(MyPoint<XSpace> s) {
		MyPoint<YSpace> p = new MyPoint<YSpace>(space2);
		p.x[0]=s.x[0]*c[0][0]+s.x[1]*c[0][1];
		p.x[1]=s.x[0]*c[0][1]+s.x[1]*c[1][1];
		
		return p;
	}

	@Override
	public Transformation<YSpace, XSpace> getInverse(XSpace s1space) {
		return null;
	}

}
