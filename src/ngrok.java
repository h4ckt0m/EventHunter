import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ngrok extends Thread {
    @Override
    public void run() {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("cmd.exe", "/c", "start ngrok.exe http 8080");
        builder.directory(new File("../server"));
        try {
            Process process = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
