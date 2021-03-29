package data;

public class AgentBuilder {
    private int AgentId;
    private String FirstName;
    private String MiddleInitial;
    private String LastName;
    private String BusPhone;
    private String Email;
    private String Position;
    private int AgencyId;

    public AgentBuilder() {}

    public Agent BuildAgent() {
        return new Agent(AgentId, FirstName, MiddleInitial, LastName, BusPhone, Email, Position, AgencyId);
    }

    public AgentBuilder AgentId (int agentId) {
        this.AgentId = agentId;
        return this;
    }

    public AgentBuilder FirstName(String firstName) {
        this.FirstName = firstName;
        return this;
    }

    public AgentBuilder MiddleInitial(String middleInitial) {
        this.MiddleInitial = middleInitial;
        return this;
    }

    public AgentBuilder LastName(String lastName) {
        this.LastName = lastName;
        return this;
    }

    public AgentBuilder BusPhone(String busPhone) {
        this.BusPhone = busPhone;
        return this;
    }

    public AgentBuilder Email(String email) {
        this.Email = email;
        return this;
    }

    public AgentBuilder Position(String position) {
        this.Position = position;
        return this;
    }

    public AgentBuilder AgencyId(int agencyId) {
        this.AgencyId = agencyId;
        return this;
    }
}
