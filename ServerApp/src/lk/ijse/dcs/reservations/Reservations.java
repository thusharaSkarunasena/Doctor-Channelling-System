package lk.ijse.dcs.reservations;

import java.util.HashMap;

public class Reservations<SuperService> {

    private HashMap<String, SuperService> reserveData = new HashMap<>();

    public boolean reserve(String id, SuperService service) {
        if (reserveData.containsKey(id)) {
            if (reserveData.get(id) == service) {
                return true;
            }
            return true;
        } else {
            reserveData.put(id, service);
            return true;
        }
    }

    public boolean release(String id, SuperService service) {
        if (reserveData.get(id) == service) {
            reserveData.remove(id);
            return true;
        }
        return false;
    }

}
