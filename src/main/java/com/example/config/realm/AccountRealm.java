package com.example.config.realm;

import com.example.entity.AdminUser;
import com.example.service.AdminUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class AccountRealm extends AuthorizingRealm {

	private Logger log = LoggerFactory.getLogger(AuthorizingRealm.class);
	
	@Autowired
	private AdminUserService adminUserService;
	

	

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// get principal
		AdminUser principal = (AdminUser)principals.getPrimaryPrincipal();
		// get user
		AdminUser adminUser = adminUserService.getByName(principal.getUsername());
		if(adminUser != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			return info;
		}
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//  AuthenticationToken trans to UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		
		//  UsernamePasswordToken get  username
		String username = upToken.getUsername();
				
		log.debug("User：{} login...", username);
		
		// search the user form db
		AdminUser adminUser = adminUserService.getByName(username);
		
		//not exist == error
		if(adminUser == null) throw new UnknownAccountException("user not exist!");
		

		// Object principal = username;
		AdminUser principal = new AdminUser();
		principal.setAdminUserId(adminUser.getAdminUserId());
		principal.setUsername(username);
		principal.setAvatar(adminUser.getAvatar());
		

		Object credentials = adminUser.getPassword();

		String realmName = getName();
		
		//encryption
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		
		return new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
	}
	
	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";
		Object credentials = "123";
		Object salt = ByteSource.Util.bytes("admin");
		int hashIterations = 1024;
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
	}

}
