package com.tecsharp.teachevaluationpro.utils.crypt;

import static com.tecsharp.teachevaluationpro.utils.crypt.HashUtils.isEqual;
import static com.tecsharp.teachevaluationpro.utils.crypt.HashUtils.sha256;


public class Sha256 {


    public String computeHash(String password, String salt, String username) {

        return "$SHA$" + salt + "$" + sha256(sha256(password) + salt);
    }


    public boolean comparePassword(String password, String hashedPassword, String username) {
        String[] line = hashedPassword.split("\\$");
        return line.length == 4 && isEqual(hashedPassword, computeHash(password, line[2], username));
    }


    public int getSaltLength() {
        return 16;
    }

}
