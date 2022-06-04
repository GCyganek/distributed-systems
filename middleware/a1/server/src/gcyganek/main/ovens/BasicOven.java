package gcyganek.main.ovens;

import Devices.*;
import com.zeroc.Ice.Current;

import java.util.Arrays;

public class BasicOven extends Oven implements IOven {

    public BasicOven(String name) {
        this.name = name;

        this.type = this.getClass().getSimpleName();

        this.minutesRange.min = 0;
        this.minutesRange.max = 300;

        this.temperatureRange.min = 0;
        this.temperatureRange.max = 300;

        this.modes = new OvenMode[]{OvenMode.BOTTOMHEAT, OvenMode.TOPHEAT, OvenMode.CONVECTION};
    }

    @Override
    public String getDeviceInfo(Current current) {
        return "Device type: " + type + "\nDevice name: " + name + "\nMinutes range: [" + minutesRange.min + ", " + minutesRange.max + "]\n" +
                "Temperature range: [" + temperatureRange.min + ", " + temperatureRange.max + "]\n" + "Modes: " + Arrays.toString(modes);
    }


    @Override
    public String startOven(OvenMode mode, short minutes, short temperature, Current current) throws OutOfRangeException, UnknownOvenModeException {
        if (Arrays.stream(modes).noneMatch(m -> m == mode)) {
            String reason = mode + " mode is not supported by device " + name + " of type " + type;
            throw new UnknownOvenModeException(reason);
        }

        if (temperature < temperatureRange.min || temperature > temperatureRange.max) {
            String reason = temperature + " temperature is out of range. Temperature value should be in range " +
                    "[" + temperatureRange.min + ", " + temperatureRange.max + " ]";
            throw new OutOfRangeException(reason);
        }

        if (minutes < minutesRange.min || minutes > minutesRange.max) {
            String reason = minutes + " minutes is out of range. Minutes values should be in range " +
                    "[" + minutesRange.min + ", " + minutesRange.max + " ]";
            throw new OutOfRangeException(reason);
        }

        try {
            Thread.sleep(minutes);
        } catch (InterruptedException ignored) {
        }

        return type + " named " + name + " finished working. Used data - Mode: " + mode +
                " Minutes: " + minutes + " Temperature: " + temperature;

    }
}
