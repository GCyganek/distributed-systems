package gcyganek.main.lights;

import Devices.*;
import com.zeroc.Ice.Current;

import java.util.Arrays;

public class BasicLight extends Light implements ILight {

    public BasicLight(String name) {
        this.name = name;

        this.type = this.getClass().getSimpleName();

        this.mode.color = LightColor.RED;
        this.mode.intensity = LightIntensity.LOW;
    }

    @Override
    public String getDeviceInfo(Current current) {
        return "Device type: " + type + "\nDevice name: " + name + "\nCurrent light color: " +
                mode.color + "\nCurrent light intensity: " + mode.intensity + "\nLight colors available: " +
                Arrays.toString(LightColor.values()) + "\nLight intensity modes: " + Arrays.toString(LightIntensity.values());
    }

    @Override
    public String toggleLightColor(Current current) {
        String result = "Light color switched to ";

        switch(mode.color) {
            case RED -> {
                mode.color = LightColor.BLUE;
                result += "blue";
            }
            case BLUE -> {
                mode.color = LightColor.YELLOW;
                result += "yellow";
            }
            case YELLOW -> {
                mode.color = LightColor.GREEN;
                result += "green";
            }
            case GREEN -> {
                mode.color = LightColor.RED;
                result += "red";
            }
        };

        return result;
    }

    @Override
    public String toggleLightIntensity(Current current) {
        String result = "Light intensity switched to ";

        switch(mode.intensity) {
            case LOW -> {
                mode.intensity = LightIntensity.MEDIUM;
                result += "medium";
            }
            case MEDIUM -> {
                mode.intensity = LightIntensity.HIGH;
                result += "high";
            }
            case HIGH -> {
                mode.intensity = LightIntensity.LOW;
                result += "low";
            }
        };

        return result;
    }
}
