package com.buddywindow.auth.constant;

public enum PrivateClaims {
	 PROFILE,
     IDENTITY,
     UID;
     public String key;

     PrivateClaims() {
         this.key = this.name().toLowerCase();
     }
}
