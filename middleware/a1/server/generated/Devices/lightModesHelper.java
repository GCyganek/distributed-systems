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

/**
 * Helper class for marshaling/unmarshaling lightModes.
 **/
public final class lightModesHelper
{
    public static void write(com.zeroc.Ice.OutputStream ostr, LightMode[] v)
    {
        if(v == null)
        {
            ostr.writeSize(0);
        }
        else
        {
            ostr.writeSize(v.length);
            for(int i0 = 0; i0 < v.length; i0++)
            {
                LightMode.ice_write(ostr, v[i0]);
            }
        }
    }

    public static LightMode[] read(com.zeroc.Ice.InputStream istr)
    {
        final LightMode[] v;
        final int len0 = istr.readAndCheckSeqSize(2);
        v = new LightMode[len0];
        for(int i0 = 0; i0 < len0; i0++)
        {
            v[i0] = LightMode.ice_read(istr);
        }
        return v;
    }

    public static void write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<LightMode[]> v)
    {
        if(v != null && v.isPresent())
        {
            write(ostr, tag, v.get());
        }
    }

    public static void write(com.zeroc.Ice.OutputStream ostr, int tag, LightMode[] v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            lightModesHelper.write(ostr, v);
            ostr.endSize(pos);
        }
    }

    public static java.util.Optional<LightMode[]> read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            LightMode[] v;
            v = lightModesHelper.read(istr);
            return java.util.Optional.of(v);
        }
        else
        {
            return java.util.Optional.empty();
        }
    }
}