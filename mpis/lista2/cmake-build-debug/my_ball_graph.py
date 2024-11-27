import numpy as np
import matplotlib.pyplot as plt

# Funkcja do wczytania danych z pliku
def read_data(file_name):
    n_values = []
    values = []
    with open(file_name, "r") as file:
        for line in file:
            n, value = line.strip().split(":")
            n_values.append(int(n))
            values.append(float(value))
    return np.array(n_values), np.array(values)

# Wczytaj dane z plików
files = ["Bn.txt", "Un.txt", "Cn.txt", "Dn.txt", "En.txt"]
data = {}

for file_name in files:
    variable_name = file_name.split(".")[0]
    n_values, values = read_data(file_name)
    data[variable_name] = {"n": n_values, "values": values}

# Obliczanie średnich dla każdego n
means = {}
for var, content in data.items():
    unique_n = np.unique(content["n"])
    mean_values = [np.mean(content["values"][content["n"] == n]) for n in unique_n]
    means[var] = {"n": unique_n, "mean": np.array(mean_values)}

# Wykresy dla każdej zmiennej
plt.figure(figsize=(12, 8))

for i, (var, content) in enumerate(data.items(), start=1):
    plt.subplot(3, 2, i)
    plt.scatter(content["n"], content["values"], label=f"{var} - pojedyncze dane", alpha=0.6)
    plt.plot(means[var]["n"], means[var]["mean"], label=f"{var} - średnia", color="red", linewidth=2)
    plt.title(f"Wykres zmiennej {var}")
    plt.xlabel("n")
    plt.ylabel("Wartość")
    plt.legend()
    plt.grid(True)

plt.tight_layout()
plt.savefig("zmienne_i_srednie.png")  # Zapisanie wykresu do pliku PNG
plt.close()
