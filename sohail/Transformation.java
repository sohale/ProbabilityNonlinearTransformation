package sohail;

public interface Transformation<S1 extends Space,S2 extends Space> {
	MyPoint<S2> transform(MyPoint<S1> s);
	//MyPoint<S2> transform(MyPoint<?> s);
	
	//Transformation<S2,S1> getInverse();  /brilliant
	//Transformation<S2,S1> getInverse(S2 s1space);
	Transformation<S2,S1> getInverse(S1 s1space);
}
