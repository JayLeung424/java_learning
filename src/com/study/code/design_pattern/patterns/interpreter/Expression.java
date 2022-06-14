package com.design.patterns.interpreter;

import java.util.HashMap;

/**
 * 抽象类表达式 通过HashMap键值对， 可以获取到变量的值
 */
public abstract class Expression {
	/**
	 * 解释公式和数值， key 就是公式（表达式） 参数【a,b,c】，value就是具体的值
	 * 	例: HashMap {a=10,b=20}
	 * @param var
	 * @return
	 */
	public abstract int interpreter(HashMap<String, Integer> var);
}
