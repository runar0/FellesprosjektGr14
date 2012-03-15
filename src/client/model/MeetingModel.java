package client.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

/**
 * A model for the meetings in the calendar
 * 
 * @author peterringset
 *
 */
public class MeetingModel extends AbstractModel{
	
	protected int id;
	protected Calendar timeFrom, timeTo;
	protected String name, description;
	protected MeetingRoomModel room;
	protected boolean active;
	protected UserModel owner;
	protected String ownerId;
	protected ArrayList<UserModel> antendees;
	
	/**
	 * Construct a new meeting model
	 * Note that timeTo should be after timeFrom
	 * 
	 * @param timeFrom
	 * @param timeTo
	 * @param owner
	 * @throws IllegalArgumentException if timeFrom is after timeTo
	 */
	public MeetingModel(Calendar timeFrom, Calendar timeTo, UserModel owner) {
		this();
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
		this.owner = owner;
		if(!timeFrom.before(timeTo)) {
			throw new IllegalArgumentException("MeetingModel: From-time is after to-time");
		}
	}
	
	public int getId() {
		return id;
	}
	
	public MeetingModel() {
		id = -1;
	}
	
	public Calendar getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(Calendar timeFrom) {
		this.timeFrom = timeFrom;
	}

	public Calendar getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(Calendar timeTo) {
		this.timeTo = timeTo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MeetingRoomModel getRoom() {
		return room;
	}

	public void setRoom(MeetingRoomModel room) {
		this.room = room;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public UserModel getOwner() {
		return owner;
	}
	
	public String toString() {
		return getName() + "(" + timeFrom + " - " + timeTo + ")";
	}

	/**
	 * Read model from stream
	 */
	@Override
	public void fromStream(BufferedReader reader) throws IOException {
		id = Integer.parseInt(reader.readLine());
		setName(reader.readLine());
		StringBuilder desc = new StringBuilder();
		String line;
		while(!(line = reader.readLine()).equals("\0"))
			desc.append(line+"\r\n");
		setDescription(desc.toString());
		
		DateFormat df = DateFormat.getDateTimeInstance();		
		try {
			Calendar timeFrom = Calendar.getInstance();
			timeFrom.setTime(df.parse(reader.readLine()));
			setTimeFrom(timeFrom);
			
			Calendar timeTo = Calendar.getInstance();
			timeTo.setTime(df.parse(reader.readLine()));			
			setTimeFrom(timeFrom);
			setTimeTo(Calendar.getInstance());
			
		} catch(ParseException e) {
			e.printStackTrace();
		}		
		ownerId = reader.readLine();
	}

	/**
	 * Dump the model to stream
	 * 
	 */
	@Override
	public void toStream(BufferedWriter writer) throws IOException {
		StringBuilder sb = new StringBuilder();
		DateFormat df = DateFormat.getDateTimeInstance();
		
		sb.append("MeetingModel\r\n");
		sb.append(getId() + "\r\n");
		sb.append(getName() + "\r\n");
		sb.append(getDescription().trim() + "\r\n\0\r\n");	
		sb.append(df.format(timeFrom.getTime()) + "\r\n");
		sb.append(df.format(timeTo.getTime()) + "\r\n");		
		sb.append(owner.getUsername()+"\r\n");
		
		writer.write(sb.toString());
	}
	
	public static final Comparator<MeetingModel> timeFromComparator = 
			new Comparator<MeetingModel>() {
				@Override
				public int compare(MeetingModel A, MeetingModel B) {					
					return A.getTimeFrom().compareTo(B.getTimeFrom());
				}
			};
			
	public static final Comparator<MeetingModel> timeToComparator = 
			new Comparator<MeetingModel>() {
				@Override
				public int compare(MeetingModel A, MeetingModel B) {					
					return A.getTimeTo().compareTo(B.getTimeTo());
				}
			};
			
			
}
