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

public class LightMode implements java.lang.Cloneable,
                                  java.io.Serializable
{
    public LightColor color;

    public LightIntensity intensity;

    public LightMode()
    {
        this.color = LightColor.RED;
        this.intensity = LightIntensity.LOW;
    }

    public LightMode(LightColor color, LightIntensity intensity)
    {
        this.color = color;
        this.intensity = intensity;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        LightMode r = null;
        if(rhs instanceof LightMode)
        {
            r = (LightMode)rhs;
        }

        if(r != null)
        {
            if(this.color != r.color)
            {
                if(this.color == null || r.color == null || !this.color.equals(r.color))
                {
                    return false;
                }
            }
            if(this.intensity != r.intensity)
            {
                if(this.intensity == null || r.intensity == null || !this.intensity.equals(r.intensity))
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public int hashCode()
    {
        int h_ = 5381;
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::Devices::LightMode");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, color);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, intensity);
        return h_;
    }

    public LightMode clone()
    {
        LightMode c = null;
        try
        {
            c = (LightMode)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        LightColor.ice_write(ostr, this.color);
        LightIntensity.ice_write(ostr, this.intensity);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.color = LightColor.ice_read(istr);
        this.intensity = LightIntensity.ice_read(istr);
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, LightMode v)
    {
        if(v == null)
        {
            _nullMarshalValue.ice_writeMembers(ostr);
        }
        else
        {
            v.ice_writeMembers(ostr);
        }
    }

    static public LightMode ice_read(com.zeroc.Ice.InputStream istr)
    {
        LightMode v = new LightMode();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<LightMode> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, LightMode v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            ice_write(ostr, v);
            ostr.endSize(pos);
        }
    }

    static public java.util.Optional<LightMode> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            return java.util.Optional.of(LightMode.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final LightMode _nullMarshalValue = new LightMode();

    /** @hidden */
    public static final long serialVersionUID = -1475978353L;
}