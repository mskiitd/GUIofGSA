package mas.jobproxy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/** Represents a manufacturing shop floor job
 */

public class job implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final double lowRegretMultiplier = 1,
			MediumRegretMultiplier = 2,
			HighRegretMultiplier = 3;

	//Required parameters
	private int jobNo;
	private String jobID;
	private double CPN;
	private double Cost;
	private double penaltyRate;
	private Date startTime;
	private Date jobDuedateByCust;
	private ArrayList<jobOperation> operations;
	private Date generationTime;
	private Date completionTime;

	//Optional parameters initialized to default values
	public int currentOperationNumber = 0;
	public int acceptance = 0;
	public double slack;
	private double regret;
	private int position;
	private double BidByLSA ;

	private long waitingTime;
	private double profit;
	/*private AID WinnerLSA;
	private AID LSABidder;*/

	private double deliveryTime;
	private double deliveryStatus;

	private boolean IsComplete=false;

	public static class Builder {
		//Required parameters
		private int jobNo;
		private String jobID;
		private double CPN;
		private double Cost;
		private double Penalty;
		private Date custdDate; //due date mentioned by customer for job 
		//this will be same as due date of last operation for local due date calculation
		private Date custStartDate;//start date of job given by customer i.e. start time of 1st operation
		private Date genTime;
		// Optional parameters - initialized to default values
		private ArrayList<jobOperation> jOperations;

		public Builder(String jobID) {
			this.jobID = jobID;
			this.jOperations = new ArrayList<jobOperation>();
		}

		public Builder jobCost(double val)
		{ Cost = val; return this; }

		public Builder jobCPN(double val)
		{ CPN = val; return this; }

		public Builder jobPenalty(double val)
		{ Penalty = val; return this; }

		public Builder jobDueDateTime(Date val)
		{ custdDate = val; return this; }

		public Builder jobDueDateTime(long val)
		{ custdDate = new Date(val); return this; }

		public Builder jobStartTimeByCust(long val)
		{
			custStartDate=new Date(val);
			return this;
		}
		
		public Builder jobGenTime(Date val)
		{ genTime = val; return this; }

		public Builder jobGenTime(Long val)
		{ genTime = new Date(val); return this; }

		public Builder jobOperation(ArrayList<jobOperation> val)
		{ jOperations.addAll(val); return this; }

		public job build() {
			return new job(this);
		}
	}
	private job(Builder builder) {
		jobID = builder.jobID;
		jobNo = builder.jobNo;
		CPN = builder.CPN;
		Cost = builder.Cost;
		penaltyRate = builder.Penalty;
		jobDuedateByCust = builder.custdDate;
		generationTime = builder.genTime;
		this.operations = new ArrayList<jobOperation>();
		operations.addAll(builder.jOperations);
		startTime=builder.custStartDate;
	}

	public double getRegretMultiplier(){
		if(this.regret < 1.0)
			return lowRegretMultiplier;
		else if( this.regret < 1.1)
			return MediumRegretMultiplier;
		else
			return HighRegretMultiplier;
	}

	@Override
	public boolean equals(Object o) {
		
		if( !(o instanceof job)) {
			return false;
		}
		job j = (job)o;
		
		return (this.jobNo == j.jobNo) && (this.jobID.equals(j.jobID));
	}

	public double getPenaltyRate() {
		return penaltyRate;
	}

	public void setPenaltyRate(double penaltyRate) {
		this.penaltyRate = penaltyRate;
	}
	
	public void setCurrentOperationDueDate(long dueDate) {
		this.operations.get(currentOperationNumber).setDueDate(dueDate);
	}
	
	public long getCurrentOperationDueDate() {
		return this.operations.get(currentOperationNumber).getDueDate();
	}
	
	public void setCurrentOperationStartTime(long startTime) {
		this.operations.get(currentOperationNumber).setStartTime(startTime);
	}
	
	public long getCurrentOperationStartTime() {
		return this.operations.get(currentOperationNumber).getStartTime();
	}

	public ArrayList<jobDimension> getCurrentOperationDimensions() {
		return this.operations.get(this.currentOperationNumber).getjDims();
	}
	
	public void setCurrentOperationDimension(ArrayList<jobDimension> jDim) {
		this.operations.get(currentOperationNumber).setjDims(jDim);
	}

	public ArrayList<jobOperation> getOperations() {
		return operations;
	}

	public jobOperation getCurrentOperation() {
		if(this.currentOperationNumber < operations.size())
			return operations.get(this.currentOperationNumber);
		return null;
	}
	
	public int getCurrentOperationNumber() {
		return currentOperationNumber;
	}

	public void setCurrentOperationNumber(int currentOperationNumber) {
		this.currentOperationNumber = currentOperationNumber;
	}

	public long getCurrentOperationProcessTime() {
		return operations.get(this.currentOperationNumber).getProcessingTime();
	}
	
	public long getProcessTime(int index) {
		return this.operations.get(index).getProcessingTime();
	}
	
	public void setCurrentOperationProcessingTime(long processingTime) {
		operations.get(currentOperationNumber).setProcessingTime(processingTime);
	}
	
	public void IncrementOperationNumber(){
		this.currentOperationNumber++;
		if(this.currentOperationNumber>this.operations.size()-1){
			IsComplete=true;
		}
	}
	
	public boolean isComplete(){
		return this.IsComplete;
	}
	public long getTotalProcessingTime() {
		long total = 0;
		for(int i = 0 ; i < operations.size(); i++){
			total += operations.get(i).getProcessingTime();
		}
		return total;
	}
	
	public void setOperations(ArrayList<jobOperation> operations) {
		this.operations = operations;
	}

	public int getPosition() {
		return position;
	}

	public double getSlack() {
		return slack;
	}

	public void setSlack(double slack) {
		this.slack = slack;
	}

	public double getRegret() {
		return regret;
	}

	public void setRegret(double regret) {
		this.regret = regret;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getJobID(){
		return this.jobID;
	}

	public int getJobNo() {
		return jobNo;
	}

	public void setJobNo(int jobNo) {
		this.jobNo = jobNo;
	}

	public double getCPN() {
		return CPN;
	}

	public void setCPN(double cPN) {
		CPN = cPN;
	}

	public Date getStartTimeByCust() {
		return startTime;
	}
	
	public void setJobStartTimeByCust(Date startTime) {
		this.startTime = startTime;
	}

	public void setJobStartTimeByCust(long startTime) {
		this.startTime = new Date(startTime);
	}

	public Date getGenerationTime() {
		return generationTime;
	}

	public void setGenerationTime(Date generationTime) {
		this.generationTime = generationTime;
	}

	public void setGenerationTime(long generationTime) {
		this.generationTime = new Date(generationTime);
	}

	public Date getCompletionTime() {
		return completionTime;
	}
	
	public long getCompletionTime(int index) {
		return this.operations.get(index).getCompletionTime();
	}
	
	public long getStartTime(int index) {
		return this.operations.get(index).getStartTime();
	}

	public void setCompletionTime(Date completionTime) {
		this.completionTime = completionTime;
	}

	public void setCompletionTime(long completionTime) {
		this.completionTime = new Date(completionTime);
	}

	public int getAcceptance() {
		return acceptance;
	}

	public void setAcceptance(int acceptance) {
		this.acceptance = acceptance;
	}

	public long getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(long waitingTime) {
		this.waitingTime = waitingTime;
	}

	public void setJobID(String jobID) {
		this.jobID = jobID;
	}

	public Date getJobDuedatebyCust() {
		return jobDuedateByCust;
	}

	public void setJobDuedatebyCust(Date duedate) {
		this.jobDuedateByCust = duedate;
	}

	public void setJobDuedatebyCust(long duedate) {
		this.jobDuedateByCust.setTime(duedate);
	}

	public double getCost() {
		return Cost;
	}

	public void setCost(double cost) {
		Cost = cost;
	}
	public double getBidByLSA() {
		return BidByLSA;
	}

	public void setBidByLSA(double bidByLSA) {
		BidByLSA = bidByLSA;
	}

	/**
	 * 
	 * @return  LSA which won bid
	 */
	/*public AID getBidWinnerLSA() {
		return WinnerLSA;
	}

	public void setBidWinnerLSA(AID winner_LSA){
		this.WinnerLSA=winner_LSA;
	}*/

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	/**
	 * LSA which is sending bid proposal (bid winners hasn't been announced yet)
	 * @param LSA
	 * 
	 */
/*	public void setLSABidder(AID LSA) { 
		this.LSABidder = LSA; 

	}

	public AID getLSABidder(){
		return this.LSABidder;
	}*/

}

