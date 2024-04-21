public class Probabilites {

    public static double getEntropy(double[] probabilites) {
        double sum = 0;
        double base = 2.0;
        for (int i = 0; i < probabilites.length; i++) {
            sum += probabilites[i] * (Math.log(probabilites[i])/Math.log(base));
        }
        return sum * (-1);
    }
}
