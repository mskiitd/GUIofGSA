package mas.globalScheduling.gui;
import java.awt.Font;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.*;
import mas.jobproxy.job;

public class JobTile{

	private String jobID;
	private Date startDate;
	private Date dueDate;
	private int priority;
	private int JobNo;
	
	public JobTile(job givenJob){
		jobID=givenJob.getJobID();
		startDate=givenJob.getStartTimeByCust();
		dueDate=givenJob.getJobDuedatebyCust();
		JobNo=givenJob.getJobNo();
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getJobID() {
		return jobID;
	}

	public void setJobID(String jobID) {
		this.jobID = jobID;
	}

	public int getJObNo() {
		return JobNo;
	}
}
