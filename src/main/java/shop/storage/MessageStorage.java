package shop.storage;

import shop.data.DataClass;
import shop.model.Message;
import shop.model.User;
import java.util.LinkedList;
import java.util.List;


public class MessageStorage {
    private List<Message> messageList=new LinkedList<>();


    public void add(Message message) {
        messageList.add(message);
        serial();
    }




    public void printMessageByUser(User currentUser){
        for (Message message : messageList) {
            if (message.getToUser().equals(currentUser)){
                System.out.println(" "+message.getFromUser().getName()+"--"+message.getText()+" ");
            }
        }

    }

    public void serial(){
        DataClass.serializeMessage(messageList);
    }

    public void initData(){
        List<Message> list=DataClass.deSerializeMessages();
        if (list!=null){
            messageList=list;
        }
    }
}
