package cn.trusteye.work.chapter2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: rayman
 * @Description:
 * @Date: Create in 2017/12/26 11:07
 * @Modified By:
 *
 */


public class FilterMessage {
    public static void main(String[] args) {
        List<Message> messages = getMessages();


        //需求1：查询所有login动作的消息
//        List<Message> loginMessage = filterMessageByAction(messages,"login","",true);
        List<Message> loginMessage = filterMessage(messages,(Message message)-> "login".equals(message.getAction_type()));
        System.out.println(loginMessage);

        //需求2：查询所有logout动作的消息
//        loginMessage = filterMessageByAction(messages,"logout","",true);
        loginMessage = filterMessage(messages,new LogoutPredicat());
        System.out.println(loginMessage);

        //需求3：查询网站类型为michat的消息
//        loginMessage = filterMessageByAction(messages,"","QQ",false);
        loginMessage = filterMessage(messages,new MichatPredicat());
        System.out.println(loginMessage);



        List<Integer> numbers = new ArrayList<>();
        numbers.add(new Integer(1));
        numbers.add(new Integer(2));
        numbers.add(new Integer(3));
        numbers.add(new Integer(4));
        numbers.add(new Integer(5));
        numbers.add(new Integer(6));

        List<Integer> evenNumbers = filter(numbers, (Integer number) -> number % 2 == 0);
        System.out.println(evenNumbers);

        Collections.sort(messages,(Message m1,Message m2) -> m1.getCapture_time() .compareTo(m2.getCapture_time()) );
        System.out.println(messages);


    }

    private static List<Message> getMessages() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("QQ", "login", 100L));
        messages.add(new Message("QQ", "logout", 200L));
        messages.add(new Message("wechat", "login", 100L));
        messages.add(new Message("wechat", "logout", 300L));
        messages.add(new Message("michat", "login", 250L));
        messages.add(new Message("wechat", "logout", 200L));

        messages = Arrays.asList();
        return messages;
    }

    private static List<Message> filterMessageByAction(List<Message> messages, String action,String siteName,Boolean flag) {
        List<Message> result = new ArrayList<>();
        for (Message message : messages) {
            if(flag&&action.equals(message.getAction_type())){
                result.add(message);
            }
            if(!flag&&siteName.equals(message.getSite_name())){
                result.add(message);
            }
        }
        return result;
    }

    private static List<Message> filterMessage(List<Message> messages, MessagePredicat predicat) {
        List<Message> result = new ArrayList<>();
        for (Message message : messages) {
            if(predicat.test(message)){
                result.add(message);
            }
        }
        return result;
    }

    private static <T> List<T> filter(List<T> messages, CommonPredicat<T> predicat) {
        List<T> result = new ArrayList<>();
        for (T t : messages) {
            if(predicat.test(t)){
                result.add(t);
            }
        }
        return result;
    }

}

interface CommonPredicat<T>{
    Boolean test(T t);
}

interface MessagePredicat extends CommonPredicat<Message>{
    @Override
    Boolean test(Message message);
}

class LoginPredicat implements MessagePredicat{

    @Override
    public Boolean test(Message message) {
        return "login".equals(message.getAction_type());
    }
}

class LogoutPredicat implements MessagePredicat{

    @Override
    public Boolean test(Message message) {
        return "logout".equals(message.getAction_type());
    }
}

class MichatPredicat implements MessagePredicat{

    @Override
    public Boolean test(Message message) {
        return "michat".equals(message.getSite_name());
    }
}


