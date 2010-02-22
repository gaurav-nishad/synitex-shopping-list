package com.clinics.core.api.services.auth;

public interface Authentication {

    byte[] getToken();

    String getUserIdentity();

}