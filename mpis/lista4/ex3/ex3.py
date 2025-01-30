import numpy as np
import matplotlib.pyplot as plt
# Ustal liczby kroków i liczbę realizacji
step_counts = [100, 1000, 10000]
k = 5000  # Liczba realizacji


# Funkcja symulująca proces błędzenia losowego
def simulate_random_walk(N):
    # Generowanie kroków (+1/-1) z równym prawdopodobieństwem
    steps = np.random.choice([-1, 1], size=N)

    # Obliczanie pozycji w każdym kroku
    S = np.cumsum(steps)  # S_N = X_1 + ... + X_N

    return S


# Funkcja obliczająca frakcję czasu nad osią OX
def calculate_fraction(N, realizations):
    L = np.zeros(k)  # Zliczanie momentów nad osią OX (D_n)

    for i, walk in enumerate(realizations):
        # Wyznaczanie D_n - momenty nad osią OX
        D = (walk > 0) | (np.roll(walk, 1) > 0)  # D_n = 1, gdy S_n > 0 lub S_n-1 > 0
        L[i] = np.sum(D)  # Zliczanie momentów dla realizacji i

    # Frakcja czasu
    P = L / N  # Frakcja czasu P_N
    return P


# Główna pętla do symulacji i obliczeń
results = {}

for N in step_counts:
    # Symulacja k realizacji
    realizations = [simulate_random_walk(N) for _ in range(k)]

    # Obliczanie frakcji czasu
    P_N = calculate_fraction(N, realizations)

    # Zapisanie wyników dla danej wartości N
    results[N] = P_N

# Liczba kubełków
num_bins = 20

# Funkcja gęstości prawdopodobieństwa dla rozkładu arcsin
def arcsin_pdf(x):
    pdf = np.zeros_like(x)
    mask = (x > 0) & (x < 1)  # Tylko wartości wewnętrzne
    pdf[mask] = 1 / (np.pi * np.sqrt(x[mask] * (1 - x[mask])))
    return pdf

# Wygeneruj wartości x do wykresu gęstości
x_values = np.linspace(0, 1, 1000)[1: -1]
pdf_values = arcsin_pdf(x_values)

# Iteracja przez różne wartości N
for N in [100, 1000, 10000]:
    data = results[N]  # Frakcje czasu nad osią OX dla N

    # Tworzenie histogramu z normalizacją
    plt.figure()  # Tworzenie nowej figury dla każdego histogramu
    plt.hist(data, bins=num_bins, density=True, alpha=0.6, color='b', edgecolor='black')

    # Dodanie wykresu gęstości prawdopodobieństwa
    plt.plot(x_values, pdf_values, color='r', label='Gęstość prawdopodobieństwa arcsin(x)')

    # Ustawienia tytułu i etykiet osi
    plt.title(f'Histogram z estymacją funkcji gęstości prawdopodobieństwa (N={N})')
    plt.xlabel('Wartości')
    plt.ylabel('Gęstość prawdopodobieństwa')

    # Zapisz histogram do pliku
    plt.grid(True)
    plt.savefig(f'histogram_N_{N}.png')  # Zapisz wykres jako plik PNG
    plt.close()  # Zamknij wykres, aby uniknąć złożenia wielu wykresów w jednym oknie