# Load required libraries
library(ggplot2)
library(tidyr)
library(dplyr)

# Read the CSV file
data <- read.csv("results/Ai_deadcode_results_Standard.csv")
data1 <- read.csv("results/Ai_deadcode_results_Level1.csv")
data2 <- read.csv("results/Ai_deadcode_results_Level2.csv")
# Add dataset identifier to each dataframe
data$Dataset <- "Standard"
data1$Dataset <- "Level1"
data2$Dataset <- "Level2"
# Combine all datasets
combined_data <- bind_rows(data, data1, data2)



# 1. Box plots for similarity values with all datasets
similarity_data <- combined_data %>%
  # convert possible string/factor indicators to logical safely for filtering
  mutate(
    JavaLanguageFeatureNotSupported = as.logical(JavaLanguageFeatureNotSupported),
    CpgErrorException = as.logical(CpgErrorException),
    RuntimeException = as.logical(RuntimeException)
  ) %>%
  # filter out any rows that had those errors
  filter(
    !coalesce(JavaLanguageFeatureNotSupported, FALSE),
    !coalesce(CpgErrorException, FALSE),
    !coalesce(RuntimeException, FALSE)
  ) %>%
  select(FileName, NoRemoval, SimpleRemoval, FullRemoval, Dataset) %>%
  pivot_longer(cols = c(NoRemoval, SimpleRemoval, FullRemoval),
               names_to = "Method",
               values_to = "Similarity") %>%
  # place SimpleRemoval before NoRemoval, and ensure dataset order: Standard, Level1, Level2
  mutate(
    DatasetPlot = if_else(Method %in% c("NoRemoval", "SimpleRemoval"),
                          "All datasets",
                          Dataset),
    Method = factor(Method, levels = c("NoRemoval", "SimpleRemoval", "FullRemoval")),
    DatasetPlot = factor(DatasetPlot, levels = c("Standard", "Level1", "Level2", "All datasets"))
  ) %>%
  # remove duplicate rows that come from identical datasets for the combined methods
  distinct(Method, FileName, Similarity, DatasetPlot, .keep_all = TRUE)

p1 <- ggplot(similarity_data, aes(x = Method, y = Similarity, fill = DatasetPlot)) +
  geom_boxplot() +
  labs(title = "Similarity Score Comparison by Method",
       x = "Dead Code Removal Method",
       y = "Similarity Score (%)",
       fill = "Dataset") +
  theme_minimal() +
  theme(legend.position = "bottom")
ggsave("similarity_boxplot.pdf", p1, width = 8, height = 6)





#2. box plots for execution times with all datasets
time_data <- combined_data %>%
select(FileName, TimeNoRemoval.ms., TimeSimpleRemoval.ms., TimeFullRemoval.ms., Dataset) %>%
pivot_longer(cols = starts_with("Time"),
names_to = "Method",
values_to = "Time") %>%
mutate(
Method = gsub("Time|.ms.", "", Method),
DatasetPlot = if_else(Method %in% c("NoRemoval", "SimpleRemoval"),
"All datasets",
Dataset),
Method = factor(Method, levels = c("NoRemoval", "SimpleRemoval", "FullRemoval")),
DatasetPlot = factor(DatasetPlot, levels = c("Standard", "Level1", "Level2", "All datasets"))
) %>%
distinct(Method, FileName, Time, DatasetPlot, .keep_all = TRUE)

p2 <- ggplot(time_data, aes(x = Method, y = Time, fill = DatasetPlot)) +
geom_boxplot() +
labs(title = "Execution Time Comparison by Method",
x = "Dead Code Removal Method",
y = "Time (ms)",
fill = "Dataset") +
theme_minimal() +
theme(legend.position = "bottom")
ggsave("execution_time_boxplot.pdf", p2, width = 8, height = 6)



# 4. Bar plot for error frequencies with all datasets
combined_data <- combined_data %>%
  mutate(
    JavaLanguageFeatureNotSupported = as.logical(JavaLanguageFeatureNotSupported),
    CpgErrorException = as.logical(CpgErrorException),
    RuntimeException = as.logical(RuntimeException)
  )
error_summary <- combined_data %>%
  group_by(Dataset) %>%
  summarise(JavaNotSupported = sum(JavaLanguageFeatureNotSupported),
            CpgErrors = sum(CpgErrorException),
            RuntimeErrors = sum(RuntimeException),
            NoErrors = sum(!JavaLanguageFeatureNotSupported & !CpgErrorException)) %>%
  pivot_longer(-Dataset, names_to = "ErrorType", values_to = "Count") %>%
  mutate(ErrorType = case_when(
    ErrorType == "JavaNotSupported" ~ "Java feature not supported",
    ErrorType == "CpgErrors" ~ "CPG error",
    ErrorType == "RuntimeErrors" ~ "Runtime exception",
    ErrorType == "NoErrors" ~ "No error",
    TRUE ~ ErrorType
  ))
p4 <- ggplot(error_summary, aes(x = ErrorType, y = Count, fill = factor(Dataset, levels = c("Standard", "Level1", "Level2")))) +
  geom_bar(stat = "identity", position = "dodge") +
  geom_text(aes(label = Count), vjust = -0.5, size = 3, position = position_dodge(width = 0.9)) +
  labs(title = "Error Frequency Analysis", x = "", y = "Count", fill = "Dataset")+
  theme_minimal() +
  theme(legend.position = "bottom")
ggsave("error_frequency_barplot.pdf", p4, width = 8, height = 6)





#7. Box plot for removed tokens with all datasets
removed_tokens_data <- combined_data %>%
  select(FileName, RemovedFullTokens, Dataset) %>%
  rename(RemovedTokens = RemovedFullTokens) %>%
  mutate(
    Method = "",
    Dataset = factor(Dataset, levels = c("Standard", "Level1", "Level2"))
  )
p_tokens <- ggplot(removed_tokens_data, aes(x = Method, y = RemovedTokens, fill = Dataset)) +
  geom_boxplot() +
  labs(y = "Number of Removed Tokens") +
  theme_minimal() +
  theme(legend.position = "bottom")
ggsave("removed_tokens_boxplot.pdf", p_tokens, width = 6, height = 6)



# Summary statistics with all datasets
summary_stats <- similarity_data %>%
  group_by(Method, Dataset) %>%
  summarise(
    Mean = mean(Similarity, na.rm = TRUE),
    Median = median(Similarity, na.rm = TRUE),
    SD = sd(Similarity, na.rm = TRUE),
    Min = min(Similarity, na.rm = TRUE),
    Max = max(Similarity, na.rm = TRUE),
    .groups = "drop"
  )
print(summary_stats)
tokens_summary <- removed_tokens_data %>%
  group_by(Method, Dataset) %>%
  summarise(
    Mean = mean(RemovedTokens, na.rm = TRUE),
    Median = median(RemovedTokens, na.rm = TRUE),
    SD = sd(RemovedTokens, na.rm = TRUE),
    Min = min(RemovedTokens, na.rm = TRUE),
    Max = max(RemovedTokens, na.rm = TRUE),
    .groups = "drop"
  )
print(tokens_summary)
