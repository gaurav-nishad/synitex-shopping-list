package com.clinics.core.api.services.auth.impl;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.clinics.core.api.services.auth.Authentication;

public class AuthenticationImpl implements Authentication {

    private String userIdentity;

    private byte[] token;

    public AuthenticationImpl() {
    }

    /**
     * @param userIdentity
     * @param token
     * @param suceed
     */
    public AuthenticationImpl(String canonicUserId, byte[] token) {
        this.userIdentity = canonicUserId;
        this.token = token;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AuthenticationImpl)) {
            return false;
        }
        final Authentication rhs = (Authentication) obj;
        return new EqualsBuilder() //
        /* */.append(getUserIdentity(), rhs.getUserIdentity())
        /* */.append(getToken(), rhs.getToken()) //
        .isEquals();
    }

    /**
     * {@inhetitedDoc}
     */
    public byte[] getToken() {
        return ArrayUtils.clone(this.token);
    }

    /**
     * {@inhetitedDoc}
     */
    public String getUserIdentity() {
        return userIdentity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder() //
        /* */.append(getUserIdentity())
        /* */.append(getToken()) //
        .toHashCode();
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    public void setUserIdentity(String canonicUserId) {
        this.userIdentity = canonicUserId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

};
