
#ifndef DEVICES_ICE
#define DEVICES_ICE

module Devices 
{

    enum OvenMode
    {
        CONVECTION, ECO, TOPHEAT, BOTTOMHEAT, GRILL, DEFROST, ROASTING
    };

    sequence<OvenMode> ovenModes;

    enum Recipe
    {
        LEMONCAKE, CARROTCAKE, CHOCOLATECAKE
    };

    enum LightColor
    {
        RED, BLUE, YELLOW, GREEN
    };

    sequence<LightColor> lightColors;

    enum LightIntensity
    {
        LOW, MEDIUM, HIGH
    };

    sequence<LightIntensity> lightIntensities;

    struct LightMode
    {
        LightColor color;
        LightIntensity intensity;
    };

    sequence<LightMode> lightModes;

    struct Range
    {
        short min;
        short max;
    }

    exception UnknownOvenModeException 
    {
        string reason;
    };

    exception UnknownRecipeException
    {
        string reason;
    };

    exception UnknownLightColor
    {
        string reason;
    };

    exception UnknownLightIntensity
    {
        string reason;
    };

    exception OutOfRangeException
    {
        string reason;
    };

    class Device
    {
        string name;
        string type;
    };

    interface IDevice
    {
        string getDeviceInfo();
    };

    class Oven extends Device
    {
        ovenModes modes;
        Range temperatureRange;
        Range minutesRange;
    };

    interface IOven extends IDevice
    {
        string startOven(OvenMode mode, short minutes, short temperature) throws UnknownOvenModeException, OutOfRangeException;
    };
    
    interface IProOven extends IOven
    {
        string displayRecipe(Recipe recipe) throws UnknownRecipeException;
    };

    class Light extends Device
    {
        LightMode mode;
    }

    interface ILight extends IDevice
    {
        string toggleLightColor();
        string toggleLightIntensity();
    }

    interface IProLight extends ILight
    {
        string setLightMode(LightColor lightColor, LightIntensity lightIntensity) throws UnknownLightColor, UnknownLightIntensity;
    }
}

#endif