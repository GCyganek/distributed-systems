//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.7
//
// <auto-generated>
//
// Generated from file `devices.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package Devices;

public enum LightColor implements java.io.Serializable
{
    RED(0),
    BLUE(1),
    YELLOW(2),
    GREEN(3);

    public int value()
    {
        return _value;
    }

    public static LightColor valueOf(int v)
    {
        switch(v)
        {
        case 0:
            return RED;
        case 1:
            return BLUE;
        case 2:
            return YELLOW;
        case 3:
            return GREEN;
        }
        return null;
    }

    private LightColor(int v)
    {
        _value = v;
    }

    public void ice_write(com.zeroc.Ice.OutputStream ostr)
    {
        ostr.writeEnum(_value, 3);
    }

    public static void ice_write(com.zeroc.Ice.OutputStream ostr, LightColor v)
    {
        if(v == null)
        {
            ostr.writeEnum(Devices.LightColor.RED.value(), 3);
        }
        else
        {
            ostr.writeEnum(v.value(), 3);
        }
    }

    public static LightColor ice_read(com.zeroc.Ice.InputStream istr)
    {
        int v = istr.readEnum(3);
        return validate(v);
    }

    public static void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<LightColor> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    public static void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, LightColor v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.Size))
        {
            ice_write(ostr, v);
        }
    }

    public static java.util.Optional<LightColor> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.Size))
        {
            return java.util.Optional.of(ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static LightColor validate(int v)
    {
        final LightColor e = valueOf(v);
        if(e == null)
        {
            throw new com.zeroc.Ice.MarshalException("enumerator value " + v + " is out of range");
        }
        return e;
    }

    private final int _value;
}