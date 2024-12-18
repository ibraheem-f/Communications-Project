package Common.Messages;

import Common.MessageInterface;
import Common.MessageType;
import Common.User.User;
import Common.ChatBox.ChatBox;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public record LoginResponse(User user, List<ChatBox> chatBoxList) implements MessageInterface,Serializable {
    private static final MessageType type = MessageType.LOGIN_RESPONSE;

    public LoginResponse(User user, List<ChatBox> chatBoxList) {
        this.user = user;
        this.chatBoxList = new ArrayList<>();
        
        if (chatBoxList != null)
        {
        	for (ChatBox chatBox : chatBoxList) {
                this.chatBoxList.add(chatBox.getEmpty());
            }
        }
        

    }

    public MessageType getType() {
        return type;
    }

}
