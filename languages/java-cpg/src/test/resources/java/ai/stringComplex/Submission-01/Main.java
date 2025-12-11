import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author GitHub Copilot (GPT-5 mini)
 */
public final class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    private Main() {
        throw new IllegalStateException();
    }

    public static void main(String[] args) {
        try {
            Map<String, String> opts = parseArgs(args);

            Person person = new Person(opts.get("name"), opts.get("title"));
            boolean shout = Boolean.parseBoolean(opts.getOrDefault("shout", "false"));
            int repeat = Integer.parseInt(opts.getOrDefault("repeat", "1"));
            String locale = opts.getOrDefault("locale", "en");
            boolean formal = Boolean.parseBoolean(opts.getOrDefault("formal", "false"));

            String greeting = GreetingService.generateGreeting(person, locale, formal);
            if (shout) {
                greeting = greeting.toUpperCase(Locale.ROOT) + "!!!";
            }

            for (int i = 0; i < Math.max(1, repeat); i++) {
                System.out.println(greeting);
            }

            // Compute uppercase name asynchronously (demonstrates concurrency)
            CompletableFuture<String> upperFuture = CompletableFuture.supplyAsync(() ->
                    person.getName().toUpperCase(Locale.ROOT)
            );

            String result2 = upperFuture.get(1, TimeUnit.SECONDS);
            System.out.println("Uppercase name: " + result2);

        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Execution failed: " + e.getMessage(), e);
            System.err.println("Usage examples:");
            System.err.println("  java Main --name=\"Jane Doe\" --title=Dr --locale=de --formal --shout --repeat=2");
        }
    }

    /**
     * Simple parser supporting:
     * --key=value and flags like --shout (treated as true)
     * first positional argument is treated as name if present
     */
    private static Map<String, String> parseArgs(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (String a : args) {
            if (a == null || a.isBlank()) continue;
            if (a.startsWith("--")) {
                int eq = a.indexOf('=');
                if (eq > 0) {
                    String k = a.substring(2, eq);
                    String v = a.substring(eq + 1);
                    map.put(k, v);
                } else {
                    map.put(a.substring(2), "true");
                }
            } else if (!map.containsKey("name")) {
                map.put("name", a);
            }
        }
        map.putIfAbsent("name", "John Doe");
        return map;
    }
}
