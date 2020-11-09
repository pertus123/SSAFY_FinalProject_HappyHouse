package com.project.happyhouse.model;

public class MemberDto {

	private String userid;
	private String userpwd;
	private String username;
	private String userbirth;
	private String userphone;
	private String joindate;
	private boolean isadmin;

	public MemberDto() {
		super();
	}

	public MemberDto(String userid, String userpwd, String username, String userbirth, String userphone, String joindate,
			boolean isadmin) {
		super();
		this.userid = userid;
		this.userpwd = userpwd;
		this.username = username;
		this.userbirth = userbirth;
		this.userphone = userphone;
		this.joindate = joindate;
		this.isadmin = isadmin;
	}

	public String getUserid() {
		return userid;
	}

	@Override
	public String toString() {
		return "UserDto [userid=" + userid + ", userpwd=" + userpwd + ", username=" + username + ", userbirth="
				+ userbirth + ", userphone=" + userphone + ", joindate=" + joindate + ", isadmin=" + isadmin + "]";
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserbirth() {
		return userbirth;
	}

	public void setUserbirth(String userbirth) {
		this.userbirth = userbirth;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getJoindate() {
		return joindate;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}

	public boolean isIsadmin() {
		return isadmin;
	}

	public void setIsadmin(boolean isadmin) {
		this.isadmin = isadmin;
	}

}
