package cn.wolfcode.managesystem.util;

public class StringIsEmpty {
	//判断字符串是否有长度
		public static boolean hasLength(String str){
			return str != null && str.trim().length() > 0;
		}
}
