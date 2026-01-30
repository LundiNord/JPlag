#@author Anthropic Claude Sonnet 4.5

# Load required libraries
library(ggplot2)
library(tidyr)
library(dplyr)

# Read the CSV file
data <- read.csv("results/Progpedia_deadcode_results_Standard.csv")
data1 <- read.csv("results/Progpedia_deadcode_results_Level1.csv")
data2 <- read.csv("results/Progpedia_deadcode_results_Level2.csv")

data %>% filter(RemovedFullTokens > 20) %>% count()


# 1. Box plots for similarity values (NoRemoval, SimpleRemoval, FullRemoval)
similarity_data <- data %>%
  select(FileName, NoRemoval, SimpleRemoval, FullRemoval) %>%
  pivot_longer(cols = c(NoRemoval, SimpleRemoval, FullRemoval),
               names_to = "Method",
               values_to = "Similarity")
# Create box plot for similarity
p1 <- ggplot(similarity_data, aes(x = Method, y = Similarity, fill = Method)) +
  geom_boxplot() +
  labs(title = "Similarity Score Comparison by Method",
       x = "Dead Code Removal Method",
       y = "Similarity Score (%)") +
  theme_minimal() +
  theme(legend.position = "bottom")



# 2. Box plots for execution times
time_data <- data %>%
  select(FileName, TimeNoRemoval.ms., TimeSimpleRemoval.ms., TimeFullRemoval.ms.) %>%
  pivot_longer(cols = starts_with("Time"),
               names_to = "Method",
               values_to = "Time") %>%
  mutate(Method = gsub("Time|.ms.", "", Method))

p2 <- ggplot(time_data, aes(x = Method, y = Time, fill = Method)) +
  geom_boxplot() +
  labs(title = "Execution Time Comparison by Method",
       x = "",
       y = "Time (ms)") +
  theme_minimal() +
  theme(legend.position = "none")




# 4. Bar plot for error frequencies
# Convert string "true"/"false" to logical TRUE/FALSE
data <- data %>%
  mutate(
    JavaLanguageFeatureNotSupported = as.logical(JavaLanguageFeatureNotSupported),
    CpgErrorException = as.logical(CpgErrorException),
    RuntimeException = as.logical(RuntimeException)
  )
error_summary <- data %>%
  summarise(JavaNotSupported = sum(JavaLanguageFeatureNotSupported),
            CpgErrors = sum(CpgErrorException),
            RuntimeErrors = sum(RuntimeException),
            NoErrors = sum(!JavaLanguageFeatureNotSupported & !CpgErrorException)) %>%
  pivot_longer(everything(), names_to = "ErrorType", values_to = "Count") %>%
  mutate(ErrorType = case_when(
    ErrorType == "JavaNotSupported" ~ "Java feature not supported",
    ErrorType == "CpgErrors" ~ "CPG error",
    ErrorType == "RuntimeErrors" ~ "Runtime exception",
    ErrorType == "NoErrors" ~ "No error",
    TRUE ~ ErrorType
  ))

p4 <- ggplot(error_summary, aes(x = ErrorType, y = Count, fill = ErrorType)) +
  geom_bar(stat = "identity") +
  geom_text(aes(label = Count), vjust = -0.5, size = 4) +
  labs(title = "Error Frequency Analysis",
       x = "",
       y = "Count") +
  theme_minimal() +
  theme(legend.position = "none")




# Display plots
print(p1)
print(p2)
print(p4)

# Save plots
#ggsave("similarity_boxplot.png", p1, width = 10, height = 6, dpi = 300)
#ggsave("time_boxplot.png", p2, width = 10, height = 6, dpi = 300)
#ggsave("error_frequency.png", p4, width = 8, height = 6, dpi = 300)
# Save plots as PDF
ggsave("similarity_boxplot.pdf", p1, width = 10, height = 6)
ggsave("time_boxplot.pdf", p2, width = 10, height = 6)
ggsave("error_frequency.pdf", p4, width = 8, height = 6)


# 6. Summary statistics table
summary_stats <- similarity_data %>%
  group_by(Method) %>%
  summarise(
    Mean = mean(Similarity, na.rm = TRUE),
    Median = median(Similarity, na.rm = TRUE),
    SD = sd(Similarity, na.rm = TRUE),
    Min = min(Similarity, na.rm = TRUE),
    Max = max(Similarity, na.rm = TRUE)
  )

print(summary_stats)


#7: Create box plot for removed tokens
removed_tokens_dataStandard <- data %>%
  select(FileName, RemovedSimpleTokens, RemovedFullTokens) %>%
  pivot_longer(cols = c(RemovedSimpleTokens, RemovedFullTokens),
               names_to = "Method",
               values_to = "RemovedTokens") %>%
  mutate(Method = case_when(
    Method == "RemovedSimpleTokens" ~ "SimpleRemoval",
    Method == "RemovedFullTokens" ~ "FullRemoval",
    TRUE ~ Method
  ))
removed_tokens_data_Level1 <- data1 %>%
  select(FileName, RemovedSimpleTokens, RemovedFullTokens) %>%
  pivot_longer(cols = c(RemovedSimpleTokens, RemovedFullTokens),
               names_to = "Method",
               values_to = "RemovedTokens") %>%
  mutate(Method = case_when(
    Method == "RemovedSimpleTokens" ~ "SimpleRemoval",
    Method == "RemovedFullTokens" ~ "FullRemoval",
    TRUE ~ Method
  ))
removed_tokens_data_Level2 <- data2 %>%
  select(FileName, RemovedSimpleTokens, RemovedFullTokens) %>%
  pivot_longer(cols = c(RemovedSimpleTokens, RemovedFullTokens),
               names_to = "Method",
               values_to = "RemovedTokens") %>%
  mutate(Method = case_when(
    Method == "RemovedSimpleTokens" ~ "SimpleRemoval",
    Method == "RemovedFullTokens" ~ "FullRemoval",
    TRUE ~ Method
  ))
p_tokens <- ggplot(removed_tokens_dataStandard, aes(x = Method, y = RemovedTokens, fill = Method)) +
  geom_boxplot() +
  labs(title = "Removed Tokens Comparison by Method",
       x = "Dead Code Removal Method",
       y = "Number of Removed Tokens") +
  theme_minimal() +
  theme(legend.position = "none")




print(p_tokens)
ggsave("removed_tokens_boxplot.pdf", p_tokens, width = 8, height = 6)






tokens_summary <- removed_tokens_dataStandard %>%
  group_by(Method) %>%
  summarise(
    Mean = mean(RemovedTokens, na.rm = TRUE),
    Median = median(RemovedTokens, na.rm = TRUE),
    SD = sd(RemovedTokens, na.rm = TRUE),
    Min = min(RemovedTokens, na.rm = TRUE),
    Max = max(RemovedTokens, na.rm = TRUE)
  )
print(tokens_summary)
