package onlineShop.storage;

import onlineShop.exceptions.UserNofFountException;
import onlineShop.models.User;
import onlineShop.models.enums.UserType;
import onlineShop.util.StorageSerializeUtil;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private Map<String,User> users = new HashMap<>();


    public void registerUser(User user) {
        users.put(user.getEmail(),user);
        StorageSerializeUtil.serializeUserStorage(this);
    }

    public User getUserById(String userId) {
        return users.get(userId);
    }

    public User getByEmail(String email) {
        return users.get(email);
    }

    public void printUserByType(UserType user) {
        for (User value : users.values()){
            if (value.getUserType() == user){
                System.out.println(value);
            }
        }
    }
}
