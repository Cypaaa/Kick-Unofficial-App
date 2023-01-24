package com.cyprien.kickunofficialapp;

public final class KickEndpoint {
    public static final String GithubAppVersion = "https://raw.githubusercontent.com/Cypaaa/Kick-Unofficial-App/main/version";
    public static final String KickBase = "https://kick.com/";
    public static final String GoogleAuth = "https://accounts.google.";
    /*
    "https://accounts.google.com/o/oauth2/auth", // first redirection
    "https://accounts.google.com/signin/oauth", // after entering an email
    "https://accounts.google.com/AccountChooser", // if you already have accounts connected you have to chose one
    "https://accounts.google.com/ServiceLogin", // redirection
    "https://accounts.google.com/signin/", // redirection
    "https://accounts.google.com/CheckCookie", // connecting to account
    "https://accounts.google.com/restart", // if you press back bcs ur email is wrong
    */

    // private
    private static final String KickAuth = KickBase + "auth/";

    // public
    public static final String KickProfile = KickBase + "profile";
    public static final String KickFollowing = KickBase + "following";
    public static final String KickCategories = KickBase + "categories";
    public static final String KickLogin = KickAuth + "login";
    public static final String KickSignup = KickAuth + "signup";

}
