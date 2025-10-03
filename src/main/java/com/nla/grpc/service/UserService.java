package com.nla.grpc.service;

import com.nla.grpc.*;
import com.nla.grpc.dao.UserDao;
import com.nla.grpc.model.User;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;


@GrpcService
public class UserService extends  UserServiceGrpc.UserServiceImplBase{

    @Autowired
    private UserDao userDao;
    @Override
    public void createUser(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        User u=toUser(request);
        User user=userDao.save(u);
        UserResponse us=toUserResponse(user);
        responseObserver.onNext(us);
        responseObserver.onCompleted();
    }

    private User toUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        return user;
    }

    private UserResponse toUserResponse(User user){
        return UserResponse.newBuilder().setId(user.getId()).setName(user.getName()).setEmail(user.getEmail()).build();
    }



    @Override
    public void updateUser(UserUpdate request, StreamObserver<UserResponse> responseObserver) {
        super.updateUser(request, responseObserver);
    }

    @Override
    public void getUser(GetRequest request, StreamObserver<UserResponse> responseObserver) {
        super.getUser(request, responseObserver);
    }
}
