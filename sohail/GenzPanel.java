package sohail;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.Vector;

import javax.swing.JFrame;

// class GenzPanel<S extends Space,Transf>  !!
//public class GenzPanel<S extends Space,Transf> extends JFrame {
//GenzPanel.S
public class GenzPanel<XS extends Space> extends JFrame {


	final int RADIUS=3;


	//XSpace xsinstance=new XSpace();
	//XS xsinstance=new XS();
	XS xsinstance;

	//XSpace XS;
	Vector<MyPoint<XS>> points;
	//XSAace
	//private static final long serialVersionUID = 111111L;

	//Almost working:
	//	Vector<MyPoint<S>> points=new Vector<>();
	//	MyPoint<S> trypoint=new MyPoint<>();

	void addPoint(MouseEvent e){
		points.add(new MyPoint<XS>(xsinstance)); // !
		//points.add(new MyPoint<GenzPanel.S>()); // !
		//p=new MyPoint<GenzPanel.S>();
		//p.x[0]=e.getPoint().x;
		//p.x[1]=e.getPoint().y;
		//points.add(p);

		//points.lastElement().x[0]=e.getPoint().x - this.getWidth()/2;
		//points.lastElement().x[1]=e.getPoint().y - this.getHeight()/2;
		double w=this.getW();
		double h=this.getH();
		points.lastElement().x[0]=(e.getPoint().x/w-0.5)*2;
		points.lastElement().x[1]=(e.getPoint().y/h-0.5)*2;
	}
	void tryPoint(MouseEvent e){
	}

	int getW(){
		return this.getWidth()-20;
	}
	int getH(){
		return this.getHeight()-20;
	}
	GenzPanel(XS s){

		//XS xsinstance; //=new XS();
		xsinstance = s;

		this.setSize(400, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				untryPoint();

			}

			@Override
			public void mousePressed(MouseEvent e) {
				addPoint(e);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				untryPoint();								
			}

			private void untryPoint() {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				tryPoint(e);				
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				addPoint(e);

			}
		});

		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				tryPoint(e);
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				addPoint(e);

			}
		});

		points=new Vector<MyPoint<XS>>();


		//setTitle(XS.class.toString());
		//setTitle(XS.toString());
		setTitle(xsinstance.getClass().toString());
	}

	public void paint(Graphics g){
		super.paint(g);

		double w=this.getW();
		double h=this.getH();

		g.setColor(new Color(100,100,100));
		g.drawLine(0, (int)(h/2), (int)(w), (int)(h/2));
		g.drawLine((int)(w/2), 0, (int)(w/2), (int)(h));



		//Timer.this
		for(MyPoint<XS> p : points){
			g.drawOval((int)(p.x[0]*w/2+w/2),(int)(p.x[1]*h/2+h/2), RADIUS, RADIUS);

		}
		//g.drawOval(50,	 50, 10, (int) (10+Math.random()));

		//for(MyPoint<XS> p : points)
		//	g.drawOval((int)(p.x[0]*w+w/2),(int)(p.x[1]*h+h/2), 10, (int) (10+Math.random()));

		for(GenzPanel<?> ps : pointSources) {
			//q=ps.providePoint();
			//for(MyPoint<XS> p : q)
			//	g.drawOval((int)(p.x[0]),(int)(p.x[1]), 10, (int) (10+Math.random()));
			//for(MyPoint<?> p : ps.points)
			//working: (but no transform)

			//for(MyPoint<?> p : ps.points)
			//	g.drawOval((int)(p.x[0]),(int)(p.x[1]), 10, (int) (10+Math.random()));


			//for(MyPoint<?> p : ps.points){
			//	MyPoint p2 = ps.trans.transform(p);				
			//		g.drawOval((int)(p2.x[0]),(int)(p2.x[1]), 10, (int) (10+Math.random()));
			//}

			Vector<MyPoint<?>> pa = ps.provideTransformedPoints();
			for(MyPoint<?> p : pa)
				g.drawOval((int)(p.x[0]*w/2+ w/2),(int)(p.x[1]*h/2+ h/2), RADIUS, RADIUS);
			//works!
			//but not the other way round

		}


		repaint();
		if (trans_target != null)
			trans_target.repaint();
	}

	//The type (target space) won't be XS
	private Vector<MyPoint<?>> provideTransformedPoints() {
		Vector<MyPoint<?>> r=new Vector<>();
		for(MyPoint<XS> p : points){
			//MyPoint<?> p2 = ps.trans.transform(p);				
			//r.add(p2);
			r.add(this.trans.transform(p));
		}
		return r;
	}

	Transformation<XS,?> trans;
	GenzPanel<XS> trans_target;

	Vector<GenzPanel> pointSources=new Vector<>();



	void addTransformed(Transformation<XS,?> t, GenzPanel<XS> y){
		this.trans = t;
		this.trans_target=y;
		this.trans_target.pointSources.add(this);
	}




	public static void main(String arg[]){
		XSpace xs=new XSpace();
		YSpace ys=new YSpace();
		ZSpace zs=new ZSpace();
		GenzPanel xpanel=new GenzPanel<XSpace>(xs);
		GenzPanel ypanel=new GenzPanel<YSpace>(ys);
		GenzPanel zpanel=new GenzPanel<ZSpace>(zs);
		Transformation txy=new LinearTransformationX2Y(-2,-2, ys);
		Transformation tyz=new TransformationY2Z(zs);
		xpanel.addTransformed(txy, ypanel);
		ypanel.addTransformed(tyz, zpanel);
	}
}

