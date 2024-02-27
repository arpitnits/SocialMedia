package org.example.services;

import org.example.Utils.CommonUtils;
import org.example.enums.UserType;
import org.example.pojos.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    public static Map<String, User> userMap = new HashMap<>();
    public User createUser(String username, UserType userType) {
        String uuid = CommonUtils.getUUID();
        User user = User.builder()
                .uuid(uuid)
                .name(username)
                .userType(userType)
                .build();

        userMap.put(uuid, user);

        System.out.println("Created user "  + user);

        return userMap.get(uuid);
    }

    private void updateUserStatus(String uuid, boolean status) {
        if(!userMap.containsKey(uuid)) {
            System.out.println("User " + uuid + " did not exist");
            return;
        }
        userMap.get(uuid).setActive(status);

        System.out.println("Updated user "  + userMap.get(uuid));
    }

    private void deleteUser() {

    }
}
