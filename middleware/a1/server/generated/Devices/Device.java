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

public class Device extends com.zeroc.Ice.Value
{
    public Device()
    {
        this.name = "";
        this.type = "";
    }

    public Device(String name, String type)
    {
        this.name = name;
        this.type = type;
    }

    public String name;

    public String type;

    public Device clone()
    {
        return (Device)super.clone();
    }

    public static String ice_staticId()
    {
        return "::Devices::Device";
    }

    @Override
    public String ice_id()
    {
        return ice_staticId();
    }

    /** @hidden */
    public static final long serialVersionUID = -1700642851L;

    /** @hidden */
    @Override
    protected void _iceWriteImpl(com.zeroc.Ice.OutputStream ostr_)
    {
        ostr_.startSlice(ice_staticId(), -1, true);
        ostr_.writeString(name);
        ostr_.writeString(type);
        ostr_.endSlice();
    }

    /** @hidden */
    @Override
    protected void _iceReadImpl(com.zeroc.Ice.InputStream istr_)
    {
        istr_.startSlice();
        name = istr_.readString();
        type = istr_.readString();
        istr_.endSlice();
    }
}
