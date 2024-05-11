package com.fosanzdev.trainingbrain.grpcConsumers;

import com.fosanzdev.trainingbrain.auth.AuthServiceGrpc;
import com.fosanzdev.trainingbrain.auth.AuthServiceGrpc.AuthServiceBlockingStub;
import com.fosanzdev.trainingbrain.auth.LoginRequest;
import com.fosanzdev.trainingbrain.auth.LoginResponse;
import com.fosanzdev.trainingbrain.auth.LogoutRequest;
import com.fosanzdev.trainingbrain.auth.LogoutResponse;
import com.fosanzdev.trainingbrain.auth.RefreshTokenRequest;
import com.fosanzdev.trainingbrain.auth.RefreshTokenResponse;
import com.fosanzdev.trainingbrain.auth.RegisterRequest;
import com.fosanzdev.trainingbrain.auth.RegisterResponse;
import com.fosanzdev.trainingbrain.auth.VerifyRequest;
import com.fosanzdev.trainingbrain.auth.VerifyResponse;
import com.fosanzdev.trainingbrain.models.SessionDTO;

import io.grpc.ManagedChannelBuilder;

public class AuthConsumer {

    private static final String HOST = "api.trainingbrain.es";
    private static final int PORT = 50051;

    private static AuthServiceBlockingStub newStub(){
        return AuthServiceGrpc.newBlockingStub(ManagedChannelBuilder.forAddress(HOST, PORT)
                .usePlaintext()
                .build());
    }

    public SessionDTO login(SessionDTO session) {
        AuthServiceBlockingStub stub = newStub();

        LoginRequest request = LoginRequest.newBuilder()
                .setUsername(session.getUsername())
                .setPassword(session.getPassword())
                .build();

        LoginResponse response = stub.login(request);
        return SessionDTO.newBuilder().setAuthToken(response.getAuthToken()).build();
    }

    public SessionDTO register(SessionDTO session) {
        AuthServiceBlockingStub stub = newStub();

        RegisterRequest request = RegisterRequest.newBuilder()
                .setName(session.getName())
                .setUsername(session.getUsername())
                .setPassword(session.getPassword())
                .build();

        RegisterResponse response = stub.register(request);

        return SessionDTO.newBuilder()
                .setAuthToken(response.getAuthToken())
                .build();
    }

    public SessionDTO verify(SessionDTO session) {
        AuthServiceBlockingStub stub = newStub();

        VerifyRequest request = VerifyRequest.newBuilder()
                .setUsername(session.getUsername())
                .setPassword(session.getPassword())
                .setAuthToken(session.getAuthToken())
                .build();

        VerifyResponse response = stub.verify(request);

        return SessionDTO.newBuilder()
                .setAccessToken(response.getAccessToken())
                .setRefreshToken(response.getRefreshToken())
                .build();
    }

    public SessionDTO refreshToken(SessionDTO session) {
        AuthServiceBlockingStub stub = newStub();

        RefreshTokenRequest request = RefreshTokenRequest.newBuilder()
                .setRefreshToken(session.getRefreshToken())
                .setAccessToken(session.getAccessToken())
                .build();

        RefreshTokenResponse response = stub.refreshToken(request);

        return SessionDTO.newBuilder()
                .setAccessToken(response.getAccessToken())
                .build();

    }

    public boolean logout(SessionDTO session){
        AuthServiceBlockingStub stub = newStub();

        LogoutRequest request = LogoutRequest.newBuilder()
                .setUsername(session.getUsername())
                .setRefreshToken(session.getRefreshToken())
                .build();

        return stub.logout(request).getLogout();
    }
}
