package rmi;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * The Class Message.
 */
public class Message implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id_sender. */
	private UUID id_sender; 
	
	/** The id_receiver. */
	private UUID id_receiver;
	
	/** The name_sender. */
	private String name_sender;
	
	/** The name_receiver. */
	private String name_receiver; 
	
	/** The message. */
	private String message;
	
	/** The date. */
	private Date date;
	
	/** The df. */
	private DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	
	/**
	 * Gets the id_sender.
	 *
	 * @return the id_sender
	 */
	public UUID getId_sender() {
		return id_sender;
	}
	
	/**
	 * Sets the id_sender.
	 *
	 * @param id_sender the new id_sender
	 */
	public void setId_sender(UUID id_sender) {
		this.id_sender = id_sender;
	}
	
	/**
	 * Gets the id_receiver.
	 *
	 * @return the id_receiver
	 */
	public UUID getId_receiver() {
		return id_receiver;
	}
	
	/**
	 * Sets the id_receiver.
	 *
	 * @param id_receiver the new id_receiver
	 */
	public void setId_receiver(UUID id_receiver) {
		this.id_receiver = id_receiver;
	}
	
	/**
	 * Gets the name_sender.
	 *
	 * @return the name_sender
	 */
	public String getName_sender() {
		return name_sender;
	}
	
	/**
	 * Sets the name_sender.
	 *
	 * @param name_sender the new name_sender
	 */
	public void setName_sender(String name_sender) {
		this.name_sender = name_sender;
	}
	
	/**
	 * Gets the name_receiver.
	 *
	 * @return the name_receiver
	 */
	public String getName_receiver() {
		return name_receiver;
	}
	
	/**
	 * Sets the name_receiver.
	 *
	 * @param name_receiver the new name_receiver
	 */
	public void setName_receiver(String name_receiver) {
		this.name_receiver = name_receiver;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Instantiates a new message.
	 *
	 * @param id_sender the id_sender
	 * @param id_receiver the id_receiver
	 * @param name_sender the name_sender
	 * @param name_receiver the name_receiver
	 * @param message the message
	 */
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
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
}
