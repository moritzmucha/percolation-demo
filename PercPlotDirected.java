public class PercPlotDirected {
	public static void curve(int N, double x0, double y0, double x1, double y1) {
		double gap = 0.01;
		double err = 0.005;
		int T = 10000;
		double xm = (x0 + x1)/2;
		double ym = (y0 + y1)/2;
		double fxm = EstimateDirected.eval(N, xm, T);
		if (x1 - x0 < gap && Math.abs(ym - fxm) < err) {
			StdDraw.setPenColor();
			StdDraw.line(x0, y0, x1, y1);
			return;
		}
		curve(N, x0, y0, xm, fxm);
		StdDraw.setPenColor(255, 0, 0);
		StdDraw.filledCircle(xm, fxm, 0.0025);
		curve(N, xm, fxm, x1, y1);
	}
	
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		StdDraw.setCanvasSize(1200, 800);
		curve(N, 0.0, 0.0, 1.0, 1.0);
	}
}