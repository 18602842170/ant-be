package com.ant.be.common;

public class WechatConstData {
	
	private static String access_token;
	
	private static Long access_token_extdate;
	
    private static WechatConstData instance;
    
    private WechatConstData(){

    }
    public static WechatConstData getInstance(){
        if(instance==null){
            instance=new WechatConstData();
        }
        return instance;
    }
    
	public static String getAccess_token() {
		return access_token;
	}
	public static void setAccess_token(String access_token) {
		WechatConstData.access_token = access_token;
	}
	
	/**
	 * 判断时间，
	 * true表示没过期
	 * @param now
	 * @return
	 */
	public static boolean check_Access_token_extdate(Long now) {
		// 预留10分钟
		return (now + 10*60*1000) < access_token_extdate;
	}
	
	public static void setAccess_token_extdate(Long access_token_extdate) {
		WechatConstData.access_token_extdate = access_token_extdate;
	}
    
    
}
