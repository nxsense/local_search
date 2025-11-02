package experiment;
public class ParameterConfig {
    public int nMin, nMax, step, instancesPerN, kMax;
    public double eps;
    public ParameterConfig(int nMin, int nMax, int step, int instancesPerN, int kMax, double eps) {
        this.nMin = nMin; this.nMax = nMax; this.step = step;
        this.instancesPerN = instancesPerN; this.kMax = kMax; this.eps = eps;
    }
}