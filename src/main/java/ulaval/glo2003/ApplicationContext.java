package ulaval.glo2003;

public class ApplicationContext {
    public enum ApplicationMode {
        Staging, Production, Dev
    }

    public ApplicationMode getApplicationMode() {
        String mode = System.getenv("MODE");

        if (mode == null || mode.equals("DEV")) {
            return ApplicationMode.Dev;
        } else if (mode.equals("PROD")) {
            return ApplicationMode.Production;
        } else if (mode.equals("STAGING")) {
            return ApplicationMode.Staging;
        }

        throw new UnsupportedOperationException("Mode " + mode + " not handled");
    }
}