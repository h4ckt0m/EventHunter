import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        ngrok ng = new ngrok();
        ng.start();
        launchCommand("mvn clean compile","../server/jersey-service");
        launchCommand("mvn exec:java","../server/jersey-service");
    }
    public static void launchCommand(String command, String direction) throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("cmd.exe", "/c", command);
        builder.directory(new File(direction));
        Process process = builder.start();

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(process.getInputStream()));
        String s;
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }
    }
}
