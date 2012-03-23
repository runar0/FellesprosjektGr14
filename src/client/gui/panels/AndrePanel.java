package client.gui.panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import client.gui.CheckListManager;
import client.gui.VerticalLayout;
import client.model.FilteredUserListModel;
import client.model.UserModel;
import client.gui.usersearch.FilteredUserList;


/**
 * Panel for the "Andre Kalendere" tab
 * @author Susanne
 *
 */
public class AndrePanel extends JPanel{
	
	private final JList activeCalenders;
	private final JButton upButton, downButton, newAppointmentButton;
	final PersonLabel personLabel;
	final UserModel person;
	static JCheckBox checkBox;
	CheckListManager checkListManager; 
	FilteredUserList search;

	public AndrePanel(){
		super(new VerticalLayout(5,SwingConstants.LEFT));
		
		// Top content, the person label
		JPanel topPanel = new JPanel();
		personLabel = new PersonLabel();
		personLabel.setPreferredSize(new Dimension(310, 50));
		topPanel.add(personLabel);
		
		// Employees label
		JLabel ansatte = new JLabel();
		ansatte.setText("Ansatte");
		
		
		
		//search panel
		search = new FilteredUserList(new FilteredUserListModel());
		search.setPreferredSize(new Dimension(310,150));
		person = new UserModel();
		
		//button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.setPreferredSize(new Dimension(310,30));
		upButton = new JButton("Legg til");
		downButton = new JButton("Fjern");
		buttonPanel.add(Box.createHorizontalGlue());
		upButton.setAlignmentX(LEFT_ALIGNMENT);
		buttonPanel.add(upButton);
		downButton.setAlignmentX(RIGHT_ALIGNMENT);
		buttonPanel.add(downButton);
		buttonPanel.add(Box.createHorizontalGlue());
		this.add(buttonPanel);
		
		//active calenders center
		JLabel aktiveKalendere = new JLabel();
		aktiveKalendere.setText("Aktive kalendere");
		
		//bottomPanel
		final JPanel bottomPanel = new JPanel(new BorderLayout());
		 
		final DefaultListModel lol = new DefaultListModel();
		activeCalenders = new JList(lol);
		checkListManager = new CheckListManager(activeCalenders);
		bottomPanel.setPreferredSize(new Dimension(310,100));
		activeCalenders.setForeground(Color.black);
		bottomPanel.add(activeCalenders);
		JScrollPane scroll2 = new JScrollPane(activeCalenders);
		scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		bottomPanel.add(scroll2);
		
		// meetingBox panel
		JPanel addMeetingPanel = new JPanel(new BorderLayout());
		addMeetingPanel.setPreferredSize(new Dimension(310,100));
		newAppointmentButton = new JButton("Opprett en avtale/m�te");
		newAppointmentButton.setOpaque(true);
		addMeetingPanel.add(newAppointmentButton);
		
		upButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				for (UserModel model : search.getSelectedUsers())
					lol.addElement(model);
			}
		});
		
		downButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				lol.removeElement(activeCalenders.getSelectedValue());
 			}
		});

		
		//add elements

		this.add(topPanel);
		this.add(Box.createVerticalStrut(10));
		this.add(ansatte);
		this.add(search);
		this.add(Box.createVerticalStrut(10));
		this.add(buttonPanel);
		this.add(Box.createVerticalStrut(2));
		this.add(aktiveKalendere);
		this.add(bottomPanel);
		this.add(Box.createVerticalStrut(20));
		this.add(addMeetingPanel);

	}
	
	public static UserModel createUser(String userName, String email){
		
		UserModel person = new UserModel();
		return person;
	}
		
	public static UserModel createUser(String userName, String email, String fullName){
		
		UserModel person = new UserModel(userName, email, fullName);
		return person;
		
	}
	
	public JButton getNewAppointmentButton() {
		return newAppointmentButton;
	}
}
