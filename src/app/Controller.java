package app;

import data.Agent;
import data.MySQL;
import data.SQLTableRow;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    String url = "jdbc:mysql://localhost:3306/travelexperts";
    String username = "root";
    String password = "";

    //Instantiates database object.
    MySQL database = new MySQL(url, username, password);

    @FXML
    GridPane agentInfo;
    @FXML
    ComboBox<Integer> comboAgentID;
    @FXML
    TextField txtAgentId;
    @FXML
    TextField txtFirstName;
    @FXML
    TextField txtMiddleInitial;
    @FXML
    TextField txtLastName;
    @FXML
    TextField txtBusPhone;
    @FXML
    TextField txtEmail;
    @FXML
    TextField txtPosition;
    @FXML
    TextField txtAgencyId;
    @FXML
    Button btnEdit;
    @FXML
    Button btnSave;

    /**
     * Populates the Combobox with AgentIDs.
     */
    public void setIDs() {
        try (Connection connection = database.OpenConnection()) {

            List<Integer> AgentIds = new ArrayList<>();
            List<SQLTableRow> AgentIdColumn = new ArrayList<>();

            SQLTableRow.CreateTable(database.Select("AgentId").Table("agents").Args("ORDER BY AgentId").ExecuteQueryWithArgs(connection), AgentIdColumn);

            for (SQLTableRow row : AgentIdColumn) {
                AgentIds.add((Integer) row.row.get("AgentId").getData());
            }
            comboAgentID.getItems().addAll(FXCollections.observableArrayList(AgentIds));

        } catch (Exception err) {
            System.out.println("Error populating Agent IDs.");
            System.out.println(err.getMessage());
        }
    }

    /**
     * Retrieves all information of an agent after an AgentId has been selected on the form.
     */
    public void fetchAgentInfo() {
        int currentID = comboAgentID.getValue();
        List<SQLTableRow> AgentInfo = new ArrayList<>();

        try (Connection connection = database.OpenConnection()) {

            SQLTableRow.CreateTable(database.Select("*").Table("agents").Args(String.format("WHERE AgentId=%d", currentID)).ExecuteQueryWithArgs(connection), AgentInfo);

            txtAgentId.setText(AgentInfo.get(0).row.get("AgentId").toString());
            txtFirstName.setText(AgentInfo.get(0).row.get("AgtFirstName").toString());
            txtMiddleInitial.setText(AgentInfo.get(0).row.get("AgtMiddleInitial").toString());
            txtLastName.setText(AgentInfo.get(0).row.get("AgtLastName").toString());
            txtBusPhone.setText(AgentInfo.get(0).row.get("AgtBusPhone").toString());
            txtEmail.setText(AgentInfo.get(0).row.get("AgtEmail").toString());
            txtPosition.setText(AgentInfo.get(0).row.get("AgtPosition").toString());
            txtAgencyId.setText(AgentInfo.get(0).row.get("AgencyId").toString());

        } catch (Exception err) {
            System.out.println("Error retrieving agent information.");
            System.out.println(err.getMessage());
        }
    }

    /**
     * Enables editing of an agent's information on the form. Disables edit button.
     */
    public void enableEdit() {
        btnEdit.setDisable(true);
        btnSave.setDisable(false);

        for (Node node : agentInfo.getChildren()) {
            if (node instanceof TextField && node != txtAgentId) {
                ((TextField) node).setEditable(true);
            }
        }
    }

    /**
     * Saves changes made to an agent's information. Disables save button and re-enables edit button.
     */
    public void saveEdit() {
        Agent currentAgent = new Agent(
                Integer.parseInt(txtAgentId.getText()),
                txtFirstName.getText(),
                txtMiddleInitial.getText(),
                txtLastName.getText(),
                txtBusPhone.getText(),
                txtEmail.getText(),
                txtPosition.getText(),
                Integer.parseInt(txtAgencyId.getText())
        );

        try (Connection connection = database.OpenConnection()) {
            database.Table("agents").ExecuteUpdate(connection, currentAgent);
        } catch (Exception err) {
            System.out.println("Error retrieving agent information.");
            System.out.println(err.getMessage());
        }

        btnSave.setDisable(true);
        btnEdit.setDisable(false);

        for (Node node : agentInfo.getChildren()) {
            if (node instanceof TextField) {
                ((TextField) node).setEditable(false);
            }
        }
    }
}
