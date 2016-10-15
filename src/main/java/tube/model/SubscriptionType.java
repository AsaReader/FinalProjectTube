package tube.model;

public enum SubscriptionType {

	LOGGED_SUBSCRIBE("SUBSCRIBE"), UNLOGGED_SUBSCRIBE("SUBSCRIBE"), UNSUBSCRIBE("UNSUBSCRIBE");

	private String value;
	
	private SubscriptionType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
