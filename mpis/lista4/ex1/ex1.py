import pandas as pd
from scipy.stats import binom

# Given values of n
n_values = [100, 1000, 10000]

# Store results
results = []

for n in n_values:
    # Expectation and variance
    E_X = n / 2
    Var_X = n / 4

    # Markov Bound
    markov_bound_a = 5 / 6  # P(X ≥ 6/5 E[X]) ≤ E[X] / (6/5 E[X]) = 5/6

    # Part (a): P(X >= (6/5) * E[X])
    a = (6/5) * E_X  # Threshold a
    chebyshev_bound_a = (Var_X / ((E_X - a) ** 2)) if (E_X - a) != 0 else 1

    # Exact probability for P(X >= (6/5) * E[X]) using binomial distribution
    exact_prob_a = 1 - binom.cdf(a - 1, n, 0.5)

    # Part (b): P(|X - E[X]| >= (1/10) * E[X])
    k = (1/10) * E_X  # Deviation k

    #Markov Bound
    markov_bound_b = 10 / 11 # P(X >= 11/10 E[X]) + P(X <= 9/10 E[X]) <= P(X >= 11/10 E[X])

    # Chebyshev Bound
    k_sigma = E_X / 10  # k = (1/10) * E[X]
    chebyshev_bound_b = 100 / n  # P(|X - E[X]| ≥ k) ≤ σ^2 / k^2 = 100 / n

    # Exact probability for P(|X - E[X]| >= (1/10) * E[X])
    exact_prob_b = binom.sf(E_X + k - 1, n, 0.5) + binom.cdf(E_X - k, n, 0.5)

    # Store results
    results.append((n, markov_bound_a, chebyshev_bound_a, exact_prob_a, markov_bound_b, chebyshev_bound_b, exact_prob_b))

# Create a DataFrame to display the results
df_all = pd.DataFrame(results, columns=[
    "n",
    "Markov Bound (P(X >= 6/5 E[X]))",
    "Chebyshev Bound (P(X >= 6/5 E[X]))",
    "Exact P(X >= 6/5 E[X])",
    "Markov Bound (P(|X - E[X]| >= 1/10 E[X]))",
    "Chebyshev Bound (P(|X - E[X]| >= 1/10 E[X])",
    "Exact P(|X - E[X]| >= 1/10 E[X])"
])

# Transpose the DataFrame
df_transposed = df_all.T

# Print the transposed DataFrame for review
print(df_transposed)

# Save the transposed results to a TXT file with a nice table
with open("results_transposed.txt", "w") as f:
    f.write(df_transposed.to_string(header=False))  # option to remove header for cleaner look