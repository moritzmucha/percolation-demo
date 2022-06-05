public class Estimate {
	public static double eval(int N, double p, int T) {
		int count = 0;
		for (int t = 0; t < T; t++) {
			boolean[][] open = Percolation.random(N, p);
			if (Percolation.percolates(open)) count++;
		}
		return (double) count / T;
	}
	
	public static void main(String[] args) {
		int N    = Integer.parseInt(args[0]);
		double p = Double.parseDouble(args[1]);
		int T    = Integer.parseInt(args[2]);
		double q = eval(N, p, T);
		System.out.println(q);
	}
}