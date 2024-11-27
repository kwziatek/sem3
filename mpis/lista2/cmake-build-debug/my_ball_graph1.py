import numpy as np
import matplotlib.pyplot as plt

# Funkcja do wczytania danych (powtórzona)
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
files = ["Bn.txt", "Un.txt", "Cn.txt", "Dn.txt"]
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

# Obliczenie d(n) - c(n)
d_minus_c = {
    "n": means["Cn"]["n"],  # Zakładamy, że n dla Dn i Cn są identyczne
    "mean": means["Dn"]["mean"] - means["Cn"]["mean"]
}

# Wykresy
plt.figure(figsize=(16, 12))

# (a) b(n)/n oraz b(n)/sqrt(n)
b_n = means["Bn"]["mean"]
n = means["Bn"]["n"]
plt.subplot(3, 2, 1)
plt.plot(n, b_n / n, label=r"$b(n)/n$")
plt.plot(n, b_n / np.sqrt(n), label=r"$b(n)/\sqrt{n}$")
plt.xlabel("n")
plt.ylabel("Wartość")
plt.title("Ilorazy b(n)")
plt.legend()
plt.grid(True)

# (b) u(n)/n
u_n = means["Un"]["mean"]
n = means["Un"]["n"]
plt.subplot(3, 2, 2)
plt.plot(n, u_n / n, label=r"$u(n)/n$")
plt.xlabel("n")
plt.ylabel("Wartość")
plt.title("Iloraz u(n)/n")
plt.legend()
plt.grid(True)

# (c) Ilorazy c(n)
c_n = means["Cn"]["mean"]
n = means["Cn"]["n"]
plt.subplot(3, 2, 3)
plt.plot(n, c_n / n, label=r"$c(n)/n$")
plt.plot(n, c_n / (n * np.log(n)), label=r"$c(n)/n\ln(n)$")
plt.plot(n, c_n / (n ** 2), label=r"$c(n)/n^2$")
plt.xlabel("n")
plt.ylabel("Wartość")
plt.title("Ilorazy c(n)")
plt.legend()
plt.grid(True)

# (d) Ilorazy d(n)
d_n = means["Dn"]["mean"]
n = means["Dn"]["n"]
plt.subplot(3, 2, 4)
plt.plot(n, d_n / n, label=r"$d(n)/n$")
plt.plot(n, d_n / (n * np.log(n)), label=r"$d(n)/n\ln(n)$")
plt.plot(n, d_n / (n ** 2), label=r"$d(n)/n^2$")
plt.xlabel("n")
plt.ylabel("Wartość")
plt.title("Ilorazy d(n)")
plt.legend()
plt.grid(True)

# (e) Ilorazy d(n) - c(n)
d_c_n = d_minus_c["mean"]
n = d_minus_c["n"]
plt.subplot(3, 2, 5)
plt.plot(n, d_c_n / n, label=r"$(d(n)-c(n))/n$")
plt.plot(n, d_c_n / (n * np.log(n)), label=r"$(d(n)-c(n))/(n\ln(n))$")
plt.plot(n, d_c_n / (n * np.log(np.log(n))), label=r"$(d(n)-c(n))/(n\ln\ln(n))$")
plt.xlabel("n")
plt.ylabel("Wartość")
plt.title("Ilorazy d(n) - c(n)")
plt.legend()
plt.grid(True)

plt.tight_layout()
plt.savefig("ilorazy_funkcji.png")  # Zapisanie wykresu do pliku PNG
plt.show()
