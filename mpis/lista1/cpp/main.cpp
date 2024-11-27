#include <cmath>
#include <fstream>
#include <iostream>
#include <vector>
using namespace std;
class MersenneTwister {
private:
    static const int N = 624;
    static const int M = 397;
    static const unsigned long long MATRIX_A = 0x9908b0dfU;
    static const unsigned long long UPPER_MASK = 0x80000000U;
    static const unsigned long long LOWER_MASK = 0x7fffffffU;

    std::vector<unsigned long long> state;
    int index;

    void twist() {
        for (int i = 0; i < N; i++) {
            unsigned long long x = (state[i] & UPPER_MASK) + (state[(i + 1) % N] & LOWER_MASK);
            unsigned long long xA = x >> 1;
            if (x % 2 != 0) {
                xA ^= MATRIX_A;
            }
            state[i] = state[(i + M) % N] ^ xA;
        }
        index = 0;
    }

public:
    MersenneTwister(unsigned long long seed) : state(N), index(N + 1) {
        state[0] = seed;
        for (int i = 1; i < N; i++) {
            state[i] = (1812433253U * (state[i - 1] ^ (state[i - 1] >> 30)) + i) & 0xffffffffU;
        }
    }

    unsigned long long extract() {
        if (index >= N) {
            twist();
        }

        unsigned long long y = state[index++];
        y ^= (y >> 11);
        y ^= (y << 7) & 0x9d2c5680U;
        y ^= (y << 15) & 0xefc60000U;
        y ^= (y >> 18);

        return y;
    }

    // Function to get a random floating-point number in the range [0, 1)
    double getRandomDouble() {
        // Extract a 32-bit random number and divide by the maximum 32-bit unsigned value (0xFFFFFFFF)
        return static_cast<double>(extract() & 0xFFFFFFFF) / static_cast<double>(0xFFFFFFFF);
    }

    double getRandomDoubleInRange(double a, double b) {
        return a + (b - a) * getRandomDouble();
    }
};  /// implementation of Mersenne Twister - should be good enough for usage in statistics

class UsefulMethods {
public:
    static double valueOfFunctionExA(double x) {
        return power(x, 1.0 / 3.0);
    }

    static double valueOfFunctionExB(double x) {
        return sin(x);
    }

    static double valueOfFunctionExC(double x) {
        return 4 * x * power(1 - x, 3);
    }

    static double power(double base, double exponent) {
        return pow(base, exponent);
    }

    static void solveFor5_1(double a, double b, double m, MersenneTwister mt) {
        for(int n = 50; n <= 5000; n += 50) {
            ofstream out1("out1.txt", ios::app);
            double mean = 0;
            for(int j = 0; j < 5; j++) {
                double count = 0;
                for (int i = 0; i < n; i++) {
                    double x = mt.getRandomDoubleInRange(a, b);
                    double y = mt.getRandomDoubleInRange(0, m);
                    if(y <= UsefulMethods::valueOfFunctionExA(x)) {
                        count++;
                    }
                }
                double approx = (b - a) * m * count / (n * 1.0);
                mean += approx;
                out1 << n << " " <<  approx << endl;
            }
            out1.close();
            ofstream out2("out7.txt", ios::app);
            out2 << n << " " << mean / 5.0 << endl;
            out2.close();
        }
    }

    static void solveFor20_1(double a, double b, double m, MersenneTwister mt) {
        for(int n = 50; n <= 5000; n += 50) {
            ofstream out1("out3.txt", ios::app);
            double mean = 0;
            for(int j = 0; j < 50; j++) {
                double count = 0;
                for (int i = 0; i < n; i++) {
                    double x = mt.getRandomDoubleInRange(a, b);
                    double y = mt.getRandomDoubleInRange(0, m);
                    if(y <= UsefulMethods::valueOfFunctionExA(x)) {
                        count++;
                    }
                }
                double approx = (b - a) * m * count / (n * 1.0);
                mean += approx;
                out1 << n << " " << approx << endl;
            }
            out1.close();
            ofstream out2("out8.txt", ios::app);
            out2 << n << " " << mean / 50.0 << endl;
            out2.close();
        }
    }

    static void solveFor5_2(double a, double b, double m, MersenneTwister mt) {
        for(int n = 50; n <= 5000; n += 50) {
            ofstream out1("out9.txt", ios::app);
            double mean = 0;
            for(int j = 0; j < 5; j++) {
                double count = 0;
                for (int i = 0; i < n; i++) {
                    double x = mt.getRandomDoubleInRange(a, b);
                    double y = mt.getRandomDoubleInRange(0, m);
                    if(y <= UsefulMethods::valueOfFunctionExB(x)) {
                        count++;
                    }
                }
                double approx = (b - a) * m * count / (n * 1.0);
                mean += approx;
                out1 << n << " " << approx << endl;
            }
            out1.close();
            ofstream out2("out2.txt", ios::app);
            out2 << n << " " << mean / 5.0 << endl;
            out2.close();
        }
    }

    static void solveFor20_2(double a, double b, double m, MersenneTwister mt) {
        for(int n = 50; n <= 5000; n += 50) {
            ofstream out1("out5.txt", ios::app);
            double mean = 0;
            for(int j = 0; j < 50; j++) {
                double count = 0;
                for (int i = 0; i < n; i++) {
                    double x = mt.getRandomDoubleInRange(a, b);
                    double y = mt.getRandomDoubleInRange(0, m);
                    if(y <= UsefulMethods::valueOfFunctionExB(x)) {
                        count++;
                    }
                }
                double approx = (b - a) * m * count / (n * 1.0);
                mean += approx;
                out1 << n << " " << approx << endl;
            }
            out1.close();
            ofstream out2("out10.txt", ios::app);
            out2 << n << " " << mean / 50.0 << endl;
            out2.close();
        }
    }

    static void solveFor5_3(double a, double b, double m, MersenneTwister mt) {
        for(int n = 50; n <= 5000; n += 50) {
            ofstream out1("out6.txt", ios::app);
            double mean = 0;
            for(int j = 0; j < 5; j++) {
                double count = 0;
                for (int i = 0; i < n; i++) {
                    double x = mt.getRandomDoubleInRange(a, b);
                    double y = mt.getRandomDoubleInRange(0, m);
                    if(y <= UsefulMethods::valueOfFunctionExC(x)) {
                        count++;
                    }
                }
                double approx = (b - a) * m * count / (n * 1.0);
                mean += approx;
                out1 << n << " " << approx << endl;
            }
            out1.close();
            ofstream out2("out11.txt", ios::app);
            out2 << n << " " << mean / 5.0 << endl;
            out2.close();
        }
    }

    static void solveFor20_3(double a, double b, double m, MersenneTwister mt) {
        for(int n = 50; n <= 5000; n += 50) {
            ofstream out1("out7.txt", ios::app);
            double mean = 0;
            for(int j = 0; j < 50; j++) {
                double count = 0;
                for (int i = 0; i < n; i++) {
                    double x = mt.getRandomDoubleInRange(a, b);
                    double y = mt.getRandomDoubleInRange(0, m);
                    if(y <= UsefulMethods::valueOfFunctionExC(x)) {
                        count++;
                    }
                }
                double approx = (b - a) * m * count / (n * 1.0);
                mean += approx;
                out1 << n << " " << approx << endl;
            }
            out1.close();
            ofstream out2("out12.txt", ios::app);
            out2 << n << " " << mean / 50.0 << endl;
            out2.close();
        }
    }
};

int main() {

    MersenneTwister mt(5489U);  // Common default seed value

    double a, b, m;
    double count = 0;

    ofstream clean1("out1.txt");
    clean1.close();

    ofstream clean2("out2.txt");
    clean2.close();

    cin >> a >> b >> m;
    UsefulMethods::solveFor5_1(a, b, m, mt);
    UsefulMethods::solveFor20_1(a, b, m, mt);

    cin >> a >> b >> m;
    UsefulMethods::solveFor5_2(a, b, m, mt);
    UsefulMethods::solveFor20_2(a, b, m, mt);

    cin >> a >> b >> m;
    UsefulMethods::solveFor5_3(a, b, m, mt);
    UsefulMethods::solveFor20_3(a, b, m, mt);


    return 0;
}


