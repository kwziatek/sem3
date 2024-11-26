#include <iostream>
#include <random>
#include <limits>


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

int main() {
    Simulation simulation;
    //skorzystać z vectora zamaist tablicy
    //vector<int> urns(0, n + 1);
    //odwoływac się urns[mt.generate]
    //nie ma co bawić się w podejście obiektowe - napisać jedną metode solve która policzy wszystko na raz
    double result = 0.0;
    int k = 50;
    for(int i = 0; i < k; i++) {
        int singleResult = simulation.putBallsIntoBinsA(1000);
        result += singleResult;
    }
    result /= k;
    cout << result << endl;

    result = 0;

    return 0;
}
