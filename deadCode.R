library(ggplot2)
library(tidyr)
library(dplyr)

# Read the data
deadcode_results <- read.csv("deadcode_results.csv")

# 1. Comparison of removal methods (boxplot)
data_long <- pivot_longer(deadcode_results,
                          cols = c(NoRemoval, SimpleRemoval, FullRemoval),
                          names_to = "Method",
                          values_to = "Coverage")

ggplot(data_long, aes(x = Method, y = Coverage, fill = Method)) +
  geom_boxplot() +
  labs(title = "Code Coverage by Removal Method",
       x = "Removal Method",
       y = "Coverage (%)") +
  theme_minimal()

# 2. Scatter plot comparing NoRemoval vs FullRemoval
ggplot(deadcode_results, aes(x = NoRemoval, y = FullRemoval)) +
  geom_point(alpha = 0.6, size = 3) +
  geom_abline(intercept = 0, slope = 1, linetype = "dashed", color = "red") +
  labs(title = "Coverage: No Removal vs Full Removal",
       x = "No Removal (%)",
       y = "Full Removal (%)") +
  theme_minimal()

# 3. Histogram of FullRemoval coverage
ggplot(deadcode_results, aes(x = FullRemoval)) +
  geom_histogram(bins = 20, fill = "steelblue", color = "black", alpha = 0.7) +
  labs(title = "Distribution of Full Removal Coverage",
       x = "Coverage (%)",
       y = "Count") +
  theme_minimal()

# 4. Line plot showing coverage difference
deadcode_results$Difference <- deadcode_results$FullRemoval - deadcode_results$NoRemoval
deadcode_results$Index <- seq_len(nrow(deadcode_results))

ggplot(deadcode_results, aes(x = Index, y = Difference)) +
  geom_line(color = "darkblue") +
  geom_hline(yintercept = 0, linetype = "dashed", color = "red") +
  labs(title = "Coverage Difference (FullRemoval - NoRemoval)",
       x = "Project Index",
       y = "Coverage Difference (%)") +
  theme_minimal()
