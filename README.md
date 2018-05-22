# ImageCardView
android tv item view   
![show](show.gif)

# Usage

在xml或者代码中添加imagecardview
```java
    <com.wzq.cardview.ImageCardView
        android:id="@+id/img2"
        android:layout_marginStart="20dp"
        android:layout_width="300dp"
        android:layout_height="200dp"
        app:ic_showBottom="true"/>
``` 

设置图片，名称
```java
        ImageCardView img2 = findViewById(R.id.img2);
        //设置图片
        img2.getMainImage().setImageResource(R.mipmap.p2);
        //设置title
        img2.setTitle("test image card view, test image card view, test image card view");
```
   
 
参数说明
- ic_border  设置背景图片或者颜色 （通常用于添加边框）， 默认为白色边框
- ic_showBottom  是否显示title栏 默认不显示
- ic_is_light 是否展示闪光动画 默认展示
- ic_light_duration 闪光动画时间 默认700ms
- ic_img 设置展示图片 默认为空 
 
