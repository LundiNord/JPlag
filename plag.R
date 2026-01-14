library(ggplot2)
library(tidyr)
library(dplyr)

# Read the data
plagiarism_results <- read.csv("plagiarism_results.csv")

# 1. Comparison of detection methods - boxplot
p1 <- ggplot(plagiarism_results %>%
               pivot_longer(cols = c(JPlag, CpgMinimal, CpgStandard, CpgAI),
                            names_to = "Method", values_to = "Score")) +
  geom_boxplot(aes(x = Method, y = Score, fill = Method)) +
  labs(title = "Plagiarism Detection Method Comparison",
       y = "Similarity Score",
       x = "Detection Method") +
  theme_minimal()

# 2. Correlation heatmap between methods
correlation_data <- cor(plagiarism_results[, c("JPlag", "CpgMinimal", "CpgStandard", "CpgAI")])
p2 <- ggplot(reshape2::melt(correlation_data), aes(Var1, Var2, fill = value)) +
  geom_tile() +
  geom_text(aes(label = round(value, 2)), color = "white") +
  scale_fill_gradient2(low = "blue", mid = "white", high = "red", midpoint = 0.5) +
  labs(title = "Correlation Between Detection Methods",
       x = "", y = "") +
  theme_minimal()

# 3. Scatter plot comparing methods
p3 <- ggplot(plagiarism_results, aes(x = JPlag, y = CpgAI)) +
  geom_point(alpha = 0.6, size = 3) +
  geom_abline(intercept = 0, slope = 1, linetype = "dashed", color = "red") +
  labs(title = "JPlag vs CpgAI Detection Scores",
       x = "JPlag Score",
       y = "CpgAI Score") +
  theme_minimal()

# 4. Distribution histogram for each method
p4 <- ggplot(plagiarism_results %>%
               pivot_longer(cols = c(JPlag, CpgMinimal, CpgStandard, CpgAI),
                            names_to = "Method", values_to = "Score")) +
  geom_histogram(aes(x = Score, fill = Method), bins = 20, alpha = 0.7) +
  facet_wrap(~Method, ncol = 2) +
  labs(title = "Score Distributions by Method",
       x = "Similarity Score",
       y = "Frequency") +
  theme_minimal()

# Display plots
print(p1)
print(p2)
print(p3)
print(p4)
