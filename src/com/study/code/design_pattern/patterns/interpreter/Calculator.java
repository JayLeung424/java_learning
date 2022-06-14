package com.design.patterns.interpreter;

import java.util.HashMap;
import java.util.Stack;

public class Calculator {
	// 定义表达式
	private Expression expression;
	// 构造函数传参, 并解析
	public Calculator(String expStr) { // expStr = a + b
		// 安排运算先后顺序
		Stack<Expression> stack = new Stack<>();
		// 表达式拆分成字符数组  [ a + b ]
		char[] charArray = expStr.toCharArray();

		Expression left = null;
		Expression right = null;
		// 遍历所有的字符数组, 即遍历
		for (int i = 0; i < charArray.length; i++) {
			switch (charArray[i]) {
			case '+':
				// 如果是+号, 从stack中取出left -> a
				left = stack.pop();
				// 取出右边的表达式  -> b
				right = new VarExpression(String.valueOf(charArray[++i]));
				// 根据得到的left 和 right 构建 AddExpression 并放入stack中
				stack.push(new AddExpression(left, right));
				break;
			case '-':
				left = stack.pop();
				right = new VarExpression(String.valueOf(charArray[++i]));
				stack.push(new SubExpression(left, right));
				break;
			default:
				// 如果是字符, 就创建一个VarExpression 并push到我们的stack中
				stack.push(new VarExpression(String.valueOf(charArray[i])));
				break;
			}
		}
		// 当遍历完整个charArray 数组后, stack就得到最后Expression
		this.expression = stack.pop();
	}

	public int run(HashMap<String, Integer> var) {
		// 最后将表达式a+b 和 var ={a=10,b=20}
		// 然后传递给expression的interpreter进行解释执行
		return this.expression.interpreter(var);
	}
}