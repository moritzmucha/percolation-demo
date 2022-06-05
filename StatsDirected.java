public class StatsDirected {
	public static double[] requiredVacancy(int N, double percTarget, boolean debug) {
		double[] requiredV = new double[N+1];
		int precFactor = 10;
		int precFactor1 = (int) Math.ceil(1.0/percTarget);
		int precFactor2 = (int) Math.ceil(1.0/(1.0 - percTarget));
		if (precFactor1 > precFactor) precFactor = precFactor1;
		if (precFactor2 > precFactor) precFactor = precFactor2;
		int precision = precFactor * N;
		int     pBase = 0;
		int   pOffset = 99;
		if (precFactor1 - 1 > pOffset) pOffset = precFactor1 - 1;
		double p = (double) pBase / (pBase + pOffset);
		double percRate = 0.0;
		boolean tooLow = false, tooHigh = false;
		
		for (int i = 1; i <= N; i++) {
			int T = (int) Math.ceil((double) precision/i);
			double eps = 0.6/T;
			do {
				if (tooLow) pBase++;
				if (tooHigh) pBase--;
				p = (double) pBase / (pBase + pOffset);
				for (int t = 1, percCount = 0; t < T; t++) {
					boolean[][] open = PercolationDirected.random(i, p);
					if (PercolationDirected.percolates(open)) percCount++;
					percRate = (double) percCount / t;
				}
				tooLow  = percRate < percTarget - eps || percRate == 0;
				tooHigh = percRate > percTarget + eps || percRate == 1;
			}
			while (tooLow || tooHigh);
			requiredV[i] = p;
			if (debug) {
				StdOut.printf(" i =%4d, p = %7.5f, pBase =%5d, percRate = %6.4f"
						+ " (%6.4f+/-%7.5f), %d trials\n",
						i, p, pBase, percRate, percTarget, eps, T);
			}
		}
		return requiredV;
	}
	
	public static void testEst(int N, int I, int precision) {
		for (int i = 1; i <= I; i++) {
			double p = (double) i / (i + 10);
			StdOut.printf("i = %d: %.3f\n", i, Estimate.eval(N, p, precision));
		}
	}
	
	public static void graph(double[] requiredV) {
		StdDraw.show(0);
		StdDraw.setCanvasSize(1200, 800);
		StdDraw.setPenColor(0, 96, 191);
		StdStats.plotBars(requiredV);
		StdDraw.show();
	}
	
	public static void main(String[] args) {
		int         N = Integer.parseInt(args[0]);
		boolean debug = false;
		try {
			if (args[2].equals("debug")) debug = true;
		} catch (ArrayIndexOutOfBoundsException e) {}
		try {
			double target = Double.parseDouble(args[1]);
			graph(requiredVacancy(N, target, debug));
		} catch (ArrayIndexOutOfBoundsException e) {
			for (int i = 1; i < 20; i++) {
				double target = i/20.0;
				graph(requiredVacancy(N, target, debug));
				StdOut.printf("Simulation for percolation probability %.2f finished.\n",
							  target);
				StdDraw.show(2000);
			}
		}
	}
}