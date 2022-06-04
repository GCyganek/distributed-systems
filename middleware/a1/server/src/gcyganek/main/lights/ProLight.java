package gcyganek.main.lights;

import Devices.*;
import com.zeroc.Ice.Current;

import java.util.Arrays;

public class ProLight extends BasicLight implements IProLight {

    public ProLight(String name) {
        super(name);

        this.mode.intensity = LightIntensity.LOW;
        this.mode.color = LightColor.RED;
    }

    @Override
    public String setLightMode(LightColor lightColor, LightIntensity lightIntensity, Current current) throws UnknownLightColor, UnknownLightIntensity {
        if (Arrays.stream(LightColor.values()).noneMatch(l -> l == lightColor)) {
            String reason = lightColor + " light color is not supported";
            throw new UnknownLightColor(reason);
        }

        if (Arrays.stream(LightIntensity.values()).noneMatch(i -> i == lightIntensity)) {
            String reason = lightIntensity + " light intensity is not supported";
            throw new UnknownLightIntensity(reason);
        }

        this.mode.intensity = lightIntensity;
        this.mode.color = lightColor;

        return "Light color set to " + mode.color + " and intensity set to " + mode.intensity;
    }
}
