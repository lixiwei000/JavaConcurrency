package JavaConcurrencyInAction.chapter04;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于监视器模式的车辆追踪
 *
 * @author NikoBelic
 * @create 16/6/7 14:16
 */
public class MonitorVehicleTracker
{
    private final Map<String,MutablePoint> locations;

    public MonitorVehicleTracker(Map<String,MutablePoint> locations)
    {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String,MutablePoint> getLocations()
    {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id)
    {
        MutablePoint loc = locations.get(id);
        return loc == null?null:new MutablePoint(loc);      // 返回新的u而不返回当前对象的引用
    }

    private static Map<String,MutablePoint> deepCopy(Map<String,MutablePoint> m)
    {
        Map<String,MutablePoint> result = new HashMap<String, MutablePoint>();
        for (String id : m.keySet())
        {
            result.put(id,new MutablePoint(m.get(id)));
        }
        return Collections.unmodifiableMap(result);
    }
}
