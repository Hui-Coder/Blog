package dh.com.blog.utils;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import dh.com.blog.bean.Hot;

/**
 * Created by King on 2018/3/18.
 */

public class XmlUtils {

    private static final String TAG = "XmlUtils";

    public static ArrayList<Hot.EntryBean> parseXml(String xml ) {

        /**
         * 解析的字段list
         */
        List<String> fields = new ArrayList<String>();
        fields.add("id");
        fields.add("title");
        fields.add("summary");
        fields.add("published");
        fields.add("views");
        fields.add("comments");
        fields.add("updated");
        fields.add("topicIcon");


        ArrayList<Hot.EntryBean> hotList = null;
        Hot.EntryBean entryBean = null;
        try {
            InputStream is = new ByteArrayInputStream(xml.getBytes());
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(is, "utf-8");
            int typeEvent = parser.getEventType();
            while (typeEvent != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();
                switch (typeEvent) {
                    case XmlPullParser.START_DOCUMENT:
                        hotList = new ArrayList<Hot.EntryBean>();
                        break;
                    case XmlPullParser.START_TAG:
                        if ("entry".equals(tagName)) {  //实体开始
                            entryBean = new Hot.EntryBean();
                        }
                        if (entryBean != null && fields.contains(tagName)) {
                            setFieldValue(entryBean,tagName,parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("entry".equals(tagName)) {
                            hotList.add(entryBean);
                            entryBean = null;
                        }
                        break;
                    default:
                        break;
                }
                typeEvent = parser.next();
            }
        } catch (Exception e) {
            Log.d(TAG, "parse data exception：" + e.getMessage());
            e.printStackTrace();
        }
        return hotList;
    }


    /**
     * 通过反射，填充实体值
     * @param obj
     * @param propertyName
     * @param value
     */
    public static void setFieldValue(Object obj, String propertyName,
                                     Object value) {
        try {
            Field field = obj.getClass().getDeclaredField(propertyName);

            String type =field.getGenericType()+"";
            field.setAccessible(true);
            if ("int".equals(type)){
                field.set(obj, Integer.parseInt((String)value));
            }else{
                field.set(obj, value);
            }
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }
}
