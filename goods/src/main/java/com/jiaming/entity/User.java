package com.jiaming.entity;

public class User {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.uid
     *
     * @mbggenerated Tue Jun 23 15:02:12 CST 2020
     */
    private String uid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.loginname
     *
     * @mbggenerated Tue Jun 23 15:02:12 CST 2020
     */
    private String loginname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.loginpass
     *
     * @mbggenerated Tue Jun 23 15:02:12 CST 2020
     */
    private String loginpass;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.email
     *
     * @mbggenerated Tue Jun 23 15:02:12 CST 2020
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.status
     *
     * @mbggenerated Tue Jun 23 15:02:12 CST 2020
     */
    private Boolean status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.activationCode
     *
     * @mbggenerated Tue Jun 23 15:02:12 CST 2020
     */
    private String activationcode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.uid
     *
     * @return the value of user.uid
     *
     * @mbggenerated Tue Jun 23 15:02:12 CST 2020
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.uid
     *
     * @param uid the value for user.uid
     *
     * @mbggenerated Tue Jun 23 15:02:12 CST 2020
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.loginname
     *
     * @return the value of user.loginname
     *
     * @mbggenerated Tue Jun 23 15:02:12 CST 2020
     */
    public String getLoginname() {
        return loginname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.loginname
     *
     * @param loginname the value for user.loginname
     *
     * @mbggenerated Tue Jun 23 15:02:12 CST 2020
     */
    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.loginpass
     *
     * @return the value of user.loginpass
     *
     * @mbggenerated Tue Jun 23 15:02:12 CST 2020
     */
    public String getLoginpass() {
        return loginpass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.loginpass
     *
     * @param loginpass the value for user.loginpass
     *
     * @mbggenerated Tue Jun 23 15:02:12 CST 2020
     */
    public void setLoginpass(String loginpass) {
        this.loginpass = loginpass == null ? null : loginpass.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.email
     *
     * @return the value of user.email
     *
     * @mbggenerated Tue Jun 23 15:02:12 CST 2020
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.email
     *
     * @param email the value for user.email
     *
     * @mbggenerated Tue Jun 23 15:02:12 CST 2020
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.status
     *
     * @return the value of user.status
     *
     * @mbggenerated Tue Jun 23 15:02:12 CST 2020
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.status
     *
     * @param status the value for user.status
     *
     * @mbggenerated Tue Jun 23 15:02:12 CST 2020
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.activationCode
     *
     * @return the value of user.activationCode
     *
     * @mbggenerated Tue Jun 23 15:02:12 CST 2020
     */
    public String getActivationcode() {
        return activationcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.activationCode
     *
     * @param activationcode the value for user.activationCode
     *
     * @mbggenerated Tue Jun 23 15:02:12 CST 2020
     */
    public void setActivationcode(String activationcode) {
        this.activationcode = activationcode == null ? null : activationcode.trim();
    }
}