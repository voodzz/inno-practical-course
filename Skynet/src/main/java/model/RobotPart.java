package model;

import java.util.concurrent.ThreadLocalRandom;

public enum RobotPart {
    HEAD, TORSO, HANDS, FEET;

    public static RobotPart getPart() {
        return values()[ThreadLocalRandom.current().nextInt(values().length)];
    }
}
