package rmi;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private UUID id_sender; 
	private UUID id_receiver;
	private String name_sender;
	private String name_receiver; 
	private String message;
	private Date date;
	
	private DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	
	public UUID getId_sender() {
		return id_sender;
	}
	public void setId_sender(UUID id_sender) {
		this.id_sender = id_sender;
	}
	public UUID getId_receiver() {
		return id_receiver;
	}
	public void setId_receiver(UUID id_receiver) {
		this.id_receiver = id_receiver;
	}
	public String getName_sender() {
		return name_sender;
	}
	public void setName_sender(String name_sender) {
		this.name_sender = name_sender;
	}
	public String getName_receiver() {
		return name_receiver;
	}
	public void setName_receiver(String name_receiver) {
		this.name_receiver = name_receiver;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Message(UUID id_sender, UUID id_receiver, String name_sender, String name_receiver, String message) {
		super();
		this.id_sender = id_sender;
		this.id_receiver = id_receiver;
		this.name_sender = name_sender;
		this.name_receiver = name_receiver;
		this.message = message;
		this.setDate(Calendar.getInstance().getTime());
	}
	
	@Override
	public String toString() {
		return "-------------------------------------------------\n"
				+ "From: " + name_sender 
				+ " (" + id_sender + ")\n"
				+ "To: " + name_receiver
				+ " (" + id_receiver + ")\n"
				+ "Date: " + df.format(date) + "\n"
				+ message;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
