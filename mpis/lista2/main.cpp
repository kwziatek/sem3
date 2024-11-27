#include <iostream>
#include <random>
#include <limits>
#include <fstream>


using namespace std;
class MersenneTwister {
private:
    mt19937 generator; // Silnik Mersenne Twister
    uniform_int_distribution<int> distribution; // Dystrybucja jednorodna

public:
    // Konstruktor z możliwością ustawienia zakresu i nasienia
    MersenneTwister(int min = numeric_limits<int>::min(),
                    int max = numeric_limits<int>::max(),
                    unsigned seed = random_device{}())
        : generator(seed), distribution(min, max) {}

    // Metoda generująca losową liczbę całkowitą
    int generate() {
        return distribution(generator);
    }

    // Metoda zmieniająca zakres liczb losowych
    void setRange(int min, int max) {
        distribution = uniform_int_distribution<int>(min, max);
    }

    // Metoda ustawiająca nowe nasienie
    void setSeed(unsigned seed) {
        generator.seed(seed);
    }
};

class Simulation {
private:
    MersenneTwister mt;
    int counter = 0;
    static int * initailize(int n) {
        int * numberOfBalls;
        for(int i = 0; i < n + 1; i++) {
            numberOfBalls[i] = 0;
        }
        return numberOfBalls;
    }

public:
    int putBallsIntoBinsA(int n) {
        int * numberOfBalls = initailize(n);
        counter = 0;
        mt.setRange(1, n);
        while(true){
            int random = mt.generate();
            numberOfBalls[random]++;
            counter++;
            if(numberOfBalls[random] == 2) {
                break;
            }

        }
        return counter;
    }
    int putBallsIntoBinsB(int n) {
        int * numberOfBalls = initailize(n);
        counter = 0;
        mt.setRange(1, n);
        for(int i = 0; i < n; i++) {
            numberOfBalls[mt.generate()]++;
        }
        for(int i = 0; i < n; i++) {
            if(numberOfBalls[i] == 0) {
                counter++;
            }
        }
        return counter;
    }

};

class Solution{
    public:
         static vector<int> solve(int n) {
            int counter = 0;
            vector<int> urns(n + 2, 0);
            MersenneTwister mt(1, n);
            int smallestIndexFor1 = 1;
            int smallestIndexFor2 = 1;
            int B = 0;
            int U = 0;
            int C = 0;
            int D = 0;
            int E = 0;
            while(true) {
                counter++;
                int random = mt.generate();
                urns[random]++;
                if(counter == n) {
                    int count = 0;
                    for(int i = 0; i < n + 1; i++) {
                        if(urns[i] == 0) {
                            count++;
                        }
                    }
                    U = count;
                }
                if(random == smallestIndexFor1) {
                    int foo = random + 1;
                    while(urns[foo] > 0) {
                        foo++;
                    }
                    smallestIndexFor1 = foo;
//                    cout << "1 " << smallestIndexFor1 << " " << counter << " " << C << endl;
                }
                if(random == smallestIndexFor2 && urns[random] == 2){
                    int foo = random + 1;
                    while(urns[foo] > 1) {
                        foo++;
                    }
                    smallestIndexFor2 = foo;
//                    cout << "2 " << smallestIndexFor2 << " " << counter << " " << C << endl;

                }

                if(B == 0 && urns[random] == 2) {
                    B = counter;

                }
                if(smallestIndexFor1 == n + 1 && C == 0) {
                    C = counter;
                }
                if(smallestIndexFor2 == n + 1) {
                    D = counter;
                    E = D - C;
                    break;
                }

            }
//            cout << B << " " << U << " " << C << " " << D << " " << E << endl;
            vector<int> result = {B, U, C, D, E};
             return result;
        }
};

int main() {
    //skorzystać z vectora zamaist tablicy
    //vector<int> urns(0, n + 1);
    //odwoływac się urns[mt.generate]
    //nie ma co bawić się w podejście obiektowe - napisać jedną metode solve która policzy wszystko na raz
    double time1, timedif;
    time1 = (double) clock();            /* get initial time */
    time1 = time1 / CLOCKS_PER_SEC;      /*    in seconds    */

    ofstream outBn("Bn.txt");
    ofstream outUn("Un.txt");
    ofstream outCn("Cn.txt");
    ofstream outDn("Dn.txt");
    ofstream outEn("En.txt");
    double resultB;
    double resultU;
    double resultC;
    double resultD;
    double resultE;

    int k = 50;
    for(int j = 1000; j <= 100000; j += 1000) {
        resultB = 0.0;
        resultU = 0.0;
        resultC = 0.0;
        resultD = 0.0;
        resultE = 0.0;
        for(int i = 0; i < k; i++) {
            vector<int> result = Solution::solve(j);
//            resultB += result[0];
//            resultU += result[1];
//            resultC += result[2];
//            resultD += result[3];
//            resultE += result[4];
            outBn << j << ":"<< result[0] << endl;
            outUn << j << ":"<< result[1] << endl;
            outCn << j << ":"<< result[2] << endl;
            outDn << j << ":"<< result[3] << endl;
            outEn << j << ":"<< result[4] << endl;
        }
//        resultB /= k;
//        resultU /= k;
//        resultC /= k;
//        resultD /= k;
//        resultE /= k;

    }

    /* call clock a second time */
    timedif = ( ((double) clock()) / CLOCKS_PER_SEC) - time1;
    printf("The elapsed time is %lf seconds\n", timedif);

    return 0;
}
