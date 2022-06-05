public class Visualize {
	public static void main(String[] args) {
		int N    = Integer.parseInt(args[0]);
		double p = Double.parseDouble(args[1]);
		int T    = Integer.parseInt(args[2]);
		StdDraw.setCanvasSize(800, 800);
		StdDraw.show(0);
		for (int i = 0; i < T; i++) {
			StdDraw.clear();
			boolean[][] open = Percolation.random(N, p);
			boolean[][] full = Percolation.flow(open);
			String percolates = "doesn't percolate";
			StdDraw.setPenColor(0, 0, 0);
			Percolation.show(open, false);
			StdDraw.setPenColor(191, 191, 191);
			Percolation.show(open, true);
			StdDraw.setPenColor(0, 127, 255);
			Percolation.show(full, true);
			StdDraw.show(5000);
			for (int j = 0; j < N; j++) {
				if (full[N-1][j]) percolates = "percolates";
			}
			StdOut.printf(" Simulation #%d finished: " + percolates + "\n", (i+1));
		}
	}
}