package View;

public abstract class Command {
    private String key, description;
    public Command(String key, String description) { this.key = key; this.description = description;}
    public abstract void execute();
    String getKey(){return key;}
    String getDescription(){return description;}
}
