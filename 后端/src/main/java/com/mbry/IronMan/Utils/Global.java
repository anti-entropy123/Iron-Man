package com.mbry.IronMan.Utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Global {
	static public class CheckCode{
		public String checkCode;
		private long genTime;
		private final long expireTime = 5 * 60;
		
	    public CheckCode(){	
		}

		public CheckCode(String checkCode){
			this.checkCode = checkCode;
			this.genTime = (new Date().getTime()) / 1000;
		}

		public Boolean isExpire(){
			return ((new Date().getTime()) / 1000) - this.genTime < this.expireTime;
		}

		public String getCheckCode(){
			return this.checkCode;
		}
	}

	final public static int pageSize = 8;
	
	final public static int rentType = 1;
	
	final public static int sellType = 2;
	
	final public static int askRentType = 3;
	
	final public static int askSellType = 4;
	
	final public static int roomMateCardType = 5;

	final public static String letterTable = "123456789";

	public static Map<String, CheckCode> mobileToCheckCode = new HashMap<>();

}
