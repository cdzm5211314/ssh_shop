/**
 * 
 */
package cn.itcast.shop.utils;

import java.util.UUID;

/**
 * @ClassName: UUIDUtils
 * @Description: 验证码获取随机字符串 
 * @Author: ChenD
 * @CreateDate: Aug 7, 2017 9:08:50 AM
 */
public class UUIDUtils {
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
