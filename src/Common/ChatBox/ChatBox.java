package Common.ChatBox;

import Common.User.User;
import Common.Message.Message;
import java.util.HashSet;
import java.util.List;
import java.util.SortedSet;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Comparator;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class ChatBox implements Serializable {
    private static final long serialVersionUID = 1L;

    // Static atomic integer for generating unique chatBoxIDs
    private static final AtomicInteger chatBoxIdGenerator = new AtomicInteger(0);

    // Attributes
    private int chatBoxID;
    private final String name;
    private HashSet<User> participants;
    private SortedSet<Message> messages;
    private boolean isHidden;

    // Serializable Comparator
    private static final Comparator<Message> MESSAGE_TIMESTAMP_COMPARATOR = new SerializableComparator();

    private static class SerializableComparator implements Comparator<Message>, Serializable {
        private static final long serialVersionUID = 1L;

        @Override
        public int compare(Message m1, Message m2) {
            return m1.getTimestamp().compareTo(m2.getTimestamp());
        }
    }

    // Constructor
    // Initializes a ChatBox with a unique ID and optional name
    public ChatBox() {
        this.chatBoxID = chatBoxIdGenerator.incrementAndGet();
        this.participants = new HashSet<>();
        this.messages = new TreeSet<>(MESSAGE_TIMESTAMP_COMPARATOR);
        this.isHidden = false;
        this.name = "ChatBox " + this.chatBoxID;
    }

    public ChatBox(String name) {
        this.chatBoxID = chatBoxIdGenerator.incrementAndGet();
        this.participants = new HashSet<>();
        this.messages = new TreeSet<>(MESSAGE_TIMESTAMP_COMPARATOR);
        this.isHidden = false;
        this.name = name;
    }

    // Getters

    // Returns the unique ChatBox ID
    public int getChatBoxID() {
        return chatBoxID;
    }

    public String getName() {
        return name;
    }

    // Returns the set of participants in the ChatBox
    public HashSet<User> getParticipants() {
        return participants;
    }

    // Returns the set of messages in the ChatBox
    public SortedSet<Message> getMessages() {
        return messages;
    }

    // Returns the hidden status of the ChatBox
    public boolean isHidden() {
        return isHidden;
    }

    // Methods

    // Adds a message to the ChatBox
    // INPUT: message (Message)
    // OUTPUT: none
    public void addMessage(Message message) {
        messages.add(message);
    }

    // Adds a participant to the ChatBox
    // INPUT: user (User)
    // OUTPUT: true if user added successfully, false otherwise
    public boolean addParticipant(User user) {
        return participants.add(user); // Adds the user if not already present in the set
    }

    // Returns a list of all messages in the ChatBox
    // INPUT: none
    // OUTPUT: List of messages
    public List<Message> getMessagesList() {
        return new ArrayList<>(messages);
    }

    // Returns a list of all participants in the ChatBox
    // INPUT: none
    // OUTPUT: List of participants
    public List<User> getParticipantsList() {
        return new ArrayList<>(participants);
    }

    // Removes a participant from the ChatBox
    // INPUT: user (User)
    // OUTPUT: true if user removed successfully, false otherwise
    public boolean removeParticipant(User user) {
        return participants.remove(user); // Removes the user if present in the set
    }

    // Hides the ChatBox from users
    // INPUT: none
    // OUTPUT: none
    public void hideChatBox() {
        this.isHidden = true;
    }

    // Creates a new chatbox with specified participants
    // INPUT: List of participants (users)
    // OUTPUT: ChatBox object
    public static ChatBox createChatBox(List<User> participantsList) {
        ChatBox chatBox = new ChatBox();
        for (User participant : participantsList) {
            chatBox.addParticipant(participant);
        }
        return chatBox;
    }

    // Returns a copy of the chatbox with no messages (for data safety)
    // INPUT: none
    // OUTPUT: ChatBox object with participants but no messages
    public ChatBox getEmpty() {
        ChatBox empty = new ChatBox(this.name);
        empty.chatBoxID = this.chatBoxID; // Keep the same chatBoxID
        empty.participants = new HashSet<>(this.participants);
        empty.isHidden = this.isHidden;
        // Do not copy messages
        return empty;
    }

    // Implement equals and hashCode based on chatBoxID
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ChatBox)) return false;
        ChatBox other = (ChatBox) obj;
        return this.chatBoxID == other.chatBoxID;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(chatBoxID);
    }

    @Override
    public String toString() {
        return name + " (ID: " + chatBoxID + ")";
    }
}