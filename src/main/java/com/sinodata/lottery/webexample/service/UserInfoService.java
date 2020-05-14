
package com.sinodata.lottery.webexample.service;


import com.sinodata.lottery.grpc.User.UserGrpc.UserBlockingStub;
import com.sinodata.lottery.grpc.User.*;
import com.sinodata.lottery.webexample.core.model.User;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserInfoService {

    @GrpcClient("lottery-service")
    private UserBlockingStub userStub;

    public User getUserByName(final String name) {
        try {
            final UserReply response = this.userStub.getUserByName(UserRequest.newBuilder().setName(name).build());
            UserInfo info = response.getUser();
            User user = new User();
            BeanUtils.copyProperties(info, user);

            return user;
        } catch (final StatusRuntimeException e) {
            return null;
        }
    }
    public List<User> getUserList() {
        try {
            final UserListReply response = this.userStub.getUserList(UserListRequest.newBuilder().build());
            // fixme response.getUserList can not work

            int count = response.getUserCount();
            List<User> userList = new ArrayList<>();
            for(int i=0;i<count;i++){
                UserInfo info = response.getUser(i);
                User user = new User();
                BeanUtils.copyProperties(info, user);
                userList.add(user);
            }
            return userList;

        } catch (final StatusRuntimeException e) {
            return null;
        }
    }
}
