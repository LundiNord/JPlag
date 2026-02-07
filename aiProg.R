#@author Anthropic Claude Sonnet 4.5

# Load required libraries
library(ggplot2)
library(dplyr)
library(tidyr)
library(gridExtra)

# Read the data
data <- read.csv("results/AI_plagiarism_results_Standard.csv")
data1 <- read.csv("results/AI_plagiarism_results_Level1.csv")
data2 <- read.csv("results/AI_plagiarism_results_Level2.csv")
# Add dataset identifier to each dataframe
data$Dataset <- "Standard"
data1$Dataset <- "Level1"
data2$Dataset <- "Level2"
# Combine all datasets
combined_data <- bind_rows(data, data1, data2)



# 1. Comparison of Detection Methods (Box Plot) with all datasets
detection_comparison <- combined_data %>%
  select(JPlag, CpgMinimal, CpgStandard, CpgAI, Dataset) %>%
  pivot_longer(cols = c(JPlag, CpgMinimal, CpgStandard, CpgAI),
               names_to = "Method",
               values_to = "Score") %>%
  mutate(
    DatasetPlot = if_else(Method %in% c("JPlag", "CpgMinimal", "CpgStandard"),
                          "all",
                          Dataset),
    Method = factor(Method, levels = c("JPlag", "CpgMinimal", "CpgStandard", "CpgAI")),
    DatasetPlot = factor(DatasetPlot, levels = c("Standard", "Level1", "Level2", "all"))
  ) %>%
  distinct(Method, Score, DatasetPlot, .keep_all = TRUE)
p1 <- ggplot(
  detection_comparison,
  aes(x = interaction(Method, DatasetPlot, sep = " / "), y = Score, fill = DatasetPlot)
) +
  geom_boxplot() +
  labs(
    #title = "",
    x = "Detection Method / Data Types",
    y = "Similarity Score",
    fill = "Dataset"
  ) +
  theme_minimal() +
  theme(legend.position = "none")






# 2. Performance Time Comparison (Box Plot) with all datasets
time_comparison <- combined_data %>%
  select(TimeJPlag.ms., TimeStandardCpg.ms., TimeMinimalCpg.ms., TimeAi.ms., Dataset) %>%
  pivot_longer(cols = starts_with("Time"),
               names_to = "Method",
               values_to = "Time") %>%
  mutate(
    Method = gsub("Time", "", Method),
    Method = gsub("\\.ms\\.", "", Method),
    DatasetPlot = if_else(Method %in% c("JPlag", "StandardCpg", "MinimalCpg"),
                          "all",
                          Dataset),
    Method = factor(Method, levels = c("JPlag", "StandardCpg", "MinimalCpg", "Ai")),
    DatasetPlot = factor(DatasetPlot, levels = c("Standard", "Level1", "Level2", "all"))
  ) %>%
  distinct(Method, Time, DatasetPlot, .keep_all = TRUE)
p2 <- ggplot(
  time_comparison,
  aes(x = interaction(Method, DatasetPlot, sep = " / "), y = Time, fill = DatasetPlot)
) +
  geom_boxplot() +
  labs(
    #title = "",
    x = "Detection Method / Data Types",
    y = "Time (ms)",
    fill = "Dataset"
  ) +
  theme_minimal() +
  theme(legend.position = "none")





# 5. Line Plot: Method Performance Across File Pairs (per dataset)
line_data <- combined_data %>%
  group_by(Dataset) %>%
  mutate(Pair = row_number()) %>%
  ungroup() %>%
  select(Pair, JPlag, CpgMinimal, CpgStandard, CpgAI, Dataset) %>%
  pivot_longer(cols = c(JPlag, CpgMinimal, CpgStandard, CpgAI),
               names_to = "Method",
               values_to = "Score") %>%
  mutate(Dataset = factor(Dataset, levels = c("Standard", "Level1", "Level2")))

p5 <- ggplot(line_data, aes(x = Pair, y = Score, color = Method)) +
  geom_line(alpha = 0.7) +
  facet_wrap(~Dataset, ncol = 1) +
  labs(title = "Detection Scores Across File Pairs",
       x = "File Pair Index",
       y = "Similarity Score") +
  theme_minimal()



# Save all plots
ggsave("plagiarism_comparison.png", p1, width = 10, height = 6)
ggsave("time_comparison.png", p2, width = 10, height = 6)
ggsave("scores_across_pairs.png", p5, width = 12, height = 8)


# Summary statistics for Detection Score Comparison (Plot #1)
detection_stats <- detection_comparison %>%
  group_by(Method, DatasetPlot) %>%
  summarise(
    Mean = mean(Score, na.rm = TRUE),
    Median = median(Score, na.rm = TRUE),
    SD = sd(Score, na.rm = TRUE),
    Min = min(Score, na.rm = TRUE),
    Max = max(Score, na.rm = TRUE),
    N = n(),
    .groups = "drop"
  ) %>%
  arrange(Method, DatasetPlot)
print("Detection Score Statistics:")
print(detection_stats)
#write.csv(detection_stats, "detection_score_statistics.csv", row.names = FALSE)
# Summary statistics for Execution Time Comparison (Plot #2)
time_stats <- time_comparison %>%
  group_by(Method, DatasetPlot) %>%
  summarise(
    Mean = mean(Time, na.rm = TRUE),
    Median = median(Time, na.rm = TRUE),
    SD = sd(Time, na.rm = TRUE),
    Min = min(Time, na.rm = TRUE),
    Max = max(Time, na.rm = TRUE),
    N = n(),
    .groups = "drop"
  ) %>%
  arrange(Method, DatasetPlot)
print("Execution Time Statistics:")
print(time_stats)
#write.csv(time_stats, "execution_time_statistics.csv", row.names = FALSE)



print("All diagrams have been created and saved!")
