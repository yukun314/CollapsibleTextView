# CollapsibleTextView
CollapsibleTextView是一个可展开/折叠显示文本的TextView。默认是折叠的，当你点击文章之后它会自动展开。再次点击他又会缩回去。<br>
<br>
###效果图:
####文字内容未超过设定(或默认)的最大行数时的显示效果
![](https://github.com/yukun314/CollapsibleTextView/raw/master/preview/image1.png)
####文字内容超过设定(或默认)的最大行数时的显示效果
折叠时:<br>
![](https://github.com/yukun314/CollapsibleTextView/raw/master/preview/image2.png)<br>
展开时:<br>
![](https://github.com/yukun314/CollapsibleTextView/raw/master/preview/image3.png)
<br>
###如何使用CollapsibleTextView
```java
mTextView = (CollapsibleTextView) findViewById(R.id.activity_test_textview);
mTextView.setImage(R.drawable.down,R.drawable.up);
mTextView.getTextView().setTextColor(Color.WHITE);//设置字体颜色
mTextView.getTextView().setTextSize(23);//设置字体大小
//......
mTextView.setMaxLines(4);//设置最大行
//......
```
```xml
<com.zyk.collapsibletextview.CollapsibleTextView
        android:id="@+id/activity_test_textview"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:layout_margin="15dp"
        android:layout_marginTop="15dp"/>
```
<br>
###实现思路
通过RelativeLayout添加一个TextView和一个ImageView封装实现

