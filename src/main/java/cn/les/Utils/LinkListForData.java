package cn.les.Utils;

import org.apache.catalina.util.StringParser;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by 严峰 on 2016/7/28 0028.
 */
public class LinkListForData {
    private LinkedList<String> linkedList;

    public LinkedList<String> getLinkedList() {
        return linkedList;
    }

    public LinkListForData(){
        linkedList= new LinkedList<String>();
    }
    public void put(String str){
        if(linkedList.size()<10){
            linkedList.offer(str);
        }else {

            linkedList.addLast(str);
            linkedList.removeFirst();
        }
    }
    public void publish(){
        if(linkedList.size()<10){
            return;
        }
        for(int i=0;i<linkedList.size();i++){
            String string=linkedList.get(i);
            System.out.println(""+i+"."+string);
        }

    }
}
