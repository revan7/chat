package client;

import client.commandlinerunner.CommandLineRunner;
import client.guirunner.GUIRunner;
import model.connection.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainClient {

    private final static Logger logger = LogManager.getLogger(MainClient.class);

    public static void main(String[] args) {
        final Address address = new Address();
        final String ip = System.getProperty("server.ip");
        final int port = Integer.parseInt(System.getProperty("server.port"));
        final String username = System.getProperty("username");
        address.setIp(ip);
        address.setPort(port);
        final String profile = System.getProperty("profile");
        Runner runner = null;
        if (profile.equalsIgnoreCase("cmdline")) {
            runner = new CommandLineRunner(address, username);
        }
        if (profile.equalsIgnoreCase("gui")) {
            runner = new GUIRunner(address, username);
        }
        if (runner != null) {
            runner.init();
        } else {
            logger.error("No runner available for chosen profile");
        }
    }

}
