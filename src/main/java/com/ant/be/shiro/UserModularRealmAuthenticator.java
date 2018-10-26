package com.ant.be.shiro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

public class UserModularRealmAuthenticator  extends ModularRealmAuthenticator {

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        //UserModularRealmAuthenticator:method doAuthenticate() execute
        // 判断getRealms()是否返回为空
        assertRealmsConfigured();
        // 强制转换回自定义的UserToken
        UserToken userToken = (UserToken) authenticationToken;
        // 登录类型
        String loginType = userToken.getLoginType();
        // 所有Realm
        Collection<Realm> realms = getRealms();
        Collection<Realm> typeRealms = new ArrayList<>();
        for (Realm realm : realms) {
            if (realm.getName().contains(loginType)) {
            	typeRealms.add(realm);
            }
        }

        // 判断是单Realm还是多Realm
        if (typeRealms.size() == 1){
            //doSingleRealmAuthentication() execute
        	Iterator<Realm> iterator = typeRealms.iterator();
//        	int i=0;
//        	while(iterator.hasNext()) {
//        		i++;
//        	}
        	return doSingleRealmAuthentication(iterator.next(), userToken);
        }
        else{
            //doMultiRealmAuthentication() execute 
            return doMultiRealmAuthentication(typeRealms, userToken);
        }
    }
}
