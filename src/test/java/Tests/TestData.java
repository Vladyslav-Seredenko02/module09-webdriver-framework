package Tests;

import utils.ConfigReader;

public class TestData {
    public static final String RECIPIENT = ConfigReader.get("recipient");
    public static final String SUBJECT = ConfigReader.get("subject");
    public static final String BODY = ConfigReader.get("body");
}
