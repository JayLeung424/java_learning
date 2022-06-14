package com.design.principle.opc.improve;

/**
 * @ClassName: Ocp
 * @Description:
 * @Author: jiel
 * @Date: 2022/3/29 16:01
 **/
public class Ocp {
    public static void main(String[] args) {
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Rectangle());
        graphicEditor.drawShape(new Circle());
        graphicEditor.drawShape(new Triangle());
        graphicEditor.drawShape(new OtherGraphic());
    }
}

//这是一个用于绘图的类 [使用方]
class GraphicEditor {
    //接收 Shape 对象，调用 draw 方法
    public void drawShape(Shape s) {
        s.draw();
    }
}

//Shape 类，基类
abstract class Shape {
    public abstract void draw();//抽象方法
}

class Rectangle extends Shape {
    @Override
    public void draw() {
        System.out.println(" 绘制矩形 ");
    }
}

class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println(" 绘制圆形 ");
    }
}

//新增画三角形
class Triangle extends Shape {
    @Override
    public void draw() {
        System.out.println(" 绘制三角形  ");
    }
}

//新增一个图形
class OtherGraphic extends Shape {
    @Override
    public void draw() {
        System.out.println(" 绘制其它图形 ");
    }
}
