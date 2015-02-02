package top.duyt.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * 枚举类型工具包
 * @author Alvin Du
 *
 */
public class EnumUtil {
	
	/**
	 * 将指定枚举类型转换为整型集合
	 * @param clz 目标枚举类型
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List<Integer> enum2ordinal(Class clz){
		List<Integer> rels = null;
		if (clz.isEnum()) {
			rels = new ArrayList<Integer>();
			Enum[] ens =  (Enum[]) clz.getEnumConstants();
			for (Enum en : ens) {
				rels.add(en.ordinal());
			}
		}
		return rels;
	}
	
	/**
	 * 将指定枚举类型转换为字符串集合
	 * @param clz 目标枚举类型
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List<String> enum2Name(Class clz){
		List<String> rels = null;
		if (clz.isEnum()) {
			rels = new ArrayList<String>();
			Enum[] ens =  (Enum[]) clz.getEnumConstants();
			for (Enum en : ens) {
				rels.add(en.name());
			}
		}
		return rels;
	}
	
	/**
	 * 将指定的枚举类型转换为枚举下标和枚举名组成的Map
	 * @param clz 目标枚举类型
	 * @return Map<ordinal as Key,name as value>
	 */
	@SuppressWarnings("rawtypes")
	public static Map enum2Map(Class clz){
		Map<Integer, Object> relMap = null;
		if (clz.isEnum()) {
			relMap = new HashMap<Integer, Object>();
			Enum[] ens =  (Enum[]) clz.getEnumConstants();
			for (Enum en : ens) {
				relMap.put(en.ordinal(), en.name());
			}
		}
		return relMap;
	}
	
	/**
	 * 将指定的枚举类型转换为枚举下标和指定属性组成的Map
	 * @param clz
	 * @param propName
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("rawtypes")
	public static Map enumProp2MapThrouOrdinal(Class clz,String propName) throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException{
		Map<Integer, Object> relMap = null;
		if (clz.isEnum()) {
			relMap = new HashMap<Integer, Object>();
			Enum[] ens =  (Enum[]) clz.getEnumConstants();
			for (Enum en : ens) {
				relMap.put(en.ordinal(), PropertyUtils.getProperty(en, propName));
			}
		}
		return relMap;
	}
	
	/**
	 * 将指定的枚举类型转换为枚举名和指定属性组成的Map
	 * @param clz
	 * @param propName
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("rawtypes")
	public static Map enumProp2MapThrouEnumName(Class clz,String propName) throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException{
		Map<String, Object> relMap = null;
		if (clz.isEnum()) {
			relMap = new HashMap<String, Object>();
			Enum[] ens =  (Enum[]) clz.getEnumConstants();
			for (Enum en : ens) {
				relMap.put(en.name(), PropertyUtils.getProperty(en, propName));
			}
		}
		return relMap;
	}
	
	/**
	 * 将指定枚举类型中的属性转换为List
	 * @param clz
	 * @param propName
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings("rawtypes")
	public static List<Object> enumProp2List(Class clz,String propName) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<Object> relList = null;
		if (clz.isEnum()) {
			relList = new ArrayList<Object>();
			Enum[] ens =  (Enum[]) clz.getEnumConstants();
			for (Enum en : ens) {
				relList.add(PropertyUtils.getProperty(en, propName));
			}
		}
		return relList;
	}

}
