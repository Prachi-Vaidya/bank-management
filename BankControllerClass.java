import java.sql.*;

public class BankControllerClass {

    public Connection connect_To_DB(String dbUrl, String dbUser, String dbPassword) {
        Connection conn = null;
        try {
            //establishing connection with db
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            if (conn != null) {
                System.out.println("Connection to Bank Database Established!!!");
            } else {
                System.out.println("Failed to connect to Bank's Database...");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void createTable(Connection con) {
        try {
            PreparedStatement prepStat;
            prepStat = con.prepareStatement("create table bank (acNumber SERIAL,cName varchar(50) NOT NULL, branch varchar(100) NOT NULL, acType varchar(100) NOT NULL, acbalance double precision NOT NULL, primary key(acNumber))");
            prepStat.executeUpdate();
            System.out.println("Table created successfully....");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createCustomer(Connection con,String cName,String branch, String acType, double acbalance){
        try{
            PreparedStatement prepStat;
            prepStat = con.prepareStatement("insert into bank (cName, branch, acType, acbalance) values(?, ?, ?, ?)");
            prepStat.setString(1,cName);
            prepStat.setString(2,branch);
            prepStat.setString(3,acType);
            prepStat.setDouble(4,acbalance);
            prepStat.executeUpdate();
            System.out.println("Customer information inserted into table bank successfully.");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void checkBalance(Connection con, int acNo){
        try{
            PreparedStatement prepStat;
            prepStat = con.prepareStatement("select * from bank  where acNumber = ?");
            prepStat.setInt(1,acNo);
            prepStat.executeQuery();
            ResultSet resultSet = prepStat.executeQuery();
            while (resultSet.next()){
                int acNumber = resultSet.getInt("acNumber");
                String cName = resultSet.getString("cName");
                double acbalance = resultSet.getDouble("acbalance");
                System.out.println("Account number : " +acNumber+ "\nCustomer Name : " +cName+ "\nBalance : " +acbalance);
            }

            System.out.println("Customer information retrieved successfully.");

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void WithdrawMoney(Connection con, int acNo, double acbalance){
        try{
            PreparedStatement prepStat;
            prepStat = con.prepareStatement("update bank set acbalance = acbalance - ? where acNumber = ?");
            prepStat.setDouble(1,acbalance);
            prepStat.setInt(2,acNo);
            prepStat.executeUpdate();
            System.out.println("Withdrawal Successful !!!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void accountType(Connection con, String acType){
        try {
            PreparedStatement prepStat;
            prepStat = con.prepareStatement("select * from bank where acType = ? ");
            prepStat.setString(1,acType);
            prepStat.executeQuery();
            ResultSet resultSet = prepStat.executeQuery();
            while (resultSet.next()){
                int accountNo = resultSet.getInt("acNumber");
                String customerName = resultSet.getString("cName");
                String branch = resultSet.getString("branch");
                double balance = resultSet.getDouble("acbalance");
                System.out.println("Account number : " +accountNo+ "\nCustomer Name : "+customerName + "\nBranch : " +branch + "\nBalance :" +balance);
                System.out.println("---------");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void depositMoney(Connection con, int acNumber, double acbalance){
        try{
            PreparedStatement prepStat;
            prepStat = con.prepareStatement("update bank set acbalance = acbalance + ? where acNumber = ?");
            prepStat.setDouble(1,acbalance);
            prepStat.setInt(2,acNumber);
            prepStat.executeUpdate();
            System.out.println("Amount deposited Successfully !!!");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void changeBranch(Connection con, String cName, String branch){
        try{
            PreparedStatement prepStat;
            prepStat = con.prepareStatement("update bank set branch = ? where cName = ?");
            prepStat.setString(1,branch);
            prepStat.setString(2,cName);
            prepStat.executeUpdate();
            System.out.println("Changes in the Branch column is made successfully!!!");
        }catch (SQLException e){
            e.printStackTrace();

        }
    }

    public void printAllCustomer(Connection con){
        try{
            PreparedStatement prepStat;
            prepStat = con.prepareStatement("select * from bank");
            ResultSet resultSet = prepStat.executeQuery();
            while (resultSet.next()){
                int accountNo = resultSet.getInt("acNumber");
                String customerName = resultSet.getString("cName");
                String branch = resultSet.getString("branch");
                String accountType = resultSet.getString("acType");
                double balance = resultSet.getDouble("acbalance");
                System.out.println("Account number : " +accountNo+ "\nCustomer Name : "+customerName + "\nBranch : " +branch + "" +
                        "\n Account Type : " +accountType+ "\nBalance :" +balance);
                System.out.println("---------");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void checkUser(Connection con, String cName, String acType) {
        try {
            PreparedStatement preparedStatement;
            String sql = "SELECT * FROM bank WHERE cName = ? AND acType = ?";
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            prepareStatement.setString(1, cName);
            prepareStatement.setString(2, acType);
            ResultSet rs = prepareStatement.executeQuery();

            // Process the result
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    System.out.println("User already exists with the same Account type.");
                } else {
                    System.out.println("User exists with a different account type.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void transfer(Connection con, int acNo, double acbalance, String cName){
        try {
            String sql = "UPDATE bank " +
                    " SET acbalance = " +
                    "    CASE" +
                    "        WHEN acNumber = ? THEN acbalance - ?" +
                    "        WHEN cName = ? THEN acbalance + ?" +
                    "    END " +
                    " WHERE acNumber = ? OR cName = ? ";
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            prepareStatement.setInt(1, acNo);
            prepareStatement.setDouble(2, acbalance);
            prepareStatement.setString(3, cName);
            prepareStatement.setDouble(4, acbalance);
            prepareStatement.setInt(5, acNo);
            prepareStatement.setString(6, cName);
            prepareStatement.executeUpdate();
            System.out.println("Transfer Successfull!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


