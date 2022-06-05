public class VisualizeEZ {
	public static void main(String[] args) {
		int N    = Integer.parseInt(args[0]);
		double p = Double.parseDouble(args[1]);
		int T    = Integer.parseInt(args[2]);
		StdDraw.setCanvasSize(800, 800);
		StdDraw.show(0);
		for (int i = 0; i < T; i++) {
			StdDraw.clear();
			boolean[][] open = PercolationEZ.random(N, p);
			boolean[][] full = PercolationEZ.flow(open);
			String percolates = "doesn't percolate";
			StdDraw.setPenColor(0, 0, 0);
			PercolationEZ.show(open, false);
			StdDraw.setPenColor(191, 191, 191);
			PercolationEZ.show(open, true);
			StdDraw.setPenColor(0, 127, 255);
			PercolationEZ.show(full, true);
			StdDraw.show(5000);
			for (int j = 0; j < N; j++) {
				if (full[N-1][j]) percolates = "percolates";
			}
			StdOut.printf(" Simulation #%d finished: " + percolates + "\n", (i+1));
		}
	}
}