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

public interface IProLight extends ILight
{
    String setLightMode(LightColor lightColor, LightIntensity lightIntensity, com.zeroc.Ice.Current current)
        throws UnknownLightColor,
               UnknownLightIntensity;

    /** @hidden */
    static final String[] _iceIds =
    {
        "::Devices::IDevice",
        "::Devices::ILight",
        "::Devices::IProLight",
        "::Ice::Object"
    };

    @Override
    default String[] ice_ids(com.zeroc.Ice.Current current)
    {
        return _iceIds;
    }

    @Override
    default String ice_id(com.zeroc.Ice.Current current)
    {
        return ice_staticId();
    }

    static String ice_staticId()
    {
        return "::Devices::IProLight";
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
     * @throws com.zeroc.Ice.UserException -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_setLightMode(IProLight obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        LightColor iceP_lightColor;
        LightIntensity iceP_lightIntensity;
        iceP_lightColor = LightColor.ice_read(istr);
        iceP_lightIntensity = LightIntensity.ice_read(istr);
        inS.endReadParams();
        String ret = obj.setLightMode(iceP_lightColor, iceP_lightIntensity, current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        ostr.writeString(ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /** @hidden */
    final static String[] _iceOps =
    {
        "getDeviceInfo",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "setLightMode",
        "toggleLightColor",
        "toggleLightIntensity"
    };

    /** @hidden */
    @Override
    default java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceDispatch(com.zeroc.IceInternal.Incoming in, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        int pos = java.util.Arrays.binarySearch(_iceOps, current.operation);
        if(pos < 0)
        {
            throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return IDevice._iceD_getDeviceInfo(this, in, current);
            }
            case 1:
            {
                return com.zeroc.Ice.Object._iceD_ice_id(this, in, current);
            }
            case 2:
            {
                return com.zeroc.Ice.Object._iceD_ice_ids(this, in, current);
            }
            case 3:
            {
                return com.zeroc.Ice.Object._iceD_ice_isA(this, in, current);
            }
            case 4:
            {
                return com.zeroc.Ice.Object._iceD_ice_ping(this, in, current);
            }
            case 5:
            {
                return _iceD_setLightMode(this, in, current);
            }
            case 6:
            {
                return ILight._iceD_toggleLightColor(this, in, current);
            }
            case 7:
            {
                return ILight._iceD_toggleLightIntensity(this, in, current);
            }
        }

        assert(false);
        throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
    }
}