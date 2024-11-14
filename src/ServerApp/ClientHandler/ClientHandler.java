package ServerApp.ClientHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Queue;

import ServerApp.ChatBox.ChatBox;
import ServerApp.Message.Message;
import ServerApp.Server.Server;
import ServerApp.User.User;

public class ClientHandler implements Runnable {
	private final Socket clientSocket;
	private OutputStream outputStream;
	private ObjectOutputStream objectOutputStream;
	private InputStream inputStream;
	private ObjectInputStream objectInputStream;
	private User user;
	private Server server;
	private MessageHandler messageHandler;
	private Map<String, ChatBox> chatBoxList;
	private Queue<Message> messageQueue;
	
	
	
	//Constructor
	public ClientHandler(Socket socket) {
		this.clientSocket=socket;
	}

	@Override
	public void run() {
		
		
	}
	public Message recieveMessage() {
		return null;
	}
	public void processRequest(Message request) {
		
	}
	public void sendResponse(String response) {
		//Change Response to String for now
		//Will talk about that later
	}
	public void sendMessage(Message message, int chatBoxID) {
		
	}
	public void sendChatBoxLog(int chatBoxID, String chatBoxLog) {
		
	}
	public void acknowledgeDelivery(String messageID) {
		
	}
	public void closeConnection() {
		
	}
}