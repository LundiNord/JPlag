library(ggplot2)
library(tidyr)
library(dplyr)

# Read the data
deadcode_results <- read.csv("deadcode_results.csv")
plagiarism_results <- read.csv("plagiarism_results.csv")

# Plot 1: Distribution of FullRemoval scores
p1 <- ggplot(deadcode_results, aes(x = FullRemoval)) +
  geom_histogram(bins = 20, fill = "steelblue", color = "white") +
  labs(title = "Distribution of Full Dead Code Removal Scores",
       x = "Score (%)",
       y = "Count") +
  theme_minimal()

# Plot 2: Comparison of removal methods
deadcode_long <- deadcode_results %>%
  pivot_longer(cols = c(NoRemoval, SimpleRemoval, FullRemoval),
               names_to = "Method",
               values_to = "Score")

p2 <- ggplot(deadcode_long, aes(x = Method, y = Score, fill = Method)) +
  geom_boxplot() +
  labs(title = "Comparison of Dead Code Removal Methods",
       x = "Removal Method",
       y = "Score (%)") +
  theme_minimal()

# Plot 3: Plagiarism detection methods comparison
plag_long <- plagiarism_results %>%
  pivot_longer(cols = c(JPlag, CpgMinimal, CpgStandard, CpgAI),
               names_to = "Method",
               values_to = "Similarity")

p3 <- ggplot(plag_long, aes(x = Method, y = Similarity, fill = Method)) +
  geom_boxplot() +
  labs(title = "Plagiarism Detection Methods Comparison",
       x = "Detection Method",
       y = "Similarity Score") +
  theme_minimal()

# Plot 4: Correlation between methods
p4 <- ggplot(plagiarism_results, aes(x = JPlag, y = CpgAI)) +
  geom_point(color = "darkred", alpha = 0.6) +
  geom_smooth(method = "lm", se = TRUE) +
  labs(title = "JPlag vs CpgAI Correlation",
       x = "JPlag Score",
       y = "CpgAI Score") +
  theme_minimal()

# Display plots
print(p1)
print(p2)
print(p3)
print(p4)
